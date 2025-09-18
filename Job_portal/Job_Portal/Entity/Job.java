package com.Job_Portal.Entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {
	// Primary Key: Unique identifier for each job 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private int jobId;

    @Column(nullable = false)
    private String title;

    @Column(name = "company_name", length = 50)
    private String companyName;

    @Column(name = "location", length = 100)
    private String location;

    @Column(length = 50)
    private String education;

    @Column(length = 30)
    private String category;

    @Column(name = "additional_requirement", length = 150)
    private String additionalRequirement;

    @Column(length = 30)
    private int vacancy;

    @Column(name = "employment_status", length = 50)
    private String employmentStatus;

    @Column(length = 100)
    private String experience;

    @Column(length = 50)
    private double salary;

    @Column(name = "approved",length = 30)
    private boolean approved;

    @Column(name = "deadline", length = 30)
    private LocalDate deadline;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "apply_date")
    private LocalDate applyDate;

  
    @ManyToMany(mappedBy = "appliedJobs", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> applicants = new HashSet<>();
    
   
    //  Default Constructor
    public Job() {}

    
    
 // ✅ FOREIGN KEY: Links each job to one employer (Many jobs -> One employer)
    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Employer employer;

 // ✅ FOREIGN KEY (via join table): Links jobs to users who applied (Many-to-Many)
    @ManyToMany(mappedBy = "appliedJobs", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<User> getapplicants = new HashSet<>();
    @ManyToMany(mappedBy = "appliedJobs", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();
   
    // Parameterized Constructor
    public Job(String title, String companyName, String location, String education, LocalDate deadline,
            String category, String additionalRequirement, int vacancy, String employmentStatus,
            String experience, double salary) {
     this.title = title;
     this.companyName = companyName;
     this.location = location;
     this.education = education;
     this.deadline = deadline;
     this.category = category;
     this.additionalRequirement = additionalRequirement;
     this.vacancy = vacancy;
     this.employmentStatus = employmentStatus;
     this.experience = experience;
     this.salary = salary;
     this.approved = false;
 }

    // Getters and Setters

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAdditionalRequirement() {
        return additionalRequirement;
    }

    public void setAdditionalRequirement(String additionalRequirement) {
        this.additionalRequirement = additionalRequirement;
    }

    public int getVacancy() {
        return vacancy;
    }

    public void setVacancy(int vacancy) {
        this.vacancy = vacancy;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Set<User> getApplicants() {
        return getapplicants;
    }

    public void setApplicants(Set<User> applicants) {
        this.getapplicants = applicants;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    public LocalDate getApplyDate() {
    	return applyDate;
    	}
    public void setApplyDate(LocalDate applyDate) { 
    	this.applyDate = applyDate; 
    	}
    
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Job [jobId=" + jobId + ", title=" + title + ", companyName=" + companyName +
                ", location=" + location + ", education=" + education + ", deadline=" + deadline +
                ", category=" + category + ", additionalRequirement=" + additionalRequirement +
                ", vacancy=" + vacancy + ", employmentStatus=" + employmentStatus + 
                ", experience=" + experience + ", salary=" + salary +
                ", status=" + status + ", approved=" + approved + ", applyDate=" + applyDate + ",users="+ users+"]";
    }
}