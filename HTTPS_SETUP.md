# HTTPS setup

This repository keeps the same deployment structure as the source project, including NGINX and Certbot support.

Before using the production compose file:

1. Replace placeholder domains such as `harmoni.example.com` with your real domain.
2. Mount valid TLS certificates into `certbot/conf`.
3. Update the frontend API base URL to the final public backend URL.
4. Verify that `nginx/nginx-ssl.conf` matches your DNS and certificate paths.
