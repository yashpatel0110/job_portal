package com.Job_Portal;

import java.util.Scanner;

public class App { 
	
	

    public static void main(String[] args) {
    	
    	// Create a Scanner object to read user input from the console
    	 Scanner sc = new Scanner(System.in);
    	 
    	// Create an instance of MainOperation and pass the Scanner to it
         MainOperation mainOperation = new MainOperation(sc);
         
        // Call the start() method which displays the main menu and handles navigation
          mainOperation.start();
    }
}