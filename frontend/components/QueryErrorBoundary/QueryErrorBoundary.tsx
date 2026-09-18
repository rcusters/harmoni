import { ReactNode } from 'react';
import ErrorBoundary from '@/components/ErrorBoundary';

export default function QueryErrorBoundary({ children }: { children: ReactNode }) {
  return <ErrorBoundary>{children}</ErrorBoundary>;
}
