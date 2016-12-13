/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on October 7, 2013, 6:01 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include "lib.h"

/*
 * 
 */

void stringCopy(const char source[], char destination[], int n);

int charFind(const char string[], char ch);

void letterFreq(const char string[], int freq[]);




void stringCopy(const char source[], char destination[], int n){ //this function copies the input array and stores it under the name source

    int i;    
    for (i=0; i<n; i++){    
    destination[i] = source[i];    
    }
    destination[n-1] ='\0';
}

int charFind(const char string[], char ch){ //this function checks how many times a letter appears in a string
    
    int i, position;    
    while(string[i] != '\0'){ //runs a while loop as long as the value at i isn't equal to null
    
        if (string[i] == ch) { // checks to see if the value of i equals the value of the character
            position = i;     // increases the counter by 1 if the letter appears in the string   
            break;
        }
        else {
            position = -1;     //if it doesn't appear it assigns a value of -1   
        }
     i++;
    }
    return position; 

}

void letterFreq(const char string[], int freq[]) { //checks the frequency of each letter in a string and stores the value in an array

    int i;  

    for (i = 0; i < 26; i++) { //sets all the values in the string to 0
        freq[i] = 0;
    }

    i=0;

    while (string[i] != '\0') { //runs a while loop as long as i doesn't equal null
        if (string[i] >= 65 && string[i] < 91) { //if the value of i falls in between 65-90 it increases the value of the counter by 1
            freq[string[i]-65] += 1;
        } 
        else if (string[i] >= 97 && string[i] < 123) { //if the value of i falls in between 97-122 it increases the value of counter by 1
            freq[string[i]-97] += 1;
        }
        i++;
    }


}  



int main(int argc, char** argv) {
     
    
    int num = 6, i;
    char ch = 'z';
    char sentence1[]={'a','b','c','d','e','f','g','h','A','Z'}; //stores a string with some values
    char sentence2[num]; //creates an empty string with a size num =6
    char *pointer1 = &sentence2; //creates a pointer to sentence2
    stringCopy(sentence1,  pointer1, num); //calls the function stringCopy
    printf("%s \n", sentence2);    //prints the string sentence2
    printf("The character position is: %d \n", charFind(sentence1, ch)); //searches for an input character
    int alphabets[26]; //creates an empty array with size 26
    int *pointer2 = &alphabets; //creates a pointer to alphabets
    letterFreq(sentence1,pointer2); //calls upon the function letter frequency
    for (i=0;i<26;i++) //prints the array alphabets which stores the frequency of the letters
    printf("%d ",alphabets[i]);
    

    return (EXIT_SUCCESS);
}