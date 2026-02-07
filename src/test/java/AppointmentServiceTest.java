// Unit tests for AppointmentService add and delete behavior (JUnit Jupiter).

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppointmentServiceTest {

    private static final long SAFE_FUTURE_MS = 86_400_000L;

    private AppointmentService service;

    @BeforeEach
    void setUp() {
        service = new AppointmentService();
    }

    private static Date futureDate() {
        return new Date(System.currentTimeMillis() + SAFE_FUTURE_MS);
    }

    private static Appointment buildAppointment(String id) {
        return new Appointment(id, futureDate(), "Routine appointment");
    }

    @Test
    @DisplayName("S1 - addAppointment stores appointment by id")
    void addAppointmentStoresAppointment() {
        Appointment a = buildAppointment("A1");
        service.addAppointment(a);

        Appointment stored = service.getAppointment("A1");
        assertNotNull(stored);
        assertEquals("A1", stored.getAppointmentId());
        assertEquals("Routine appointment", stored.getDescription());
        assertNotNull(stored.getAppointmentDate());
        assertEquals(1, service.getAppointmentCount());
    }

    @Test
    @DisplayName("S1 - addAppointment rejects null appointment")
    void addNullAppointmentThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.addAppointment((Appointment) null)
        );
    }

    @Test
    @DisplayName("S1 - adding a duplicate id is rejected")
    void addDuplicateIdThrows() {
        service.addAppointment(buildAppointment("A1"));

        assertThrows(IllegalArgumentException.class, () ->
            service.addAppointment(buildAppointment("A1"))
        );
    }

    @Test
    @DisplayName("S2 - deleteAppointment removes appointment")
    void deleteAppointmentRemovesAppointment() {
        service.addAppointment(buildAppointment("A1"));
        assertEquals(1, service.getAppointmentCount());

        service.deleteAppointment("A1");
        assertEquals(0, service.getAppointmentCount());
        assertNull(service.getAppointment("A1"));
    }

    @Test
    @DisplayName("S2 - delete unknown id throws")
    void deleteUnknownIdThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.deleteAppointment("NOPE")
        );
    }

    @Test
    @DisplayName("S2 - delete null id throws")
    void deleteNullIdThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.deleteAppointment(null)
        );
    }
}
