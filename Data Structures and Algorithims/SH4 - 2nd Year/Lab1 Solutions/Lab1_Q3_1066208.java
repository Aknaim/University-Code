/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on September 14, 2013, 10:07 AM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
int main(int argc, char** argv) {
    
printf("Part A \n");
    
int y;

printf("Please enter a value for x (0 to end):");
scanf("%d",&y);

if (y==0)
    
    printf("Thank you for using my software, Goodbye! \n");
   
else 
    
        if (y<1000 && y>99 || y<-99 && y>-1000)
                
                printf("Your input is a three-digit number: %d \n", y);
   
        else printf("Your input is not a three-digit number: %d \n", y);


printf("Part B \n");

int x=1;

while (x != 0){
        
printf("Please enter an integer (0 to end):");
scanf("%d",&x);


if (x<1000 && x>99 || x<-99 && x>-1000){
    
    printf("That Number is 3 Digits: %d \n", x);
}
else {
    printf("That Number is not 3 Digits: %d\n", x);    
}
  

}
    
    printf("Goodbye!\n");

    return (EXIT_SUCCESS);
}

