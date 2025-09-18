import java.util.ArrayList;
import java.util.Scanner;

class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phone + ", Email: " + email;
    }
}

public class ContactManagementSystem {
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void addContact() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        for (Contact c : contacts) {
            if (c.getPhone().equals(phone) || c.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Duplicate contact! Cannot add.");
                return;
            }
        }

        contacts.add(new Contact(name, phone, email));
        System.out.println("Contact added successfully!");
    }

    public static void viewContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }
        System.out.println("\n--- Contact List ---");
        for (int i = 0; i < contacts.size(); i++) {
            System.out.println((i + 1) + ". " + contacts.get(i));
        }
    }

    public static void updateContact() {
        viewContacts();
        if (contacts.isEmpty()) return;

        System.out.print("Enter contact number to update: ");
        int index = sc.nextInt();
        sc.nextLine(); 

        if (index < 1 || index > contacts.size()) {
            System.out.println("Invalid contact number!");
            return;
        }

        Contact c = contacts.get(index - 1);

        System.out.print("Enter new Name (leave blank to keep same): ");
        String name = sc.nextLine();
        if (!name.isEmpty()) c.setName(name);

        System.out.print("Enter new Phone (leave blank to keep same): ");
        String phone = sc.nextLine();
        if (!phone.isEmpty()) c.setPhone(phone);

        System.out.print("Enter new Email (leave blank to keep same): ");
        String email = sc.nextLine();
        if (!email.isEmpty()) c.setEmail(email);

        System.out.println("Contact updated successfully!");
    }

    public static void deleteContact() {
        viewContacts();
        if (contacts.isEmpty()) return;

        System.out.print("Enter contact number to delete: ");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < 1 || index > contacts.size()) {
            System.out.println("Invalid contact number!");
            return;
        }

        contacts.remove(index - 1);
        System.out.println("Contact deleted successfully!");
    }

    
    public static void searchContact() {
        System.out.print("Enter keyword (name, phone, or email): ");
        String keyword = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Contact c : contacts) {
            if (c.getName().toLowerCase().contains(keyword) ||
                c.getPhone().contains(keyword) ||
                c.getEmail().toLowerCase().contains(keyword)) {
                System.out.println(c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching contact found.");
        }
    }

    public static void displayMenu() {
        System.out.println("\n--- Contact Management System ---");
        System.out.println("1. Add Contact");
        System.out.println("2. View Contacts");
        System.out.println("3. Update Contact");
        System.out.println("4. Delete Contact");
        System.out.println("5. Search Contact");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1: addContact(); break;
                case 2: viewContacts(); break;
                case 3: updateContact(); break;
                case 4: deleteContact(); break;
                case 5: searchContact(); break;
                case 6: exit = true; System.out.println("Exiting... Thank you!"); break;
                default: System.out.println("Invalid choice!");
            }
        }
    }
}

