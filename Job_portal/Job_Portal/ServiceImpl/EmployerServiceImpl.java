package com.Job_Portal.ServiceImpl;

import java.util.List;
import org.hibernate.Session;
import com.Job_Portal.Entity.Employer;
import com.Job_Portal.Entity.Job;
import com.Job_Portal.Service.EmployerService;
import com.Job_Portal.Util.HibernateUtil;
import com.Job_Portal.Dao.EmployerDao;
import com.Job_Portal.DaoImpl.EmployerDaoImpl;
import com.Job_Portal.DaoImpl.JobDaoImpl;
import com.Job_Portal.Dao.JobDao;

public class EmployerServiceImpl implements EmployerService {
	
	 // Declare an instance of EmployerDao to interact with the database
    private EmployerDao employerDao = new EmployerDaoImpl();
    // Declare an instance of JobDao for job-related actions
    private JobDao jobDao = new JobDaoImpl(); // Initialized jobDao here

    // Method to save or update an employer's information
    @Override
    public void saveEmployer(Employer employer) {
        employerDao.saveEmployer(employer);  // Calls EmployerDao to save the employer
    }

    // Method to find an employer by their email
    @Override
    public Employer findByEmail(String email) {
        return employerDao.findByEmail(email);  // Delegates to EmployerDao to find the employer by email
    }

    // Method to retrieve all employers from the database
    @Override
    public List<Employer> getAllEmployers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return employerDao.getAllEmployers(session);  // Calls EmployerDao to retrieve all employers
        }
    }

    // Method to register an employer by saving their information
    @Override
    public void registerEmployer(Employer employer) {
        employerDao.saveEmployer(employer);  // Calls EmployerDao to save the employer during registration
    }

    // Method to authenticate an employer's login with email and password
    @Override
    public Employer loginEmployer(String email, String password) {
        Employer employer = employerDao.findByEmail(email);  // Finds employer by email using EmployerDao
        if (employer != null && employer.getPassword().equals(password)) {  // Checks if the password matches
            return employer;  // If valid, return the employer object
        }
        return null;  // If login fails, return null
    }

    // Method to retrieve an employer by their ID
    @Override
    public Employer getEmployerById(int id) {
        return employerDao.getEmployerById(id);  // Calls EmployerDao to retrieve employer by ID
    }

    // Method to authenticate an employer's login using email and password (Alternative method)
    @Override
    public Employer login(String email, String password) {
        return employerDao.getEmployerByEmailAndPassword(email, password);  // Delegates to EmployerDao for login
    }

    // Method to retrieve an employer by email and password for authentication
    @Override
    public Employer getEmployerByEmailAndPassword(String email, String password) {
        return employerDao.getEmployerByEmailAndPassword(email, password);  // Delegates to EmployerDao for login
    }

    // Method to insert an employer's data into the database
    @Override
    public void insertEmployer(Employer employer) {
        employerDao.insertEmployer(employer);  // Calls EmployerDao to insert the employer
    }

    // Method to post a job by calling the addJob method in JobDao
    @Override
    public void postJob(Job job) {
        jobDao.addJob(job);  // Delegates to JobDao to add the job posting
    }

    // Method to view all jobs posted by a specific employer
    @Override
    public List<Job> viewJobsByEmployer(int employerId) {
        return employerDao.viewJobsByEmployer(employerId);  // Calls EmployerDao to get jobs by employer ID
    }
 //Method to Deletes an employer by their ID
    @Override
    public void deleteEmployerById(int empId) {
        Employer emp = employerDao.getEmployerById(empId);
        if (emp != null) {
            employerDao.deleteEmployer(emp);
        } else {
            System.out.println("❌ Employer not found with ID: " + empId);
        }
    }
   // Method to Retrieves all employers in the system
   // Useful for Admin dashboard or management reports
    @Override
    public List<Employer> viewAllEmployers() {
        return employerDao.getAllEmployers();
    }
    
  

}