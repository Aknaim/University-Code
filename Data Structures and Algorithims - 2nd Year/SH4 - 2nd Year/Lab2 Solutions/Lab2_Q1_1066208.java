/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on October 6, 2013, 6:24 PM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */

int qualityPoints (float avg) { // returns a value for grade in a 4 point scale

int grade; 

    if (avg >= 90 && avg <= 100 ) //checks if the grade falls between 90 and 100 then assigns a value of 4 to the output variable
                grade = 4;
    else if (avg >= 80 && avg < 90) //checks if the grade falls between 80 and 90 then assigns a value of 3 to the output variable
                grade = 3;
    else if (avg >= 70 && avg < 80) //checks if the grade falls between 70 and 80 then assigns a value of 2 to the output variable
                grade = 2;
    else if (avg >= 60 && avg <70) //checks if the grade falls between 60 and 70 then assigns a value of 1 to the output variable
                grade = 1;
    else if (avg >=0  && avg < 60) //checks if the grade falls between 50 and 60 then assigns a value of 0 to the output variable
                grade = 0; 
    else    
             grade = -1; // if the grade does not fall between 0 and 100 a grade of -1 is returned 

 return grade; //returns the grade to the main function
      
}
int main(int argc, char** argv) {
    
    float avg;
    int gra;    
    
    printf("Please enter a value for average: \n");
    scanf("%f", &avg);
    gra = qualityPoints(avg);  //calls upon qualityPoints, and inputs the average that the use picked
    printf("Your grade is %d \n", gra); //prints the value returned by qualityPoints
    

    return (EXIT_SUCCESS);
}

