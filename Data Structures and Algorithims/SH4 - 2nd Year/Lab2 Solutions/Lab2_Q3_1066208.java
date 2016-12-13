/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on October 7, 2013, 4:34 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "lib.h"


/*
 * 
 */

void Printvector (double vector[], int size);

double norm1 (double vector[], int size);

void addVectors(double vector1[], double vector2 [], double vector3 [], int n);

double scalarProd (double vector1[], double vector2[], int n);

double norm2 (double vector[], int size);

int main(void) {
    
    double v1[] = {3.0, 4.0,5.0,6.0,-7.0};
    int number = 5; //the size of the v1 matrix
    double valuereturn, scalarreturn, squarereturn;
    Printvector(v1,number); //calls upon Printvector and gives the parameters v1 and number
    valuereturn = norm1 (v1,number); //stores the value that is returned by norm1
    printf("%.1f \n", valuereturn);
    double v2[] = {6.0,3.0,8.0,5.0,3.0};
    double v3[5];
    double *v3pointer = &v3; //asigns a pointer to the empty vector v3[5]
    addVectors(v1,v2,v3pointer,number); //calls upon the function addVectors
    int i =0; 
    for (i=0 ; i<5; i++){ //prints the values of the matrix v3[5] once addvector has assigned the values
        printf("%.1f ", v3[i]);
        }
    printf("\n");
    scalarreturn = scalarProd(v1,v2,number); //stores the value returned by the function scalarProd
    printf("%.1f \n", scalarreturn); //prints the above stored value
    squarereturn = norm2 (v1,number); //stores the value returned by the function squarereturn
    printf("%.1f \n", squarereturn); //prints the above stored value
}

double norm1(double vector[],int size){ //this function returns the sum of the absolute values of the vector components

    int i;
    double L1_norm = 0 ;
    
    for (i=0; i < size; i++){        
        if (vector[i] < 0){
                L1_norm += (vector[i] * -1);               
        }
        if (vector[i] >= 0){
                L1_norm += (vector[i]);        
        }
    }
    return L1_norm;
}

void Printvector (double vector[], int size){ //prints the components of the inputted vector

    int i;    
    for (i=0;i<size;i++){        
        if (i < size-1){
            printf("%.1f, ", vector[i]);            
        }
        else {        
            printf("%.1f", vector[i]);        
        }       
    }  
                   printf("\n");                   
}

void addVectors(double vector1[], double vector2 [], double vector3 [], int n){ // adds two vectors and stores there value in another vector

    int j;    
    for(j=0; j<n; j++){    
        vector3[j] += vector1[j]+vector2[j];   
    }
}

double scalarProd (double vector1[], double vector2[], int n){ //returns the scalar product of two vectors

int i;
double scalar = 0 ;    
    for (i=0; i < n; i++){          
        scalar += (vector1[i] * vector2 [i]);
    }
return scalar;
}

double norm2 (double vector[], int n){ //takes the scalar product of a vector with itself, then takes the square root of it
    
    double scalarproduct, L2_norm;    
    scalarproduct=scalarProd(vector,vector, n);
    L2_norm = sqrt(scalarproduct);    
    return L2_norm;
}