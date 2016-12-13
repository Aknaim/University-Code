/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on October 24, 2013, 12:23 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define N 3

/*
 * 
 */


int isDiagDom(int mat[][N]) {

    int i = 0, j = 0, sum = 0, diagonal, answer;

    for (i = 0; i < N; i++) {
        sum = 0;
        for (j = 0; j < N; j++) {
            if (i == j)
                diagonal = mat[i][j];
            else {
                sum = sum + myAbs(mat[i][j]);
            }



        }
        if (myAbs(diagonal) > sum) {
            answer = 1;
        } else {
            answer = 0;
            break;
        }
    }

    return answer;

}

int myAbs(int x) {

    int absolute;

    if (x >= 0) {
        absolute = x;

    }
    else {
        absolute = (-1) * x;
    }

}

int main(int argc, char** argv) {

    int mat[N][N] = {9, 1, 1, 1, 9, 1, 1, 1, 9};
    int mat2 [N][N] = {2, 3, 4, 5, 6, 7, 8, 9, 10};

    int i = 0, j = 0;
    i = isDiagDom(mat);
    j = isDiagDom(mat2);
    printf("value is: %d \n", i);
    printf("value is: %d \n", j);

    return (EXIT_SUCCESS);
}

