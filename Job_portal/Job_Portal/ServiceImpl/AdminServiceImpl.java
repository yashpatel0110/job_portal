package com.Job_Portal.ServiceImpl;

import com.Job_Portal.Entity.Admin;
import com.Job_Portal.Service.AdminService;
import com.Job_Portal.Util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.Job_Portal.Dao.AdminDao;
import com.Job_Portal.DaoImpl.AdminDaoImpl;

public class AdminServiceImpl implements AdminService {
	

	// Declare an instance of AdminDao to interact with the database
    private AdminDao adminDao = new AdminDaoImpl();

    // Method to log in an admin by validating email and password
    @Override
    public boolean login(String email, String password) {
        return adminDao.adminLogin(email, password);  // Delegates to AdminDao for login validation
    }

    // Method to approve a job by its jobId
    @Override
    public void approveJob(int jobId) {
        adminDao.approveJob(jobId);  // Calls AdminDao to approve the job
    }

    // Method to delete a job by its jobId
    @Override
    public void deleteJob(int jobId) {
        adminDao.deleteJob(jobId);  // Delegates the deletion of the job to AdminDao
    }

    // Method to delete a user by their userId
    @Override
    public void deleteUser(int userId) {
        adminDao.deleteUser(userId);  // Calls AdminDao to delete the user
    }

    // Method to save an admin's information (e.g., when creating a new admin)
    @Override
    public void saveAdmin(Admin admin) {
        adminDao.saveAdmin(admin);  // Delegates saving the admin data to AdminDao
    }

    // Method to register a new admin in the system
    @Override
    public void registerAdmin(Admin admin) {
        adminDao.registerAdmin(admin);  // Delegates admin registration to AdminDao
    }

    // Method to retrieve an admin using their email and password for login
    @Override
    public Admin getAdminByEmailAndPassword(String email, String password) {
        return adminDao.getAdminByEmailAndPassword(email, password);  // Calls AdminDao for authentication
    }
    
    // Method to log in an admin by their email and password
    @Override
    public Admin loginAdmin(String email, String password) {
        Admin admin = adminDao.findByEmail(email);  // Finds the admin by email using AdminDao
        if (admin != null && admin.getPassword().equals(password)) {  // Checks if password matches
            return admin;  // If valid, return the admin object
        }
        return null;  // If login fails, return null
    }
   

}