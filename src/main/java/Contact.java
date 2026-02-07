// Component: Contact
// Purpose: Enforce field integrity for an in-memory contact record.

public final class Contact {

    // Immutable after construction; used as lookup key.
    private final String contactId;

    // Mutable; see setters for validation rules.
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        validate(contactId, "contactId", 10, false);
        validate(firstName, "firstName", 10, false);
        validate(lastName, "lastName", 10, false);
        validate(phone, "phone", 10, true);
        validate(address, "address", 30, false);

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
        validate(firstName, "firstName", 10, false);
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validate(lastName, "lastName", 10, false);
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        validate(phone, "phone", 10, true);
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        validate(address, "address", 30, false);
        this.address = address;
    }

    // Validates a required String field. If strictDigits is true, value must be exactly maxLength digits.
    private static void validate(String value, String fieldName, int maxLength, boolean strictDigits) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " must not be null");
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
