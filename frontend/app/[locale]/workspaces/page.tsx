import { getTranslations } from 'next-intl/server';
import WorkspaceList from '@/components/WorkspaceList';

export default async function WorkspacesPage() {
  const t = await getTranslations('workspaces');

  return (
    <section style={{ maxWidth: 960, margin: '0 auto', padding: '3rem 1.5rem' }}>
      <header style={{ marginBottom: '1.5rem' }}>
        <h1>{t('title')}</h1>
        <p>{t('description')}</p>
      </header>
      <WorkspaceList />
    </section>
  );
}
