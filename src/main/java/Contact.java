// Component: Contact
// Purpose: Enforce field integrity for an in-memory contact record.

public final class Contact {

    // Single source of truth for field constraints.
    private static final int CONTACT_ID_MAX_LENGTH = 10;
    private static final int NAME_MAX_LENGTH = 10;
    private static final int PHONE_DIGITS = 10;
    private static final int ADDRESS_MAX_LENGTH = 30;

    // Immutable after construction; used as lookup key.
    private final String contactId;

    // Mutable; see setters for validation rules.
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        validate(contactId, "contactId", CONTACT_ID_MAX_LENGTH, false);
        validate(firstName, "firstName", NAME_MAX_LENGTH, false);
        validate(lastName, "lastName", NAME_MAX_LENGTH, false);
        validate(phone, "phone", PHONE_DIGITS, true);
        validate(address, "address", ADDRESS_MAX_LENGTH, false);

        this.contactId = contactId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        validate(firstName, "firstName", NAME_MAX_LENGTH, false);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validate(lastName, "lastName", NAME_MAX_LENGTH, false);
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        validate(phone, "phone", PHONE_DIGITS, true);
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        validate(address, "address", ADDRESS_MAX_LENGTH, false);
        this.address = address;
    }

    // Validates a required String field. If strictDigits is true, value must be exactly maxLength digits.
    private static void validate(String value, String fieldName, int maxLength, boolean strictDigits) {
        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException(fieldName + " must not be null or empty");
        }

        if (strictDigits) {
            if (!value.matches("\\d{" + maxLength + "}")) {
                throw new IllegalArgumentException(fieldName + " must be exactly " + maxLength + " digits");
            }
            return;
        }

        if (value.length() > maxLength) {
            throw new IllegalArgumentException(fieldName + " must be " + maxLength + " characters or fewer");
        }
    }
}
