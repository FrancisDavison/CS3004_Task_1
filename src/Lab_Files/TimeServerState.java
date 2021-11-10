package Lab_Files;
import java.net.*;
import java.io.*;
import java.util.Date;



public class TimeServerState {

	//Set up the date object

	Date date = new Date();
	
    /* The processInput method */

    	public String processInput(String theInput) {
    		System.out.println("processInput "+theInput);
    		String theOutput = null;
    		// Check what the client said
    		if (theInput.equalsIgnoreCase("What time is it?")) {
    			//Correct request
    			theOutput = date.toString();
    		} else { //incorrect request
    			theOutput = "Incorrect request - only understand \"What time is it?\"";
		
    		}
 
     		//Return the output message to the TimeServer
    		System.out.println(theOutput);
    		return theOutput;
    	}	
}