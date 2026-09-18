import Link from 'next/link';
import { getLocale, getTranslations } from 'next-intl/server';

const cardStyle = {
  background: '#ffffff',
  borderRadius: '16px',
  padding: '1.5rem',
  boxShadow: '0 10px 30px rgba(15, 23, 42, 0.08)'
} as const;

export default async function HomePage() {
  const locale = await getLocale();
  const t = await getTranslations('home');

  return (
    <section style={{ maxWidth: 960, margin: '0 auto', padding: '4rem 1.5rem' }}>
      <div style={{ textAlign: 'center', marginBottom: '3rem' }}>
        <h1 style={{ fontSize: '3rem', marginBottom: '1rem' }}>{t('title')}</h1>
        <p style={{ fontSize: '1.25rem', lineHeight: 1.6, maxWidth: 720, margin: '0 auto' }}>{t('description')}</p>
      </div>
      <div style={{ display: 'grid', gap: '1rem', gridTemplateColumns: 'repeat(auto-fit, minmax(240px, 1fr))' }}>
        <article style={cardStyle}>
          <h2>{t('cards.workspaces.title')}</h2>
          <p>{t('cards.workspaces.description')}</p>
          <Link href={`/${locale}/workspaces`}>{t('cards.workspaces.link')}</Link>
        </article>
        <article style={cardStyle}>
          <h2>{t('cards.engine.title')}</h2>
          <p>{t('cards.engine.description')}</p>
        </article>
        <article style={cardStyle}>
          <h2>{t('cards.platform.title')}</h2>
          <p>{t('cards.platform.description')}</p>
        </article>
      </div>
    </section>
  );
}
