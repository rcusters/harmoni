'use client';

import Link from 'next/link';
import { useLocale, useTranslations } from 'next-intl';
import LanguageSelector from '@/components/LanguageSelector';

export default function AppHeader() {
  const t = useTranslations('menu');
  const locale = useLocale();

  return (
    <header style={{ background: '#111827', color: '#fff', padding: '1rem 1.5rem' }}>
      <div style={{ maxWidth: 1100, margin: '0 auto', display: 'flex', alignItems: 'center', justifyContent: 'space-between', gap: '1rem' }}>
        <div>
          <Link href={`/${locale}`} style={{ color: '#fff', textDecoration: 'none', fontWeight: 700, fontSize: '1.25rem' }}>
            harmoni
          </Link>
        </div>
        <nav style={{ display: 'flex', gap: '1rem', alignItems: 'center' }}>
          <Link href={`/${locale}/workspaces`} style={{ color: '#fff' }}>{t('workspaces')}</Link>
          <LanguageSelector />
        </nav>
      </div>
    </header>
  );
}
