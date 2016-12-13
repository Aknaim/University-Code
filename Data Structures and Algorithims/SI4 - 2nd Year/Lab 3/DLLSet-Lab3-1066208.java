/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si4.lab.pkg3;

/**
 *
 * @author Akbar
 */
public class DLLNode {
    
    int element;
    DLLNode prev;
    DLLNode next;
    DLLNode (int i, DLLNode n, DLLNode p)
            {element =i;next = n; prev = p;}

}




/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si4.lab.pkg3;

/**
 *
 * @author Akbar
 */
public class DLLSet {

    private int size;
    private DLLNode head;
    private DLLNode tail;

    //instance fields of DLLSet
    public DLLSet() {

        size = 0;
        head = new DLLNode(0, tail, null);
        tail = new DLLNode(0, null, head);
        head.next = tail;
        //constructs a DLLSet of size 0 with head pointing to tail

    }

    public DLLSet(int[] sortedArray) {
        size = 0;
        head = new DLLNode(0, tail, null);
        tail = new DLLNode(0, null, head);
        head.next = tail;

        for (int i = 0; i < sortedArray.length; i++) {
            add(sortedArray[i]);
        }
        //constructs a DLLSet of the size of the sortedArray with head pointing to tail
    }

    public int getSize() {

        return this.size;
        //returns the size of the DLLSet
    }

    public DLLSet copy() {

        DLLSet copy = new DLLSet();

        DLLNode temp = head.next;
        for (; temp.next != null; temp = temp.next) {
            copy.add(temp.element);
        }
        //creates and returns a deep copy of the DLLSet
        return copy;
    }

    public boolean isIn(int v) {

        boolean isIn = false;

        if (this.size == 0) {
            return false;
        }
        DLLNode temp = head.next;
        for (; temp.next != null; temp = temp.next) {
            if (v == temp.element) {
                isIn = true;
                break;
            } else {
                isIn = false;
            }
        }
        //checks to see if the integer is already in the list
        return isIn;

    }

    public void add(int v) {
        if (isIn(v) == true) {
            return;
            //if the element is already in the list it does nothing
        } else if (size == 0) {
            DLLNode temp0 = new DLLNode(v, tail, head);
            head.next = temp0;
            tail.prev = temp0;
            size++;
            return;
            // if the list is of size 0 then the element is added between head and tail, and referenced to head and tail size is increased by 1
        } else if (isIn(v) == false) {
            DLLNode temp = head.next;
            for (; temp.next != tail; temp = temp.next) {
                if (v > temp.element && v < temp.next.element) {
                    DLLNode temp1 = new DLLNode(v, temp.next, temp);
                    (temp.next).prev = temp1;
                    temp.next = temp1;
                    size++;
                    return;
                    //if the element fits in the middle of the list then it is added there and referenced to the previous and next nodes size is increased by 1
                } else if (v < head.next.element) {
                    DLLNode temp2 = new DLLNode(v, temp, temp.prev);
                    head.next = temp2;
                    temp.prev = temp2;
                    size++;
                    return;
                } // if the element fits in the front of the list then it is added there and referenced to head and the next node size is increased by 1
            }
            DLLNode temp3 = new DLLNode(v, tail, temp);
            tail.prev = temp3;
            temp.next = temp3;
            size++;
            //if the element fits in the end of the list then it is added there and referenced to tail and the previous node, size is increased by 1
            return;

        }


    }

    public void remove(int v) {

        DLLNode temp = head.next;
        if (this.size == 0) {
            return;
            //checks to see if the list is of size 0, if it is then it does nothing
        } else if (v == temp.element) {
            head.next = temp.next;
            temp.next.prev = head;
            size--;
            return;
            //if the element is being removed from the front of the list, then change the header to point to the next node after, etc (and decrease the size)
        }
        for (; temp.next != tail; temp = temp.next) {
            if (v == temp.element) {
                // System.out.println("test2");
                temp.prev.next = temp.next;
                temp.next.prev = temp.prev;
                size--;
                return;
            }
            //if the element is equal to the int being tested then the nodes around the integer are re-referenced to point around the node that is being removed (and decrease the size)

        }
        if (v == temp.element) {
            //System.out.println("test2");
            tail.prev = temp.prev;
            temp.prev.next = tail;
            size--;
            return;
        }
        //if the element is being removed from the end of the list, then change the tail to point to the previous node before, etc (and decrease the size of the list)
    }

