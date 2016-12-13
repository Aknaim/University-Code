/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on November 13, 2013, 8:36 PM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */

typedef struct{
    
    int studentNum;
    int midterm;
    int exam;
    float coursemark;
    
}student;

student **create_class_list(int *sizePtr);
int find(int idNo, student **list, int size);
void input_grades(student **list, int size);
void compute_final_course_marks(student **list, int size);
void output_final_course_mark(student **list, int size);
void print_backwards(student **list, int size);
int withdraw(int idNo, student **list, int size);
void destroy_list(student **list, int size);




student **create_class_list(int *sizePtr){
    
    int i;
    
   FILE *input_file=fopen("ID.txt","r");
    
   fscanf(input_file,"%d", sizePtr);
   student **termgrades;
   int temp;
   termgrades = (student**)calloc(*sizePtr,sizeof(student*));
   
   for (i=0; i < *sizePtr;i++) {
       fscanf(input_file,"%d",&temp );
       
       termgrades[i]=(student*)calloc(1,sizeof(student));
       termgrades[i]->studentNum = temp;
       
   
        
   } 
   fclose(input_file);
   return termgrades;
}

int find(int idNo, student **list, int size){

    int middle, low = 0, high = size -1;
    
    while(low <=high){
    
        middle = (low+high)/2;
        if (idNo == list[middle]->studentNum)
            return middle;
        if ( idNo > list [middle]->studentNum )
            low = middle +1;
        else high = middle -1;
    }
    return -1;
    
    
    }
    
    
void input_grades(student **list, int size){

    int i;
    
   FILE *input_file=fopen("grades.txt","r");
   
   int studentNum;
   int midterm;
   int exam;
   
   for (i=0;i<size;i++){
      fscanf(input_file,"%d",&studentNum);
      int studentinfo = find(studentNum,list,size);
      fscanf(input_file,"%d",&midterm);
      list[studentinfo]->midterm = midterm;
      fscanf(input_file,"%d",&exam);
      list[studentinfo]->exam = exam;
   
   }  
       
   fclose(input_file);  
      
   } 

void compute_final_course_marks(student **list, int size){

    int i,midterm,exam;
    float final;
    
    for(i=0; i<size; i++){
        midterm=list[i]->midterm;
        exam=list[i]->exam;
        
        final=(midterm*0.3+final*0.7);
        
        list[i]->coursemark=final;
    }
    
    
}

void output_final_course_mark(student **list, int size){

    int i;
    
    FILE *output_file=fopen("coursemarks.txt","w");
    
    for (i=0;i<size;i++){
        fprintf(output_file,"%d %.3f \r\n",list[i]->studentNum,list[i]->coursemark);
    
    }

    fclose(output_file);
}

void print_backwards(student **list, int size){
    
    if (size>0){
        
        printf("%d\n",list[size-1]->studentNum);
        print_backwards(list,size-1);
           
    }
    else return;
          
    
}

int withdraw(int idNo, student **list, int size){
    int i;
    int index = find(idNo,list,size);
    if (index == -1) {
    return 0;
    }
    else {
        free(list[index]);
        for(i=index;i<size-1;i++){
            list[i]=list[i+1];        
        }        
    }  
    return 1;
}

void destroy_list(student **list, int size){

    int i;
    for(i=0;i<size;i++){
    free(list[i]);
    
    }
    free(list);
}


int main(int argc, char** argv) {
    
int i=5, size;
    
student** list = create_class_list(&size);
printf("ID 1243567 is at INDEX %d\n", find(1243567, list, size));
input_grades(list, size);
compute_final_course_marks(list, size);
output_final_course_mark(list, size);
print_backwards(list, size);
size -= withdraw(1264535, list, size);
printf("\n\n");
print_backwards(list, size);
destroy_list(list, size);
   
    
    

    return (EXIT_SUCCESS);
}

