/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on September 14, 2013, 11:19 AM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
int main(int argc, char** argv) {

    
    
char c,x;
int y=0;

printf("What letter do you want counted:");
scanf("%c", &x);


printf("Please enter your sentence (end with a !): \n");
        

while((c = getchar()) !='!')
{
   
    if (c==x){
        y++;
    }
    
}     
    
printf("Your letter appeared %d times. \n", y);
    
    
    
    
    return (EXIT_SUCCESS);
}

