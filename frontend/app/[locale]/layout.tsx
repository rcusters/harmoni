import { NextIntlClientProvider } from 'next-intl';
import { getMessages } from 'next-intl/server';
import { ReactNode } from 'react';
import Providers from '@/components/Providers';
import AppHeader from '@/components/AppHeader';

export default async function LocaleLayout({
  children,
  params
}: {
  children: ReactNode;
  params: Promise<{ locale: string }>;
}) {
  const { locale } = await params;
  const messages = await getMessages();

  return (
    <div lang={locale} style={{ margin: 0, fontFamily: 'Arial, sans-serif', background: '#f6f8fb', color: '#1f2937', minHeight: '100vh' }}>
      <NextIntlClientProvider messages={messages}>
        <Providers>
          <AppHeader />
          <main>{children}</main>
        </Providers>
      </NextIntlClientProvider>
    </div>
  );
}
