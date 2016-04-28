#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <pwd.h>
#include <grp.h>
#include <dirent.h>
#include <string.h>

void listdir(char const* dirname, int level){

    char* subdir;
    DIR* current;
    struct dirent *current_struc;
    char buf[512];
    //char subdir[512];

    // if (current == NULL){ //if current directly is a dead end return
    //     return;
    // }

    if (!(current = opendir(dirname)))
        return;
    //printf("!dir");
    

    while( (current_struc = readdir(current)) != NULL){
        // printf("%s\n", current_struc->d_name);
        if( (current_struc->d_type) == DT_DIR ){

            subdir = malloc(strlen(dirname) + strlen(current_struc -> d_name) + 2);
            strcpy(subdir, dirname);
            strcat(subdir, "/");
            strcat(subdir, current_struc -> d_name);
            //buf[subdir] = 0;

            //int subdir = snprintf(buf, sizeof(buf)-1, "%s/%s", dirname, current_struc->d_name);
            //buf[subdir] = 0;

            if ( ( strcmp(current_struc->d_name, ".") == 0) || (strcmp(current_struc->d_name,"..") == 0) )
                continue;
                // subdir = malloc(strlen(dirname) + strlen(current_struc -> d_name) + 2);
                // strcpy(subdir, dirname);
                // strcat(subdir, "/");
                // strcat(subdir, current_struc -> d_name);
                //sprintf(buf, "%s/%s", current, current_struc->d_name);
                //printf("%s\n", current_struc->d_name);
                // printf("hello\n");
                
                // listdir(subdir, level+1);
                // free(subdir);
                
                printf("level is: %d, directory is: %s\n", level, current_struc->d_name);
                //printf("\n");
                listdir(subdir, level+1);
                free(subdir);

                // if ( (current_struc -> d_name == dirname) ){
                //     return;
                // }
            
        }

        else {
            //printf("hello2\n");
            //printf("\n");
            printf("level is: %d, non directory entry: %s\n", level, current_struc->d_name);
            //printf("\n");
        }

    }

    closedir(current);

    return;
}



int main(int argc, char **argv)
{
    // if(argc != 2)
    //     return 1;

    struct stat fileStat;
    if(stat(argv[1],&fileStat) < 0)    
        return 1;

    char* filename = argv[1];

    if (S_ISREG(fileStat.st_mode)){

        printf("The file %s a symbolic link\n", (S_ISLNK(fileStat.st_mode)) ? "is" : "is not");
        
        if (S_ISLNK(fileStat.st_mode) != 1){
            printf("Information for %s\n",argv[1]);
            printf("---------------------------\n");

            printf("File Permissions: \t");
            printf( (S_ISDIR(fileStat.st_mode)) ? "d" : "-");
            printf( (fileStat.st_mode & S_IRUSR) ? "r" : "-");
            printf( (fileStat.st_mode & S_IWUSR) ? "w" : "-");
            printf( (fileStat.st_mode & S_IXUSR) ? "x" : "-");
            printf( (fileStat.st_mode & S_IRGRP) ? "r" : "-");
            printf( (fileStat.st_mode & S_IWGRP) ? "w" : "-");
            printf( (fileStat.st_mode & S_IXGRP) ? "x" : "-");
            printf( (fileStat.st_mode & S_IROTH) ? "r" : "-");
            printf( (fileStat.st_mode & S_IWOTH) ? "w" : "-");
            printf( (fileStat.st_mode & S_IXOTH) ? "x" : "-");
            printf("\n");
            printf("Number of links: \t%d\n",(int)fileStat.st_nlink);
            //printf("File owner ID: %s\n",(char *)fileStat.st_uid);
            printf("File owner name: %s\n",getpwuid(fileStat.st_uid)->pw_name);
            //printf("Group name: %s\n",(char *)fileStat.st_gid);
            printf("Group name: %s\n",getgrgid(fileStat.st_gid)->gr_name);
            printf("File size in bytes: %d bytes\n",(int)fileStat.st_size);
            printf("File size in blocks: %d bytes\n",(int)fileStat.st_blocks);
            printf("Last modification time: %d\n",(int)fileStat.st_mtime);
            printf("File Name: %s\n",filename);
        }
    }

    else if (S_ISDIR(fileStat.st_mode)){


        listdir(argv[1],0);       //call function listdir to list contents of all subdirectories

        //DIR* mydir;
        //mydir = opendir(argv[1]);
        //listdir(argv[1]);       //call function listdir to list contents of all subdirectories

        // struct dirent *myfile;
        // struct stat mystat;

        //char buf[512]; 
        // mydir = opendir(argv[1]);
        // listdir(char* mydir);
        //while((myfile = readdir(mydir)) != NULL){
            //sprintf(buf, "%s/%s", argv[1], myfile->d_name);
            //printf("%s \n", myfile->d_name);
            // listdir(mydir);
        //}
        //closedir(mydir);
    }

    return 0;
}




// --------------------- old code ---------------------------

// void listdir(const char *name, int level)
// {
//     DIR *dir;
//     struct dirent *entry;

//     if (!(dir = opendir(name)))
//         //printf("!dir");
//         return;

//     do {
//         //printf("in loop");
//         if (entry->d_type == DT_DIR) {
//             //printf("in if statement#1");
//             char path[1024];
//             int len = snprintf(path, sizeof(path)-1, "%s/%s", name, entry->d_name);
//             path[len] = 0;
//             if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
//                 continue;
//             printf("%*s[%s]\n", level*2, "", entry->d_name);
//             listdir(path, level + 1);
//         }
//         else
//             printf("%*s- %s\n", level*2, "", entry->d_name);
//     } while ((entry = readdir(dir)));
//     closedir(dir);
// }

// void listdir(char const* dirname){

//     char* subdir;
//     DIR* current = opendir(dirname);
//     struct dirent *current_struc;
//     //char buf[512];

//     if (current == NULL){ //if current directly is a dead end return
//         return;
//     }

//     while( (current_struc = readdir(current)) != NULL){
//         printf("%s\n", current_struc->d_name);
//         if( (current_struc->d_type) == DT_DIR ){
//             if ( !( strcmp(current_struc->d_name, ".") == 0) || !(strcmp(current_struc->d_name,"..") == 0) ){

//                 subdir = malloc(strlen(dirname) + strlen(current_struc -> d_name) + 2);

//                 strcpy(subdir, dirname);
//                 strcat(subdir, "/");
//                 strcat(subdir, current_struc -> d_name);
//                 //sprintf(buf, "%s/%s", current, current_struc->d_name);
//                 //printf("%s\n", current_struc->d_name);
//                 listdir(subdir);
//                 free(subdir);
//                 // if ( (current_struc -> d_name == dirname) ){
//                 //     return;
//                 // }

//             }
//         }

//     }

//     closedir(current);

//     return;
// }


// struct stat {
//     dev_t     st_dev;     /* ID of device containing file */
//     ino_t     st_ino;     /* inode number */
//     mode_t    st_mode;    /* protection */
//     nlink_t   st_nlink;   /* number of hard links */
//     uid_t     st_uid;     /* user ID of owner */
//     gid_t     st_gid;     /* group ID of owner */
//     dev_t     st_rdev;    /* device ID (if special file) */
//     off_t     st_size;    /* total size, in bytes */
//     blksize_t st_blksize; /* blocksize for filesystem I/O */
//     blkcnt_t  st_blocks;  /* number of blocks allocated */
//     time_t    st_atime;   /* time of last access */
//     time_t    st_mtime;   /* time of last modification */
//     time_t    st_ctime;   /* time of last status change */
// };