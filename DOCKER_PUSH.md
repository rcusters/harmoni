# Docker push

Use the shared Gradle task to publish both container images:

```bash
./gradlew dockerPush
```

This pushes `harmoni-backend` and `harmoni-frontend` to the configured GitHub Container Registry namespace.
