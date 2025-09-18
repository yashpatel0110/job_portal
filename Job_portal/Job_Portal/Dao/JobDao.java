package com.Job_Portal.Dao;

import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;

import com.Job_Portal.Entity.Job;

public interface JobDao {
	
	//Methods
	
	// Adds a new job to the system. This method is used to insert a job into the system.
    void addJob(Job job); 

    // Deletes a job from the system by its job ID. This method removes a job using its unique identifier.
    void deleteJob(int jobId);

    // Saves a new or updated job to the database. It can be used for both adding a new job or updating an existing one.
    void saveJob(Job job);

    // Approves a job by setting its status to "approved". This method is used to mark a job as approved in the system.
    void approveJob(int jobId);

    // Posts a new job to the system. This is equivalent to the `addJob` method, used to insert a new job.
    void postJob(Job job); 

    // Deletes a job from the system using the job object. This method removes a job from the system by passing the job entity directly.
    void deleteJob(Job job); 

    // Updates the details of an existing job in the system. This method allows modifying job information.
    void updateJob(Job job);

    // Retrieves a list of all jobs available in the system. This method returns all jobs, regardless of their status (approved/pending).
    List<Job> getAllJobs();

    // Retrieves all jobs posted by a specific employer, identified by their company name. This method fetches jobs based on the employer's company name.
    List<Job> getJobsByEmployer(String companyName);

    // Retrieves a list of all approved jobs in the system. This method returns only jobs that are marked as approved.
    List<Job> viewAllApprovedJobs();

    // Retrieves a list of all jobs in the system, including both approved and pending jobs. It provides all available jobs, regardless of status.
    List<Job> viewAllJobs(); 

    // Retrieves a job by its ID. This method fetches a single job entity using its unique identifier.
    Job getJobById(int jobId);

    // Retrieves jobs posted by a specific employer, identified by their employer ID. This method returns jobs associated with a given employer ID.
    Set<Job> getJobsByEmployerId(int employerId);

    // Retrieves a set of approved jobs in the system. This method returns only the jobs that are approved.
    Set<Job> getApprovedJobs();

    // Retrieves a set of all pending jobs in the system. This method returns all jobs that are yet to be approved.
    Set<Job> getAllPendingJobs();

    // Retrieves a set of all approved jobs. This method might overlap with `getApprovedJobs` if they both return the same data.
    Set<Job> getAllApprovedJobs();
}