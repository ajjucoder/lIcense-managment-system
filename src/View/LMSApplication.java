package View;

import Controller.LicenseManagementSystem;

import java.util.Scanner;

public class LMSApplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LicenseManagementSystem lms = new LicenseManagementSystem();

        while (true) {
            System.out.println("\n=== Model.License Management System ===");
            System.out.println("1. Register as Normal User");
            System.out.println("2. Login as Normal User");
            System.out.println("3. Login as admin");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    lms.registerUser(sc);
                    break;
                case "2":
                    lms.normalUserLogin(sc);
                    break;
                case "3":
                    lms.adminLogin(sc);
                    break;
                case "4":
                    System.out.println("Exiting the system.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
