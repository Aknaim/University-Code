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
    // char resource[100];
    // char amount[100];
    // int resource_in;
    // int amount_in;
    // char * resource;
    // char * amount;
    // resource = (char *) malloc(50);
    // amount = (char * ) malloc(50);
    int resource;
    int amount;
    int fresource;
    int famount;
    char buf[50];

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

    // printf("testing map[0]: %d\n", atoi(&map[0]));
    // printf("testing map[2]: %d\n", atoi(&map[2]));
    // printf("testing map[4]: %d\n", atoi(&map[4]));
    // printf("testing map[6]: %d\n", atoi(&map[6]));
    // printf("testing map[8]: %d\n", atoi(&map[8]));
    // printf("testing map[10]: %d\n", atoi(&map[10]));
    
    while(1){

        printf("Requested resource: \n");
        scanf("%d", &resource);
        printf("Amount needed: \n");
        scanf("%d", &amount);
        printf("Input was: %d, %d \n", resource, amount);

        // printf("Requested resource: ");
        // fgets(resource,100, stdin);
        // printf("Amount needed: ");
        // fgets(amount,100, stdin);
        // printf("Input resource was: %s", resource);
        // printf("Input amount was: %s", amount);

        // resource_in = atoi(resource);
        // printf("here\n");
        // amount_in = atoi(amount);
        // printf("here2\n");
        // int test = resource_in + amount_in;
        // printf("%d\n", test);
        // printf("Input resource was: %d", resource_in);
        // printf("Input amount was: %d", amount_in);

        // int line = 0;
        // while(scanf(map, "%d %d", &fresource, &famount) == 2)
        // { 
        //     if (fresource == resource)
        //     {
        //         if ((famount - amount) >= 0){
        //             map[line*4 + 2] = famount - amount;
        //             msync(map, FILESIZE, MS_SYNC);
        //         }
        //         else {
        //             printf("Not enough of resource \n");
        //         }
        //     }

        //     line++;
        // }

        if (resource == atoi(&map[0]) && (atoi(&map[2])-amount) >= 0){
            sprintf(buf, "%d", (atoi(&map[2]) - amount));
            map[2] = buf[0];
            msync(map, FILESIZE, MS_SYNC);
        }
        else if (resource == atoi(&map[4]) && (atoi(&map[6])-amount) >= 0){
            sprintf(buf, "%d", (atoi(&map[6]) - amount));
            map[6] = buf[0];
            msync(map, FILESIZE, MS_SYNC);
        }
        else if (resource == atoi(&map[8])  && (atoi(&map[10])-amount) >= 0){
            sprintf(buf, "%d", (atoi(&map[10]) - amount));
            map[10] = buf[0];
            msync(map, FILESIZE, MS_SYNC);
        }
        else {
            printf("Not enough of resource \n");
        }

    }

    if (munmap(map, FILESIZE) == -1) {
    perror("Error un-mmapping the file");
    }
    close(fd);
    return 0;
}