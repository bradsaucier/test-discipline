// Unit tests for Contact validation and update controls (JUnit 5).

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactTest {

    private static Contact buildValidContact() {
        return new Contact("ID12345678", "John", "Smith", "1234567890", "123 Main St");
    }

    @Test
    @DisplayName("R1 - contactId must not be null")
    void contactIdNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "John", "Smith", "1234567890", "123 Main St")
        );
    }

    @Test
    @DisplayName("R1 - contactId must not be empty")
    void contactIdEmptyThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("", "John", "Smith", "1234567890", "123 Main St")
        );
    }

    @Test
    @DisplayName("R1 - contactId length must be <= 10")
    void contactIdTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("12345678901", "John", "Smith", "1234567890", "123 Main St")
        );
    }

    @Test
    @DisplayName("R2 - firstName must not be null")
    void firstNameNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", null, "Smith", "1234567890", "123 Main St")
        );
    }

    @Test
    @DisplayName("R2 - firstName must not be empty")
    void firstNameEmptyThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "", "Smith", "1234567890", "123 Main St")
        );
    }

    @Test
    @DisplayName("R2 - firstName length must be <= 10")
    void firstNameTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "ABCDEFGHIJK", "Smith", "1234567890", "123 Main St")
        );
    }

    @Test
    @DisplayName("R3 - lastName must not be null")
    void lastNameNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", null, "1234567890", "123 Main St")
        );
    }

    @Test
    @DisplayName("R3 - lastName length must be <= 10")
    void lastNameTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "ABCDEFGHIJK", "1234567890", "123 Main St")
        );
    }

    @Test
    @DisplayName("R4 - phone must not be null")
    void phoneNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "Smith", null, "123 Main St")
        );
    }

    @Test
    @DisplayName("R4 - phone must be exactly 10 digits (too short)")
    void phoneTooShortThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "Smith", "123456789", "123 Main St")
        );
    }

    @Test
    @DisplayName("R4 - phone must be exactly 10 digits (too long)")
    void phoneTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "Smith", "12345678901", "123 Main St")
        );
    }

    @Test
    @DisplayName("R4 - phone must be exactly 10 digits (non-digit content)")
    void phoneNonDigitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "Smith", "12345ABCDE", "123 Main St")
        );
    }

    @Test
    @DisplayName("R5 - address must not be null")
    void addressNullThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "Smith", "1234567890", null)
        );
    }

    @Test
    @DisplayName("R5 - address length must be <= 30")
    void addressTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "Smith", "1234567890", "1234567890123456789012345678901")
        );
    }

    @Test
    @DisplayName("S1 - setters update allowed fields and id remains stable")
    void settersUpdateFieldsAndIdStaysStable() {
        Contact c = buildValidContact();
        String originalId = c.getContactId();

        c.setFirstName("Jane");
        c.setLastName("Doe");
        c.setPhone("0987654321");
        c.setAddress("456 Oak Ave");

        assertAll(
            () -> assertEquals(originalId, c.getContactId()),
            () -> assertEquals("Jane", c.getFirstName()),
            () -> assertEquals("Doe", c.getLastName()),
            () -> assertEquals("0987654321", c.getPhone()),
            () -> assertEquals("456 Oak Ave", c.getAddress())
        );
    }

    @Test
    @DisplayName("S1 - setters reject invalid updates")
    void settersRejectInvalidUpdates() {
        Contact c = buildValidContact();

        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null)),
            () -> assertThrows(IllegalArgumentException.class, () -> c.setLastName(null)),
            () -> assertThrows(IllegalArgumentException.class, () -> c.setPhone(null)),
            () -> assertThrows(IllegalArgumentException.class, () -> c.setAddress(null))
        );
    }
}
