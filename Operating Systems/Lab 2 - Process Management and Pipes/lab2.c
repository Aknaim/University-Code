//ps: report process status
//Question 1-----------
//ps -e: select all processes
//ps -1: select process in current folder????
//grep filters based on your search string
//Question 2-----------
//ps -e | grep Google
//look right under the next terminal line to find the PID NOT at the end!
//kill -15 (send term sig) 19771 (process ID)
//Question 3------------- ./a.out
// #include <stdio.h>
// #include <unistd.h> 
// #include <stdlib.h> 
// #include <sys/types.h>

// int main(){
// if (fork() == 0) 
// 	printf("This is the child process\n"); 
// else 
// 	printf("I am the parent process\n");

// printf("Both parent and child reach here.\n"); 
// return 0;
// }
// 2 times, once for each the parent and the child
//Question 4-------------
//pstree displays all the current processes in a tree diagram

//forks and pipes

#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include <unistd.h> 
#include <sys/types.h>
#include <fcntl.h>

int main(void){

	int fd[2], fd1[2];
	int nbytes = 0;
	int i = 0;
	int sum = 0 ;
	pid_t childpid; //pid_t represents a process ID
	char string[30]; 
	char readbuffer [80];
	int counter = 0;
	char array [30];
	char sum_char[80];	
	char final[80];
	int sum_flag = 0;
	int x = 1;
	char test[80];


	pipe(fd); //create a pipe fd, where the 1st element fd[0] is for reading
			  //2nd element fd[1] is for writing
	pipe(fd1);

	//int flags = fcntl(fd1[0], F_GETFL, 0);
	//fcntl(fd1[0], F_SETFL, flags | O_NONBLOCK);

// while(1){
		if((childpid = fork()) == -1){ //if child process was not created properly
			perror("fork"); //print error
			exit(0); //exit the program
		}

		if(childpid == 0){ //child process is running
			close(fd[0]); //close the reading end for child
			close(fd1[1]); //close the writing end for child
			while (1){
				if (x == 0){
				read(fd1[0],final,sizeof(final));
				nbytes = atoi(final);
				//printf("nbytes is: %d \n", nbytes);
				}
				x=0;
				
				if(nbytes > 0){
					printf("Sum was: %s \n", final);
					exit(0);
				}
				//else if (nbytes == -1){
					printf("Type in a number \n");
					fgets(string,sizeof(string),stdin);
				    write(fd[1],string,strlen(string)+1); //writes from a buffer declared by user to a given device
				    //write (int fd //input, const void *buf //points to character array, size_t nbytes //number of bytes to be written)
				//}
			    //printf("nbytes is: %d \n", nbytes);
			    //printf("final is: %s \n", final);

			}

		}

		else{
			close(fd[1]); //close the writing end for parent
			close(fd1[0]); //close the reading end for parent
			while (1) {
				read(fd[0],readbuffer,sizeof(readbuffer));
				// for (i=0;i<strlen(readbuffer)-1;i++){
				// 		printf("what is in read buffer: %s \n", readbuffer);
				// }
				printf("Received string: %s \n", readbuffer);

				//sum the integer values together and then send back once int is -1
				if(atoi(readbuffer) == -1){
					//printf("working \n");
					//sum_flag = 1;
					for (i=0;i<(strlen(array));i++){
						int char_to_int = (array[i] - '0');
							//printf("int after conv %d \n", char_to_int);
						sum = sum + char_to_int;
					}
					//printf("Sum is: %d \n", sum);
					sprintf(sum_char,"%d",sum);
					//printf("sum_char is: %s \n",sum_char);
					write(fd1[1],sum_char,strlen(sum_char)+1);
				}
				else{
					array[counter] = *readbuffer;
					counter = counter + 1;	
					//printf("not working \n");
				  //   for (i=0;i<sizeof(array);i++){
						// printf("what is in array buffer: %c \n", array[i]);
				  //   }
					write(fd1[1],test,strlen(test)+1);			
				}
			}
		}
// }
		return 0;
	}



