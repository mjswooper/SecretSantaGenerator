import java.util.Arrays;

/////////////////////////
/// Objects  ////////////
/////////////////////////

class Person {

	String ssName = "";
	String ssType = ""; //adult, teenager or child (a,t,c)
	String ssCat = ""; //which household or group they are in
	String p[] = new String[6]; 
	int totalp = 0; //total of presents allocated
	
	
	/////////////////////////////////
	// Object Functions:
	/////////////////////////////////
	
	public int totalPresents() {
		//returns the tally of the presents this person has.
		return totalp; 
	}
	
	public boolean givePresent (String toGive, int pNumber) {
		//sets present and returns true on success.
		//toGive = name of secret santa, pNumber is the number of the present (p1).
		if (p[pNumber] != null){
			return false;
		}
		else
			switch (pNumber) {
			case 1: p[0] = toGive;
					totalp++;
					return true;
			case 2: p[1] = toGive;
					totalp++;
					return true;
			case 3: p[2] = toGive;
					totalp++;
					return true;
			case 4: p[3] = toGive;
					totalp++;
					return true;
			case 5: p[4] = toGive;
					totalp++;
					return true;
			case 6: p[5] = toGive;
					totalp++;
					return true;
			default: return false;
			}		
	}
	
	public String printPresents(){
		 return Arrays.toString(p);
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
