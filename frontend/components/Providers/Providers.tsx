import { ReactNode } from 'react';
import ThemeRegistry from '@/components/ThemeRegistry';
import QueryProvider from '@/components/QueryProvider';

export default function Providers({ children }: { children: ReactNode }) {
  return (
    <ThemeRegistry>
      <QueryProvider>{children}</QueryProvider>
    </ThemeRegistry>
  );
}
