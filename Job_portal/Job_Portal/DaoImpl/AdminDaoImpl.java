package com.Job_Portal.DaoImpl;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Job_Portal.Dao.AdminDao;
import com.Job_Portal.Entity.Admin;
import com.Job_Portal.Entity.Job;
import com.Job_Portal.Entity.User;
import com.Job_Portal.Util.HibernateUtil;

public class AdminDaoImpl implements AdminDao{
	 // Admin login logic. Verifies the admin credentials based on email and password.
    @Override
    public boolean adminLogin(String email, String password) {
        Admin admin = getAdminByEmailAndPassword(email, password); // Fetch the admin by email and password
        return admin != null; // Returns true if admin exists, otherwise false
    }

    // Approves a job by setting its 'approved' status to true.
    @Override
    public void approveJob(int jobId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start transaction
            Job job = session.get(Job.class, jobId); // Fetch job by jobId
            if (job != null) {
                job.setApproved(true); // Set job's status to approved
                session.update(job); // Update the job in the database
            }
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if any exception occurs
            e.printStackTrace(); // Print exception stack trace for debugging
        }
    }

    // Deletes a job from the system based on its jobId.
    @Override
    public void deleteJob(int jobId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start transaction
            Job job = session.get(Job.class, jobId); // Fetch the job by jobId
            if (job != null) {
                session.delete(job); // Delete the job
            }
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if any exception occurs
            e.printStackTrace(); // Print exception stack trace for debugging
        }
    }

    // Deletes a user from the system based on their userId.
    @Override
    public void deleteUser(int userId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start transaction
            User user = session.get(User.class, userId); // Fetch user by userId
            if (user != null) {
                session.delete(user); // Delete the user
            }
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if any exception occurs
            e.printStackTrace(); // Print exception stack trace for debugging
        }
    }

    // Saves a new admin to the database. Calls registerAdmin method for registration.
    @Override
    public void saveAdmin(Admin admin) {
        registerAdmin(admin); // Register a new admin using the registerAdmin method
    }

    // Registers a new admin by saving the admin object to the database.
    @Override
    public void registerAdmin(Admin admin) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start transaction
            session.save(admin); // Save the admin entity to the database
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback if any exception occurs
            e.printStackTrace(); // Print exception stack trace for debugging
        }
    }

    // Retrieves an admin based on their email.
    @Override
    public Admin findByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Admin> query = session.createQuery("FROM Admin WHERE email = :email", Admin.class); // HQL query to fetch admin by email
            query.setParameter("email", email); // Set email parameter
            return query.uniqueResult(); // Return the unique result (single admin object)
        }
    }

    // Retrieves an admin by both email and password for login authentication.
    @Override
    public Admin getAdminByEmailAndPassword(String email, String password) {
        Transaction tx = null;
        Admin admin = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction(); // Start transaction

            // HQL query to fetch admin by both email and password
            String hql = "FROM Admin WHERE adEmail = :email AND password = :password";
            Query<Admin> query = session.createQuery(hql, Admin.class); 
            query.setParameter("email", email); // Set email parameter
            query.setParameter("password", password); // Set password parameter

            admin = query.uniqueResult(); // Get the unique result (admin object)
            tx.commit(); // Commit the transaction
        } catch (Exception e) {
            if (tx != null) tx.rollback(); // Rollback if any exception occurs
            e.printStackTrace(); // Print exception stack trace for debugging
        }
        return admin; // Return the admin object if found, otherwise null
    }
}