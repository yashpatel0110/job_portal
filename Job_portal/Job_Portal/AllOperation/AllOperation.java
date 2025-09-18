package com.Job_Portal;

import java.io.Console;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.hibernate.Session;

import com.Job_Portal.Entity.Admin;
import com.Job_Portal.Entity.Employer;
import com.Job_Portal.Entity.Job;
import com.Job_Portal.Entity.User;
import com.Job_Portal.Service.AdminService;
import com.Job_Portal.Service.EmployerService;
import com.Job_Portal.Dao.*;
import com.Job_Portal.DaoImpl.*;
import com.Job_Portal.Service.JobService;
import com.Job_Portal.Service.UserService;
import com.Job_Portal.ServiceImpl.AdminServiceImpl;
import com.Job_Portal.ServiceImpl.EmployerServiceImpl;
import com.Job_Portal.ServiceImpl.JobServiceImpl;
import com.Job_Portal.ServiceImpl.UserServiceImpl;
import com.Job_Portal.Util.HibernateUtil;

import com.Job_Portal_System.App ;


//This class handles all business logic operations for Admin, Employer, and User roles
public class AllOperation {
	 Scanner scanner = new Scanner(System.in);
	    private static Session session = HibernateUtil.getSessionFactory().openSession();
	    private JobDao jobDao = new JobDaoImpl();
	    private static final String ADMIN_SECRET_KEY = "OnlyForAdmin";
	    private static final String EMPLOYER_SECRET_KEY = "OnlyForEmployer";

	    private final Scanner sc;
	    private final AdminService adminService;
	    private final EmployerService employerService;
	    private final UserService userService;
	    private final JobService jobService;
	    private static boolean isLoggedIn = false; // Static variable to track login status

	    // Constructor initializes all services with a shared Hibernate session
	    public AllOperation(Scanner sc) {
	        this.sc = sc;
	        this.adminService = new AdminServiceImpl();
	        this.employerService = new EmployerServiceImpl();
	        this.userService = new UserServiceImpl();
	        this.jobService = new JobServiceImpl(session);
	    }

	    // Utility method to handle hidden input (for password)
	    private String getHiddenInput(String prompt) {
	        // Use Scanner and Console for password input
	        Console console = System.console();
	        if (console != null) {
	            char[] passwordArray = console.readPassword(prompt);
	            return new String(passwordArray);
	        } else {
	            System.out.print(prompt);
	            return sc.nextLine();
	        }
	    }

	    // ==================== Admin Registration ====================
	    public void adminRegistration() {
	        System.out.print("Enter Admin Registration Key: ");
	        String key = sc.nextLine();

	        // Validate the secret key for Admin registration
	        if (!key.equals(ADMIN_SECRET_KEY)) {
	            System.out.println("❌ Invalid key. Access denied.");
	            return;
	        }

	        // Collect Admin details
	        System.out.print("Enter First Name: ");
	        String firstName = sc.nextLine();
	        System.out.print("Enter Last Name: ");
	        String lastName = sc.nextLine();

	        // Email validation loop
	        String email;
	        while (true) {
	            System.out.print("Enter Email: ");
	            email = sc.nextLine();
	            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	                break;
	            } else {
	                System.out.println("❌ Invalid email format. Please try again.");
	            }
	        }

	        // Phone validation loop (10-digit only)
	        String phone;
	        while (true) {
	            System.out.print("Enter Phone (10 digits only): ");
	            phone = sc.nextLine();
	            if (phone.matches("\\d{10}")) {
	                break;
	            } else {
	                System.out.println("❌ Invalid mobile number. Please enter exactly 10 digits.");
	            }
	        }

	        System.out.print("Enter Address: ");
	        String address = sc.nextLine();
	        String password = getHiddenInput("Enter Password: ");

