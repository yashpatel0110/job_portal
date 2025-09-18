package com.Job_Portal.Service;

import java.util.List;

import com.Job_Portal.Entity.Employer;
import com.Job_Portal.Entity.Job;

public interface EmployerService {
	 // Saves employer details into the database
    void saveEmployer(Employer employer);

    // Registers a new employer into the system
    void registerEmployer(Employer employer);

    // Inserts an employer record into the system (alternative to save/register)
    void insertEmployer(Employer employer); 

    // Allows an employer to post a new job
    void postJob(Job job); 

    // Retrieves an employer using their email
    Employer findByEmail(String email);

    // Logs in an employer with email and password, returns the employer object
    Employer loginEmployer(String email, String password);

    // Retrieves an employer based on their unique ID
    Employer getEmployerById(int id);

    // Authenticates an employer using email and password, returns the employer object
    Employer login(String email, String password);

    // Retrieves an employer using both email and password
    Employer getEmployerByEmailAndPassword(String email, String password);

    // Returns a list of all employers in the system
    List<Employer> getAllEmployers();

    // Returns all jobs posted by a specific employer
    List<Job> viewJobsByEmployer(int employerId);
    
    //  Returns all employers - appears redundant with getAllEmployers()
    List<Employer> viewAllEmployers();
    
   //Deletes an employer from the system based on their ID
    void deleteEmployerById(int empId);
   
}