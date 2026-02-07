// Unit tests for Appointment validation and immutability controls (JUnit 5).

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AppointmentTest {

    private static final long SAFE_FUTURE_MS = 86_400_000L;
    private static final long SAFE_PAST_MS = 86_400_000L;

    private static Date futureDate() {
        return new Date(System.currentTimeMillis() + SAFE_FUTURE_MS);
    }

    private static Date pastDate() {
        return new Date(System.currentTimeMillis() - SAFE_PAST_MS);
    }

    @Test
    @DisplayName("R1 - appointmentId must not be null")
    void appointmentIdNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment(null, futureDate(), "Routine appointment")
        );
    }

    @Test
    @DisplayName("R1 - appointmentId too long throws")
    void appointmentIdTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("12345678901", futureDate(), "Routine appointment")
        );
    }

    @Test
    @DisplayName("R1 - appointmentId length 10 is accepted")
    void appointmentIdLengthTenAccepted() {
        assertDoesNotThrow(() ->
            new Appointment("1234567890", futureDate(), "Routine appointment")
        );
    }

    @Test
    @DisplayName("R2 - appointmentDate must not be null")
    void appointmentDateNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("A1", null, "Routine appointment")
        );
    }

    @Test
    @DisplayName("R2 - appointmentDate in the past throws")
    void appointmentDatePastThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("A1", pastDate(), "Routine appointment")
        );
    }

    @Test
    @DisplayName("R2 - appointmentDate in the future is accepted")
    void appointmentDateFutureAccepted() {
        assertDoesNotThrow(() ->
            new Appointment("A1", futureDate(), "Routine appointment")
        );
    }

    @Test
    @DisplayName("R3 - description must not be null")
    void descriptionNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("A1", futureDate(), null)
        );
    }

    @Test
    @DisplayName("R3 - description too long throws")
    void descriptionTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Appointment("A1", futureDate(),
                "123456789012345678901234567890123456789012345678901")
        );
    }

    @Test
    @DisplayName("R3 - description length 50 is accepted")
    void descriptionLengthFiftyAccepted() {
        assertDoesNotThrow(() ->
            new Appointment("A1", futureDate(),
                "12345678901234567890123456789012345678901234567890")
        );
    }

    @Test
    @DisplayName("Getters - return expected values and protect Date state")
    void gettersReturnExpectedValuesAndProtectDate() {
        Date input = futureDate();
        Appointment a = new Appointment("A1", input, "Routine appointment");

        assertAll(
            () -> assertEquals("A1", a.getAppointmentId()),
            () -> assertEquals(input.getTime(), a.getAppointmentDate().getTime()),
            () -> assertEquals("Routine appointment", a.getDescription())
        );

        Date d1 = a.getAppointmentDate();
        Date d2 = a.getAppointmentDate();
        assertNotSame(d1, d2);
        assertEquals(d1.getTime(), d2.getTime());

        long mutated = System.currentTimeMillis() - SAFE_PAST_MS;
        input.setTime(mutated);
        assertNotEquals(input.getTime(), a.getAppointmentDate().getTime());
    }
}
