# Security Posture

Scope: This repository is an academic artifact. The security objective is to demonstrate baseline supply-chain hygiene and disciplined dependency handling.

Controls in place

1. Dependabot updates for Maven dependencies and GitHub Actions (.github/dependabot.yml)
2. Locked action versions using major tags in workflows (reproducible runner behavior)
3. CI defaults to least privilege (workflow permissions set to contents: read, job elevates to contents: write only for badge commits)

Optional hardening (enable when the repository is public, or when GitHub Advanced Security is available)

1. Code scanning (CodeQL) for Java
2. Dependency review for pull requests
3. OWASP Dependency Check in a scheduled workflow to avoid slowing the primary build
