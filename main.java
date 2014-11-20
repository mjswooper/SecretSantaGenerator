/**
 * 
 * Author Asterisk 
 * Last update: 1/11/2014
 * 
 * 
 * Notes: 
 *  
 *  Rules: 
 *    - Each person gets 6 presents
 *    
 *    - Adult (a) give 4 adult presents and 2 child
 *    - Teenagers (t) give 4 adult presents, no child
 *    - Children only receive 
 *    
 *    - People within the same house (category) should not give to each other
 *  
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class main {

	public static void main(String[] args) {

//		Debug:
		System.out.println("///////// Starting Object Test ////");
//		testObj();
		testArrayList(testObj());
		System.out.println("/////////// Ending.////////////////");
				
		ArrayList<Person> santaList = new ArrayList<Person>(); //create arraylist to store reference to objects

	}

	/////////
	//Testing
	////////
	
	public static Person testObj(){
		Person ss1 = new Person();
		ss1.setName("swooper");
		ss1.setType("A");
		ss1.setCat("County");
		System.out.println("Details: " + ss1.getName() + " " + ss1.getType() + " " + ss1.getCat());
		ss1.givePresent("bob",0);
		ss1.givePresent("yoyo",1);
		ss1.givePresent("Magus",5);
		System.out.println("Presents: " + ss1.printPresents());
		System.out.println("Total: " + ss1.totalPresents());
		return ss1;
	}
	
	public static void testArrayList(Person testPerson){
		ArrayList<Person> testList = new ArrayList<Person>();
		testList.add(testPerson);
		System.out.println(testList.get(0) + " " + testList.get(0).getName());
	}
	
	//////////
	//End test
	//////////

	public  void csvReader(){
		
        try {
            BufferedReader CSV = new BufferedReader(new FileReader(
                        "./SantaNames.csv"));
            System.out.println("***Successfully read csv file***");
            String currentLine = ""; // where we will load in lines

            // iterates through, sets current line and detects end
            // Also stops where ",," exists from excel empty cells. 
            // RULE: Assumes no blanks.

            while ((currentLine = CSV.readLine()) != null
            && currentLine.equals(",,") == false) {
                
                //santaDetails will be what we rip off the csv. The rest is for the array use.
                String[] arrPresents = currentLine.split(","); // split string into parts
                
                if (arrPresents.length != 3) {
                    System.out.println("CSV error. This line has empty cells(excel) and thus missing information.");
                    break;
                }
            }
            CSV.close(); // close off the CSV reader.

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // end array list loop
	
	}

}	