    public DLLSet union(DLLSet s) {

        DLLSet union = new DLLSet();
        DLLNode snode = s.head.next;
        DLLNode thisnode = this.head.next;
        while (snode != s.tail || thisnode != this.tail) { //run for as long as both the lists are not finished
            if (snode == s.tail) {
                DLLNode temp3 = new DLLNode(thisnode.element, union.tail, union.tail.prev);
                (union.tail.prev).next = temp3;
                union.tail.prev = temp3;
                union.size++;
                thisnode = thisnode.next;
                //if we reach the end of snode list then we want to union it with the matching thisnode 
            } else if (thisnode == this.tail) {
                DLLNode temp3 = new DLLNode(snode.element, union.tail, union.tail.prev);
                (union.tail.prev).next = temp3;
                union.tail.prev = temp3;
                union.size++;
                snode = snode.next;
                //if we reach the end of those list then we want to union it with the matching snode
            } else if (snode.element == thisnode.element) {
                DLLNode temp3 = new DLLNode(snode.element, union.tail, union.tail.prev);
                (union.tail.prev).next = temp3;
                union.tail.prev = temp3;
                snode = snode.next;
                thisnode = thisnode.next;
                //if the two values are equal we want to add the value to the union and increment both lists
            } else if (snode.element > thisnode.element) {
                DLLNode temp3 = new DLLNode(thisnode.element, union.tail, union.tail.prev);
                (union.tail.prev).next = temp3;
                union.tail.prev = temp3;
                union.size++;
                thisnode = thisnode.next;
                //if only the snode element is greater then the thisnode element then we want to increment up thisnode
            } else if (snode.element < thisnode.element) {
                DLLNode temp3 = new DLLNode(snode.element, union.tail, union.tail.prev);
                (union.tail.prev).next = temp3;
                union.tail.prev = temp3;
                union.size++;
                snode = snode.next;
                //if only the thisnode element is greater then the snode element then we want to icnreant up snode
            }
        }


        return union;
    }

    public DLLSet intersection(DLLSet s) {

        DLLSet intersection = new DLLSet();
        DLLNode snode = s.head.next;
        DLLNode thisnode = this.head.next;

        while (snode != s.tail && thisnode != this.tail) {
            if (snode.element == thisnode.element) {
                DLLNode temp3 = new DLLNode(snode.element, intersection.tail, intersection.tail.prev);
                (intersection.tail.prev).next = temp3;
                intersection.tail.prev = temp3;
                intersection.size++;

                snode = snode.next;
                thisnode = thisnode.next;
                //if the two values are equal then we want to add it and increment both lists

            } else if (snode.element < thisnode.element) {

                snode = snode.next;
                //if snode is less then thisnode then we know the two values cant be intersecting so we want to increment snode by one

            } else if (snode.element > thisnode.element) {

                thisnode = thisnode.next;
            }   //if thisnode is less then snode then we know the two values cant be intersecting so we want to increament thisnode by one


        }

        return intersection;

    }

//     public static DLLSet recUnion(DLLSet[] sArray){
//         
//         return recUnion(sArray, 0, sArray.length-1);
//         
//    }
//     
//     private static DLLSet recUnion(DLLSet[] sArray, int first, int last) {
//     
//        int j=0;
//        int p = sArray.length;
//        
//        while (p != 1) {
//            for (int i = 0; i < (p / 2); i++) {
//                sArray[i] = sArray[j].union(sArray[j + 1]);
//                j += 2;
//            }
//            recUnion.
//        }
//        return sArray;
//     }
    
    public static DLLSet fastUnion(DLLSet[] sArray) {
        
        int j = 0;
        int p = sArray.length;
        DLLSet[] array = new DLLSet[p];
        for (int i = 0; i < p; i++) {
            array[i] = sArray[i];
        }
        //stores the values in a new array

        while (p != 1) {
            for (int i = 0; i < (p / 2); i++) {
                array[i] = array[j].union(array[j + 1]);
                j += 2;
            }
            //unions the lists at location 0 and 1, and stores them at location zero, then stores the union of 2 and 3 at location 1, etc
            j = 0;
            p = p / 2;
            DLLSet[] temp = new DLLSet[p];
            for (int i = 0; i < p; i++) {
                temp[i] = array[i];
            }
            //copies the new unionized lists over to a new array of half the size
            array = new DLLSet[p];
            for (int i = 0; i < p; i++) {
                array[i] = temp[i];
            }
            //returns the value back to the orginal array after shrinking its size by half
        }
        DLLSet finals = array[0];
        return finals;

    }

    public String toString() {
        String longString = "";
        if (size == 0) {
            longString = "The set is empty";
        } else {
            DLLNode p = head.next;
            longString = new String("");
            for (; p.next != tail; p = p.next) {
                longString = longString + p.element + "\n";
            }//end for
            longString = longString + p.element;
        }
        return longString;
    }

    public String toStringb() { //runs toString backwards to see if the reverse direction of the list is properly made
        String longString = "";
        if (size == 0) {
            longString = "The set is empty";
        } else {
            DLLNode p = tail.prev;
            longString = new String("");
            for (; p.prev != head; p = p.prev) {
                longString = longString + p.element + "\n";
            }//end for
            longString = longString + p.element;
        }
        return longString;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int ints[] = {1, 2, 3, 6, 10};
        int ints1[] = {2, 4, 7, 8, 10};
        int ints2[] = {4, 8, 8, 10, 11};
        DLLSet test = new DLLSet(ints);
        DLLSet test1 = new DLLSet(ints1);
        DLLSet test2 = new DLLSet(ints2);
        //test.remove(1);
        DLLSet test3 = test.union(test2);
        System.out.println(test3);
//        DLLSet test4 = test1.intersection(test2);
//        System.out.println(test4);
//        System.out.println(test.isIn(1));
//        System.out.println("to string");
//        System.out.println(test.toString());
//        System.out.println("to string");
//        System.out.println(test.toStringb());
    }
}
