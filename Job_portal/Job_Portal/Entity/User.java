package com.Job_Portal.Entity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

@Entity
public class User {
	 // Primary Key: Unique identifier for each user
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private int userId;

	    @Column(name = "first_name", length = 30)
	    private String firstName;

	    @Column(name = "last_name", length = 30)
	    private String lastName;

	    @Column(name = "email", length = 30)
	    private String email;

	    @Column(name = "phone", length = 30)
	    private String phone;

	    @Column(name = "address",length = 100)
	    private String address;

	    @Column(name = "resume",length = 150)
	    private String resume;

	    @Column(name="user_password",length = 30)
	    private String password;

	    
	    @Transient // Not persisted by Hibernate
	    private Map<Integer, LocalDate> appliedJobDates = new HashMap<>();
	    
	    // ✅ FOREIGN KEYS: This join table holds foreign keys 
	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(name = "user_applied_jobs", 
	        joinColumns = @JoinColumn(name = "user_id"), 
	        inverseJoinColumns = @JoinColumn(name = "job_id"))
	    private Set<Job> appliedJobs = new HashSet<>();
	  
	    // Default Constructor
	    public User() {}

	    //Parameterized Constructor
	    public User(String firstName, String lastName, String email,
	                String phone, String address, String resume, String password) {
	        this.firstName = firstName;
	        this.lastName = lastName;
	        this.email = email;
	        this.phone = phone;
	        this.address = address;
	        this.resume = resume;
	        this.password = password;
	    }

	    //Getters and Setters
	    public int getUserId() {
	        return userId;
	    }

	    public void setUserId(int userId) {
	        this.userId = userId;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getResume() {
	        return resume;
	    }

	    public void setResume(String resume) {
	        this.resume = resume;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public Set<Job> getAppliedJobs() {
	        return appliedJobs;
	    }

	    public void setAppliedJobs(Set<Job> appliedJobs) {
	        this.appliedJobs = appliedJobs;
	    }

	    // toString()
	    @Override
	    public String toString() {
	        return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + 
	               ", email=" + email + ", phone=" + phone + ", address=" + address + ", resume=" + resume + "]";
	    }
}