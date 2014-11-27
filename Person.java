import java.util.Arrays;
import java.util.ArrayList;

/////////////////////////
/// Objects  ////////////
/////////////////////////

class Person {

    String ssName = "";
    String ssType = ""; //adult, teenager or child (a,t,c)
    String ssCat = ""; //which household or group they are in
    String arrP[]; //names of those to give presents to go here.
    int totalp = 0; //total of presents allocated
    int chosen = 0; //times been choosen
    /////////////////////////////////
    // Object Functions:
    /////////////////////////////////

    public int totalPresents() {
        //returns the tally of the presents this person has.
        return totalp; 
    }

    public int timesChosen(){
        return chosen;
    }
    
    public void setPresents(int pNum){
        arrP = new String[pNum];
    }

    public int numPresents(){
        return arrP.length;
    }

    public boolean givePresent (String toGive, int pNumber) {
        //sets present and returns true on success.
        //toGive = name of secret santa, pNumber is the number of the present (p1).

        if (arrP[pNumber] != null){
            System.out.println("arrP[pNumber] != null");
            return false;
        }
        else{
            arrP[pNumber] = toGive;
            chosen++;
            return true;
        }
    }

    public boolean checkPresent(String propPerson){
        for (int a = 0; a < arrP.length; a++) {
            if (arrP[a] != null)
                if (arrP[a].equals(propPerson)) return true;
        }
        return false;
    }

    public String printPresents(){
        //no inbuilt means to turn into a string like an array.
        //System.out.println("p0:" + arrP[0] + " p0:" + arrP[1] + " p0:" + arrP[2] + " p0:" + arrP[3] + " p0:" + arrP[4] + " p0:" + arrP[5]); 
        return Arrays.toString(arrP);
    }

    public void setName(String gName){
        ssName = gName;
    }

    public String getName(){
        return ssName;
    }

    public void setType(String gType){
        ssType = gType;
    }

    public String getType(){
        return ssType;
    }

    public void setCat(String gCat){
        ssCat = gCat;       
    }

    public String getCat(){
        return ssCat;       
    }

}
