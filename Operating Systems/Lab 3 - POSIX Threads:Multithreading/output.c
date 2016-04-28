Last login: Fri Mar  4 01:37:21 on ttys000
Akbars-MacBook-Pro:~ Aknaim$ gcc lab3.c
clang: error: no such file or directory: 'lab3.c'
clang: error: no input files
Akbars-MacBook-Pro:~ Aknaim$ cd /Users/Aknaim/Documents/Aknaim\'s\ Stuff/4th\ Year/Semester\ 2/3SH3/Lab\ 3/lab3.c 
-bash: cd: /Users/Aknaim/Documents/Aknaim's Stuff/4th Year/Semester 2/3SH3/Lab 3/
Akbars-MacBook-Pro:Lab 3 Aknaim$ gcc lab3.c
^[[A^[[AAkbars-MacBook-Pro:Lab 3 ./a.out
Input [0][0] is: 3 
Input [0][1] is: 11 
Input [0][2] is: 6 
Input [0][3] is: 16 
Input [1][0] is: 8 
Input [1][1] is: 1 
Input [1][2] is: 5 
Input [1][3] is: 10 
Input [2][0] is: 14 
Input [2][1] is: 7 
Input [2][2] is: 12 
Input [2][3] is: 2 
Input [3][0] is: 4 
Input [3][1] is: 13 
Input [3][2] is: 9 
Input [3][3] is: 15 
Creating thread 0...
Creating thread 1...
Creating thread 2...
Local array_ROW is: 3 t_ID: 0
Creating thread 3...
Local array_ROW is: 11 t_ID: 0
Local array_ROW is: 6 t_ID: 0
Local array_ROW is: 16 t_ID: 0
modified local array_ROW_L_H is: 3 t_ID: 0
modified local array_ROW_L_H is: 6 t_ID: 0
modified local array_ROW_L_H is: 11 t_ID: 0
Local array_ROW is: 8 t_ID: 1
modified local array_ROW_L_H is: 16 t_ID: 0
Local array_ROW is: 1 t_ID: 1
Local array_ROW is: 5 t_ID: 1
Local array_ROW is: 10 t_ID: 1
modified local array_ROW_H_L is: 10 t_ID: 1
modified local array_ROW_H_L is: 8 t_ID: 1
modified local array_ROW_H_L is: 5 t_ID: 1
modified local array_ROW_H_L is: 1 t_ID: 1
Local array_ROW is: 14 t_ID: 2
Local array_ROW is: 7 t_ID: 2
Local array_ROW is: 12 t_ID: 2
Local array_ROW is: 2 t_ID: 2
modified local array_ROW_L_H is: 2 t_ID: 2
modified local array_ROW_L_H is: 7 t_ID: 2
modified local array_ROW_L_H is: 12 t_ID: 2
Local array_ROW is: 4 t_ID: 3
modified local array_ROW_L_H is: 14 t_ID: 2
Local array_ROW is: 13 t_ID: 3
Local array_ROW is: 9 t_ID: 3
Local array_ROW is: 15 t_ID: 3
modified local array_ROW_H_L is: 15 t_ID: 3
modified local array_ROW_H_L is: 13 t_ID: 3
modified local array_ROW_H_L is: 9 t_ID: 3
modified local array_ROW_H_L is: 4 t_ID: 3
ROW Final --------- [0][0] is: 3 , t_ID: 0
ROW Final --------- [0][1] is: 6 , t_ID: 0
ROW Final --------- [0][2] is: 11 , t_ID: 0
ROW Final --------- [0][3] is: 16 , t_ID: 0
counter test: 1 
************BLOCKED*************: t_ID: 0 , phase: 0
ROW Final --------- [1][0] is: 10 , t_ID: 1
ROW Final --------- [1][1] is: 8 , t_ID: 1
ROW Final --------- [1][2] is: 5 , t_ID: 1
ROW Final --------- [1][3] is: 1 , t_ID: 1
counter test: 2 
************BLOCKED*************: t_ID: 1 , phase: 0
ROW Final --------- [2][0] is: 2 , t_ID: 2
ROW Final --------- [2][1] is: 7 , t_ID: 2
ROW Final --------- [2][2] is: 12 , t_ID: 2
ROW Final --------- [2][3] is: 14 , t_ID: 2
counter test: 3 
************BLOCKED*************: t_ID: 2 , phase: 0
ROW Final --------- [3][0] is: 15 , t_ID: 3
ROW Final --------- [3][1] is: 13 , t_ID: 3
ROW Final --------- [3][2] is: 9 , t_ID: 3
ROW Final --------- [3][3] is: 4 , t_ID: 3
else condition 
i is: 0 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 0
i is: 1 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 0
i is: 2 
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 0 , phase: 0
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 0
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 1 , phase: 0
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 2 , phase: 0
i is: 3 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 0
counter reset: 0 
phase test: 1 
Local array_COLUMN is: 3 t_ID: 0
Local array_COLUMN is: 10 t_ID: 0
Local array_COLUMN is: 2 t_ID: 0
Local array_COLUMN is: 15 t_ID: 0
modified local array_Coulmn is: 2 t_ID: 0
modified local array_Coulmn is: 3 t_ID: 0
modified local array_Coulmn is: 10 t_ID: 0
Local array_COLUMN is: 6 t_ID: 1
modified local array_Coulmn is: 15 t_ID: 0
Local array_COLUMN is: 8 t_ID: 1
Local array_COLUMN is: 7 t_ID: 1
Local array_COLUMN is: 13 t_ID: 1
modified local array_Coulmn is: 6 t_ID: 1
Local array_COLUMN is: 11 t_ID: 2
modified local array_Coulmn is: 7 t_ID: 1
Local array_COLUMN is: 5 t_ID: 2
modified local array_Coulmn is: 8 t_ID: 1
Local array_COLUMN is: 12 t_ID: 2
modified local array_Coulmn is: 13 t_ID: 1
Local array_COLUMN is: 9 t_ID: 2
modified local array_Coulmn is: 5 t_ID: 2
modified local array_Coulmn is: 9 t_ID: 2
Local array_COLUMN is: 16 t_ID: 3
modified local array_Coulmn is: 11 t_ID: 2
Local array_COLUMN is: 1 t_ID: 3
modified local array_Coulmn is: 12 t_ID: 2
Local array_COLUMN is: 14 t_ID: 3
Local array_COLUMN is: 4 t_ID: 3
modified local array_Coulmn is: 1 t_ID: 3
modified local array_Coulmn is: 4 t_ID: 3
modified local array_Coulmn is: 14 t_ID: 3
COLUMN Final *********** [0][0] is: 2 , t_ID: 0
modified local array_Coulmn is: 16 t_ID: 3
COLUMN Final *********** [1][0] is: 3 , t_ID: 0
COLUMN Final *********** [2][0] is: 10 , t_ID: 0
COLUMN Final *********** [3][0] is: 15 , t_ID: 0
counter test: 1 
************BLOCKED*************: t_ID: 0 , phase: 1
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 0 , phase: 1
COLUMN Final *********** [0][1] is: 6 , t_ID: 1
COLUMN Final *********** [1][1] is: 7 , t_ID: 1
COLUMN Final *********** [2][1] is: 8 , t_ID: 1
COLUMN Final *********** [3][1] is: 13 , t_ID: 1
counter test: 2 
************BLOCKED*************: t_ID: 1 , phase: 1
COLUMN Final *********** [0][2] is: 5 , t_ID: 2
COLUMN Final *********** [1][2] is: 9 , t_ID: 2
COLUMN Final *********** [2][2] is: 11 , t_ID: 2
COLUMN Final *********** [3][2] is: 12 , t_ID: 2
counter test: 3 
************BLOCKED*************: t_ID: 2 , phase: 1
COLUMN Final *********** [0][3] is: 1 , t_ID: 3
COLUMN Final *********** [1][3] is: 4 , t_ID: 3
COLUMN Final *********** [2][3] is: 14 , t_ID: 3
COLUMN Final *********** [3][3] is: 16 , t_ID: 3
else condition 
i is: 0 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 1
i is: 1 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 1
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 1 , phase: 1
i is: 2 
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 2 , phase: 1
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 1
i is: 3 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 1
counter reset: 0 
phase test: 2 
Local array_ROW is: 2 t_ID: 0
Local array_ROW is: 6 t_ID: 0
Local array_ROW is: 5 t_ID: 0
Local array_ROW is: 1 t_ID: 0
modified local array_ROW_L_H is: 1 t_ID: 0
modified local array_ROW_L_H is: 2 t_ID: 0
modified local array_ROW_L_H is: 5 t_ID: 0
Local array_ROW is: 3 t_ID: 1
modified local array_ROW_L_H is: 6 t_ID: 0
Local array_ROW is: 7 t_ID: 1
Local array_ROW is: 9 t_ID: 1
Local array_ROW is: 4 t_ID: 1
modified local array_ROW_H_L is: 9 t_ID: 1
modified local array_ROW_H_L is: 7 t_ID: 1
modified local array_ROW_H_L is: 4 t_ID: 1
Local array_ROW is: 10 t_ID: 2
modified local array_ROW_H_L is: 3 t_ID: 1
Local array_ROW is: 8 t_ID: 2
Local array_ROW is: 11 t_ID: 2
Local array_ROW is: 14 t_ID: 2
modified local array_ROW_L_H is: 8 t_ID: 2
modified local array_ROW_L_H is: 10 t_ID: 2
Local array_ROW is: 15 t_ID: 3
modified local array_ROW_L_H is: 11 t_ID: 2
Local array_ROW is: 13 t_ID: 3
modified local array_ROW_L_H is: 14 t_ID: 2
Local array_ROW is: 12 t_ID: 3
Local array_ROW is: 16 t_ID: 3
modified local array_ROW_H_L is: 16 t_ID: 3
modified local array_ROW_H_L is: 15 t_ID: 3
ROW Final --------- [0][0] is: 1 , t_ID: 0
modified local array_ROW_H_L is: 13 t_ID: 3
ROW Final --------- [0][1] is: 2 , t_ID: 0
modified local array_ROW_H_L is: 12 t_ID: 3
ROW Final --------- [0][2] is: 5 , t_ID: 0
ROW Final --------- [0][3] is: 6 , t_ID: 0
counter test: 1 
************BLOCKED*************: t_ID: 0 , phase: 2
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 0 , phase: 2
ROW Final --------- [1][0] is: 9 , t_ID: 1
ROW Final --------- [1][1] is: 7 , t_ID: 1
ROW Final --------- [1][2] is: 4 , t_ID: 1
ROW Final --------- [1][3] is: 3 , t_ID: 1
counter test: 2 
************BLOCKED*************: t_ID: 1 , phase: 2
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 1 , phase: 2
ROW Final --------- [2][0] is: 8 , t_ID: 2
ROW Final --------- [2][1] is: 10 , t_ID: 2
ROW Final --------- [2][2] is: 11 , t_ID: 2
ROW Final --------- [2][3] is: 14 , t_ID: 2
counter test: 3 
************BLOCKED*************: t_ID: 2 , phase: 2
ROW Final --------- [3][0] is: 16 , t_ID: 3
ROW Final --------- [3][1] is: 15 , t_ID: 3
ROW Final --------- [3][2] is: 13 , t_ID: 3
ROW Final --------- [3][3] is: 12 , t_ID: 3
else condition 
i is: 0 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 2
i is: 1 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 2
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 2 , phase: 2
i is: 2 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 2
i is: 3 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 2
counter reset: 0 
phase test: 3 
Local array_COLUMN is: 1 t_ID: 0
Local array_COLUMN is: 9 t_ID: 0
Local array_COLUMN is: 8 t_ID: 0
Local array_COLUMN is: 16 t_ID: 0
modified local array_Coulmn is: 1 t_ID: 0
modified local array_Coulmn is: 8 t_ID: 0
modified local array_Coulmn is: 9 t_ID: 0
modified local array_Coulmn is: 16 t_ID: 0
Local array_COLUMN is: 2 t_ID: 1
Local array_COLUMN is: 7 t_ID: 1
Local array_COLUMN is: 10 t_ID: 1
Local array_COLUMN is: 15 t_ID: 1
modified local array_Coulmn is: 2 t_ID: 1
modified local array_Coulmn is: 7 t_ID: 1
modified local array_Coulmn is: 10 t_ID: 1
Local array_COLUMN is: 5 t_ID: 2
modified local array_Coulmn is: 15 t_ID: 1
Local array_COLUMN is: 4 t_ID: 2
Local array_COLUMN is: 11 t_ID: 2
Local array_COLUMN is: 13 t_ID: 2
modified local array_Coulmn is: 4 t_ID: 2
modified local array_Coulmn is: 5 t_ID: 2
Local array_COLUMN is: 6 t_ID: 3
modified local array_Coulmn is: 11 t_ID: 2
Local array_COLUMN is: 3 t_ID: 3
modified local array_Coulmn is: 13 t_ID: 2
Local array_COLUMN is: 14 t_ID: 3
Local array_COLUMN is: 12 t_ID: 3
modified local array_Coulmn is: 3 t_ID: 3
COLUMN Final *********** [0][0] is: 1 , t_ID: 0
modified local array_Coulmn is: 6 t_ID: 3
COLUMN Final *********** [1][0] is: 8 , t_ID: 0
modified local array_Coulmn is: 12 t_ID: 3
COLUMN Final *********** [2][0] is: 9 , t_ID: 0
modified local array_Coulmn is: 14 t_ID: 3
COLUMN Final *********** [3][0] is: 16 , t_ID: 0
counter test: 1 
************BLOCKED*************: t_ID: 0 , phase: 3
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 0 , phase: 3
COLUMN Final *********** [0][1] is: 2 , t_ID: 1
COLUMN Final *********** [1][1] is: 7 , t_ID: 1
COLUMN Final *********** [2][1] is: 10 , t_ID: 1
COLUMN Final *********** [3][1] is: 15 , t_ID: 1
counter test: 2 
************BLOCKED*************: t_ID: 1 , phase: 3
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 1 , phase: 3
COLUMN Final *********** [0][2] is: 4 , t_ID: 2
COLUMN Final *********** [1][2] is: 5 , t_ID: 2
COLUMN Final *********** [2][2] is: 11 , t_ID: 2
COLUMN Final *********** [3][2] is: 13 , t_ID: 2
counter test: 3 
************BLOCKED*************: t_ID: 2 , phase: 3
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 2 , phase: 3
COLUMN Final *********** [0][3] is: 3 , t_ID: 3
COLUMN Final *********** [1][3] is: 6 , t_ID: 3
COLUMN Final *********** [2][3] is: 12 , t_ID: 3
COLUMN Final *********** [3][3] is: 14 , t_ID: 3
else condition 
i is: 0 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 3
i is: 1 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 3
i is: 2 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 3
i is: 3 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 3
counter reset: 0 
phase test: 4 
Local array_ROW is: 1 t_ID: 0
Local array_ROW is: 2 t_ID: 0
Local array_ROW is: 4 t_ID: 0
Local array_ROW is: 3 t_ID: 0
modified local array_ROW_L_H is: 1 t_ID: 0
modified local array_ROW_L_H is: 2 t_ID: 0
modified local array_ROW_L_H is: 3 t_ID: 0
Local array_ROW is: 8 t_ID: 1
modified local array_ROW_L_H is: 4 t_ID: 0
Local array_ROW is: 7 t_ID: 1
Local array_ROW is: 5 t_ID: 1
Local array_ROW is: 6 t_ID: 1
modified local array_ROW_H_L is: 8 t_ID: 1
modified local array_ROW_H_L is: 7 t_ID: 1
modified local array_ROW_H_L is: 6 t_ID: 1
Local array_ROW is: 9 t_ID: 2
modified local array_ROW_H_L is: 5 t_ID: 1
Local array_ROW is: 10 t_ID: 2
Local array_ROW is: 11 t_ID: 2
Local array_ROW is: 12 t_ID: 2
modified local array_ROW_L_H is: 9 t_ID: 2
modified local array_ROW_L_H is: 10 t_ID: 2
Local array_ROW is: 16 t_ID: 3
modified local array_ROW_L_H is: 11 t_ID: 2
Local array_ROW is: 15 t_ID: 3
modified local array_ROW_L_H is: 12 t_ID: 2
Local array_ROW is: 13 t_ID: 3
Local array_ROW is: 14 t_ID: 3
modified local array_ROW_H_L is: 16 t_ID: 3
modified local array_ROW_H_L is: 15 t_ID: 3
ROW Final --------- [0][0] is: 1 , t_ID: 0
modified local array_ROW_H_L is: 14 t_ID: 3
ROW Final --------- [0][1] is: 2 , t_ID: 0
modified local array_ROW_H_L is: 13 t_ID: 3
ROW Final --------- [0][2] is: 3 , t_ID: 0
ROW Final --------- [0][3] is: 4 , t_ID: 0
counter test: 1 
************BLOCKED*************: t_ID: 0 , phase: 4
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 0 , phase: 4
ROW Final --------- [1][0] is: 8 , t_ID: 1
ROW Final --------- [1][1] is: 7 , t_ID: 1
ROW Final --------- [1][2] is: 6 , t_ID: 1
ROW Final --------- [1][3] is: 5 , t_ID: 1
counter test: 2 
************BLOCKED*************: t_ID: 1 , phase: 4
ROW Final --------- [2][0] is: 9 , t_ID: 2
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 1 , phase: 4
ROW Final --------- [2][1] is: 10 , t_ID: 2
ROW Final --------- [2][2] is: 11 , t_ID: 2
ROW Final --------- [2][3] is: 12 , t_ID: 2
counter test: 3 
************BLOCKED*************: t_ID: 2 , phase: 4
$$$$$$ UNBLOCKED process #: $$$$$$$ t_ID: 2 , phase: 4
ROW Final --------- [3][0] is: 16 , t_ID: 3
ROW Final --------- [3][1] is: 15 , t_ID: 3
ROW Final --------- [3][2] is: 14 , t_ID: 3
ROW Final --------- [3][3] is: 13 , t_ID: 3
else condition 
i is: 0 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 4
i is: 1 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 4
i is: 2 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 4
i is: 3 
$$$$$$ print sem_post $$$$$$$ t_ID: 3 , phase: 4
counter reset: 0 
phase test: 5 
exit test: 5 
Final Array [0][0] is: 1 
Final Array [0][1] is: 2 
Final Array [0][2] is: 3 
Final Array [0][3] is: 4 
Final Array [1][0] is: 8 
Final Array [1][1] is: 7 
Final Array [1][2] is: 6 
Final Array [1][3] is: 5 
Final Array [2][0] is: 9 
Final Array [2][1] is: 10 
Final Array [2][2] is: 11 
Final Array [2][3] is: 12 
Final Array [3][0] is: 16 
Final Array [3][1] is: 15 
Final Array [3][2] is: 14 
Final Array [3][3] is: 13 
Akbars-MacBook-Pro:Lab 3 Aknaim$ 
