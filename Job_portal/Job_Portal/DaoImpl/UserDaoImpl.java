package com.Job_Portal.DaoImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.Job_Portal.Dao.UserDao;
import com.Job_Portal.Entity.Job;
import com.Job_Portal.Entity.User;
import com.Job_Portal.Util.HibernateUtil;

public class UserDaoImpl implements UserDao {
	
	private SessionFactory factory = HibernateUtil.getSessionFactory(); // SessionFactory to interact with Hibernate

    private Session session;

    // Constructor that accepts a Session
    public UserDaoImpl(Session session) {
        this.session = session;
    }

    // Save or update a user (for registration or updating user profile)
    @Override
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = factory.openSession()) {
            transaction = session.beginTransaction(); // Start transaction
            session.saveOrUpdate(user); // Saves the user if new or updates if already exists
            transaction.commit(); // Commit the transaction to persist the changes
        } catch (Exception e) {
            if (transaction != null) transaction.rollback(); // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Find a user based on email (used for checking if user already exists or login)
    @Override
    public User findByEmail(String email) {
        try (Session session = factory.openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email); // Set the email parameter for the query
            return query.uniqueResult(); // Return the user or null if not found
        }
    }

    // Delete a user by ID
    @Override
    public void deleteUser(int userId) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction(); // Start transaction
            User user = session.get(User.class, userId); // Get the user by ID
            if (user != null) {
                session.delete(user); // Delete the user if found
            }
            tx.commit(); // Commit the transaction
        } catch (Exception e) {
            if (tx != null) tx.rollback(); // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Retrieve all users and return as Set (ensures no duplicates)
    @Override
    public Set<User> getAllUsers() {
        try (Session session = factory.openSession()) {
            List<User> userList = session.createQuery("FROM User", User.class).getResultList(); // Retrieve all users
            return new HashSet<>(userList); // Convert List to Set and return
        }
    }

    // Get a user by their ID
    @Override
    public User getUserById(int userId) {
        try (Session session = factory.openSession()) {
            return session.get(User.class, userId); // Fetch user by ID
        }
    }

    // Delete a user by ID and return boolean for success or failure
    @Override
    public boolean deleteUserById(int userId) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, userId);
        if (user != null) {
            session.delete(user); // Delete user if found
            tx.commit(); // Commit the transaction
            session.close(); // Close the session
            return true; // Successfully deleted
        }
        session.close(); // Close the session if user not found
        return false; // Return false if user not found
    }

    // Update user details
    @Override
    public void updateUser(User user) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction(); // Start transaction
            session.update(user); // Update the user details
            tx.commit(); // Commit the transaction to persist the changes
        } catch (Exception e) {
            if (tx != null) tx.rollback(); // Rollback in case of error
            e.printStackTrace();
        }
    }

    // Method to apply a user to a job
    @Override
    public void applyToJob(int userId, int jobId) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            // Fetch user and job from the database
            User user = session.get(User.class, userId);
            Job job = session.get(Job.class, jobId);

            if (user != null && job != null) {
                // Check if user has already applied to the job
                if (user.getAppliedJobs().contains(job)) {
                    System.out.println("❌ You have already applied for this job."); // Inform user they already applied
                } else {
                    // Add job to appliedJobs set
                    user.getAppliedJobs().add(job);
                    session.update(user); // Update user to reflect the new applied job
                    tx.commit(); // Commit the transaction
                    System.out.println("✅ Job successfully applied."); // Inform user of successful application
                }
            } else {
                System.out.println("❌ User or Job not found."); // Inform if user or job doesn't exist
                tx.rollback(); // Rollback transaction if error occurs
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print any exceptions encountered
        }
    }

    // Authenticate user by checking email and password
    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();

            // Create query to get user by email and password
            User user = (User) session.createQuery("FROM User WHERE email = :email AND password = :password")
                                      .setParameter("email", email)
                                      .setParameter("password", password)
                                      .uniqueResult();

            tx.commit(); // Commit the transaction
            return user; // Return the authenticated user
        }
    }
}