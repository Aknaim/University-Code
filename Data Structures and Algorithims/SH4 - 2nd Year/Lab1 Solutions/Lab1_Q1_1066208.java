/* 
 * File:   main.c
 * Author: Akbar
 *
 * Created on September 13, 2013, 4:49 PM
 */

#include <stdio.h>
#include <stdlib.h>

/*
 * 
 */
int main(int argc, char** argv) {
    #include <stdio.h>

{

int i,sumFor, n=30;


for(i=1;i<=n;i++) 
{
    sumFor += i*i;
}

printf("sumFor = %d\n",sumFor);

}

{
int a=1, sumWhile, k=30;
        
while (a <= k)  
{
    sumWhile += a*a;
    a++;      
}
    printf("sumWhile = %d\n",sumWhile);
}

{
    
    
    int b=1, sumDowhile, m=30;
do
{
    sumDowhile += b*b;
    b++;
    
    
} while(b<=m);
    
printf("sumDowhile = %d\n",sumDowhile);
        
}
    return (EXIT_SUCCESS);
}

