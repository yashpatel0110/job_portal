package com.Job_Portal.Dao;

import com.Job_Portal.Entity.Admin;


/*AdminDao interface provides abstract methods for performing CRUD operations and business logic related to Admin functionality
 in the Online Job Portal application.*/
public interface AdminDao {
	  //Methods
	
	 // Method to authenticate an admin by verifying their email and password.
    boolean adminLogin(String email, String password);
    
    // Method to approve a job by setting its status to "approved."
    void approveJob(int jobId);
    
    // Method to delete a job from the system using its job ID.
    void deleteJob(int jobId);
    
    // Method to delete a user account from the system based on the user's ID.
    void deleteUser(int userId);
    
    // Method to save the Admin object to the database. This is typically used for initializing or inserting admin data.
    void saveAdmin(Admin admin);
    
    // Method to register a new admin account. It is used for adding a new admin to the system.
    void registerAdmin(Admin admin);
    
    // Method to find an Admin entity by their email. Useful for retrieving an admin based on email (usually for login or validation).
    Admin findByEmail(String email);
    
    // Method to retrieve an Admin entity using both email and password. This is mainly used for login authentication.
    Admin getAdminByEmailAndPassword(String email, String password);
}