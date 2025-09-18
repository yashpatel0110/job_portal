package com.Job_Portal.Dao;


import java.util.Set;

import com.Job_Portal.Entity.User;

public interface UserDao {
	//Methods
	
	// Deletes a user from the system based on their user ID. This method removes a user from the database.
    public boolean deleteUserById(int userId);

    // Updates the details of an existing user profile. This method is used when a user wants to modify their information.
    public void updateUser(User user);

    // Saves a new user to the system, typically used during user registration. It inserts a new user into the database.
    void saveUser(User user);

    // Deletes a user from the system based on their user ID. This is a duplicate of `deleteUserById`, so it might be worth consolidating them.
    void deleteUser(int userId);

    // Handles job application logic where a user applies to a specific job. This links the user with the job they apply to.
    void applyToJob(int userId, int jobId);

    // Retrieves a user by their email. This is useful for login authentication or checking if an email already exists.
    User findByEmail(String email);

    // Fetches a user by their ID. This allows retrieval of user details using their unique identifier.
    User getUserById(int userId);

    // Retrieves a user by both their email and password. This is used for user login authentication.
    User getUserByEmailAndPassword(String email, String password);

    // Retrieves all users in the system. This can be useful for administrative tasks, such as viewing all registered users.
    Set<User> getAllUsers();
}