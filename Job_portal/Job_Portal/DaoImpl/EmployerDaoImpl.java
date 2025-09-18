package com.Job_Portal.DaoImpl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Job_Portal.Dao.EmployerDao;
import com.Job_Portal.Entity.Employer;
import com.Job_Portal.Entity.Job;
import com.Job_Portal.Util.HibernateUtil;

public class EmployerDaoImpl implements EmployerDao {
	private SessionFactory sessionFactory;

    // Constructor that initializes sessionFactory using HibernateUtil
    public EmployerDaoImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();  // Get session factory from HibernateUtil
    }

    // Saves an Employer to the database (used for both creating new and updating existing employers)
    @Override
    public void saveEmployer(Employer employer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Begin a transaction
            session.save(employer); // Save employer to the database
            transaction.commit(); // Commit the transaction to persist changes
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback in case of an error
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    // Finds and returns an Employer by their email (used for login and validation)
    @Override
    public Employer findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Create an HQL query to find employer by email
            Query<Employer> query = session.createQuery("FROM Employer WHERE email = :email", Employer.class);
            query.setParameter("email", email); // Set the email parameter
            return query.uniqueResult(); // Return the unique result (or null if not found)
        }
    }

    // Retrieves all employers from the system (useful for admin viewing all employers)
    @Override
    public List<Employer> getAllEmployers(Session session) {
        // Create an HQL query to get all employers
        Query<Employer> query = session.createQuery("FROM Employer", Employer.class);
        return query.getResultList(); // Return list of all employers
    }

    // Retrieves an employer by their employer ID
    @Override
    public Employer getEmployerById(int employerId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Retrieve employer by ID using Hibernate's get method
            return session.get(Employer.class, employerId); 
        }
    }

    // This method is redundant as it essentially calls `findByEmail`, so it's commented out.
    /*
    @Override
    public Employer getEmployerByEmail(String email) {
        return findByEmail(email); // This is redundant, so it's commented out.
    }
    */

    // Authenticates an employer based on email and password (used for login)
    @Override
    public Employer loginEmployer(String email, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Create an HQL query to authenticate employer based on email and password
            Query<Employer> query = session.createQuery("FROM Employer WHERE empEmail = :email AND password = :password", Employer.class);
            query.setParameter("email", email); // Set the email parameter
            query.setParameter("password", password); // Set the password parameter
            return query.uniqueResult(); // Return the employer if credentials match, or null
        } catch (Exception e) {
            e.printStackTrace(); // Print exception details for debugging
        }
        return null; // Return null if no matching employer found
    }

    // Retrieves an employer by their email and password for authentication purposes
    @Override
    public Employer getEmployerByEmailAndPassword(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession(); // Open session
        Employer employer = null;
        try {
            // Create an HQL query to fetch employer based on email and password
            Query<Employer> query = session.createQuery("FROM Employer WHERE empEmail = :email AND password = :password", Employer.class);
            query.setParameter("email", email); // Set email parameter
            query.setParameter("password", password); // Set password parameter
            employer = query.uniqueResult(); // Get the employer object
        } catch (Exception e) {
            e.printStackTrace(); // Print exception details for debugging
        } finally {
            session.close(); // Always close the session
        }
        return employer; // Return employer object if found, otherwise null
    }

    // Inserts a new employer into the system (similar to `saveEmployer`, but this method seems more specific)
    @Override
    public void insertEmployer(Employer employer) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start transaction
            session.save(employer);  // Save the employer object
            transaction.commit(); // Commit the transaction
            System.out.println("Employer inserted successfully."); // Log success message
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if any exception occurs
            e.printStackTrace(); // Print exception details
        }
    }

    // Deletes an employer from the system
    @Override
    public void deleteEmployer(Employer employer) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); // Start transaction
            session.delete(employer); // Delete the employer from the database
            tx.commit(); // Commit the transaction
        } catch (Exception e) {
            if (tx != null) tx.rollback(); // Rollback in case of error
            e.printStackTrace(); // Print exception details
        }
    }

    // Retrieves all jobs posted by a specific employer (by their employer ID)
    @Override
    public List<Job> viewJobsByEmployer(int employerId) {
        Transaction tx = null;
        List<Job> jobs = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction(); // Start transaction

            // Create HQL query to get jobs by employer ID
            Query<Job> query = session.createQuery("FROM Job j WHERE j.employer.empId = :eid", Job.class);
            query.setParameter("eid", employerId); // Set employer ID parameter
            jobs = query.list(); // Get list of jobs posted by the employer

            tx.commit(); // Commit transaction
        } catch (Exception e) {
            if (tx != null) tx.rollback(); // Rollback if an error occurs
            e.printStackTrace(); // Print exception details
        }
        return jobs; // Return the list of jobs
    }

    // Retrieves all employers from the system (returns a list of all employers)
    @Override
    public List<Employer> getAllEmployers() {
        Session session = sessionFactory.openSession();
        List<Employer> employers = session.createQuery("FROM Employer", Employer.class).list(); // Create and execute HQL query
        session.close(); // Close session
        return employers; // Return list of all employers
    }
}

