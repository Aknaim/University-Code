/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on October 8, 2013, 10:58 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include "lib.h"

/*
 * 
 */

void reconstruct (int source[], int m, int val[], const int pos[], int n){ //this function reconstructs the original array

    int i,j=0;
    
    for (i=0; i<m; i++){ 
        if (i == pos[j]){ //if the value of i is equal to j then the value of the non zero number is assined to that position in the source array
            source[i] = val[j];
            j++;
        }     
        else if (source[i] != pos[j]){ //if the value of i is zero then source stores a 0 in that position
            source [i] = 0;
        }      
    
    
    }
}

void efficient(const int source[], int val[], int pos[], int size) {

    int i, j=0;

    for (i = 0; i < size; i++) { 
        if (source[i] != '\0') { //if the value at position i is not equal to 0
            val[j] = source [i];  //then the value of the number is stored in source
            pos[j] = i; //the position of the value is stored at the location of i
            j++;
        }
    }

}


int main(int argc, char** argv) {
    
    //Part 1 deassembling code for array
    
    int i;    
    int sparce[] = {0,0,23,0,-7,0,0,48}; //creates a sparce vector
    int val[3]; //stores the non zero values
    int pos[3]; //stores the position of the non zero values
    efficient(sparce,val,pos,8); //calls upon the function efficient inputs the sparce array 
    printf("Values are: "); 
    for (i=0; i<3;i++){ //prints the non zero values
    printf("%d ", val[i]);
    }
    printf("\nPositions are: "); 
    for (i=0; i<3;i++){ //prints the position of the non zero values in the array
    printf("%d ", pos[i]);
    }
    printf("\n");
    
    //Part 2 reassembling code for array
    
    int remade[8]; //creates an empty array of size 8
    int *ptrassemble = remade; //assigns a pointer to the remade array
    reconstruct (ptrassemble, 8, val, pos, 3); //calls upon the reconstruct function
    printf("The original array was: ");
    for (i=0; i<8;i++){ //prints the values stored into the remade array
    printf("%d ", remade[i]);
    }  
    printf("\n");
    
    return (EXIT_SUCCESS);
}

