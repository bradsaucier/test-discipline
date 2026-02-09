# CS-320: Verification-First Test Discipline

```yaml
STATUS   : ACTIVE
AUTHOR   : Bradley Saucier, SMSgt, USAF (Ret.)
COURSE   : SNHU CS-320 - Software Testing, Automation, and Quality Assurance
LICENSE  : MIT
```

> [!IMPORTANT]
> **BOTTOM LINE UP FRONT**
> Build in-memory services for Contact, Task, and Appointment records, then verify rubric compliance with JUnit 5 and a repeatable CI evidence trail.

[![Build](https://github.com/bradsaucier/test-discipline/actions/workflows/maven-build.yml/badge.svg)](https://github.com/bradsaucier/test-discipline/actions/workflows/maven-build.yml)
![JaCoCo Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

| Java | Test Framework | Coverage Tool | Coverage Gate | Test Runner |
|------|----------------|---------------|---------------|-------------|
| 17 | JUnit Jupiter 6.0.2 | JaCoCo 0.8.14 | >= 0.80 instruction ratio | Surefire 3.5.4 (failIfNoTests) |

---

### Quick verification

```
mvn -B test                # unit tests only
mvn -B -Pci verify         # tests + coverage gate (mirrors CI)
```

---

## Table of contents

- [1 Mission profile](#1-mission-profile)
- [2 Critical constraints](#2-critical-constraints)
- [3 Execution](#3-execution)
- [4 Administration and logistics](#4-administration-and-logistics)
- [5 Command and signal](#5-command-and-signal)
- [6 Academic integrity](#6-academic-integrity)
- [7 Module 8 journal reflections](#7-module-8-journal-reflections)
- [8 Notes](#8-notes)

---

## 1 Mission profile

This repository demonstrates verification-first discipline for SNHU CS-320 by enforcing rubric-driven requirements with JUnit tests and a CI coverage gate.

Mechanism of action:

| Step | Action |
|------|--------|
| 1 | Define domain invariants (Contact, Task, Appointment); reject invalid state at construction. |
| 2 | Implement in-memory services that enforce ID uniqueness and deterministic behavior. |
| 3 | Prove contract compliance with JUnit tests covering success and failure paths. |
| 4 | Enforce regression resistance in CI via `mvn -B -Pci verify` and JaCoCo coverage checks. |

---

## 2 Critical constraints

GO / NO-GO criteria:

| # | Constraint |
|---|------------|
| 1 | Java runtime must be 17. |
| 2 | Local execution requires Maven. |
| 3 | CI compliance requires the `ci` Maven profile (coverage gate at verify). |
| 4 | Storage is in-memory only (volatile by design). No database, no network. |

---

## 3 Execution

### 3.1 Mission dashboard

| Item | Detail |
|------|--------|
| Workflow | `Test Discipline` - job: `mission-assurance` |
| Triggers | push: `main`, `develop` - pull_request: `master`, `develop` - `workflow_dispatch` |
| Paths ignored | `**/*.md`, `docs/**`, `.github/badges/**` |
| CI command | `mvn -B -Pci verify` |
| CI artifacts | `surefire-reports`, `jacoco-report` |
| Badge commit | `refs/heads/master` only, on success |
| Coverage gate | `ci` profile - `jacoco:check` at verify - >= 0.80 instruction covered ratio |

Project One (Module 8 portfolio artifact):

| Role | File |
|------|------|
| Domain | `src/main/java/Contact.java` |
| Service | `src/main/java/ContactService.java` |
| Tests | `src/test/java/ContactTest.java`, `src/test/java/ContactServiceTest.java` |

Project Two: Planned (`docs/project-two/`) - not yet present.

### 3.2 Verification outputs

After a successful `mvn -B -Pci verify`:

| Artifact | Location |
|----------|----------|
| Surefire test reports | `target/surefire-reports/` |
| JaCoCo HTML report | `target/site/jacoco/index.html` |
| JaCoCo CSV (badge source) | `target/site/jacoco/jacoco.csv` |

---

## 4 Administration and logistics

### 4.1 Repository layout

```text
.editorconfig
.gitattributes
.github/
  badges/
    .gitkeep
    branches.svg
    coverage-summary.json
    jacoco.svg
  dependabot.yml
  pull_request_template.md
  workflows/
    maven-build.yml
CONTRIBUTING.md
LICENSE
README.md
docs/
  journal/
    module-5-journal.md
  requirements/
    README.md
    module-3-contact.md
    module-4-task.md
    module-5-appointment.md
  verification/
    ci-protocol.md
    security-posture.md
pom.xml
src/
  main/java/
    Appointment.java
    AppointmentService.java
    Contact.java
    ContactService.java
    Task.java
    TaskService.java
  test/java/
    AppointmentServiceTest.java
    AppointmentTest.java
    ContactServiceTest.java
    ContactTest.java
    TaskServiceTest.java
    TaskTest.java
```

### 4.2 Architecture

| Domain object | Service | Storage |
|---------------|---------|---------|
| Contact | ContactService | In-memory Map keyed by contactId |
| Task | TaskService | In-memory Map keyed by taskId |
| Appointment | AppointmentService | In-memory Map keyed by appointmentId |

Design choices:

| # | Decision |
|---|----------|
| 1 | Services use an in-memory Map keyed by ID for deterministic lookup and enforced uniqueness. |
| 2 | IDs are immutable (`final`) with no setter. |
| 3 | Appointment uses defensive copying of `java.util.Date` to prevent external mutation. |

### 4.3 Verification strategy

Rubric-derived test intent:

| # | Intent |
|---|--------|
| 1 | Boundary value coverage for constrained fields (max-valid, over-max). |
| 2 | Required field enforcement (null, empty). |
| 3 | Phone validation enforces exactly 10 digits (too-short, too-long, non-digit). |
| 4 | Appointment date validation rejects past dates using time offsets to reduce flake risk. |
| 5 | Service test isolation via `@BeforeEach` to prevent state bleed. |

### 4.4 Traceability matrix

| Module | Requirement | Domain | Service | Tests |
|--------|-------------|--------|---------|-------|
| 3 - Contact | `docs/requirements/module-3-contact.md` | `Contact.java` | `ContactService.java` | `ContactTest.java`, `ContactServiceTest.java` |
| 4 - Task | `docs/requirements/module-4-task.md` | `Task.java` | `TaskService.java` | `TaskTest.java`, `TaskServiceTest.java` |
| 5 - Appointment | `docs/requirements/module-5-appointment.md` | `Appointment.java` | `AppointmentService.java` | `AppointmentTest.java`, `AppointmentServiceTest.java` |

All source files reside under `src/main/java/` and `src/test/java/`.

---

## 5 Command and signal

### 5.1 Evidence locker

| Evidence | Location |
|----------|----------|
| Build workflow | `.github/workflows/maven-build.yml` |
| JaCoCo instruction badge | `.github/badges/jacoco.svg` |
| Branch coverage badge | `.github/badges/branches.svg` |
| Badge source data | `.github/badges/coverage-summary.json` |
| Requirements index | `docs/requirements/README.md` |
| CI protocol | `docs/verification/ci-protocol.md` |
| Security posture | `docs/verification/security-posture.md` |
| Journal entry | `docs/journal/module-5-journal.md` |
| Test reports (CI artifact) | workflow artifact: `surefire-reports` |
| Coverage report (CI artifact) | workflow artifact: `jacoco-report` |

### 5.2 Restrictions and threats

> [!WARNING]
> **FAILURE MODES**
> 1. Running without Java 17 may fail compilation or tests.
> 2. Skipping the ci profile bypasses the coverage gate used in CI.
> 3. In-memory storage is volatile. Restart equals data loss by design.

### 5.3 Contingencies and rollback

**Condition:** regression introduced (tests fail or coverage gate fails).

| Step | Action |
|------|--------|
| 1 | Re-run local verification: `mvn -B -Pci verify` |
| 2 | Identify failing tests in `target/surefire-reports/` |
| 3 | Review coverage deltas in `target/site/jacoco/index.html` |
| 4 | Roll back the last change set (revert commit or reset branch), then re-verify. |

---

## 6 Academic integrity

This repository contains coursework artifacts produced for SNHU CS-320 and
repackaged for portfolio review. All work is my own.

If any portion is reused, cite the repository and distinguish original
content from derived material.

---

## 7 Module 8 journal reflections

<details>
<summary>1. How can I ensure that my code, program, or software is functional and secure?</summary>

I treat requirements as contracts and enforce them at the boundary. Domain objects fail fast on invalid inputs, preventing invalid state from entering the system. Services enforce uniqueness and reject invalid operations (null IDs, unknown IDs, duplicates). I verify behavior with JUnit 5 tests that cover both success paths and failure paths, and I use CI to run the full test suite on every qualifying event so regressions are detected immediately.

</details>

<details>
<summary>2. How do I interpret user needs and incorporate them into a program?</summary>

I translate user needs into explicit constraints and behaviors. In this course, the rubric is the specification, so I map each requirement to one or more tests and implement only what the contract demands. This keeps scope controlled and traceability clean: a grader or reviewer can follow the chain from requirement to test name to the enforcing code.

</details>

<details>
<summary>3. How do I approach designing software?</summary>

I start with the data model and invariants, then build a service layer that operates on those models, then build tests that prove the contract. For these projects, the model layer rejects invalid state, and the service layer focuses on lifecycle operations (add, delete, update) using a Map keyed by ID for deterministic lookup. I keep the design simple, readable, and testable, with no external dependencies that would make verification non-deterministic.

</details>

---

## 8 Notes

1. This repository contains Contact, Task, and Appointment components to document end-to-end progression across Modules 3-5.
2. The official Project One portfolio artifact set for Module 8 is limited to the Contact files listed in the Mission dashboard.
3. In-memory storage is intentionally volatile for this course scope. In production, this service layer would sit in front of a persistence layer.
