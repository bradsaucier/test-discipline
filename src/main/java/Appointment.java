// Component: Appointment
// Purpose: Enforce field integrity for an in-memory appointment record.
// Operating principle: fail fast at the boundary so invalid data does not propagate.

import java.util.Date;

public final class Appointment {

    // Single source of truth for field constraints.
    private static final int APPOINTMENT_ID_MAX_LENGTH = 10;
    private static final int DESCRIPTION_MAX_LENGTH = 50;

    // Immutable after construction; used as the service lookup key.
    private final String appointmentId;

    // Immutable after construction for this milestone (add and delete only).
    private final Date appointmentDate;
    private final String description;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        validateString(appointmentId, "appointmentId", APPOINTMENT_ID_MAX_LENGTH);
        validateDate(appointmentDate);
        validateString(description, "description", DESCRIPTION_MAX_LENGTH);

        this.appointmentId = appointmentId;
        this.appointmentDate = new Date(appointmentDate.getTime());
        this.description = description;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return new Date(appointmentDate.getTime());
    }

    public String getDescription() {
        return description;
    }

    private static void validateString(String value, String fieldName, int maxLength) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
        }

        if (value.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " cannot exceed " + maxLength + " characters");
        }
    }

    // Requirement enforcement:
    // - Use java.util.Date
    // - Use before(new Date()) to detect dates in the past
    private static void validateDate(Date appointmentDate) {
        if (appointmentDate == null) {
            throw new IllegalArgumentException("appointmentDate must not be null");
        }

        if (appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("appointmentDate cannot be in the past");
        }
    }
}
