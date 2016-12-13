/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on September 17, 2013, 5:39 PM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
int main(int argc, char** argv) {

    
int x= -1, rev = 0 ;



while (x <= 0)
{
    printf("please enter a positive integer: \n");
    scanf("%d",&x);
    if (x <= 0)
    printf("That was not a positive integer, ");
    
}
    
// debugg code
//printf("%d \n",x);

while (x != 0 ) {
    
    rev = rev *10;
    rev = rev + x%10;
    x = x/10;
        
    
 // printf(" rev is %d, x is %d \n", rev, x);    
 // Debug code
    
    
}         
        
printf("The reverse of the number is %d: \n", rev);
    
     
     
    
    
    
    return (EXIT_SUCCESS);
}

