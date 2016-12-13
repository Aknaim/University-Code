/* Syed Naim,1066208,L09*/


import java.util.ArrayList;

/* this class represents sparse boolean vectors stored efficiently using a list of integers*/
public class EffBVector {

    private ArrayList<Integer> effData;	// reference to an ArrayList of Integers which represents efficiently the boolean vector 

    public EffBVector(int n) {       /* if n<= 0 sets n to 1; initializes the EffBVector object to represent the boolean vector with n elements, 
         all set to true*/

        if (n <= 0) {
            n = 1;
        }
        effData = new ArrayList<Integer>();
        effData.add(n);

    }

    public EffBVector(boolean[] data) {       /* initializes the EffBVector object to represent the boolean vector data*/

        effData = new ArrayList<Integer>();
        set(data);

    }

    public EffBVector(int[] effArray) { /* initializes the EffBVector object 
         to represent the boolean vector whose efficient representation is the 
         sequence of integers in the integer array effArray */

        if (effArray[0] < 0) {
            throw new IllegalArgumentException("Invalid input.");
        } else {
            effData.add(effArray[0]);
        }

        int size = effArray.length;
        for (int i = 1; i < size; i++) {
            if (effArray[i] <= 0) {
                throw new IllegalArgumentException("Invalid input.");
            } else {
                effData.add(effArray[i]);
            }
        }

    }

    public void set(boolean[] data) {	/* changes this EffBVector such that to represent efficiently the boolean vector data */
        int j = 0;
        int i = 0;
        boolean temp = true;
        
        effData = new ArrayList<Integer>();
        
        for (j = 0; j < data.length; j++) {
            if (data[j] == temp) {
                i++;

            } else if (data[j] != temp) {
                this.effData.add(i);
                temp = !temp;
                i = 1;
            }
          

        }
        this.effData.add(i);  
    }

    public boolean[] convert() {	/* returns an array of booleans represented by this EffBVector  */

        int i = 0, j = 0, n=0;
        int sum = 0;
        boolean temp = true;
        
        ArrayList<Boolean> bool = new ArrayList<Boolean>();

        for (j = 0; j < (this.effData.size()); j++) {
            if (effData.get(j) == 0) {                
                  temp = false;                  
            }
            else {
                for (i = 0; i < effData.get(j); i++) 
                    bool.add(temp);
               temp = !temp;
                
                    
           }
         

     }
        
        boolean[] Array = new boolean[bool.size()];
        for(i=0;i<(bool.size());i++){
             Array[i] = bool.get(i);        
        }
        
        return Array;
    }

    public int getBVectorSize() {	/* returns the number of elements of the boolean vector */

        int sum = 0;
        int i;
        for (i=0;i<effData.size();i++){
         sum += effData.get(i);        
        }
                
        return sum;

	}

	

	 public int getListSize() {	/* returns the number of integers in the efficient representation */

        return effData.size();

    }

    public String toString() {
        /* returns a string representing this EffBVector object (lists the integers in the efficient representation of the boolean vector) */

        String output = new String();
        int m = effData.size();

        for (int i = 0; i < m; i++) {
            output += " " + effData.get(i);
        }

        return output;
    }
}//end class