# CI Protocol

Objective: Provide repeatable verification evidence for grading and portfolio review.

What CI runs

1. Maven verify using the ci profile (mvn -B -Pci verify)
2. Unit tests via Maven Surefire
3. JaCoCo report generation (HTML, XML, CSV)
4. JaCoCo coverage gate (instruction covered ratio >= 0.80)

What CI uploads as artifacts

1. target/surefire-reports
2. target/site/jacoco

Badge behavior

1. Badges generate only when target/site/jacoco/jacoco.csv exists.
2. Badges commit only on successful runs on main and never on pull_request events.
3. Badge commit message includes [skip ci] and the workflow ignores .github/badges to prevent loops.
