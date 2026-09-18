# Koyeb deployment

The repository structure supports split frontend/backend deployment.

Suggested setup:

- Deploy the backend container from `backend/Dockerfile`
- Deploy the frontend container from `frontend/Dockerfile`
- Expose the backend under `/harmoni`
- Set `NEXT_PUBLIC_API_URL` to the public backend base URL
