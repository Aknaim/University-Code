/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on September 20, 2013, 5:46 PM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
int main(int argc, char** argv) {
    
int n,i,x, num;
double Pi=0;
    
    printf("Please enter a positive value for n: \n");
    scanf("%d",&n);
    
    for (i=0; i<n ;i++){
        
        if(i%2 == 0)
        Pi += 4.0/(1 + 2*i);       
              
        else
        Pi -= 4.0/(1 + 2*i);
        
    }        
   
    printf ("The value of Pi is: %f \n", Pi);
    
    // Question Part B
    
    Pi = 0, i=0;
    
    while (num != 31415){
                
        if(i%2 == 0)
        Pi += 4.0/(1 + 2*i);       
              
        else
        Pi -= 4.0/(1 + 2*i);
        
     i++;   
        
       num = Pi * 10000;
    }
    
    
    printf("%d \n", i);
   
   
    

    return (EXIT_SUCCESS);
}

