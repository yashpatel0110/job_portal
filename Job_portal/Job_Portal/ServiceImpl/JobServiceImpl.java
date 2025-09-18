package com.Job_Portal.ServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.Job_Portal.Dao.JobDao;
import com.Job_Portal.DaoImpl.JobDaoImpl;
import com.Job_Portal.Entity.Job;
import com.Job_Portal.Entity.User;
import com.Job_Portal.Dao.UserDao;

import com.Job_Portal.Service.JobService;
import com.Job_Portal.Util.HibernateUtil;

public class JobServiceImpl implements JobService {
	
	 private SessionFactory sessionFactory; // Hibernate SessionFactory (not initialized here)
	    private JobDao jobDao;                 // JobDao instance to perform DB operations
	    private Session session;              // Hibernate Session passed via constructor

	    // Constructor that accepts a Hibernate session and initializes the DAO
	    public JobServiceImpl(Session session) {
	        this.session = session;
	        this.jobDao = new JobDaoImpl(session);
  }
    // Method to add a new job
    @Override
    public void addJob(Job job) {
        jobDao.addJob(job);  // Delegates to JobDao to add a new job
    }

    // Method to get all jobs
    @Override
    public List<Job> getAllJobs() {
        return jobDao.getAllJobs();  // Retrieves all jobs from JobDao
    }

    // Method to delete a job by its ID
    @Override
    public void deleteJob(int jobId) {
        jobDao.deleteJob(jobId);  // Deletes a job by ID using JobDao
    }

    // Method to save or update a job
    @Override
    public void saveJob(Job job) {
        jobDao.saveJob(job);  // Saves or updates the job using JobDao
    }

    // Method to update an existing job's information
    @Override
    public void updateJob(Job job) {
        jobDao.updateJob(job);  // Updates the job information using JobDao
    }

    // Method to get jobs by employer's company name
    @Override
    public List<Job> getJobsByEmployer(String companyName) {
        return jobDao.getJobsByEmployer(companyName);  // Retrieves jobs by employer's company name using JobDao
    }

    // Method to get a job by its ID
    @Override
    public Job getJobById(int jobId) {
        return jobDao.getJobById(jobId);  // Retrieves a job by ID from JobDao
    }

    // Method to get jobs posted by an employer using their employer ID
    @Override
    public Set<Job> getJobsByEmployerId(int employerId) {
        return jobDao.getJobsByEmployerId(employerId);  // Retrieves jobs by employer's ID using JobDao
    }

    // Method to post a new job
    @Override
    public void postJob(Job job) {
        jobDao.postJob(job);  // Posts the job using JobDao
    }

    // Method to get all approved jobs
    @Override
    public Set<Job> getApprovedJobs() {
        return jobDao.getAllApprovedJobs();  // Retrieves all approved jobs from JobDao
    }

    // Method to get all pending jobs
    @Override
    public Set<Job> getPendingJobs() {
        return jobDao.getAllPendingJobs();  // Retrieves all pending jobs from JobDao
    }

    // Method to view all approved jobs
    @Override
    public List<Job> viewAllApprovedJobs() {
        return jobDao.viewAllApprovedJobs();  // Retrieves all approved jobs from JobDao
    }

    // Method to view all jobs with information like job title and approval status
    @Override
    public List<Job> viewAllJobs() {
        List<Job> jobs = jobDao.getAllJobs();  // Retrieves all jobs from JobDao

        if (jobs == null || jobs.isEmpty()) {
            System.out.println("❌ No jobs found.");  // Prints a message if no jobs are found
            return jobs;
        }

        System.out.println("===== All Jobs =====");
        for (Job job : jobs) {
            System.out.println("ID: " + job.getJobId() +
                               ", Title: " + job.getTitle() +
                               ", Approved: " + job.isApproved());  // Prints the job details
        }

        return jobs;
    }

    // Method to approve a job by its ID
    @Override
    public void approveJob(int jobId) {
        Job job = jobDao.getJobById(jobId);  // Retrieves the job by ID from JobDao
        if (job != null) {
            if (!job.isApproved()) {
                job.setApproved(true);  // Sets the job as approved
                jobDao.updateJob(job);  // Updates the job status in the database
                System.out.println("✅ Job with ID " + jobId + " approved successfully.");  // Confirmation message
            } else {
                System.out.println("⚠️ Job with ID " + jobId + " is already approved.");  // If already approved
            }
        } else {
            System.out.println("❌ Job with ID " + jobId + " not found.");  // If job not found
        }
    }

    // Method to delete a job by its ID
    @Override
    public void deleteJobById(int jobId) {
        Job job = jobDao.getJobById(jobId);  // Retrieves the job by ID from JobDao
        if (job != null) {
            jobDao.deleteJob(job);  // Deletes the job from the database
            System.out.println("✅ Job deleted successfully.");  // Confirmation message
        } else {
            System.out.println("❌ No job found with ID: " + jobId);  // If job not found
        }
    }

    // Method to get all approved jobs (again, similar to getApprovedJobs)
    @Override
    public Set<Job> getAllApprovedJobs() {
        return jobDao.getAllApprovedJobs();  // Retrieves all approved jobs from JobDao
    }

    // Method for a user to apply for a job
    @Override
    public void applyForJob(int userId, int jobId) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            User user = session.get(User.class, userId);
            Job job = session.get(Job.class, jobId);

            if (user == null || job == null) {
                System.out.println("❌ User or Job not found.");
                return;
            }

            if (LocalDate.now().isAfter(job.getDeadline())) {
                System.out.println("⚠️ Cannot apply. Deadline has passed.");
                return;
            }

            job.setApplyDate(LocalDate.now());
            user.getAppliedJobs().add(job);

            session.update(user);
            tx.commit();

            System.out.println("✅ Applied successfully on: " + job.getApplyDate());

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        
        
    }
   
}