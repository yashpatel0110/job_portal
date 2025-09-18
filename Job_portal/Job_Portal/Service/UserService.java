package com.Job_Portal.Service;


import java.util.List;
import java.util.Set;

import com.Job_Portal.Entity.Job;
import com.Job_Portal.Entity.User;

public interface UserService {
	
	 // Views all the users in the system
    public void viewAllUsers();

    // Deletes a user by their ID
    public void deleteUserById(int userId);

    // Views all the jobs that a specific user has applied for by their ID
    public void viewAppliedJobs(int userId);

    // Allows a user to apply for a job by job ID
    public void applyForJob(int userId, int jobId);

    // Saves or updates the user data in the system
    void saveUser(User user);

    // Registers a new user in the system
    void registerUser(User user);

    // Allows a user to apply for a job by job ID
    void applyToJob(int userId, int jobId);

    // Deletes a user by their ID
    void deleteUser(int userId);

    // Finds a user by their email
    User findByEmail(String email);

    // Logs in a user by their email and password
    User login(String email, String password);

    // Retrieves a user by their unique ID
    User getUserById(int userId);

    // Retrieves a user by their email and password for login purposes
    User getUserByEmailAndPassword(String email, String password);

    // Retrieves all jobs available in the system
    List<Job> getAllJobs();

    // Retrieves all users in the system as a set
    Set<User> getAllUsers();
    
    // Retrieves all Jobs in the system as a set
    Set<Job> getAllApprovedJobs();
}