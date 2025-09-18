package com.Job_Portal.DaoImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Job_Portal.Dao.JobDao;
import com.Job_Portal.Entity.Job;

import com.Job_Portal.Util.HibernateUtil;

public class JobDaoImpl implements JobDao {
	// SessionFactory instance for creating Session objects
    private SessionFactory factory = HibernateUtil.getSessionFactory();

    // Optional Session object for use in case of constructor injection
    private Session session;

    // Constructor accepting a session (used for dependency injection or testing)
    public JobDaoImpl(Session session) {
        this.session = session;
    }

    // Default constructor initializes session from HibernateUtil
    public JobDaoImpl() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    // Save or update a Job object in the database
    @Override
    public void saveJob(Job job) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin transaction
            session.saveOrUpdate(job);                // Save or update the job
            transaction.commit();                     // Commit changes
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback on error
            e.printStackTrace();                             // Log exception
        }
    }

    // Delete a job from the database using its jobId
    @Override
    public void deleteJob(int jobId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Job job = session.get(Job.class, jobId);     // Fetch job by ID
            if (job != null) {
                session.delete(job);                    // Delete if exists
            }
            transaction.commit();                        // Commit changes
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Approve a job by setting its 'approved' field to true
    @Override
    public void approveJob(int jobId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Job job = session.get(Job.class, jobId);     // Fetch job by ID
            if (job != null) {
                job.setApproved(true);                   // Set approved flag
                session.update(job);                     // Update job
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    // Get all jobs posted by a specific company using company name
    @Override
    public List<Job> getJobsByEmployer(String companyName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Job> query = session.createQuery("FROM Job WHERE companyName = :companyName", Job.class);
            query.setParameter("companyName", companyName);
            return query.list();  // Return result list
        }
    }

    // Get all jobs posted by an employer using employerId
    @Override
    public Set<Job> getJobsByEmployerId(int employerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Job> query = session.createQuery("FROM Job WHERE employer.empId = :empId", Job.class);
            query.setParameter("empId", employerId);
            List<Job> jobs = query.list();             // Convert List to Set
            return new HashSet<>(jobs);
        }
    }

    // Post a new job to the system
    @Override
    public void postJob(Job job) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(job);                         // Save job to DB
            tx.commit();                                // Commit changes
            System.out.println("✅ Job posted successfully!");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Retrieve all jobs that are pending (not approved yet)
    @Override
    public Set<Job> getAllPendingJobs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Job> jobs = session.createQuery("FROM Job WHERE approved = false", Job.class).getResultList();
            return new HashSet<>(jobs); // Convert List to Set
        }
    }

    /*
    // (Optional) Delete job by ID and return success status
    @Override
    public boolean deleteJobById(int jobId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Job job = session.get(Job.class, jobId);
            if (job != null) {
                session.delete(job);
                tx.commit();
                return true;
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return false;
    }
    */

    // View all jobs that have been approved
    @Override
    public List<Job> viewAllApprovedJobs() {
        List<Job> approvedJobs = new ArrayList<>();
        try (Session session = factory.openSession()) {
            String hql = "FROM Job WHERE approved = true";
            approvedJobs = session.createQuery(hql, Job.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvedJobs;
    }

    // Alias method for posting job (can be renamed to postJob)
    @Override
    public void addJob(Job job) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.save(job);
            tx.commit();
            System.out.println("✅ Job posted successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // View all jobs in the system (approved or not)
    @Override
    public List<Job> viewAllJobs() {
        Transaction tx = null;
        List<Job> jobs = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            jobs = session.createQuery("from Job", Job.class).getResultList();
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                try {
                    tx.rollback();
                } catch (Exception rollbackEx) {
                    System.err.println("Rollback failed: " + rollbackEx.getMessage());
                }
            }
            e.printStackTrace();
        }
        return jobs;
    }

    // Get all jobs without any filters
    @Override
    public List<Job> getAllJobs() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Job> jobs = session.createQuery("FROM Job", Job.class).list();
        session.close(); // Close session to prevent memory leaks
        return jobs;
    }

    // Update an existing job in the database
    @Override
    public void updateJob(Job job) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(job);
            tx.commit();
            System.out.println("✅ Job updated successfully.");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
 // Delete a job by passing the Job object
    @Override
    public void deleteJob(Job job) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Job persistentJob = session.get(Job.class, job.getJobId());
            if (persistentJob != null) {
                session.delete(persistentJob);
                System.out.println("✅ Job deleted successfully.");
            } else {
                System.out.println("❌ Job not found.");
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Retrieve a job by its unique jobId
    @Override
    public Job getJobById(int jobId) {
        Transaction tx = null;
        Job job = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            job = session.get(Job.class, jobId);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return job;
    }

    // Get all approved jobs where approved flag is true
    @Override
    public Set<Job> getApprovedJobs() {
        Set<Job> approvedJobs = new HashSet<>();
        try {
            String hql = "FROM Job WHERE approved = true";
            Query<Job> query = session.createQuery(hql, Job.class);
            approvedJobs = new HashSet<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return approvedJobs;
    }
    
    // Get all approved jobs where status = 'approved' (string)
    @Override
    public Set<Job> getAllApprovedJobs() {
        Set<Job> jobs = new HashSet<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Only fetch jobs where approved = true
            Query<Job> query = session.createQuery("FROM Job WHERE approved = true", Job.class);
            jobs = new HashSet<>(query.getResultList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jobs;
    }
}
