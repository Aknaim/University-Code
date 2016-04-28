#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <pthread.h>
#include <semaphore.h>
#include <stdbool.h>
#define num_threads 4

#define KNRM  "\x1B[0m"
#define KRED  "\x1B[31m"
#define KGRN  "\x1B[32m"
#define KYEL  "\x1B[33m"
#define KBLU  "\x1B[34m"
#define KMAG  "\x1B[35m"
#define KCYN  "\x1B[36m"
#define KWHT  "\x1B[37m"
#define RESET "\033[0m"


//global variables
int numberArray[4][4];  //initialize 4x4 array
pthread_mutex_t mutex;  //define mutex
sem_t *s1, *s2;         //define semaphore
int phase = 0;
bool switch_var = 0;     //switches threads from doing row sorts to column sorts
int counter = 0;        //number of threads done this stage

//step 4 - sort the array using shearsort

void *sort(void *threadid){  //sorting function each thread runs
 
    int i;  //row counter
    int j;  //column counter
    int k = 0;  //local array counter
    int local_array [4];
    int t_ID = (long)threadid;
    int temp;

    while(1){

        pthread_mutex_lock(&mutex);     //enter mutex

        //read into a 4 input array
        if (switch_var == 0){         //if switch_var is even read in rows
            k =0;
            for(j=0;j<4;j++){           //read in row = thread number
                local_array[k] = numberArray[t_ID][j];
                k++;
            }

            for(i=0;i<4;i++){
                printf(KRED "Local array_ROW is: %d t_ID: %d\n" RESET, local_array[i], t_ID);
            }
        }

        else if (switch_var == 1){    //if switch_var is odd read in columns
            k=0;
            for(j=0;j<4;j++){           //read in the column equal to thread number
                local_array[k] = numberArray[j][t_ID];
                k++;
            }

            for(i=0;i<4;i++){
                printf(KRED "Local array_COLUMN is: %d t_ID: %d\n" RESET, local_array[i], t_ID);
            }
        }    

        pthread_mutex_unlock(&mutex);   //exit mutex

        //printf("test - outside---- t_ID: %d\n", t_ID);

        
        if (t_ID %2 == 0 && switch_var == 0){      //sort the even rows from low to high
        //printf("test ---inside --- t_ID: %d\n", t_ID);

            temp = 0;

            for (j = 0; j < 4; j++){
                for (i = j + 1; i < 4; i++){
                    if (local_array[i] < local_array[j]){
                        temp = local_array[i];
                        local_array[i] = local_array[j];
                        local_array[j] = temp;
                    }
                }
            }

            //print modified array:
            for(i=0;i<4;i++){
                printf(KGRN "modified local array_ROW_L_H is: %d t_ID: %d\n" RESET, local_array[i], t_ID);
            }
        }

        
        else if (t_ID %2 == 1 && switch_var == 0){      //sort the odd rows from high to low
            
            temp = 0;

             for (j = 0; j < 4; j++){
                for (i = j + 1; i < 4; i++){
                    if (local_array[i] > local_array[j]){
                        temp = local_array[i];
                        local_array[i] = local_array[j];
                        local_array[j] = temp;
                    }
                }
            }

            //print modified array:
            for(i=0;i<4;i++){
                printf(KYEL "modified local array_ROW_H_L is: %d t_ID: %d\n" RESET, local_array[i], t_ID);
            }

        }

        else if (switch_var == 1){      //sort the the columns from low to high
            
            temp = 0;

             for (j = 0; j < 4; j++){
                for (i = j + 1; i < 4; i++){
                    if (local_array[i] < local_array[j]){
                        temp = local_array[i];
                        local_array[i] = local_array[j];
                        local_array[j] = temp;
                    }
                }
            }

            //print modified array:
            for(i=0;i<4;i++){
                printf(KCYN "modified local array_Coulmn is: %d t_ID: %d\n" RESET, local_array[i], t_ID);
            }

        }

        //enter mutex
              
        pthread_mutex_lock(&mutex);        

        if (switch_var == 0){         //if switch_var is even write in rows
            k = 0;
            for(j=0;j<4;j++){           //write in the row equal to thread number
                numberArray[t_ID][j] = local_array[k];
                k++;
            }

            for (i = 0; i < 4; i++){    //print phase row
                printf(KMAG "ROW Final --------- [%d][%d] is: %d , t_ID: %d\n" RESET, t_ID,i, numberArray[t_ID][i], t_ID);
            }
        }

        else if (switch_var == 1){    //if switch_var is odd write in columns
            k = 0;
            for(j=0;j<4;j++){           //write in the column equal to thread number
                numberArray[j][t_ID] = local_array[k];
                k++;
            }

            for (i = 0; i < 4; i++){    //print 
                printf(KBLU "COLUMN Final *********** [%d][%d] is: %d , t_ID: %d\n" RESET, i,t_ID, numberArray[i][t_ID], t_ID); 
            }
        }              

        if (counter < 3){   //if less then 4 threads are done this stage block them

            counter++; //increment the number of processes that are done this phase
            printf("counter test: %d \n", counter);

            pthread_mutex_unlock(&mutex); //exit mutex before blocking thread

            printf("************BLOCKED*************: t_ID: %d , phase: %d\n", t_ID, phase);
            sem_wait(s1);      //decrement semaphore s1
            printf("$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: %d , phase: %d\n", t_ID,phase);            

            //pthread_mutex_lock(&mutex);

            //printf("$$$$$$ UNBLOCKED $$$$$$$ t_ID: %d , phase: %d\n", t_ID,phase);
        }

        else{   //once the 4th thread is done this stage unblock the previous 3 threads and move onto the next phase
            printf("else condition \n");
            for(i=0;i<4;i++){           //increment semaphore s1 by 3
                printf("i is: %d \n", i);
                sem_post(s1);
                printf("$$$$$$ print sem_post $$$$$$$ t_ID: %d , phase: %d\n", t_ID,phase);
            }
            
            phase++;                    //increment the phase
            counter = 0;                //reset counter
            printf("counter reset: %d \n", counter);
            switch_var = !switch_var;   //switch from rows to columns or vice versa

            printf("phase test: %d \n", phase);
            
            if(phase == 5){
                printf("exit test: %d \n", phase);
                sem_post(s2);
                pthread_exit(NULL);
            }

            pthread_mutex_unlock(&mutex);   //exit mutex after setting variables for next phase            
        }

        // pthread_mutex_unlock(&mutex);   //exit mutex        
    }

}

