package com.Job_Portal;

import java.util.Scanner;

import com.Job_Portal.Entity.Admin;
import com.Job_Portal.Entity.Employer;
import com.Job_Portal.Entity.User;

public class MainOperation {
	 // Scanner to take user input from console
    private Scanner sc;

    // Object to call all registration/login/menu logic
    private AllOperation allOperation;

    // Constructor: initializes Scanner and AllOperation
    public MainOperation(Scanner sc) {
        this.sc = sc;
        this.allOperation = new AllOperation(sc);  // Create an instance of AllOperation to handle various operations
    }

    // This method runs the main loop to show the primary menu
    public void start() {
        // Variable to hold user's choice from the menu
        int choice;
        do {
            // Display the main menu options to the user
            System.out.println("==== ONLINE JOB PORTAL =====");
            System.out.println("1. Register as Employer (Secret Key)");
            System.out.println("2. Register as Admin (Secret Key)");
            System.out.println("3. Register as User");
            System.out.println("4. Admin Login");
            System.out.println("5. Employer Login");
            System.out.println("6. User Login");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            // Read user input and trim any extra whitespace
            String input = sc.nextLine().trim();

            // Handle case where input is empty
            if (input.isEmpty()) {
                System.out.println("❌ Please enter a valid option.");
                continue;  // Go back to the top of the loop if the input is invalid
            }

            // Try to parse the input to an integer and handle non-numeric inputs
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Try again.");
                continue;  // Retry if the input is not a valid number
            }

            // Execute action based on the user's menu choice
            switch (choice) {
                case 1:  // Register as Employer
                    allOperation.employerRegistration(); // Call employer registration method from AllOperation class
                    break;
                case 2:  // Register as Admin
                    allOperation.adminRegistration(); // Call admin registration method from AllOperation class
                    break;
                case 3:  // Register as User
                    allOperation.userRegistration(); // Call user registration method from AllOperation class
                    break;
                case 4:  // Admin Login
                    Admin loggedInAdmin = allOperation.adminLogin(); // Attempt to log in the admin
                    if (loggedInAdmin != null) {
                        allOperation.adminMenu(loggedInAdmin);  // If login successful, show admin menu
                    } else {
                        System.out.println("🔙 Returning to main menu..."); // Return to main menu if login fails
                    }
                    break;
                case 5:  // Employer Login
                    Employer loggedInEmployer = allOperation.employerLogin(); // Attempt to log in the employer
                    if (loggedInEmployer != null) {
                        allOperation.employerMenu(loggedInEmployer);  // If login successful, show employer menu
                    } else {
                        System.out.println("🔙 Returning to main menu..."); // Return to main menu if login fails
                    }
                    break;
                case 6:  // User Login
                    User loggedInUser = allOperation.userLogin(); // Attempt to log in the user
                    if (loggedInUser != null) {
                        allOperation.userMenu(loggedInUser);  // If login successful, show user menu
                    } else {
                        System.out.println("🔙 Returning to main menu..."); // Return to main menu if login fails
                    }
                    break;
                case 7:  // Exit the application
                    System.out.println("👋 Thank you for using the Online Job Portal. Goodbye!");
                    return;  // Exit the program when user selects "Exit"
                default:  // Handle invalid option input (numbers outside 1–7)
                    System.out.println("❌ Invalid option. Try again.");
            }

        // Loop continues until user selects Exit (7) or the program terminates
        } while (true);
    }
}