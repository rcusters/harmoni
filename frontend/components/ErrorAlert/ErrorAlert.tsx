export default function ErrorAlert({ message }: { message: string }) {
  return (
    <div style={{ border: '1px solid #ef4444', background: '#fef2f2', color: '#991b1b', padding: '1rem', borderRadius: '12px' }}>
      {message}
    </div>
  );
}
