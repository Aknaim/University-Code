/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si4.lab.pkg4;

import java.util.ArrayList;

/**
 *
 * @author Akbar
 */
public class BinTree {

    private TNode root;
    
    public BinTree(String[] a) throws IllegalArgumentException {

        root = new TNode(null, null, null);
        //TNode p = null;
        int counter = 0;
        TNode pointer = root;
        for (int i = 0; i < a.length ; i++) {
            for (int j = 0; j < a[i].length(); j++) {

                if (a[i].charAt(j) == '0') {
                    if (pointer.left != null){
                        pointer = pointer.left;
                    }
                    else{
                        pointer.left = new TNode (null,null,null);
                        pointer = pointer.left;
                    }                      

                } else {
                    if (pointer.right != null){
                        pointer = pointer.right;
                    }
                    else{
                        pointer.right = new TNode (null,null,null);
                        pointer = pointer.right;
                    }             
                }
                
            }
            pointer.data = "c" + a[i];
            pointer = root;
        }
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(TNode t) {
        if (t != null) {
            printTree(t.left);
            if (t.data == null) {
                System.out.print("I ");
            } else {
                System.out.print(t.data + " ");
            }
            printTree(t.right);
        }


    }

    public int height() {

        return (height(root));
        //calls upon height to recursively obtain the height of the tree

    }

    private int height(TNode t) {

        if (t == null) {
            return -1;
        } else {
            return 1 + Math.max(height(t.left),height(t.right));
        }

    }
    
    public static void getvalues(TNode t, ArrayList<String> code){
        if (t==null){
            return;
        }          
        if(t.left == null && t.right ==null){
            code.add(t.data.substring(1));
        }
        getvalues(t.left,code);
        getvalues(t.right,code);
        
    }

    public ArrayList<String> getCodewords() {

        ArrayList<String> codewords = new ArrayList();
        TNode pointer = root;
        getvalues (pointer, codewords);
        return codewords;
        
//
//        TNode pointer = root;
//
//
//        while (pointer.data == null) {
//
//            codewords.add(pointer.left.data.substring(1));
//            pointer = pointer.right;
//        }
//
//        codewords.add(pointer.left.data.substring(1));
//        codewords.add(pointer.data.substring(1));
//
//        return codewords;
//-----------------old getCodeword code above (was wrong)-----------
        
    }

    public String[] convert() {

        String elements[] = new String[this.height() * 2 + 1];
        elements[0] = null;
        TNode pointer = root;
        int i = 1;

        while (pointer.data == null) {
            elements[i] = pointer.left.data;
            elements[i + 1] = "I";
            pointer = pointer.right;
            i += 2;
        }

        elements[i] = pointer.left.data;
        elements[i + 1] = pointer.data;

        return elements;

    }

    public ArrayList<String> decode(String s) {

        ArrayList<String> decode = new ArrayList();
        ArrayList<String> codewords = new ArrayList();
        codewords = this.getCodewords();
        int i = 0;
        int j = 0;
        int size = s.length();

        for (i = 0; i < size;) {
            for (j = 0; j < codewords.size(); j++) {
                if (i + codewords.get(j).length() - 1 < size) {
                    //System.out.println("code: " + codewords.get(j));
                    //System.out.println("subs: " + s.substring(i, i + (codewords.get(j)).length() ));
                    if ((codewords.get(j)).compareTo(s.substring(i, i + (codewords.get(j)).length())) == 0) {
                        //System.out.println("jhgfh");
                        decode.add(codewords.get(j));
                        i = i + (codewords.get(j)).length();
                    } else {
                    }
                }
                else{
                }
            }
        }
        return decode;
    }
    
}

/**
 *
 * @author Akbar
 */
public class TNode {

    String data;
    TNode left;
    TNode right;

    TNode(String s, TNode l, TNode r) {
        data = s;
        left = l;
        right = r;
    }
}