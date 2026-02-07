# Module 5 - Appointment Service Requirements Trace

## Requirements - Appointment

| ID | Field | Constraints |
|---|---|---|
| R1 | appointmentId | not null, <= 10 chars, not updatable |
| R2 | appointmentDate | not null, must not be in the past (Date.before(new Date()) is rejected) |
| R3 | description | not null, <= 50 chars |

Notes
- Date is stored defensively (copy on write and copy on read) to prevent external mutation.

## Requirements - AppointmentService

| ID | Capability | Requirement |
|---|---|---|
| S1 | add | add appointments with unique appointmentId |
| S2 | delete | delete appointments by appointmentId |

Test-support helpers (not rubric requirements)
- H1: getAppointment(appointmentId) returns stored Appointment or null if not present
- H2: getAppointmentCount() returns current in-memory count

## Trace to tests

| Requirement | Test coverage |
|---|---|
| R1 | AppointmentTest.appointmentIdNullThrows, AppointmentTest.appointmentIdTooLongThrows, AppointmentTest.appointmentIdLengthTenAccepted |
| R2 | AppointmentTest.appointmentDateNullThrows, AppointmentTest.appointmentDatePastThrows, AppointmentTest.appointmentDateFutureAccepted |
| R3 | AppointmentTest.descriptionNullThrows, AppointmentTest.descriptionTooLongThrows, AppointmentTest.descriptionLengthFiftyAccepted |
| Note | AppointmentTest.gettersReturnExpectedValuesAndProtectDate |
| S1 | AppointmentServiceTest.addAppointmentStoresAppointment, AppointmentServiceTest.addNullAppointmentThrows, AppointmentServiceTest.addDuplicateIdThrows |
| S2 | AppointmentServiceTest.deleteAppointmentRemovesAppointment, AppointmentServiceTest.deleteUnknownIdThrows, AppointmentServiceTest.deleteNullIdThrows |
