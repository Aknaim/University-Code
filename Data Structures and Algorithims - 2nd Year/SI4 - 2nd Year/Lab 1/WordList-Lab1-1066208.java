/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordlist;

/**
 *
 * @author Akbar
 */
public class WordList {

    private int size;
    //size = number of words
    private String[] stringone;
    //a new string called stringone which stores all our information
    private int listcapacity;
    //listcapacity = lengthof stringone array

    public WordList(int capacity) {

        size = 0;
        listcapacity = capacity;
        stringone = new String[capacity];

    }

    public WordList(String[] arrayofwords) {
        size = arrayofwords.length;
        listcapacity = (2 * (arrayofwords.length));
        stringone = new String[listcapacity];

        //System.out.println(stringone.length);

        String[] output = sort(arrayofwords);
        
        
        int i;
        for (i = 0; i < output.length; i++) {
            stringone[i] = output[i];
        }
    }

    public int getSize() {

        return this.size;

        //returns size of string

    }

    public int getCapacity() {

        return this.listcapacity;

        //returns listcapacity

    }

    public String getWordAt(int i) {



        if (i >= getSize()) {

            throw new ArrayIndexOutOfBoundsException();
        } else {
            return stringone[i];
        }
    }

    public void insert(String newword) {

        int i;
        for (i = 0; i < stringone.length; i++) {
            if (stringone[i] != null) {
                if (newword.compareTo(stringone[i]) == 0) {
                    return;
                }
            }

        }
        //compares if there is a duplicate

        String[] stringtwo;
        if (getSize() + 1 == getCapacity()) {
            stringtwo = new String[stringone.length * 2];

        } //makes a new string of double the length if stringone is full
        else {
            stringtwo = new String[stringone.length];
        }

        //makes a new string of same length as stringone if not full

        size++;
        listcapacity = stringtwo.length;

        int j = 0;
        boolean check = false;
        for (i = 0; i < stringone.length; i++) {

            if (stringone[i] != null) {

                if (stringone[i].compareTo(newword) < 0 && check == false) {
                    stringtwo[i] = stringone[j];
                    j++;
                    // System.out.println("test1:");
                } //case 1: where new word comes before old one
                else if (check == false) {
                    stringtwo[i] = newword;
                    check = true;
                    //System.out.println("test2:");
                } else {
                    stringtwo[i] = stringone[j];
                    j++;
                    // System.out.println("test3:");
                }
                //case 2:where new word comes after old one
            } else {
                if (check == false) {
                    stringtwo[i] = newword;
                    check = true;
                    //System.out.println("test4:" + stringtwo[i]);
                } else {
                    stringtwo[i] = stringone[j];
                    j++;
                }
            }

        }
        if (check == false) {
            stringtwo[i - 1] = newword;

        } else {
            stringtwo[i - 1] = stringone[j - 1];
            j++;

        }
        stringone = new String[stringtwo.length];
        for (i = 0; i < stringtwo.length; i++) {

            stringone[i] = stringtwo[i];
            //System.out.println("end"+stringtwo[i]);
        }
    }

    public int find(String word) {

        int low = 0;
        int high = this.size - 1;

        while (high >= low) {
            int mid = (high + low) / 2;
            if (stringone[mid].compareTo(word) == 0) {
                return mid;
            } else if (stringone[mid].compareTo(word) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;

        //binary search algorithm for finding a word in the string
    }

    public static String[] sort(String array[]) {

        int i = 0;
        int j = 0;
        String temp;
        for (i = 0; i < array.length - 1; i++) {
            for (j = i + 1; j < array.length; j++) {
                if (array[j] == null) {
                    break;
                }
                if (array[i].compareToIgnoreCase(array[j]) > 0) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }

        }
        return array;
        //sorts a string into alphabetical order and returns it
    }

    public void remove(String word) {


        if (this.find(word) == -1) {
            return;
        }

        int i = 0, j = 0;
        boolean check2 = false;
        for (i = 0; i < size; i++) {
            if ((word.compareTo(stringone[i]) == 0)) {
                stringone[i] = null;
                check2 = true;
                break;
            }
        }
        //Checks to see if the word is in the array, if it is the boolean check2 is flipped to true
        for (j = i; j < listcapacity - 1; j++) {

            if (check2 == true) {
                stringone[j] = stringone[j + 1];
            }
        }
        //Moves all elements up one spot from where the word was removed

        size--;
    }

    public WordList sublist(char init, char fin) {


        int i = 0, j = 0;
        String[] temp3 = new String[size];

        for (i = 0; i < size; i++) {

            if (init <= stringone[i].charAt(0) && fin >= stringone[i].charAt(0)) {
                temp3[j] = stringone[i];
                j++;
            }
        }
        //checks to see which words fall under the designated end points and puts them in string temp3
        String[] temp4 = new String[j];
        i = 0;

        for (i = 0; i < j; i++) {
            temp4[i] = temp3[i];
        }
        //moves all the elements from temp3 to temp4 which is of a more efficent size
        return new WordList(temp4);
        //returns the string temp4
    }

    public String toString() {
        int i;
        String finalstring = "";
        for (i = 0; i < this.getSize(); i++) {
            finalstring += this.getWordAt(i);
            finalstring += "\n";
        }
        return finalstring;
        //returns the final string one word in each line   
    }
}
