# Contributing

Scope: This repository is an academic portfolio artifact. Changes must preserve grading integrity and remain professional.

Rules

1. Do not modify files submitted for grading unless explicitly directed by the course rubric.
2. Keep all content professional. Do not commit notes to self or tool-generated artifacts.
3. Maintain deterministic behavior: fail fast on invalid input and keep contracts explicit.
4. Keep formatting plain and compatible with standard markdown renderers.

Local verification

1. mvn -B test
2. mvn -B -Pci verify

Pull request expectations

1. Tests pass locally and in CI.
2. Documentation updates accompany behavior changes.
3. Requirements traces remain aligned with test names.
