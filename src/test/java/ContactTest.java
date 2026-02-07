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
    @DisplayName("R1 - contactId length must be <= 10")
    void contactIdTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("12345678901", "John", "Smith", "1234567890", "123 Main St")
        );
    }

    @Test
    @DisplayName("R1 - contactId length 10 is accepted")
    void contactIdLengthTenAccepted() {
        assertDoesNotThrow(() ->
            new Contact("1234567890", "John", "Smith", "1234567890", "123 Main St")
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
    @DisplayName("R2 - firstName length must be <= 10")
    void firstNameTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "ABCDEFGHIJK", "Smith", "1234567890", "123 Main St")
        );
    }

    @Test
    @DisplayName("R2 - firstName length 10 is accepted")
    void firstNameLengthTenAccepted() {
        assertDoesNotThrow(() ->
            new Contact("ID1", "ABCDEFGHIJ", "Smith", "1234567890", "123 Main St")
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
    @DisplayName("R3 - lastName length 10 is accepted")
    void lastNameLengthTenAccepted() {
        assertDoesNotThrow(() ->
            new Contact("ID1", "John", "ABCDEFGHIJ", "1234567890", "123 Main St")
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
    @DisplayName("R4 - phone must be exactly 10 digits (too short rejected)")
    void phoneTooShortThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "Smith", "123456789", "123 Main St")
        );
    }

    @Test
    @DisplayName("R4 - phone must be exactly 10 digits (too long rejected)")
    void phoneTooLongThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "Smith", "12345678901", "123 Main St")
        );
    }

    @Test
    @DisplayName("R4 - phone must contain digits only")
    void phoneNonDigitThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "Smith", "12345abcde", "123 Main St")
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
        String address31 = "1234567890123456789012345678901"; // 31 chars
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID1", "John", "Smith", "1234567890", address31)
        );
    }

    @Test
    @DisplayName("R5 - address length 30 is accepted")
    void addressLengthThirtyAccepted() {
        String address30 = "123456789012345678901234567890"; // 30 chars
        assertDoesNotThrow(() ->
            new Contact("ID1", "John", "Smith", "1234567890", address30)
        );
    }

    @Test
    @DisplayName("Setters accept valid updates and contactId stays stable")
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
    @DisplayName("Setters reject invalid updates")
    void settersRejectInvalidValues() {
        Contact c = buildValidContact();

        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123-456-7890"));
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName("ABCDEFGHIJK"));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress("1234567890123456789012345678901"));
    }
}
