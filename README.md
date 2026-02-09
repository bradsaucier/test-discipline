# CS-320: Verification-First Test Discipline

Objective: Build in-memory services for Contact, Task, and Appointment records, then verify rubric compliance with JUnit 5 and a repeatable CI evidence trail.

[![Build Test Coverage](https://github.com/bradsaucier/test-discipline/actions/workflows/maven-build.yml/badge.svg)](https://github.com/bradsaucier/test-discipline/actions/workflows/maven-build.yml)
![JaCoCo Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)

## Status

- CI: GitHub Actions runs `mvn -B -Pci verify` on push.
- Coverage: JaCoCo report generated and enforced by the ci profile (>= 0.80 instruction covered ratio).
- Portfolio plan:
  - Project One (Module 8 submission): Contact.java, ContactService.java, ContactTest.java, ContactServiceTest.java
  - Project Two (next week): Summary and reflections report will be added under `docs/project-two/`

## Evidence

- Build workflow: `.github/workflows/maven-build.yml`
- Coverage badges:
  - `.github/badges/jacoco.svg`
  - `.github/badges/branches.svg`
- Reports published as workflow artifacts:
  - surefire-reports
  - jacoco-report

## Mission and constraints

- Requirements are treated as contracts.
- Invalid input is rejected immediately with deterministic exceptions.
- Domain objects are not allowed to exist in an invalid state.
- Services are in-memory only (no database, no network).
- Traceability is maintained from requirement to test to implementation.

## Architecture summary

- Components:
  - Contact, ContactService
  - Task, TaskService
  - Appointment, AppointmentService
- Design choices:
  - Services use an in-memory Map keyed by ID for fast lookup and enforced uniqueness.
  - IDs are immutable (final) and not updatable (no setters).
  - Appointment uses defensive copying of `java.util.Date` to prevent external mutation.

## Verification strategy

- Tests are derived directly from the rubric.
- Boundary value coverage is deliberate:
  - max-valid and over-max inputs for each constrained field
  - required field checks include null and empty string rejection
  - phone validation enforces exactly 10 digits (too-short, too-long, non-digit)
  - appointment date validation rejects past dates using time offsets to avoid flaky tests
- Test isolation:
  - Each service test starts with a clean instance via `@BeforeEach` to prevent state bleed.

## Module 8 journal reflection responses

1. How can I ensure that my code, program, or software is functional and secure?
   - I treat requirements as contracts and enforce them at the boundary. Domain objects fail fast on invalid inputs, preventing invalid state from entering the system. Services enforce uniqueness and reject invalid operations (null IDs, unknown IDs, duplicates). I verify behavior with JUnit 5 tests that cover both success paths and failure paths, and I use CI to run the full test suite on every push so regressions are detected immediately.

2. How do I interpret user needs and incorporate them into a program?
   - I translate user needs into explicit constraints and behaviors. In this course, the rubric is the specification, so I map each requirement to one or more tests and implement only what the contract demands. This keeps scope controlled and traceability clean: a grader (or employer) can follow the chain from requirement to test name to the enforcing code.

3. How do I approach designing software?
   - I start with the data model and invariants, then build a service layer that operates on those models, then build tests that prove the contract. For these projects, the model layer rejects invalid state, and the service layer focuses on lifecycle operations (add, delete, update) using a Map keyed by ID for deterministic lookup. I keep the design simple, readable, and testable, with no external dependencies that would make verification non-deterministic.

## Requirements traces

- Contact requirements: `docs/requirements/module-3-contact.md`
- Task requirements: `docs/requirements/module-4-task.md`
- Appointment requirements: `docs/requirements/module-5-appointment.md`

## Notes

- This repository contains Contact, Task, and Appointment components to document end-to-end progression across Modules 3-5.
- The official Project One portfolio artifact set for Module 8 is limited to the Contact files listed in the Status section.
- In-memory storage is intentionally volatile for this course scope. In production, this service layer would sit in front of a persistence layer.
