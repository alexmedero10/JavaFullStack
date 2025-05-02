import models.Contact;
import models.ContactDirectory;

public class ContactManager {
    public static void main(String[] args) {
        ContactDirectory directory = new ContactDirectory();

        directory.addContact(new Contact(1, "Ale", "1234567890", "ale@email.com", "Personal"));
        directory.addContact(new Contact(2, "Ger", "2345678901", "ger@email.com", "Professional"));
        directory.addContact(new Contact(3, "Pau", "3456789012", "pau@email.com", "Personal"));

        System.out.println("\nAll Contacts:");
        directory.displayContacts();

        System.out.println("\nAdding duplicate contact type:");
        directory.addContactType("Personal");
        directory.addContactType("Professional");
        directory.addContactType("Family");

        directory.displayContactTypes();

        System.out.println("\nSearch Contact 'Ale':");
        Contact found = directory.searchContactByName("Ale");
        System.out.println(found != null ? found : "Contact not found");

        System.out.println("\nUpdate Contact 'Ale':");
        if (directory.updateContact("Ale", "0000000000", "newale@email.com", "Family")) {
            System.out.println("Contact updated.");
        }

        System.out.println("\nUpdated Contact List:");
        directory.displayContacts();

        System.out.println("\nSorted Contacts:");
        directory.displaySortedContacts();
    }
}
