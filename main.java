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
 *    - Children only receive 
 *    
 *    - People within the same house (category) should not give to each other
 *  
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.FileWriter;
import java.io.IOException;



public class main {

    public static void main(String[] args) {
        //System.out.println("///////// Starting Program ////");
        //      Debug:
        //System.out.println("///////// Starting Object Test ////");
        //testObj();
        //testArrayList(testObj());
        
        
        //System.out.println("/////////// Ending.////////////////");

        ArrayList<Person> santaList = new ArrayList<Person>(); //create arraylist to store reference to objects

        csvReader(santaList);

        allocate(santaList);

        printCSV(santaList);  
        
        //System.out.println("///////// Ending Program ////");
    }

    /////////
    //Testing
    ////////

    public static Person testObj(){
        Person ss1 = new Person();
        ss1.setName("swooper");
        ss1.setType("A");
        ss1.setCat("County");
        ss1.setPresents(6);
        System.out.println("Details: " + ss1.getName() + " " + ss1.getType() + " " + ss1.getCat());
        ss1.givePresent("bob",0);
        ss1.givePresent("ytryu0yo",1);
        ss1.givePresent("y4635o",2);
        ss1.givePresent("eryo",3);
        ss1.givePresent("dfsgo",4);
        ss1.givePresent("Magus",5);
        System.out.println("Presents: " + ss1.printPresents());
        System.out.println("Total: " + ss1.totalPresents());
        System.out.println("Total: " + ss1.timesChosen());
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
    public static void printCSV(ArrayList<Person> santaList) {
    	
    	
    	//// readable
    	System.out.println("\n === Readable Output ====");
    	for (int x =0; x < santaList.size();x++){
            System.out.print(//x + ":" + 
                santaList.get(x).getName() + ": " + 
                santaList.get(x).getType() + " " + 
                santaList.get(x).getCat() +  " " + 
                santaList.get(x).printPresents() + " " + 
                santaList.get(x).totalPresents() + " " +
                santaList.get(x).timesChosen() + 
                "\n");
        }
        
    	System.out.println("\n----CSV output----");
    	System.out.println("Name,Present1,Present2,Present3,Present4,Present5,Present6");
    	for (int x = 0; x < santaList.size();x++){
            System.out.print(//x + ":" + 
                santaList.get(x).getName() + "," + 
                santaList.get(x).present(0) + "," + 
                santaList.get(x).present(1) + "," + 
                santaList.get(x).present(2) + "," + 
                santaList.get(x).present(3) + "," + 
                santaList.get(x).present(4) + "," + 
                santaList.get(x).present(5) + //"," + 
               //santaList.get(x).timesChosen() +           
                "\n");
        }

    	
    }
    

    
    public static ArrayList<Person> csvReader(ArrayList<Person> santaList){

        try {
            BufferedReader CSV = new BufferedReader(new FileReader(
                        "./SantaNames.csv"));
            System.out.println("***Successfully read csv file***");
            String currentLine = ""; // where we will load in lines

            // Iterates through, sets current line and detects end
            // Also stops where ",," exists from excel empty cells. 
            // RULE: Assumes no blanks.

            while ((currentLine = CSV.readLine()) != null
            && currentLine.equals(",,") == false) {

                //santaDetails will be what we rip off the csv. The rest is for the array use.
                String[] arrInfo = currentLine.split(","); // split string into parts

                if (arrInfo.length != 3) {
                    System.out.println("CSV error. This line has empty cells(excel) and thus missing information.");
                    break;
                }

                Person ss = new Person();
                ss.setName(arrInfo[0]);   
                ss.setType(arrInfo[1]); 
                ss.setCat(arrInfo[2]); 
                ss.setPresents(6); //hardcoded for now.
                santaList.add(ss); 

            }

            CSV.close(); // close off the CSV reader.

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // end array list loop

        return santaList;
    }

    public static ArrayList<Person> allocate(ArrayList<Person> santaList){
        int santaPick = 0; //random person picked


        //Debug:
        //System.out.println(santaList.get(0).numPresents()); 
        //System.out.println(santaList.size());

        for (int y = 0; y < santaList.get(0).numPresents(); y++) {//go across through the presents
            //System.out.println("Present: " + y);

            for (int x = 0; x < santaList.size(); x++) { //go down through the list of people
                //System.out.println("Person: " + y);
                //pick a random number, reset tally.
                santaPick = 0 + (int) (Math.random() * ((santaList.size() - 1)));
                
                
                if (santaList.get(x).getType().equals("c") == true){	//if child or teenager on last presents, no present.
                       santaList.get(x).givePresent("...", y);                        
                }
                
                else if (santaList.get(x).getType().equals("a") && y == 4 ||
                santaList.get(x).getType().equals("a") && y == 5){
                	
                    //make adults give to children for 4th and 5th presents
                	//System.out.println("Present was given from " + santaList.get(x).getName() + "to child: ");
                	santaList.get(x).givePresent(presentFinder(santaList,santaPick,x,true), y);   //true = child only
                }
                else {
                    //give anyone present
                	//System.out.println("Present was given from " + santaList.get(x).getName() + "to adult: ");
                    santaList.get(x).givePresent(presentFinder(santaList,santaPick,x,false), y);  
                }           

            }
        }

        return santaList;
    }

    public static String presentFinder(ArrayList<Person> people, int pick, int santa, boolean childOnly){
        /*
        check if this santa already has this person to give a present to. 
        If not, return the persons name
        This function mainly to not repeat code.

        person = person picked to be be a 'secret santa
        santa = person givign the present, storing the names of it's secret santas
        people = array list of people, should the one picked be no good
        lastPresent = if the last two presents, children only for adults.
         */
        int tally = 0;
        
        while (tally < 2){
	            if (people.get(pick).getCat().equals(people.get(santa).getCat()) == true || 	 
	            people.get(pick).totalPresents() >= 6 || 										
	            people.get(santa).checkPresent(people.get(pick).getName()) == true ||			
	            people.get(pick).timesChosen() >= 6 ||
	            childOnly == true && people.get(pick).getType().equals("a") ||
	            childOnly == false && people.get(pick).getType().equals("c")
	            ){
	            	pick++;
	            	
		        } else {
	            	people.get(pick).picked();
	            	//System.out.println(people.get(pick).getName());
	                return people.get(pick).getName();
		        }

	            
	            if (pick >= people.size()){
	                tally++; // count the loops (to stop infinite loop)
	                pick = 0; // reset the loop

	            }
        }
        return "none";
    }
}
  
