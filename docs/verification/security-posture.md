# Security Posture

Scope: this repository is an academic artifact. The goal is baseline supply-chain hygiene and disciplined dependency handling suitable for a public portfolio repo.

Controls in place
1. Dependabot updates for Maven dependencies and GitHub Actions (.github/dependabot.yml)
2. GitHub Actions workflow permissions set to least privilege, with temporary elevation only where required (badge commits)
3. Dependencies are pinned through Maven and reviewed via CI on every change

Optional hardening (enable when the repository is public, or when GitHub Advanced Security is available)
1. Code scanning (CodeQL) for Java
2. Dependency review for pull requests
3. OWASP Dependency-Check in a scheduled workflow to avoid slowing the primary build
