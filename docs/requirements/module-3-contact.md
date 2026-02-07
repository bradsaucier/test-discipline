# Module 3 - Contact Service Requirements Trace

## Requirements - Contact

| ID | Field | Constraints |
|---|---|---|
| R1 | contactId | not null, <= 10 chars, not updatable |
| R2 | firstName | not null, <= 10 chars |
| R3 | lastName | not null, <= 10 chars |
| R4 | phone | not null, exactly 10 digits |
| R5 | address | not null, <= 30 chars |

## Requirements - ContactService

| ID | Capability | Requirement |
|---|---|---|
| S1 | add | add contacts with unique contactId |
| S2 | delete | delete contacts by contactId |
| S3 | update | update firstName, lastName, phone, address by contactId |

## Trace to tests

| Requirement | Test coverage (examples) |
|---|---|
| R1 | ContactTest.contactIdNullThrows, ContactTest.contactIdTooLongThrows, ContactTest.contactIdLengthTenAccepted |
| R2 | ContactTest.firstNameNullThrows, ContactTest.firstNameTooLongThrows, ContactTest.firstNameLengthTenAccepted |
| R3 | ContactTest.lastNameNullThrows, ContactTest.lastNameTooLongThrows, ContactTest.lastNameLengthTenAccepted |
| R4 | ContactTest.phoneNullThrows, ContactTest.phoneTooShortThrows, ContactTest.phoneTooLongThrows, ContactTest.phoneNonDigitThrows |
| R5 | ContactTest.addressNullThrows, ContactTest.addressTooLongThrows, ContactTest.addressLengthThirtyAccepted |
| S1 | ContactServiceTest.addContactStoresContact, ContactServiceTest.addNullContactThrows, ContactServiceTest.addContactDuplicateIdThrows |
| S2 | ContactServiceTest.deleteContactRemovesContact, ContactServiceTest.deleteUnknownIdThrows |
| S3 | ContactServiceTest.updateContactUpdatesFields, ContactServiceTest.updateContactPartialUpdateKeepsOtherFields, ContactServiceTest.updateUnknownIdThrows, ContactServiceTest.invalidFieldValueThrows |
