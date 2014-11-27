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
        System.out.println("///////// Starting Program ////");
        //      Debug:
        //System.out.println("///////// Starting Object Test ////");
        //testObj();
        //testArrayList(testObj());
        //System.out.println("/////////// Ending.////////////////");

        ArrayList<Person> santaList = new ArrayList<Person>(); //create arraylist to store reference to objects

        csvReader(santaList);

        allocate(santaList);

        for (int x =0; x < santaList.size();x++){
            System.out.print(x + ":" + 
                santaList.get(x).getName() + " " + 
                santaList.get(x).getType() + " " + 
                santaList.get(x).getCat() +  " " + 
                santaList.get(x).printPresents() + " " + 
                santaList.get(x).totalPresents() + " " +
                santaList.get(x).timesChosen() + "\n");
        }
        System.out.println("///////// Ending Program ////");
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

    public static ArrayList csvReader(ArrayList<Person> santaList){

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

    public static ArrayList allocate(ArrayList<Person> santaList){
        int santaPick = 0; //random person picked
        int tally = 0; //if personal not of criteria, we cycle through to the next sequentially.

        //Debug:
        //System.out.println(santaList.get(0).numPresents()); 
        //System.out.println(santaList.size());

        for (int y = 0; y < santaList.get(0).numPresents(); y++) {//go across through the presents
            //System.out.println("Present: " + y);

            for (int x = 0; x < santaList.size(); x++) { //go down through the list of people
                //System.out.println("Person: " + y);
                //pick a random number, reset tally.
                santaPick = 0 + (int) (Math.random() * ((santaList.size() - 1)));
                tally = 0;
                        if (santaList.get(x).getType().equals("c") == true || 
                santaList.get(x).getType().equals("t") && y == 4 ||
                santaList.get(x).getType().equals("t") && y == 5 ){
                    //if child or teenager on last presents, no present.
                    santaList.get(x).givePresent("...", y);                        
                }
                else if (santaList.get(x).getType().equals("a") && y == 4 ||
                santaList.get(x).getType().equals("a") && y == 5){
                    //make adult sgive to children for certain presents
                    //give kid present
                    santaList.get(x).givePresent( presentFinder(santaList,santaPick,x,true), y);  
                }
                else {
                    //give anyone present
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
            if (people.get(pick).getCat().equals(people.get(santa).getCat()) == true ||  //if same category/house/family
            people.get(pick).totalPresents() >= 6 || //if max presents
            childOnly == true && people.get(pick).getType().equals("c") == false ||//argument for child, but pick isnt child
            people.get(santa).checkPresent(people.get(pick).getName()) == true ||// already picked for this person
            people.get(pick).timesChosen() >= 6
            ){
                pick++; //move to the next person
            } else {
                return people.get(pick).getName();
            }

            //loop if at the end
            if (pick >= people.size()){
                tally++; // count the loops (to stop infinite loop)
                pick = 0; // reset the loop
            }
        }

        return "N/A";
    }
}   
