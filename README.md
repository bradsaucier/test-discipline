# CS-320 Test Discipline Repository

Objective: verification-first unit testing, fail-fast validation, and a clean audit trail through Module 5.

[![Build Test Coverage](https://github.com/bradsaucier/test-discipline/actions/workflows/maven-build.yml/badge.svg)](https://github.com/bradsaucier/test-discipline/actions/workflows/maven-build.yml)
![JaCoCo Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

## 1. Mission and constraints

- Treat requirements as contracts.
- Reject invalid input immediately with deterministic exceptions.
- Keep domain objects impossible to construct in an invalid state.
- Keep services in-memory and deterministic for fast unit feedback.
- Maintain traceability from rubric requirement to test name to implementation.

## 2. Completion status

Completed
- Module 3 - Contact, ContactService, and unit tests
- Module 4 - Task, TaskService, and unit tests
- Module 5 - Appointment, AppointmentService, and unit tests

Documentation and trace artifacts
- Requirements traces: docs/requirements/README.md
- CI protocol: docs/verification/ci-protocol.md
- Security posture: docs/verification/security-posture.md
- Module 5 journal: docs/journal/module-5-journal.md

## 3. Repository layout

- src/main/java - domain objects and services
- src/test/java - JUnit tests (requirements and boundary focused)
- docs/requirements - requirement to test trace tables
- docs/verification - CI and security posture notes
- .github/workflows - GitHub Actions CI

## 4. Quick start - local verification

Prereqs
- JDK 17
- Maven 3.9+

Commands
- Unit tests: mvn -B test
- CI-equivalent verification: mvn -B -Pci verify

## 5. CI protocol - GitHub Actions

The CI workflow runs Maven verify using the ci profile, generates JaCoCo reports, and enforces a coverage gate (instruction covered ratio >= 0.80).
See docs/verification/ci-protocol.md.

## 6. Requirements traces

- Module 3 - Contact: docs/requirements/module-3-contact.md
- Module 4 - Task: docs/requirements/module-4-task.md
- Module 5 - Appointment: docs/requirements/module-5-appointment.md

## 7. Notes

- Tests are named to mirror rubric constraints and boundary checks.
- Service helper getters exist only to support deterministic unit tests (retrieve by id, count).
- No external dependencies (database, network) are used in these milestones.
