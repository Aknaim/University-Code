/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on October 24, 2013, 3:08 PM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */

char **readWords(int *nPtr);

void outputWords(char**sArray, int size);


char **readWords(int *nPtr){
    
   char tempstring [30];
   int n,i;
   int wordLen;
   FILE *input_file=fopen("inputfile.txt","r");
    
   fscanf(input_file,"%d",&n);
   char** sArray = (char**)malloc(n*sizeof(char*));
   
   wordLen = strlen (tempstring); 
   
   for (i=0;i<=wordLen;i++){
       fscanf(input_file,"%s",tempstring);
                   
                sArray[i] = (char*)malloc((wordLen+1)*sizeof(char));   
                strcpy (sArray[i],tempstring);
   }
   *nPtr = n;
   fclose(input_file);
   return sArray;
}

void outputWords(char**sArray, int size){
    
    int i=0;

    FILE *output_file=fopen("outputfile.txt","w");
    
    fprintf(output_file, "%d \r\n",size);
    
    for(i=0;i<size;i++){    
        fprintf(output_file,"%s \r\n",sArray[i]);
    }
fclose(output_file);
}

int main(int argc, char** argv) {
    
    int i;
    
    char **sArray = readWords(&i);
    outputWords (sArray, i);
    
    
    
    
    

    return (EXIT_SUCCESS);
}

