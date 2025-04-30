package Controller;

import Model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class LicenseManagementSystem {
    private List<NormalUser> normalUsers;
    private List<Application> applications;
    private List<License> licenses;
    private Admin admin;

    public LicenseManagementSystem() {
        normalUsers = new ArrayList<>();
        applications = new ArrayList<>();
        licenses = new ArrayList<>();
        admin = new Admin("admin", "admin123");
    }

    public void registerUser(Scanner sc) {
        System.out.println("\n--- User Registration ---");

        System.out.print("Enter your Name: ");
        String name = sc.nextLine().trim();
        if(name.isEmpty()){
            System.out.println("Error: Name cannot be empty!");
            return;
        }

        System.out.print("Enter your Address: ");
        String address = sc.nextLine().trim();
        if(address.isEmpty()){
            System.out.println("Error: Address cannot be empty!");
            return;
        }

        System.out.print("Enter your Phone Number: ");
        String phone = sc.nextLine().trim();
        if(phone.isEmpty()){
            System.out.println("Error: Phone Number cannot be empty!");
            return;
        }

        System.out.print("Enter your Citizenship Number: ");
        String citizenship = sc.nextLine().trim();
        if(citizenship.isEmpty()){
            System.out.println("Error: Citizenship Number cannot be empty!");
            return;
        }

        for (NormalUser user : normalUsers) {
            if (user.getCitizenshipNumber().equals(citizenship)) {
                System.out.println("Citizenship Number already registered!");
                return;
            }
        }

        System.out.print("Enter Age: ");
        int age = 0;
        try {
            String ageInput = sc.nextLine().trim();
            if(ageInput.isEmpty()){
                System.out.println("Error: Age cannot be empty!");
                return;
            }
            age = Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age!");
            return;
        }
        if (age < 18) {
            System.out.println("Registration failed! You must be 18+.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = sc.nextLine().trim();
        if (password.isEmpty()) {
            System.out.println("Error: Password cannot be empty!");
            return;
        }

        NormalUser newUser = new NormalUser(name, address, phone, citizenship, age, password);
        normalUsers.add(newUser);
        System.out.println("Registration successful! Please login to apply for a license.");
    }


    public void normalUserLogin(Scanner sc) {
        System.out.println("\n--- Normal User Login ---");

        System.out.print("Enter Citizenship Number: ");
        String citizenship = sc.nextLine().trim();
        if(citizenship.isEmpty()){
            System.out.println("Error: Citizenship Number cannot be empty!");
            return;
        }

        System.out.print("Enter Password: ");
        String password = sc.nextLine().trim();
        if(password.isEmpty()){
            System.out.println("Error: Password cannot be empty!");
            return;
        }

        NormalUser loggedInUser = null;
        for (NormalUser user : normalUsers) {
            if (user.getCitizenshipNumber().equals(citizenship) && user.getPassword().equals(password)) {
                loggedInUser = user;
                break;
            }
        }
        if (loggedInUser == null) {
            System.out.println("Invalid credentials!");
            return;
        }
        System.out.println("Login successful! Welcome, " + loggedInUser.getUsername());
        userDashboard(sc, loggedInUser);
    }


    private void userDashboard(Scanner sc, NormalUser user) {
        while (true) {
            System.out.println("\n--- User Dashboard ---");
            System.out.println("1. Apply for License");
            System.out.println("2. Check Application Status");
            System.out.println("3. Renew License");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    applyForLicense(sc, user);
                    break;
                case "2":
                    user.checkStatus(applications);
                    break;
                case "3":
                    renewLicense(sc, user);
                    break;
                case "4":
                    System.out.println("Logging out.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void applyForLicense(Scanner sc, NormalUser user) {
        System.out.println("\n--- l.License l.Application ---");
        System.out.print("Enter desired license category (e.g., A, B, C): ");
        String desiredCategory = sc.nextLine().trim().toUpperCase();


        License existingLicense = getLicenseByCitizenship(user.getCitizenshipNumber());
        if (existingLicense != null) {
            if (CompatibilityChecker.isCompatible(existingLicense.getCategory(), desiredCategory)) {
                System.out.println("You already hold a license in category " + existingLicense.getCategory() +
                        " which is compatible with " + desiredCategory + ". You may upgrade directly.");
            } else {
                System.out.println("You already hold a license that is not compatible with " + desiredCategory +
                        ". A new application is still required.");
            }
        }

        Application app = new Application(generateID(), user.getCitizenshipNumber(), desiredCategory, "Pending");
        applications.add(app);
        System.out.println("License application submitted successfully. Status: Pending");
    }

    private void renewLicense(Scanner sc, NormalUser user) {
        License license = getLicenseByCitizenship(user.getCitizenshipNumber());
        if (license == null) {
            System.out.println("No license found to renew.");
            return;
        }
        System.out.println("License renewed successfully.");
    }

    private License getLicenseByCitizenship(String citizenship) {
        for (License license : licenses) {
            if (license.getCitizenshipNumber().equals(citizenship)) {
                return license;
            }
        }
        return null;
    }

    public void adminLogin(Scanner sc) {
        System.out.println("\n--- Admin Login ---");
        System.out.print("Enter Admin Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Enter Admin Password: ");
        String password = sc.nextLine().trim();

        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            System.out.println("Admin login successful!");
            adminDashboard(sc);
        } else {
            System.out.println("Invalid admin credentials!");
        }
    }

    private void adminDashboard(Scanner sc) {
        while (true) {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println("1. View Pending Applications");
            System.out.println("2. Approve/Reject Application");
            System.out.println("3. Generate Report");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    viewPendingApplications();
                    break;
                case "2":
                    processApplication(sc);
                    break;
                case "3":
                    admin.generateReport(applications, licenses);
                    break;
                case "4":
                    System.out.println("Logging out.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void viewPendingApplications() {
        System.out.println("\n--- Pending Applications ---");
        boolean found = false;
        for (Application app : applications) {
            if (app.getStatus().equalsIgnoreCase("Pending")) {
                System.out.println(app);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No pending applications.");
        }
    }

    private void processApplication(Scanner sc) {
        System.out.println("\nEnter Application ID to process (or 'exit' to return): ");
        String appId = sc.nextLine().trim();
        if (appId.equalsIgnoreCase("exit")) {
            return;
        }
        Application app = null;
        for (Application a : applications) {
            if (a.getApplicationID().equals(appId) && a.getStatus().equalsIgnoreCase("Pending")) {
                app = a;
                break;
            }
        }
        if (app == null) {
            System.out.println("Application not found or already processed.");
            return;
        }
        System.out.print("Approve (A) or Reject (R): ");
        String decision = sc.nextLine().trim();
        if (decision.equalsIgnoreCase("A")) {
            app.setStatus("Approved");
            License newLicense = new License(generateID(), app.getRequestedCategory(), app.getCitizenshipNumber());
            licenses.add(newLicense);
            System.out.println("Application approved and license issued: " + newLicense);
        } else if (decision.equalsIgnoreCase("R")) {
            app.setStatus("Rejected");
            System.out.println("Application rejected.");
        } else {
            System.out.println("Invalid decision.");
        }
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }
}
