// Component: ContactService
// Purpose: Add, delete, and update contacts in memory.
// Strategy: Map keyed by contactId for fast lookups and enforced uniqueness.

import java.util.HashMap;
import java.util.Map;

public final class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact must not be null");
        }

        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("contactId already exists: " + id);
        }

        contacts.put(id, contact);
    }

    public void deleteContact(String contactId) {
        requireNonNullId(contactId);

        if (!contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("contactId not found: " + contactId);
        }

        contacts.remove(contactId);
    }

    public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        requireNonNullId(contactId);

        Contact target = contacts.get(contactId);
        if (target == null) {
            throw new IllegalArgumentException("contactId not found: " + contactId);
        }

        // Null values are skipped; non-null values are validated and applied.
        if (firstName != null) {
            target.setFirstName(firstName);
        }
        if (lastName != null) {
            target.setLastName(lastName);
        }
        if (phone != null) {
            target.setPhone(phone);
        }
        if (address != null) {
            target.setAddress(address);
        }
    }

    // Returns the contact with the given id, or null if not found.
    public Contact getContact(String contactId) {
        requireNonNullId(contactId);
        return contacts.get(contactId);
    }

    public int getContactCount() {
        return contacts.size();
    }

    private static void requireNonNullId(String contactId) {
        if (contactId == null) {
            throw new IllegalArgumentException("contactId must not be null");
        }
    }
}
