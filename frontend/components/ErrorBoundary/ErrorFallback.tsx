'use client';

export default function ErrorFallback({ message }: { message: string }) {
  return <div style={{ padding: '2rem' }}>{message}</div>;
}
