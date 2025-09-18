package com.Job_Portal.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employer")  
public class Employer {
	// Primary Key: Unique identifier for each employer
    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key.
    @Column(name = "emp_id") // Maps the field to the database column.
    private int empId; // Unique identifier for each employer (auto-generated).

    @Column(name = "company", length = 30) // Maps to the "company" column, with a max length of 30 characters.
    private String company; // The company name of the employer.

    @Column(name = "emp_first_name", length = 30) // Maps to the "emp_first_name" column, with a max length of 30 characters.
    private String empFirstName; // Employer's first name.

    @Column(name = "emp_last_name", length = 30) // Maps to the "emp_last_name" column, with a max length of 30 characters.
    private String empLastName; // Employer's last name.

    @Column(name = "emp_email", length = 50) // Maps to the "emp_email" column, with a max length of 50 characters.
    private String empEmail; // Employer's email address.

    @Column(name = "emp_phone", length = 50) // Maps to the "emp_phone" column, with a max length of 50 characters.
    private String empPhone; // Employer's phone number.

    @Column(name = "emp_address", length = 100) // Maps to the "emp_address" column, with a max length of 100 characters.
    private String empAddress; // Employer's address.

    @Column(name = "emp_password", length = 30) // Maps to the "emp_password" column, with a max length of 30 characters.
    private String password; // Employer's password (should be encrypted in practice).

    // Default Constructor
    public Employer() {}

    // Parameterized Constructor: Initializes an Employer object with the provided values.
    public Employer(String company, String empFirstName, String empLastName, 
                    String empEmail, String empPhone, String empAddress, String password) {
        this.company = company;
        this.empFirstName = empFirstName;
        this.empLastName = empLastName;
        this.empEmail = empEmail;
        this.empPhone = empPhone;
        this.empAddress = empAddress;
        this.password = password;
    }

    // Getters and Setters for each field. These allow for access and modification of the field values.
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString() method: Provides a string representation of the Employer object, 
    // which is useful for logging and debugging.
    @Override
    public String toString() {
        return "Employer [empId=" + empId + ", company=" + company + ", empFirstName=" + empFirstName +
                ", empLastName=" + empLastName + ", empEmail=" + empEmail + ", empPhone=" + empPhone +
                ", empAddress=" + empAddress + "]";
    }
}