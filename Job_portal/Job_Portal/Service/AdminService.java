package com.Job_Portal.Service;

import com.Job_Portal.Entity.Admin;

public interface AdminService {
	
	 // Authenticates the admin using the provided email and password
    boolean login(String email, String password);

    // Saves the admin details into the database
    void saveAdmin(Admin admin);

    // Registers a new admin into the system
    void registerAdmin(Admin admin);

    // Approves a job so it becomes visible to users
    void approveJob(int jobId);

    // Deletes a job from the system using its ID
    void deleteJob(int jobId);

    // Deletes a user from the system using their ID
    void deleteUser(int userId);

    // Retrieves an admin based on email and password
    Admin getAdminByEmailAndPassword(String email, String password);

    // Logs in an admin and returns the admin object if credentials are valid
    Admin loginAdmin(String email, String password);
   
}