/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on October 24, 2013, 12:47 PM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */


void process(double y[], int m, double *avPtr, double *difPtr);

void process(double y[], int m, double *avPtr, double *difPtr){

    int j=0;
    double x=0,z=0,average=0;
    
    for (j=0;j<m;j++){
    
        if (j==0){
            x = y[j];
            z = y[j];
             }
        else if (y[j]>x){
            x = y[j];
        }
        else if (y[j]<z){
            z = y[j];
        
        }
        else {}
        average = average + y[j];
    } 
    *difPtr = (x-z);
    *avPtr = (average/m);
    
        

}

int main(int argc, char** argv) {
    
    double *Ptr1;
    double avg;
    double difference;
    int x,y;
    
    printf("Please enter a value for n: \n");
    scanf("%d",&x);
    
    Ptr1 = (double*)malloc(x*sizeof(double));
    
    for(y=0;y<x;y++){
        scanf("%lf",&Ptr1[y]);   
    }
    
    process(Ptr1,x,&avg,&difference);
    
    printf("Average is: %.2f\n", avg);
    printf("Difference is: %.2f\n", difference);
    
    

    return (EXIT_SUCCESS);
}

