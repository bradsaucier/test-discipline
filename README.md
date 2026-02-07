# CS-320 Staging

Objective: Early verification, disciplined unit testing, and a clean portfolio-grade audit trail.

[![Build Test Coverage](https://github.com/bradsaucier/test-discipline/actions/workflows/maven-build.yml/badge.svg?branch=master)](https://github.com/bradsaucier/test-discipline/actions/workflows/maven-build.yml/badge.svg?branch=master) ![Coverage](.github/badges/jacoco.svg) ![Branches](.github/badges/branches.svg)

Note: Coverage and Branches badges update only after a successful run on master generates `target/site/jacoco/jacoco.csv` and commits `.github/badges`.

---

## Contents

1. [Mission and constraints](#1-mission-and-constraints)
2. [Repository layout](#2-repository-layout)
3. [Quick start - local verification](#3-quick-start---local-verification)
4. [CI protocol - GitHub Actions](#4-ci-protocol---github-actions)
5. [Command dashboard - module tracker](#5-command-dashboard---module-tracker)
6. [Module 3 - requirements trace (Contact)](#6-module-3---requirements-trace-contact)
7. [Module 4 - requirements trace (Task)](#7-module-4---requirements-trace-task)
8. [Portfolio notes (Week 8)](#8-portfolio-notes-week-8)

---

## 1. Mission and constraints

Mission

1. Verify requirements early (shift-left testing).
2. Enforce repeatable verification via CI (build, tests, coverage).
3. Preserve evidence for Project One, Project Two, and portfolio handoff.

Operating constraints

1. Privacy - keep the repo private during the term.
2. Access - add the instructor as a collaborator when required by the course.
3. Workflow - default branch (master) stays green. PR into develop, then merge to master.

---

## 2. Repository layout

| Area | Contents |
|---|---|
| `src/main/java` | Production code (Contact, Task). Appointment is planned. |
| `src/test/java` | JUnit tests for each service |
| `docs/requirements` | Requirement traces and test mapping |
| `docs/project-two-artifacts` | Project Two report drafts and final artifact |

---

## 3. Quick start - local verification

```bash
# Standard (fast)
mvn -B test

# Full verification with coverage gate (matches CI)
mvn -B -Pci verify

# Coverage report (local)
mvn -B test jacoco:report
# Open: target/site/jacoco/index.html
```

---

## 4. CI protocol - GitHub Actions

Triggers

1. workflow_dispatch (manual)
2. push and pull_request on master and develop

Outputs (workflow artifacts)

1. target/surefire-reports (test evidence)
2. target/site/jacoco (coverage evidence)

Badge logic

1. Badges generate only when `target/site/jacoco/jacoco.csv` exists.
2. Badge commits occur only on successful runs on master for non-pull_request events.
3. Uses `[skip ci]` to prevent commit loops.

---

## 5. Command dashboard - module tracker

| Module | Focus | Deliverables in repo | Status | Notes |
|---|---|---|---|---|
| 1 | Testing fundamentals | README updates | Completed | Baseline and terminology |
| 2 | CI and testing types | CI workflow baseline | Completed | Pipeline proves repeatability |
| 3 | Contact Service | Contact.java, ContactService.java, ContactTest.java, ContactServiceTest.java | Completed | Requirements-to-tests explicit |
| 4 | Task Service | Task.java, TaskService.java, TaskTest.java, TaskServiceTest.java | Completed | Fail-fast validation and symmetric tests |
| 5 | Appointment Service | Planned (not yet implemented) | Planned | Add after Task stabilization |
| 6 | Project One submission | All three services plus tests | Planned | Coverage hard deck applies to full bundle |
| 7 | Project Two report | docs/project-two-artifacts | Planned | Summary and reflections report |
| 8 | Portfolio submission | README reflection plus selected artifacts | Planned | Week 8 handoff |

---

## 6. Module 3 - requirements trace (Contact)

Trace document: docs/requirements/module-3-contact.md

Object constraints

| Field | Type | Rules |
|---|---|---|
| contactId | String | not null, <= 10 chars, not updatable |
| firstName | String | not null, <= 10 chars |
| lastName | String | not null, <= 10 chars |
| phone | String | not null, exactly 10 digits |
| address | String | not null, <= 30 chars |

Service capabilities

| Operation | Requirement |
|---|---|
| add(contact) | Enforce unique contactId |
| delete(contactId) | Remove by ID |
| update(contactId, firstName, lastName, phone, address) | Update only allowed fields; reject unknown ID per defined behavior |

Minimum test vectors (baseline)

1. Reject null values for each required field.
2. Reject boundary violations (length > 10 or > 30; phone not 10 digits; phone contains non-digits).
3. Enforce unique contactId on add.
4. Verify contactId immutability (cannot change after construction).
5. Verify update behavior when contactId does not exist (throw or return false, then test that decision).

---

## 7. Module 4 - requirements trace (Task)

Trace document: docs/requirements/module-4-task.md

Object constraints

| Field | Type | Rules |
|---|---|---|
| taskId | String | not null, <= 10 chars, not updatable |
| name | String | not null, <= 20 chars |
| description | String | not null, <= 50 chars |

Service capabilities

| Operation | Requirement |
|---|---|
| add(task) | Enforce unique taskId |
| delete(taskId) | Remove by ID |
| update(taskId, name, description) | Update only allowed fields; reject unknown ID per defined behavior |

Minimum test vectors (baseline)

1. Reject null values for each required field.
2. Reject boundary violations (taskId > 10, name > 20, description > 50).
3. Enforce unique taskId on add.
4. Verify taskId immutability (no setter; id stable across updates).
5. Verify update paths: name only, description only, both, and no-op (both null).
6. Verify delete and update behavior when taskId does not exist (throw per defined behavior).
7. Verify lookup helper behavior: getTask returns null when id not found.

---

## 8. Portfolio notes (Week 8)

Project One artifacts (GitHub)

1. Contact.java
2. ContactService.java
3. ContactTest.java
4. ContactServiceTest.java
5. Task.java
6. TaskService.java
7. TaskTest.java
8. TaskServiceTest.java

Project Two artifacts

1. Summary and reflections report in docs/project-two-artifacts

Final README update (Module 8)

1. Add course reflection responses.
2. Point to final artifacts.
