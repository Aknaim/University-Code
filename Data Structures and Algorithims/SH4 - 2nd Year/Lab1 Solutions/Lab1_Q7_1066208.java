/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on September 20, 2013, 5:50 PM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
int main(int argc, char** argv) {
    
// code for square shape ****************************************
int n,i,c; 

printf("Please enter an odd value of n between 1 and 25: \n");
scanf("%d", &n);

while (n <= 1 || n >= 25 || n%2 == 0 )  { 
            
    printf("Error value condition not met. \n");
    return 0;
    
}     
       
        
        
         
    
 for (i=0; i<n; i++ ){
              
     for (c=1; c <= 2*n-1 ; c++)
    
        printf("*");
     
     printf("\n");
}
 
 
// code for triangle shape **************************************
  
int q,e,r=0;

    
 for (q=0; q < n; q++){
    
     for (i=0; i < n-r-1; i++)                                
        
         printf(" ");    
        
     for (e=0; e < 1+(2*r) ; e++)
       
        printf("*");
        
     r++;     
     printf("\n");       
    
}
    
// code for sideways triangle **********************************
    
    int x,y;
  
      
      for (x=n; x>=1; --x){
          
          for (y=1; y <=x; y++)
              
              printf("* ");
          
          printf("\n");
          
      }
      
//Code for hourglass shape ***************************************
      
r=0;

int b;
        

  for (q=0; q < n; q++){
              
    
        for (i=0; i < r; i++)                                
        
                printf(" ");    
        
        for (e=0; e < n- 2*r ; e++)
       
                printf("*");
        
        if (q < n/2)
                
            r++;
                
        else 
             --r;
        
        printf("\n"); 
      
  }


    
    
    
    
    return (EXIT_SUCCESS);
}

