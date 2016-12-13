/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on October 24, 2013, 1:18 PM
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * 
 */

char *myStrcat(const char * const str1, const char * const str2);


char *myStrcat(const char * const str1, const char * const str2){

    int x,y,length1,length2;
    char *Ptr1;
    
    length1 = strlen(str1);
    length2 = strlen(str2);
    
    Ptr1 = (char*)malloc((length1+length2+1)*sizeof(char));
    
    for (x=0;x<length1;x++){
        Ptr1[x] = str1[x];
    
    }

    for (y=0;y<length2;y++){
        Ptr1[y+x] = str2[y];    
    }
    
    Ptr1[x+y+1] = '\0' ;
    
    return Ptr1;

}
int main(int argc, char** argv) {
    
    printf("%s \n", myStrcat("Hello", "world!"));
    
    return (EXIT_SUCCESS);
}

