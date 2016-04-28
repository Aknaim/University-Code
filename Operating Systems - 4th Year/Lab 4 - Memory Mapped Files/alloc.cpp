#include <stdio.h>
#include <stdlib.h>
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
    char buf[50];

    fd = open(FILEPATH, O_RDONLY);
    if (fd == -1) {
    perror("Error opening file for reading");
    exit(EXIT_FAILURE);
    }

    map = (char *)mmap(0, FILESIZE, PROT_READ, MAP_SHARED, fd, 0);
    if (map == MAP_FAILED) {
    close(fd);
    perror("Error mmapping the file");
    exit(EXIT_FAILURE);
    }

    printf("testing: %d\n", atoi(&map[4]))   ;
    
    while(1){

        printf("Requested resource and amount needed:");
        scanf("%d, %d", &resource, &amount);
        printf("Input was: %d, %d", resource, amount);

        if (resource == atoi(&map[0]) && (&map[2]-amount) >= 0){

            map[2] = sprintf(buf, "%d", (atoi(&map[2]) - amount));
            msync(map, FILESIZE, MS_SYNC);

        }
        else if (resource == atoi(&map[4]) && (&map[6]-amount) >= 0){

            map[6] = sprintf(buf, "%d", (atoi(&map[6]) - amount));
            msync(map, FILESIZE, MS_SYNC);

        }
        else if (resource == atoi(&map[8])  && (&map[10]-amount) >= 0){

            map[10] = sprintf(buf, "%d", (atoi(&map[10]) - amount));
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