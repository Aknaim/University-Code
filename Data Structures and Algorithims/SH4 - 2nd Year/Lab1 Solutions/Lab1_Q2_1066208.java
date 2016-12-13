/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on September 13, 2013, 8:40 PM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
int main(int argc, char** argv) {
    
 int n,x,num,max;
 
printf("Please enter the value of n:"); 
scanf("%d",&n);
    
for (x=1; x<=n; x++)
{
    printf("Please enter number %d:",x);
    scanf("%d",&num);
    
if (num > max)
        
        max = num;
   
    
           
}

printf("The maximum value entered was: %d \n", max);


    return (EXIT_SUCCESS);
}

