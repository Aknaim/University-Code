#include <unistd.h>
#include <stdio.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <pwd.h>
#include <grp.h>
#include <dirent.h>

// int call_ls (char *str){

//     char* directory = str;

//     if ((dir = opendir(".")) == NULL){
//         return 0;
//     }

//     while (d = readdir(dir)){
//         printf("%s \n", d->d_name);
//     }


// }

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

        DIR *mydir;
        struct dirent *myfile;
        struct stat mystat;

        char buf[512];
        mydir = opendir(argv[1]);
        while((myfile = readdir(mydir)) != NULL){
            sprintf(buf, "%s/%s", argv[1], myfile->d_name);
            printf("%s \n", myfile->d_name);
        }
        closedir(mydir);
    }


    

    return 0;
}






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