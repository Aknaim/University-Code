#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/mman.h>

#define FILEPATH "res.txt"
#define NUMINTS  (1000)
#define FILESIZE (NUMINTS * sizeof(int))

int main(int argc, char *argv[])
{
    int fd;
    char *map;  /* mmapped array of int's */
    int resource;
    int amount;
    int fresource;
    int famount;
    char buf[500];
    char request[500];
    pid_t pid;
    int pagesize;
    char mincore_vec;
    int mincore_int;
    size_t mincore_size = sizeof(map);

    fd = open(FILEPATH, O_RDWR);
    if (fd == -1) {
    perror("Error opening file for reading");
    exit(EXIT_FAILURE);
    }

    map = (char *)mmap(0, FILESIZE, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);
    if (map == MAP_FAILED) {
    close(fd);
    perror("Error mmapping the file");
    exit(EXIT_FAILURE);
    }

    pid = fork();

    if (pid == 0){

        while(1){

        sleep(10);
        pagesize = getpagesize();
        printf("getpagesize is: %d \n", pagesize);
        printf("Res.txt is: %s \n", map);
        mincore_int = mincore(map, mincore_size, &mincore_vec);
        printf("mincore is: %d \n", mincore_int);
        
        
        }

    }

    else{

        while(1){

            printf("Are new resources needed?: Y|N \n");
            scanf("%s", request);

            if (request[0] == 'Y'){

                printf("Enter requested resource type: \n");
                scanf("%d", &resource);
                printf("Enter amount needed: \n");
                scanf("%d", &amount);
                printf("Input was: %d, %d \n", resource, amount);

                if (resource == atoi(&map[0])){
                    sprintf(buf, "%d", (atoi(&map[2]) + amount));
                    map[2] = buf[0];
                    msync(map, FILESIZE, MS_SYNC);
                }
                else if (resource == atoi(&map[4])){
                    sprintf(buf, "%d", (atoi(&map[6]) + amount));
                    map[6] = buf[0];
                    msync(map, FILESIZE, MS_SYNC);
                }
                else if (resource == atoi(&map[8])){
                    sprintf(buf, "%d", (atoi(&map[10]) + amount));
                    map[10] = buf[0];
                    msync(map, FILESIZE, MS_SYNC);
                }

                else {
                    printf("No such resource \n");
                }
            }

            else {
                printf("Resource was not requested \n");
            }

            // if (munmap(map, FILESIZE) == -1) {
            // perror("Error un-mmapping the file");
            // }
            // close(fd);
            // return 0;
        }
    }


}