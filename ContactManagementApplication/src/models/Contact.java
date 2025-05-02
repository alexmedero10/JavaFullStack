package models;

public class Contact {
    private int contactId;
    private String name;
    private String phoneNumber;
    private String email;
    private String contactType;

    public Contact(int contactId, String name, String phoneNumber, String email, String contactType) {
        this.contactId = contactId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.contactType = contactType;
    }

    public int getContactId() {
        return contactId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getContactType() {
        return contactType;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    @Override
    public String toString() {
        return contactId + ": " + name + " | " + phoneNumber + " | " + email + " | " + contactType;
    }
}