	        // Save the Admin object
	        Admin admin = new Admin(firstName, lastName, phone, email, address, password);
	        adminService.saveAdmin(admin);
	        System.out.println("✅ Admin registered successfully.");
	    }

	    // ==================== Employer Registration ====================
	    public void employerRegistration() {
	        System.out.print("Enter Employer Registration Key: ");
	        String key = sc.nextLine();

	        // Validate the secret key for Employer registration
	        if (!key.equals(EMPLOYER_SECRET_KEY)) {
	            System.out.println("❌ Invalid key. Access denied.");
	            return;
	        }

	        // Collect Employer details
	        System.out.print("Enter Company Name: ");
	        String company = sc.nextLine();
	        System.out.print("Enter First Name: ");
	        String firstName = sc.nextLine();
	        System.out.print("Enter Last Name: ");
	        String lastName = sc.nextLine();

	        // Email validation loop
	        String email;
	        while (true) {
	            System.out.print("Enter Email: ");
	            email = sc.nextLine();
	            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	                break;
	            } else {
	                System.out.println("❌ Invalid email format. Please try again.");
	            }
	        }

	        // Phone validation loop (10-digit only)
	        String phone;
	        while (true) {
	            System.out.print("Enter Phone (10 digits only): ");
	            phone = sc.nextLine();
	            if (phone.matches("\\d{10}")) {
	                break;
	            } else {
	                System.out.println("❌ Invalid mobile number. Please enter exactly 10 digits.");
	            }
	        }

	        System.out.print("Enter Address: ");
	        String address = sc.nextLine();
	        String password = getHiddenInput("Enter Password: ");

	        // Save the Employer object
	        Employer emp = new Employer(company, firstName, lastName, email, phone, address, password);
	        employerService.saveEmployer(emp);
	        System.out.println("✅ Employer registered successfully.");
	    }

	    // ==================== User Registration ====================
	    public void userRegistration() {
	        // Collect User details
	        System.out.print("Enter First Name: ");
	        String firstName = sc.nextLine();
	        System.out.print("Enter Last Name: ");
	        String lastName = sc.nextLine();

	        // Email validation loop
	        String email;
	        while (true) {
	            System.out.print("Enter Email: ");
	            email = sc.nextLine();
	            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
	                break;
	            } else {
	                System.out.println("❌ Invalid email format. Please try again.");
	            }
	        }

	        // Phone validation loop (10-digit only)
	        String phone;
	        while (true) {
	            System.out.print("Enter Phone (10 digits only): ");
	            phone = sc.nextLine();
	            if (phone.matches("\\d{10}")) {
	                break;
	            } else {
	                System.out.println("❌ Invalid mobile number. Please enter exactly 10 digits.");
	            }
	        }

	        System.out.print("Enter Address: ");
	        String address = sc.nextLine();
	        System.out.print("Enter Resume Link (Google Drive, Dropbox, etc): ");
	        String resume = sc.nextLine();
	        String password = getHiddenInput("Enter Password: ");

	        // Save the User object
	        User user = new User(firstName, lastName, email, phone, address, resume, password);
	        userService.registerUser(user);
	        System.out.println("✅ User registered successfully.");
	    }
	

	 // ==================== Admin Login ====================//
		
	 // Method for admin login using a graphical dialog box
	 public Admin adminLogin() {
	     // Create text fields for email and password input in the dialog box
	     JTextField emailField = new JTextField();
	     JPasswordField passwordField = new JPasswordField();

	     // Dialog message that holds the email and password input fields
	     Object[] message = {
	         "Email:", emailField,
	         "Password:", passwordField
	     };

	     // Limit the admin login attempts to 3
	     for (int attempts = 0; attempts < 3; attempts++) {
	         // Show the login dialog box
	         int option = JOptionPane.showConfirmDialog(null, message, "Admin Login", JOptionPane.OK_CANCEL_OPTION);

	         if (option == JOptionPane.OK_OPTION) {
	             // Get input values from the dialog box
	             String email = emailField.getText();
	             String password = new String(passwordField.getPassword());

	             // Retrieve the admin by email and password from the service
	             Admin admin = adminService.getAdminByEmailAndPassword(email, password);

	             // Check if the credentials match
	             if (admin != null && admin.getPassword().equals(password)) {
	                 JOptionPane.showMessageDialog(null, "✅ Login Successful!");
	                 return admin; // Return the logged-in admin
	             } else {
	                 // If invalid credentials, show remaining attempts
	                 JOptionPane.showMessageDialog(null, "❌ Invalid Credentials! Attempts remaining: " + (2 - attempts));
	             }
	         } else {
	             // If the user cancels the login dialog
	             JOptionPane.showMessageDialog(null, "Login Cancelled.");
	             return null;
	         }
	     }

	     // After exceeding max login attempts, show a message
	     JOptionPane.showMessageDialog(null, "🚫 Maximum login attempts exceeded.");
	     return null;
	 }

	 // ==================== Admin Menu ====================
	 // Admin menu for selecting different actions
	 public void adminMenu(Admin admin) {
	     while (true) {
	         // Print the menu options for the admin to choose from
	         System.out.println("===== ADMIN MENU =====");
	         System.out.println("1. View All Employers");
	         System.out.println("2. View All Users");
	         System.out.println("3. View All Jobs");
	         System.out.println("4. Delete a Job");
	         System.out.println("5. Delete a User");
	         System.out.println("6. Approve a Job");
	         System.out.println("7. Reject a Job");
	         System.out.println("8. Delete an Employer");
	         System.out.println("9. Logout");
	         System.out.print("Choose an option: ");
	         int choice = sc.nextInt();
	         sc.nextLine(); // Clear buffer
	         
	         // Handle the user's choice
	         switch (choice) {
	             case 1:
	                 // Fetch and display all employers
	                 List<Employer> employers = employerService.getAllEmployers();
	                 if (employers.isEmpty()) {
	                     System.out.println("⚠️ No employers found.");
	                 } else {
	                     System.out.println("===== All Employers =====");
	                     for (Employer e : employers) {
	                         System.out.println(e); // Print each employer's details
	                     }
	                 }
	                 break;

	             case 2:
	                 // Fetch and display all users
	                 userService.viewAllUsers();
	                 break;

	             case 3:
	                 // Fetch and display all jobs
	                 jobService.viewAllJobs();
	                 break;

	             case 4:
	                 // Delete a job based on its ID
	                 System.out.print("Enter Job ID to delete: ");
	                 int jobId = sc.nextInt();
	                 sc.nextLine(); // consume the newline
	                 jobService.deleteJobById(jobId);
	                 break;

	             case 5:
	                 // Delete a user based on their ID
	                 System.out.print("Enter User ID to delete: ");
	                 int userId = sc.nextInt();
	                 sc.nextLine(); // consume the newline
	                 userService.deleteUserById(userId);
	                 break;

	             case 6:
	                 // Approve a job by its ID
	                 System.out.print("Enter Job ID to approve: ");
	                 int approveId = sc.nextInt();
	                 sc.nextLine(); // consume the newline
	                 jobService.approveJob(approveId);
	                 break;

	             case 7:
	                 // Reject a job by its ID (calls rejectJob method)
	                 System.out.print("Enter Job ID to reject: ");
	                 int rejectId = sc.nextInt();
	                 sc.nextLine(); // consume the newline
	                 rejectJob(rejectId);
	                 break;

	             case 8:
	                 // Delete an employer based on their ID (calls deleteEmployer method)
	                 System.out.print("Enter Employer ID to delete: ");
	                 int empId = sc.nextInt();
	                 sc.nextLine(); // consume the newline
	                 deleteEmployer(empId);
	                 break;

	             case 9:
	                 // Logout and exit the menu
	                 System.out.println("✅ Admin logged out.");
	                 return;

	             default:
	                 // Invalid choice, prompt the user to try again
	                 System.out.println("❌ Invalid choice. Try again.");
	         }
	     }
	 }

	 // ==================== Reject Job ====================
	 // Method to reject a job (marks job as not approved)
	 public void rejectJob(int jobId) {
	     // Fetch the job by its ID
	     Job job = jobService.getJobById(jobId);
	     if (job != null) {
	         job.setApproved(false);  // Mark job as rejected
	         jobService.updateJob(job); // Update job status in the DB
	         System.out.println("🚫 Job has been rejected.");
	     } else {
	         System.out.println("❌ Job not found.");
	     }
	 }

	 // ==================== Delete Employer ====================
	 // Method to delete an employer by their ID
	 public void deleteEmployer(int empId) {
	     // Fetch the employer by ID
	     Employer employer = employerService.getEmployerById(empId);
	     if (employer != null) {
	         employerService.deleteEmployerById(empId); // Delete employer from the DB
	         System.out.println("🗑️ Employer deleted successfully.");
	     } else {
	         System.out.println("❌ Employer not found.");
	     }
	 }

	 // ==================== Approve Job ====================
	 // Method to approve a job
	 public void approveJob() {
	     // Fetch all jobs (approved and unapproved)
	     List<Job> jobs = jobService.getAllJobs();

	     if (jobs.isEmpty()) {
	         System.out.println("❌ No jobs available to approve.");
	         return;
	     }

	     System.out.println("===== Jobs Awaiting Approval =====");
	     // Display all unapproved jobs
	     for (Job job : jobs) {
	         if (!job.isApproved()) {
	             System.out.println("ID: " + job.getJobId());
	             System.out.println("Title: " + job.getTitle());
	             System.out.println("Company: " + job.getCompanyName());
	             System.out.println("Location: " + job.getLocation());
	             System.out.println("-----------------------------");
	         }
	     }

	     // Ask for the job ID to approve
	     System.out.print("Enter Job ID to approve: ");
	     int jobId = scanner.nextInt();
	     scanner.nextLine(); // consume newline

	     // Fetch job by ID
	     Job job = jobService.getJobById(jobId);

	     if (job != null) {
	         job.setApproved(true); // Mark the job as approved
	         jobService.updateJob(job); // Save the updated job in the DB
	         System.out.println("✅ Job approved successfully.");
	     } else {
	         System.out.println("❌ Invalid Job ID.");
	     }
	 }

	 // ==================== View All Employers ====================
	 // Method to display all employers
	 public void viewAllEmployers() {
	     // Fetch all employers from the service
	     List<Employer> employers = employerService.getAllEmployers();
	     if (employers == null || employers.isEmpty()) {
	         System.out.println("⚠️ No employers found.");
	     } else {
	         System.out.println("===== EMPLOYER LIST =====");
	         for (Employer emp : employers) {
	             System.out.println(emp); // Print each employer's details
	         }
	     }
	 }

	 public Employer employerLogin() {
		    JTextField emailField = new JTextField();
		    JPasswordField passwordField = new JPasswordField();

		    // Create a dialog to get email and password from the employer
		    Object[] message = {
		        "Email:", emailField,
		        "Password:", passwordField
		    };

		    // Try to authenticate the employer for 3 attempts
		    for (int attempts = 0; attempts < 3; attempts++) {
		        int option = JOptionPane.showConfirmDialog(null, message, "Employer Login", JOptionPane.OK_CANCEL_OPTION);

		        if (option == JOptionPane.OK_OPTION) {
		            String email = emailField.getText();
		            String password = new String(passwordField.getPassword());

		            // Check credentials using the service
		            Employer employer = employerService.getEmployerByEmailAndPassword(email, password);

		            // If the credentials match, login is successful
		            if (employer != null && employer.getPassword().equals(password)) {
		                JOptionPane.showMessageDialog(null, "✅ Login Successful!");
		                return employer; // Return the logged-in employer
		            } else {
		                // If credentials are invalid, show an error message
		                JOptionPane.showMessageDialog(null, "❌ Invalid Credentials! Attempts remaining: " + (2 - attempts));
		            }
		        } else {
		            JOptionPane.showMessageDialog(null, "Login Cancelled.");
		            return null;
		        }
		    }

		    JOptionPane.showMessageDialog(null, "🚫 Maximum login attempts exceeded.");
		    return null; // If max attempts reached, return null
		}
	 public void employerMenu(Employer employer) {
		    int choice;
		    do {
		        // Show menu options to the employer
		        System.out.println("===== EMPLOYER MENU =====");
		        System.out.println("1. Post a Job");
		        System.out.println("2. View My Posted Jobs");
		        System.out.println("3. View Applications for My Jobs");
		        System.out.println("4. Logout");
		        System.out.print("Choose an option: ");
		        String input = sc.nextLine().trim();

		        // Check if input is empty and handle invalid entries
		        if (input.isEmpty()) {
		            System.out.println("❌ Please enter a valid option.");
		            continue;
		        }

		        try {
		            choice = Integer.parseInt(input);
		        } catch (NumberFormatException e) {
		            System.out.println("❌ Invalid number. Try again.");
		            continue;
		        }

		        // Handle menu actions based on the employer's choice
		        switch (choice) {
		            case 1:
		                postJob(employer); // Post a new job
		                break;
		            case 2:
		                List<Job> jobs = employerService.viewJobsByEmployer(employer.getEmpId());
		                // Show the list of jobs posted by the employer
		                if (jobs.isEmpty()) {
		                    System.out.println("❌ No jobs found.");
		                } else {
		                    System.out.println("===== Your Posted Jobs =====");
		                    for (Job job : jobs) {
		                        System.out.println("📌 Job ID: " + job.getJobId());
		                        System.out.println("Title: " + job.getTitle());
		                        System.out.println("Company: " + job.getCompanyName());
		                        System.out.println("Location: " + job.getLocation());
		                        System.out.println("Status: " + (job.isApproved() ? "Approved" : "Pending"));
		                        System.out.println("-------------------------");
		                    }
		                }
		                break;
		            case 3:
		                viewApplicationsForMyJobs(employer); // View job applications for the posted jobs
		                break;
		            case 4:
		                System.out.println("👋 Logged out successfully.");
		                return; // Logout and exit the menu
		            default:
		                System.out.println("❌ Invalid option. Try again.");
		        }
		    } while (true);
		}
	 public void viewApplicationsForMyJobs(Employer employer) {
		    List<Job> jobs = employerService.viewJobsByEmployer(employer.getEmpId());

		    if (jobs.isEmpty()) {
		        System.out.println("❌ You haven't posted any jobs.");
		        return;
		    }

		    boolean hasApplications = false;

		    // Loop through each job and check if there are any applicants
		    for (Job job : jobs) {
		        Set<User> applicants = job.getApplicants(); // Corrected here

		        if (applicants != null && !applicants.isEmpty()) {
		            hasApplications = true;
		            System.out.println("📌 Applicants for Job: " + job.getTitle() + " (ID: " + job.getJobId() + ")");
		            for (User user : applicants) {
		                System.out.println("👤 Name: " + user.getFirstName() + " " + user.getLastName());
		                System.out.println("📧 Email: " + user.getEmail());
		                System.out.println("📱 Phone: " + user.getPhone());
		                System.out.println("📄 Resume: " + user.getResume());
		                System.out.println("-----------------------------");
		            }
		        }
		    }

		    if (!hasApplications) {
		        System.out.println("❌ No applications found for your posted jobs.");
		    }
		}
	 public void postJob(Employer employer) {
		    Job job = new Job();

		    System.out.print("Enter Job Title: ");
		    job.setTitle(sc.nextLine());
		    System.out.print("Enter Category: ");
		    job.setCategory(sc.nextLine());
		    System.out.print("Enter Company Name: ");
		    job.setCompanyName(sc.nextLine());
		    System.out.print("Enter Employment Status: ");
		    job.setEmploymentStatus(sc.nextLine());
		    System.out.print("Enter Salary: ");
		    job.setSalary(sc.nextDouble());
		    sc.nextLine();
		    System.out.print("Enter Location: ");
		    job.setLocation(sc.nextLine());
		    System.out.print("Enter Deadline (yyyy-MM-dd): ");
		    job.setDeadline(LocalDate.parse(sc.nextLine()));
		    System.out.print("Enter Education Required: ");
		    job.setEducation(sc.nextLine());
		    System.out.print("Enter Experience Required: ");
		    job.setExperience(sc.nextLine());
		    System.out.print("Enter Additional Requirements: ");
		    job.setAdditionalRequirement(sc.nextLine());
		    System.out.print("Enter Number of Vacancies: ");
		    job.setVacancy(sc.nextInt());
		    sc.nextLine();

		    // Set employer and job status
		    job.setEmployer(employer);
		    job.setStatus("Pending");
		    job.setApproved(false);

		    jobService.postJob(job);
		    System.out.println("✅ Job posted successfully (awaiting admin approval).");
		}
	 public void rejectJob() {
		    System.out.print("Enter Job ID to reject: ");
		    int jobId = sc.nextInt();

		    Job job = jobService.getJobById(jobId);

		    if (job != null) {
		        if (!"Rejected".equalsIgnoreCase(job.getStatus())) {
		            job.setStatus("Rejected");
		            jobService.updateJob(job);
		            System.out.println("❌ Job rejected successfully.");
		        } else {
		            System.out.println("⚠️ This job is already rejected.");
		        }
		    } else {
		        System.out.println("❌ Job not found.");
		    }
		}
	 public void viewAllJobsForUser() {
		    Set<Job> jobs = jobService.getAllApprovedJobs();

		    if (jobs == null || jobs.isEmpty()) {
		        System.out.println("❌ No jobs available at the moment.");
		        return;
		    }

		    System.out.println("===== AVAILABLE JOBS =====");
		    for (Job job : jobs) {
		        System.out.println("Job ID: " + job.getJobId());
		        System.out.println("Title: " + job.getTitle());
		        System.out.println("Company: " + job.getCompanyName());
		        System.out.println("Location: " + job.getLocation());
		        System.out.println("---------------------------");
		    }
		}
	 public void approveJob(int jobId) {
		    Job job = jobDao.getJobById(jobId);
		    if (job != null) {
		        job.setApproved(true);
		        jobDao.updateJob(job);
		        System.out.println("✅ Job approved successfully.");
		    } else {
		        System.out.println("❌ Job not found.");
		    }
		}


	 /*----------------------------User Login -------------------------------*/
	 public User userLogin() {
	     JTextField emailField = new JTextField();  // Create email field for user input
	     JPasswordField passwordField = new JPasswordField();  // Create password field for user input

	     Object[] message = {  // Create the dialog message
	         "Email:", emailField,
	         "Password:", passwordField
	     };

	     // Allow 3 login attempts
	     for (int attempts = 0; attempts < 3; attempts++) {
	         int option = JOptionPane.showConfirmDialog(null, message, "User Login", JOptionPane.OK_CANCEL_OPTION);

	         if (option == JOptionPane.OK_OPTION) {
	             String email = emailField.getText();  // Get email input from the user
	             String password = new String(passwordField.getPassword());  // Get password input from the user

	             // Authenticate the user
	             User user = userService.getUserByEmailAndPassword(email, password);

	             if (user != null && user.getPassword().equals(password)) {
	                 JOptionPane.showMessageDialog(null, "✅ Login Successful!");  // If credentials match, login is successful
	                 return user;  // Return the logged-in user
	             } else {
	                 JOptionPane.showMessageDialog(null, "❌ Invalid Credentials! Attempts remaining: " + (2 - attempts));  // Invalid credentials
	             }
	         } else {
	             JOptionPane.showMessageDialog(null, "Login Cancelled.");  // If the user cancels, exit
	             return null;
	         }
	     }

	     // If all 3 attempts fail
	     JOptionPane.showMessageDialog(null, "🚫 Maximum login attempts exceeded.");
	     return null;
	 }


	 /*----------------------User Menu--------------------------------------*/
	 public void userMenu(User user) {
	     Scanner scanner = new Scanner(System.in);  // Initialize Scanner for user input

	     while (true) {
	         // Display the menu options
	         System.out.println("===== USER MENU =====");
	         System.out.println("1. View All Jobs");
	         System.out.println("2. Apply for a Job");
	         System.out.println("3. View Applied Jobs");
	         System.out.println("4. Logout");
	         System.out.print("Choose an option: ");
	         int choice = scanner.nextInt();  // Get the user's menu choice
	         scanner.nextLine();  // Consume leftover newline

	         switch (choice) {
	             case 1: {
	                 // Declare availableJobs inside this case block for job viewing
	                 Set<Job> availableJobs = userService.getAllApprovedJobs();  // Fetch all approved jobs

	                 if (availableJobs == null || availableJobs.isEmpty()) {
	                     System.out.println("❌ No jobs available currently.");  // No jobs available
	                 } else {
	                     System.out.println("===== AVAILABLE JOBS =====");
	                     for (Job job : availableJobs) {
	                         // Display job details
	                         System.out.println("ID: " + job.getJobId());
	                         System.out.println("Title: " + job.getTitle());
	                         System.out.println("Description: " + job.getAdditionalRequirement());
	                         System.out.println("Company: " + job.getCompanyName());
	                         System.out.println("Location: " + job.getLocation());
	                         System.out.println("Salary: " + job.getSalary());
	                         System.out.println("-----------------------------");
	                     }
	                 }
	                 break;
	             }

	             case 2: {
	                 // Declare approvedJobs inside this case block for job application
	                 Set<Job> approvedJobs = userService.getAllApprovedJobs();  // Fetch all approved jobs

	                 if (approvedJobs == null || approvedJobs.isEmpty()) {
	                     System.out.println("❌ No jobs available to apply for.");  // No jobs available to apply for
	                 } else {
	                     System.out.println("===== AVAILABLE JOBS =====");
	                     for (Job job : approvedJobs) {
	                         // Display job details
	                         System.out.println("ID: " + job.getJobId());
	                         System.out.println("Title: " + job.getTitle());
	                         System.out.println("Description: " + job.getAdditionalRequirement());
	                         System.out.println("Company: " + job.getCompanyName());
	                         System.out.println("Location: " + job.getLocation());
	                         System.out.println("Salary: " + job.getSalary());
	                         System.out.println("-----------------------------");
	                     }

	                     // Ask user for job ID to apply for
	                     System.out.print("Enter Job ID to apply: ");
	                     int jobId = scanner.nextInt();
	                     scanner.nextLine();  // Consume leftover newline

	                     userService.applyForJob(user.getUserId(), jobId);  // Call the service to apply for the selected job
	                     System.out.println("✅ Successfully applied to the job.");  // Confirmation message
	                 }
	                 break;
	             }

	             case 3:
	                 // Display jobs applied by the user
	                 userService.viewAppliedJobs(user.getUserId());
	                 break;

	             case 4:
	                 System.out.println("✅ Logged out.");
	                 return;  // Exit from the menu (log out)

	             default:
	                 System.out.println("❌ Invalid option. Try again.");  // Invalid option input
	         }
	     }
	 }
}