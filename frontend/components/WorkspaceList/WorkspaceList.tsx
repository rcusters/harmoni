'use client';

import { useEffect, useState } from 'react';
import { useTranslations } from 'next-intl';
import LoadingSpinner from '@/components/LoadingSpinner';
import ErrorAlert from '@/components/ErrorAlert';
import { getWorkspaces } from '@/lib/api/workspacesApi';
import type { Workspace } from '@/types/workspace';

export default function WorkspaceList() {
  const t = useTranslations('workspaces');
  const [workspaces, setWorkspaces] = useState<Workspace[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    void getWorkspaces()
      .then(setWorkspaces)
      .catch((err: Error) => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <LoadingSpinner />;
  if (error) return <ErrorAlert message={error} />;

  return (
    <div style={{ display: 'grid', gap: '1rem' }}>
      {workspaces.map((workspace) => (
        <article key={workspace.id} style={{ background: '#fff', borderRadius: '12px', padding: '1rem', boxShadow: '0 8px 24px rgba(15, 23, 42, 0.08)' }}>
          <h2 style={{ marginTop: 0 }}>{workspace.name}</h2>
          <p style={{ marginBottom: 0 }}>{workspace.description}</p>
        </article>
      ))}
      {workspaces.length === 0 && <p>{t('empty')}</p>}
    </div>
  );
}
