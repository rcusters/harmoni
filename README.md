# harmoni

Harmoni is a constraint-solving platform for education teams that need to coordinate schedules, resources, and other operational puzzles.

## Structure

This repository containers:

- `backend/` — multi-module Java backend using ports and adapters
- `frontend/` — Next.js frontend with internationalization support
- `nginx/` — reverse proxy configuration
- `smoketest/` — smoke-test module structure
- Gradle wrapper and root build files for the full workspace

## Development

### Backend

```bash
./gradlew :backend:spring-boot:bootRun
```

The API is served under `http://localhost:8080/harmoni`.

### Frontend

```bash
cd frontend
npm ci
npm run dev
```

The frontend is served on `http://localhost:3000` and expects the backend API at `NEXT_PUBLIC_API_URL`, defaulting to `http://localhost:8080/harmoni`.

## Validation

```bash
./gradlew :backend:core:domain:test :backend:spring-boot:test
cd frontend && npm run lint && npm run build
```
