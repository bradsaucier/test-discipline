// Component: AppointmentService
// Purpose: Add and delete appointments in memory.
// Strategy: Map keyed by appointmentId for deterministic lookups and enforced uniqueness.
// Operating principle: guard the collection, keep failures explicit and early.

import java.util.HashMap;
import java.util.Map;

public final class AppointmentService {

    private final Map<String, Appointment> appointments = new HashMap<>();

    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("appointment must not be null");
        }

        String id = appointment.getAppointmentId();

        if (appointments.containsKey(id)) {
            throw new IllegalArgumentException("appointmentId already exists: " + id);
        }

        appointments.put(id, appointment);
    }

    public void deleteAppointment(String appointmentId) {
        requireNonNullId(appointmentId);

        if (!appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("appointmentId not found: " + appointmentId);
        }

        appointments.remove(appointmentId);
    }

    // Test-support helper: retrieve by id.
    public Appointment getAppointment(String appointmentId) {
        requireNonNullId(appointmentId);
        return appointments.get(appointmentId);
    }

    // Test-support helper: current count.
    public int getAppointmentCount() {
        return appointments.size();
    }

    private static void requireNonNullId(String appointmentId) {
        if (appointmentId == null) {
            throw new IllegalArgumentException("appointmentId must not be null");
        }
    }
}
