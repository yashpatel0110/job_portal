package com.Job_Portal.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//This annotation marks the class as a JPA entity.
@Entity
//Specifies the table name in the database that corresponds to this entity class.
@Table(name = "admin")
public class Admin {
	// Primary Key: Unique identifier for each admin
    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the generation strategy for the primary key.
    @Column(name = "admin_id") // Maps the field to the database column.
    private int adminId; // Unique identifier for each admin (auto-generated).

    @Column(name = "ad_first_name", length = 30) // Maps to the "ad_first_name" column, with a max length of 30 characters.
    private String adFirstName; // Admin's first name.

    @Column(name = "ad_last_name", length = 30) // Maps to the "ad_last_name" column, with a max length of 30 characters.
    private String adLastName; // Admin's last name.

    @Column(name = "ad_phone", length = 30) // Maps to the "ad_phone" column, with a max length of 30 characters.
    private String adPhone; // Admin's phone number.

    @Column(name = "ad_email", length = 50) // Maps to the "ad_email" column, with a max length of 50 characters.
    private String adEmail; // Admin's email address.

    @Column(name = "ad_address", length = 100) // Maps to the "ad_address" column, with a max length of 100 characters.
    private String adAddress; // Admin's address.

    @Column(name = "password", length = 50) // Maps to the "password" column, with a max length of 50 characters.
    private String password; // Admin's password (should be encrypted in practice).

    // Default Constructor
    public Admin() {}

    // Parameterized Constructor: Initializes an Admin object with the provided values.
    public Admin(String adFirstName, String adLastName, String adPhone, String adEmail, String adAddress, String password) {
        this.adFirstName = adFirstName;
        this.adLastName = adLastName;
        this.adPhone = adPhone;
        this.adEmail = adEmail;
        this.adAddress = adAddress;
        this.password = password;
    }

    // Getters and Setters for each field. These allow for access and modification of the field values.
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdFirstName() {
        return adFirstName;
    }

    public void setAdFirstName(String adFirstName) {
        this.adFirstName = adFirstName;
    }

    public String getAdLastName() {
        return adLastName;
    }

    public void setAdLastName(String adLastName) {
        this.adLastName = adLastName;
    }

    public String getAdPhone() {
        return adPhone;
    }

    public void setAdPhone(String adPhone) {
        this.adPhone = adPhone;
    }

    public String getAdEmail() {
        return adEmail;
    }

    public void setAdEmail(String adEmail) {
        this.adEmail = adEmail;
    }

    public String getAdAddress() {
        return adAddress;
    }

    public void setAdAddress(String adAddress) {
        this.adAddress = adAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString() method: This is used for a string representation of the Admin object.
    // It returns the admin details (excluding the password for security reasons).
    @Override
    public String toString() {
        return "Admin [adminId=" + adminId + ", adFirstName=" + adFirstName + ", adLastName=" + adLastName +
                ", adPhone=" + adPhone + ", adEmail=" + adEmail + ", adAddress=" + adAddress + "]";
    }
}
