package com.Job_Portal.Dao;

import java.util.List;

import org.hibernate.Session;

import com.Job_Portal.Entity.Employer;
import com.Job_Portal.Entity.Job;

//EmployerDao interface defines methods for managing Employer-related operations in the Online Job Portal application.
public interface EmployerDao {
	   //Methods
	   
	     // Saves the employer entity to the database. Typically used for initializing or persisting employer data.
	    void saveEmployer(Employer employer);
	    
	    // Inserts a new employer into the system.
	    void insertEmployer(Employer employer);
	    
	    // Finds an employer by their email address.
	    Employer findByEmail(String email);
	    
	    // Retrieves an employer by their ID.
	    Employer getEmployerById(int employerId);
	    
	    // Logs in an employer using their email and password.
	    Employer loginEmployer(String email, String password);
	    
	    // Retrieves an employer by email and password. Used for authentication purposes.
	    Employer getEmployerByEmailAndPassword(String email, String password);
	    
	    // Retrieves all employers from the database using a Hibernate session.
	    List<Employer> getAllEmployers(Session session);
	    
	    // Deletes the employer from the system.
	    void deleteEmployer(Employer employer);
	    
	    // Retrieves all jobs posted by a specific employer, based on employer ID.
	    List<Job> viewJobsByEmployer(int empId);
	    
	    // Retrieves all employers from the system.
	    List<Employer> getAllEmployers();
	
}