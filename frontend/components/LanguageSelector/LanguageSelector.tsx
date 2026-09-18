'use client';

import { ChangeEvent } from 'react';
import { usePathname, useRouter } from 'next/navigation';
import { useLocale, useTranslations } from 'next-intl';

export default function LanguageSelector() {
  const router = useRouter();
  const pathname = usePathname();
  const locale = useLocale();
  const t = useTranslations('language');

  const onChange = (event: ChangeEvent<HTMLSelectElement>) => {
    const nextLocale = event.target.value;
    const segments = pathname.split('/');
    if (segments.length > 1) {
      segments[1] = nextLocale;
      router.push(segments.join('/'));
      return;
    }
    router.push(`/${nextLocale}`);
  };

  return (
    <label style={{ color: '#fff' }}>
      <span style={{ marginRight: '0.5rem' }}>{t('select')}</span>
      <select value={locale} onChange={onChange} style={{ padding: '0.25rem 0.5rem' }}>
        <option value="en">{t('en')}</option>
        <option value="nl">{t('nl')}</option>
      </select>
    </label>
  );
}
