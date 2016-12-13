/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on October 6, 2013, 6:33 PM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */

int isPerfect(int z) { //this function checks if the number is perfect

    int i=1, sum=0, value;
    
    while (i<z){
    
        if (isFactor(i,z) == 1)
                sum = sum +i;
        i++;
    } 
    
    
        if (sum == z)
                value = 1;
        else 
                value = 0;
    
    
return value;    


}

int isFactor(int x,int y){ //this function checks if x is a factor of y
 
   
    
    int isfactor;
    
    if (y%x == 0)
        isfactor = 1;
    else
        isfactor = 0;
    
 return isfactor;
    
}

int printFactors(int num){ //prints the factors

    int i=1;
    
    printf("Its factors are: ");
    
    while (i<num){
        
        if (isFactor(i, num) == 1 )
            
            printf("%d ", i);
    
        i++;
    }
    
    printf(" \n");
}

int main(int argc, char** argv) { //main function
    
    int z, valueoutput, i;
    
    printf("Please type in a positive integer: \n");
    scanf("%d", &z);

    for (i = 1; i < z + 1; i++) {
        valueoutput = isPerfect(i);

        if (valueoutput == 1) {
            printf("%d is a perfect number \n", i);
            printFactors(i);

        }

    }
    
    return (EXIT_SUCCESS);
}

