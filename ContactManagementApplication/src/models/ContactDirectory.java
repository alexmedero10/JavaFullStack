package models;

import java.util.*;

public class ContactDirectory {
    private List<Contact> contactList;
    private Set<String> contactTypes;
    private Map<String, Contact> contactMap;

    public ContactDirectory() {
        contactList = new ArrayList<>();
        contactTypes = new HashSet<>();
        contactMap = new HashMap<>();
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
        contactTypes.add(contact.getContactType());
        contactMap.put(contact.getName(), contact);
    }

    public void displayContacts() {
        for (Contact c : contactList) {
            System.out.println(c);
        }
    }

    public void displaySortedContacts() {
        contactList.stream()
                .sorted(Comparator.comparing(Contact::getName))
                .forEach(System.out::println);
    }

    public void addContactType(String type) {
        if (contactTypes.add(type)) {
            System.out.println("Contact type added: " + type);
        } else {
            System.out.println("Duplicate contact type: " + type);
        }
    }

    public void displayContactTypes() {
        System.out.println("Unique Contact Types:");
        for (String type : contactTypes) {
            System.out.println("- " + type);
        }
    }

    public Contact searchContactByName(String name) {
        return contactMap.get(name);
    }

    public boolean updateContact(String name, String newPhone, String newEmail, String newType) {
        Contact contact = contactMap.get(name);
        if (contact != null) {
            contact.setPhoneNumber(newPhone);
            contact.setEmail(newEmail);
            contact.setContactType(newType);
            contactTypes.add(newType);
            return true;
        }
        return false;
    }
}

