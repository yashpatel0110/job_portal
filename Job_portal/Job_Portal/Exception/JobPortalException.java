package com.Job_Portal.Exception;


//Custom exception class for handling application-specific errors in the Job Portal
public class JobPortalException extends RuntimeException {
	// Constructor that accepts an error message
	public JobPortalException(String message) {
		// Call the constructor of RuntimeException with the given message Or Call parent RuntimeException constructor
        super(message);  

}

}
