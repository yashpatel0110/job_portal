package com.Job_Portal.Service;

import java.util.List;
import java.util.Set;

import com.Job_Portal.Entity.Job;
import com.Job_Portal.Entity.User;

public interface JobService {
	// Adds a new job to the database
    void addJob(Job job);

    // Deletes a job by its ID
    void deleteJob(int jobId);

    // Saves or updates a job in the database
    void saveJob(Job job);

    // Approves a job, typically by an admin
    void approveJob(int jobId);

    // Posts a job to the system, usually by an employer
    void postJob(Job job);

    // Deletes a job using its ID
    void deleteJobById(int jobId);

    // Retrieves a list of jobs posted by a company name
    List<Job> getJobsByEmployer(String companyName);

    // Retrieves all jobs from the system
    List<Job> getAllJobs();

    // Displays all jobs in the system
    List<Job> viewAllJobs();

    // Displays only the jobs that have been approved
    List<Job> viewAllApprovedJobs();

    // Retrieves a job by its ID
    Job getJobById(int jobId);

    // Returns a set of jobs posted by a specific employer ID
    Set<Job> getJobsByEmployerId(int employerId);

    // Returns all jobs that have been approved
    Set<Job> getApprovedJobs();

    // Returns all jobs that are pending approval
    Set<Job> getPendingJobs();

    // Returns all approved jobs as a set
    Set<Job> getAllApprovedJobs();

    // Updates the details of an existing job
    void updateJob(Job job);
    
    // Allows a user to apply for a job by job ID
    void applyForJob(int userId, int jobId);
}