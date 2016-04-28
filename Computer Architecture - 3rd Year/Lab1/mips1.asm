## Name: Syed Naim
## Student #: 1066208
## Date: Febuary 13th, Friday 2015
## Enter an integer in console to get fib(integer)

.text
.globl main
#---------------------------------------
main:
li $t6, 0 #load 0 into t6
li $t7, 1 #load 1 into t7

la $t0, value #load address 'value' into $t0
li $v0, 5 #read an integer from the command module
syscall 
sw $v0, 0($t0) #save integer to register v0

addi $sp, $sp, 4 #move down the stack
sw $ra, 0($sp) #save return address
jal fib_of_n #jump to fib_of_n

EXIT: #exit program

#-----------------------------------------
fib_of_n: #calculate fib of n

addi $sp, $sp, 4 #move down the stack
sw $s1, 0($sp) #store integer value n-1 in memory for later

sub $t1, $v0, 1 #calculate n-1, where v0 = n
bltz $t1, fib_lessthan_0 #if (n-1)<0 then go to fib_lessthan_0
beq $t1, $zero, fib_equalto_0 #if (n-1)=0 then go to fib_equalto_0
beq $t1, 1, fib_equalto_1 #if (n-1)=1 then go to fib_equalto_1

jal fib_of_n #recursive call fib for the n-1 case --------- 

addi $sp, $sp, 4 #move down the stack
sw $s2, 0($sp) #store integer value n-2 in memory for later

sub $t2, $v0, 2 #calculate n-2, where v0 = n
bltz $t2, fib_lessthan_0 #if (n-2)<0 then go to fib_lessthan_0
beq $t2, $zero, fib_equalto_0 #if (n-2)=0 then go to fib_equalto_0
beq $t2, 1, fib_equalto_1 #if (n-2)=1 then go to fib_equalto_1

jal fib_of_n #recursive call fib for the n-2 case ----------


#-----------------------------------------
jump_return: #after hitting base case start adding two previous values back together

add $v1,$s1,$s2 #add two previous values

sw $v1, 0($sp) #store integer value fib(n-1) in memory for later
addi $sp, $sp, -4 #add for to read back previous address

#-----------------------------------------
fib_of_n_final: #do final calculation of fib using last two stored values

addi $sp, $sp, 4 #move down the stack
sw $s3, 0($sp) #store integer value fib(n-1) into s3 from memory
addi $sp, $sp, 4 #move down the stack
sw $s4, 0($sp) #store integer value fib(n-2) into s4 from memory
add $v0, $s3, $s4 #add fib(n-1) + fib (n-2)

addi $sp, $sp, -4 #add 4 to get back to old address
jr $ra #jump back to main

#----------------------------------------- # Base Cases Below
fib_lessthan_0:
add $s1, $t6, $t6 #add 0 + 0 = 0 (fib of < 0) for n-1
jal jump_return 
fib_equalto_0:
add $s1, $t6, $t6 #add 0 + 0 = 0 (fib of 0)
jal jump_return 
fib_equalto_1:
add $s1, $t6, $t7 #add 0 + 1 = 1 (fib of 1)
jal jump_return 
#------------------------------------------
.data
value: .word 0, 0, 0

