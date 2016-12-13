/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wordlist;

/**
 *
 * @author Akbar
 */
public class TestClass {
    /**
     * @param args 
     */
    public static void main(String[] args){
        String[] test = new String[]{"cat", "use", "abcc","dat"};
        
        WordList  A = new WordList(test);
        A.insert("aa");
        A.insert("zz");
        A.insert("great");
        A.insert("fog");
        A.getWordAt(2);
        A.remove("zz");
        A.remove("great");
        WordList b = A.sublist('d', 'f');
        for (int i = 0; i < b.getCapacity(); i++) {
            System.out.println(b.getWordAt(i));            
        }
        
        System.out.println("Above is sublist, below is stringone");
      
        for ( int i = 0; i < A.getCapacity(); i++) {
            System.out.println(A.getWordAt(i));
        }
        
        System.out.println(A.toString());
    }
}
