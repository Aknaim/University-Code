/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordlinkedlist;

/**
 *
 * @author Akbar
 */


public class Node {
    // node of a single linked list of strings
    
    public String word; //we assume elements are character strings
    public Node next; 
    
        public Node(String string,Node nextword){
        word = string;
        next = nextword;
        
        }
        //creates a node with the given element and next node
}
        



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordlinkedlist;

/**
 *
 * @author Akbar
 */
public class WordLinkedList {

    private int listSize;
    //size = number of words
    private Node head;
    //a reference to the beginning of the linkedlist

    public WordLinkedList() {

        head = new Node(null, null);
        listSize = 0;
        //constructor for a dummy node with nothing in the linked list

    }

    public WordLinkedList(String[] arrayOfWords) {

        head = new Node(null, null);
        listSize = 0;
        int i;
        for (i = 0; i < arrayOfWords.length; i++) {
            insert(arrayOfWords[i]);
        }
        //constructs and linked list of the array of words

    }

    public int getSize() {

        return listSize;
        //returns the size of the linked list

    }

    public String getWordAt(int i) {

        int j=0;
        
        if (i >= getSize()) {
            throw new IndexOutOfBoundsException();
        } 
        
        else {
            Node p = head.next;            
            for (j=0;j<i;j++){
             p = p.next;
            }
            return p.word;
        }
    }

    public void insert(String newword) {


        int i = 0;

        if (find(newword) == -1) {
            if (getSize() == 0) {
                Node p = new Node(newword, head.next);
                head = new Node(null, p);
                listSize++;
                //System.out.println("empty");
                return;
            } 
            //when there is an empty list, and first word is inserted
            
            else {

                Node p = head.next;
                //middle insertion code below
                for (; p.next != null; p = p.next) {
                    if (newword.compareTo(p.word) > 0 && newword.compareTo(p.next.word) < 0) {
                        Node temp = new Node(newword,p.next);
                        p.next = temp;
                        listSize++;
                        //System.out.println("middle");
                        return;
                    }
                }
                if (newword.compareTo(p.word) < 0){
                        Node temp = new Node(newword,head.next);
                        head.next = temp;
                        listSize++;
                        //System.out.println("beginning");
                        return;
                }
                
                else {
                        Node temp = new Node(newword,null);
                        p.next = temp;
                        listSize++;
                        //System.out.println("end");
                        return;
                }


                
                //list with words already in it
            }
        } else {
            //System.out.println("Word already in list");
        }

    }

    public int find(String word) {

        if (listSize == 0) {
            return -1;
        }

        int i = 0;
        Node p = head.next;

        for (; p.next != null; p = p.next) {
            if (p.word.compareTo(word) == 0) {
                return i;
            }
            i++;
        }
        if (p.word.compareTo(word) == 0) {
            return i;

        } else {
            return -1;
        }

    }
    
    public String remove(int i) {
        int j = 0;
        String temp = getWordAt(i);

        Node p = head;
        for (j = 0; j < i; j++) 
            p = p.next;
            p.next = p.next.next;
            listSize--;
            
            //System.out.println("remove");
            //remove function
          
        
        
        return temp;
    }
    
    public void mergeTo (WordLinkedList that){
        
        //System.out.println("Size of that array:"+that.getSize());
        int j = that.getSize();
        for (int i=0;i<j;i++){
            this.insert(that.remove(0));
        }
        //System.out.println("----------merge-----------");
    }


    public String toString() {
        String longString = "";
        if (listSize == 0) {
            longString = "The list is empty";
        } else {
            Node p = head.next;
            longString = new String("");
            for (; p.next != null; p = p.next) {
                longString = longString + p.word + "\n";
            }//end for
            longString = longString + p.word;
        }
        return longString;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String[] listone = new String[]{"cat", "dog", "mouse","head","pie","leauge"};
        WordLinkedList test = new WordLinkedList(listone);
        System.out.println("Size of test array:"+ test.getSize());
        String[] listtwo = new String[]{"bat", "nat", "smog","bar","tie","leauge"};
        WordLinkedList test2 = new WordLinkedList(listtwo);
        System.out.println("Size of test2 array:"+ test2.getSize());
        
        
    }
}


