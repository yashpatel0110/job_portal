package com.Job_Portal.ServiceImpl;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.Job_Portal.Dao.UserDao;
import com.Job_Portal.DaoImpl.JobDaoImpl;
import com.Job_Portal.DaoImpl.UserDaoImpl;
import com.Job_Portal.Entity.Job;
import com.Job_Portal.Entity.User;
import com.Job_Portal.Service.JobService;
import com.Job_Portal.Service.UserService;
import com.Job_Portal.Util.HibernateUtil;
import com.Job_Portal.Dao.JobDao;


public class UserServiceImpl implements UserService  {
	
	private Session session;
	private SessionFactory sessionFactory;
	private UserDao userDao;
	private JobDao jobDao;  

   
	// Constructor: Initializes SessionFactory, Session, and DAOs
    public UserServiceImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory(); // Initialize SessionFactory
        this.session = sessionFactory.openSession();             // Open a new Session
        this.userDao = new UserDaoImpl(session);                 // Initialize UserDao with session
        this.jobDao = new JobDaoImpl(session);                   // Initialize JobDao with session
    }

    // Save user to the database
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    // Find a user by their email
    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    // Delete a user by userId
    @Override
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }

    // Register a new user (alias of saveUser)
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    // Authenticate a user using email and password
    @Override
    public User login(String email, String password) {
        return userDao.getUserByEmailAndPassword(email, password);
    }

    // Apply a user to a job (old method, not used anymore if applyForJob is preferred)
    @Override
    public void applyToJob(int userId, int jobId) {
        userDao.applyToJob(userId, jobId);
    }

    // Get all users from the system
    @Override
    public Set<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    // Get a specific user by their userId
    @Override
    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    // Another method to authenticate user (same as login)
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return userDao.getUserByEmailAndPassword(email, password);
    }

    // View all users with print output
    @Override
    public void viewAllUsers() {
        Set<User> users = userDao.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("⚠️ No users found.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    // View jobs that a user has applied to
    @Override
    public void viewAppliedJobs(int userId) {
        User user = userDao.getUserById(userId);
        if (user != null) {
            Set<Job> jobs = user.getAppliedJobs();
            if (jobs.isEmpty()) {
                System.out.println("⚠️ You haven't applied for any jobs yet.");
            } else {
                System.out.println("===== APPLIED JOBS =====");
                for (Job job : jobs) {
                    // Print job details
                    System.out.println("Job ID: " + job.getJobId());
                    System.out.println("Title: " + job.getTitle());
                    System.out.println("Company: " + job.getCompanyName());
                    System.out.println("Location: " + job.getLocation());
                    System.out.println("Category: " + job.getCategory());
                    System.out.println("Employment Type: " + job.getEmploymentStatus());
                    System.out.println("Salary: " + job.getSalary());
                    System.out.println("Experience: " + job.getExperience());
                    System.out.println("Education: " + job.getEducation());
                    System.out.println("Deadline: " + job.getDeadline());
                    System.out.println("Approved: " + (job.isApproved() ? "Yes" : "No"));
                    System.out.println("---------------------------");
                }
            }
        } else {
            System.out.println("❌ User not found.");
        }
    }

    // Get all jobs (used for listing)
    @Override
    public List<Job> getAllJobs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Job", Job.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    // Get only approved jobs
    @Override
    public Set<Job> getAllApprovedJobs() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Job WHERE approved = true";
            List<Job> jobs = session.createQuery(hql, Job.class).list();
            return new HashSet<>(jobs); // Convert to Set for uniqueness
        }
    }

    // New method to apply for a job with duplicate-checking
    @Override
    public void applyForJob(int userId, int jobId) {
        Job job = jobDao.getJobById(jobId);
        if (job == null) {
            System.out.println("❌ Job not found.");
            return;
        }

        User user = userDao.getUserById(userId);
        if (user.getAppliedJobs().contains(job)) {
            System.out.println("⚠️ You have already applied for this job.");
            return;
        }

        user.getAppliedJobs().add(job);      // Add job to user
        jobDao.saveJob(job);                 // Save job (if updated)
        userDao.saveUser(user);              // Save user with updated job

        System.out.println("✅ Successfully applied for the job!");
    }

    // Delete user with applied job cleanup
    @Override
    public void deleteUserById(int userId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                // Detach user from all applied jobs
                for (Job job : user.getAppliedJobs()) {
                    job.getUsers().remove(user);  // Remove user from each job
                }
                user.getAppliedJobs().clear();     // Clear user's applied job list

                session.delete(user);              // Delete user
                tx.commit();
                System.out.println("✅ User deleted successfully.");
            } else {
                System.out.println("⚠️ User not found.");
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback(); // Rollback on error
            e.printStackTrace();
        } finally {
            session.close(); // Always close the session
        }
    }
}