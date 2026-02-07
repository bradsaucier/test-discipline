// Unit tests for ContactService add, delete, and update behavior (JUnit 5).

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService service;

    @BeforeEach
    void setUp() {
        service = new ContactService();
    }

    private static Contact makeContact(String id) {
        return new Contact(id, "John", "Smith", "1234567890", "123 Main St");
    }

    @Test
    @DisplayName("S1 - addContact stores a contact by unique id")
    void addContactStoresContact() {
        service.addContact(makeContact("ID1"));

        assertEquals(1, service.getContactCount());
        Contact stored = service.getContact("ID1");
        assertNotNull(stored);
        assertEquals("ID1", stored.getContactId());
    }

    @Test
    @DisplayName("S1 - addContact rejects null contact")
    void addNullContactThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.addContact((Contact) null)
        );
    }

    @Test
    @DisplayName("S1 - adding a duplicate id is rejected")
    void addContactDuplicateIdThrows() {
        service.addContact(makeContact("ID1"));

        assertThrows(IllegalArgumentException.class, () ->
            service.addContact(makeContact("ID1"))
        );
    }

    @Test
    @DisplayName("S2 - deleteContact removes an existing contact")
    void deleteContactRemovesContact() {
        service.addContact(makeContact("ID1"));
        service.deleteContact("ID1");

        assertEquals(0, service.getContactCount());
        assertNull(service.getContact("ID1"));
    }

    @Test
    @DisplayName("S2 - deleteContact on unknown id is rejected")
    void deleteUnknownIdThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.deleteContact("NOPE")
        );
    }

    @Test
    @DisplayName("S3 - updateContact changes updatable fields and preserves id")
    void updateContactUpdatesFields() {
        service.addContact(makeContact("ID1"));

        service.updateContact("ID1", "Jane", "Doe", "0987654321", "456 Oak Ave");

        Contact updated = service.getContact("ID1");
        assertNotNull(updated);

        assertAll(
            () -> assertEquals("ID1", updated.getContactId()),
            () -> assertEquals("Jane", updated.getFirstName()),
            () -> assertEquals("Doe", updated.getLastName()),
            () -> assertEquals("0987654321", updated.getPhone()),
            () -> assertEquals("456 Oak Ave", updated.getAddress())
        );
    }

    @Test
    @DisplayName("S3 - updateContact supports partial updates (null means no change)")
    void updateContactPartialUpdateKeepsOtherFields() {
        service.addContact(makeContact("ID1"));

        service.updateContact("ID1", "Jane", null, null, null);

        Contact after = service.getContact("ID1");
        assertNotNull(after);

        assertAll(
            () -> assertEquals("ID1", after.getContactId()),
            () -> assertEquals("Jane", after.getFirstName()),
            () -> assertEquals("Smith", after.getLastName()),
            () -> assertEquals("1234567890", after.getPhone()),
            () -> assertEquals("123 Main St", after.getAddress())
        );
    }

    @Test
    @DisplayName("S3 - updateContact on unknown id is rejected")
    void updateUnknownIdThrows() {
        assertThrows(IllegalArgumentException.class, () ->
            service.updateContact("NOPE", "Jane", null, null, null)
        );
    }

    @Test
    @DisplayName("S3 - invalid field value is rejected")
    void invalidFieldValueThrows() {
        service.addContact(makeContact("ID1"));

        assertThrows(IllegalArgumentException.class, () ->
            service.updateContact("ID1", "Janet", null, "BADPHONE", null)
        );
    }
}
