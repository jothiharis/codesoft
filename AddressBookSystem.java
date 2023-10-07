import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println("Name: " + contact.getName() + ", Phone: " + contact.getPhoneNumber() + ", Email: " + contact.getEmail());
        }
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmail());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving contacts to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(",");
                if (parts.length == 3) {
                    Contact contact = new Contact(parts[0], parts[1], parts[2]);
                    contacts.add(contact);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Address book file not found. Creating a new one.");
        }
    }
}

public class AddressBookSystem {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nAddress Book Menu:");
            System.out.println("1. Add a new contact");
            System.out.println("2. Remove a contact");
            System.out.println("3. Search for a contact");
            System.out.println("4. Display all contacts");
            System.out.println("5. Save contacts to file");
            System.out.println("6. Load contacts from file");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String email = scanner.nextLine();

                    if (!name.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {
                        Contact newContact = new Contact(name, phoneNumber, email);
                        addressBook.addContact(newContact);
                        System.out.println("Contact added successfully!");
                    } else {
                        System.out.println("Name, phone number, and email are required fields.");
                    }
                    break;
                case 2:
                    System.out.print("Enter name of the contact to remove: ");
                    String removeName = scanner.nextLine();
                    Contact removeContact = addressBook.searchContact(removeName);
                    if (removeContact != null) {
                        addressBook.removeContact(removeContact);
                        System.out.println(removeName + " removed from contacts.");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter name of the contact to search: ");
                    String searchName = scanner.nextLine();
                    Contact searchResult = addressBook.searchContact(searchName);
                    if (searchResult != null) {
                        System.out.println("Name: " + searchResult.getName() + ", Phone: " + searchResult.getPhoneNumber() + ", Email: " + searchResult.getEmail());
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 4:
                    addressBook.displayAllContacts();
                    break;
                case 5:
                    System.out.print("Enter filename to save contacts: ");
                    String saveFilename = scanner.nextLine();
                    addressBook.saveToFile(saveFilename);
                    System.out.println("Contacts saved to file.");
                    break;
                case 6:
                    System.out.print("Enter filename to load contacts from: ");
                    String loadFilename = scanner.nextLine();
                    addressBook.loadFromFile(loadFilename);
                    System.out.println("Contacts loaded from file.");
                    break;
                case 7:
                    System.out.println("Exiting Address Book. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
