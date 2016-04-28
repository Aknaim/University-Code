# Floating Point Addition
# Created 2/12/99 by Travis Furrer & David Harris
# E114, Harris
#--------------------------------
# <Please put your name here>
#--------------------------------


# The numbers below are loaded into memory (the Data Segment)
# before your program runs.  You can use a lw instruction to
# load these numbers into a register for use by your code.

        .data
atest:  .word 0x40000000 # you can change this to anything you want
btest:  .word 0x40000000 # you can change this to anything you want
smask:  .word 0x007FFFFF 
emask:  .word 0x7F800000
ibit:   .word 0x00800000
obit:   .word 0x01000000
        .text

# The main program computes e using the infinite series, and
# calls your flpadd function (below).
#
# PLEASE DO NOT CHANGE THIS PART OF THE CODE
#
# The code uses the registers as follows:
#    $s0 - 1 (constant integer)
#    $s1 - i (loop index variable)
#    $s2 - temp
#    $f0 - 1 (constant single precision float)
#    $f1 - e (result accumulator)
#    $f2 - 1/i!
#    $f3 - i!
#    $f4 - temp
        
main:   li $s0,1                # load constant 1
        mtc1 $s0,$f0            # copy 1 into $f0
        cvt.s.w $f0,$f0         # convert 1 to float
        mtc1 $0,$f1             # zero out result accumulator
        li $s1,0                # initialize loop index
tloop:  addi $s2,$s1,-11        # Have we summed the first 11 terms?
        beq $s2,$0,end          # If so, terminate loop
        bnez $s1,fact           # If this is not the first time, skip init
        mov.s $f3,$f0           # Initialize 0! = 1
        j dfact                 # bypass fact
fact:   mtc1 $s1,$f4            # copy i into $f4
        cvt.s.w $f4,$f4         # convert i to float
        mul.s $f3,$f3,$f4       # update running fact
dfact:  div.s $f2,$f0,$f3       # compute 1/i!
        #add.s $f1,$f1,$f2      # we use your flpadd function instead!
        mfc1 $a0,$f1            #\  These lines should do the same thing
        mfc1 $a1,$f2            # \ as the commented out line above.
        jal flpadd              # / This is where we call your function.
        mtc1 $v0,$f1            #/
        addi $s1,$s1,1          # increment i
        j tloop                 #
end:    j end                   #



# If you have trouble getting the right values from the program
# above, you can comment it out and do some simpler tests using
# the following program instead.  It allows you to add two numbers
# (specified as atest and btest, above), leaving the result in $v0.

#main:   lw $a0,atest
#        lw $a1,btest
#        jal flpadd
#end:    j end



# Here is the function that performs floating point addition of
# single-precision numbers.  It accepts its arguments from
# registers $a0 and $a1, and leaves the sum in register $v0
# before returning.
#
# Make sure not to use any of the registers $s0-$s7, or any
# floating point registers, because these registers are used
# by the main program.  All of the registers $t0-$t9, however,
# are okay to use.
#
# YOU SHOULD NOT USE ANY OF THE MIPS BUILT-IN FLOATING POINT
# INSTRUCTIONS.  Also, don't forget to add comments to each line
# of code that you write.
#
# Remember the single precision format (see page 276):
#          bit 31 = sign (1 bit)
#      bits 30-23 = exponent (8 bits)
#       bits 22-0 = significand (23 bits)
#
#
#
#	Explain your registers here
#

#Enter your code here

flpadd:

epo_xtract: #step1#
       #lw $t0, atest #0x40000000 test number 1
       #lw $t1, btest #0x40000000 test number 2
       or $t0, $a0, $zero #load first input float
       or $t1, $a1, $zero #load second input float
       lw $t2, emask #0x7F800000 exponent mask constant
       and $t3, $t0, $t2 #---
       srl $t3, $t3, 23 # mask and shift first exponent---
       and $t4, $t1, $t2 #---
       srl $t4, $t4, 23 # mask and shift second exponent---
sig_xtract: #step2#       
       lw $t5, smask #0x007FFFFF significand mask constant
       and $t6, $t0, $t5 #mast first significand
       addi $t6, $t6, 8388608 #append leading 1 by adding 0x800000 in decimal to significand 1
       and $t7, $t1, $t5 #mask second significand
       addi $t7, $t7, 8388608 #append leading 1 by adding 0x800000 in decimal to significand 2
compexpo: #step3#
	slt $t0, $t3, $t4 #check if expo1 < expo2 if true set $t1 = 1, else $t1 = 0
	beq $t0, $zero, sub_expo #jump to sub_expo if expo1 > expo2
	sub $t1, $t4, $t3 #if expo2 > expo1 subtract expo2 - expo1 & store the difference
	add $t2, $zero, $t4 #set exponent of result to expo2 since it is larger, #step 4#
	srlv $t6, $t6, $t1 #shift significand of smaller number to right by difference in exponent #step 5#
	addi $t6, $t6, 8388608 #re-append leading 1 since this significand got shifted right
	j sig_sum
sub_expo: #will only be reached if expo1 >= expo2
	sub $t1, $t3, $t4 #if expo1 >= expo2 subtract expo1-expo2 & store the difference
	add $t2, $zero, $t3 #set exponent of result to expo1 since it is larger, #step 4# 
	srlv $t7, $t7, $t1 #shift significand of smaller number to right by difference in exponent #step 5#
	addi $t7, $t7, 8388608 #re-append leading 1 since this significand got shifted right
sig_sum: #step 6#
	add $t7, $t6, $t7 #sum significands & store the value
overlow_check: #step 7#
	andi $t3, $t7, 8388608 #mask for overflow bit (bit 23) from sum of significands
	beqz $t3, strip #jump to step 8 if overflow bit = 0
	srl $t7, $t7, 1 #shift sum of significand by 1 to the right if there is overflow
	addi $t2, $t2, 1 #increment exponent by 1 if there is overflow
strip: #step 8#
	and $t7, $t7, $t5  #strip leading 1 from result significand by masking the significand bits of the result
return: #step 9#
	sll $t2, $t2, 23 #shift result exponent back to the left by 23 bits
	add $v0, $t2, $t7 #merge result exponent and result significand to create a floating point number representation
	jr $ra #jump back to main loop code provided to us
	
	