int main()
{
        //step 1 - read from file into 4x4 array

        FILE *myFile;
        myFile = fopen("input.txt", "r");

        int i; //row counter
        int j; //column counter

        for (i = 0; i < 4; i++){    //read into 4x4 array
        	for (j = 0; j < 4 ; j++){
        		fscanf(myFile, "%d,", &numberArray[i][j]);
        	}    
        }

        //step 2 - print 4x4 array

        fclose(myFile); //close file

        for (i = 0; i < 4; i++){    //print 4x4 array
        	for (j = 0; j<4; j++){
        		printf("Input [%d][%d] is: %d \n", i,j, numberArray[i][j]);
        	}    
        }


        //step 3 - initialize semaphores/mutex variables

        pthread_mutex_init(&mutex, NULL);  //initialize mutex variables

        s1 = sem_open("sem1", O_CREAT, 0644, 0); //initialize semaphores
        s2 = sem_open("sem2", O_CREAT, 0644, 0);

        //step 4

        pthread_t threads[num_threads]; //initialize threads
        int rc;
        long t;
        for(t=0; t<num_threads; t++){
            rc = pthread_create(&threads[t], NULL, sort, (void *)t); //creates threads to run sort()
            printf("Creating thread %ld...\n",t);
            if (rc){
                printf("ERROR; return code from pthread_create() is %d\n", rc);
                exit(-1);
            }
        }

    sem_wait(s2);

    for (i = 0; i < 4; i++){    //print shear sorted 4x4 array
        for (j = 0; j<4; j++){
            printf("Final Array [%d][%d] is: %d \n", i,j, numberArray[i][j]);
        }    
    }
}



