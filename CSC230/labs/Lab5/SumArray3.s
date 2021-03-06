@ CSc 230 Example from p. 127 textbook 
@ Sum elements of array ==> VERSION 3
@==> loop going up

@ A simple assembly program to sum the
@ elements in an array in two versions
@ following a bit the textbook example

@ Register usage:	R1 - Length of array A
@			R2 - Pointer into array A
@			R3 - Sum of elements
@			R4 - holds element of A
					
	.text           @ Begin a "text" section, i.e. instructions
	.global _start  @ Expose the label "_start" outside this program

_start:
	LDR	R1, =Length	@ R1 <- address of Length
	LDR	R1,[R1]		@ R1 <- Length
	MOV	R5,#0		@ R5 <= loop counter i=0
	LDR	R2,=A		@ R2 <- address of A
	MOV	R3,#0		@ R3=Sum <- 0
loop:
	CMP	R5,R1		@ is i<Length?
	BEQ	DoneLoop	@ if Length=0, exit loop
	LDR	R4,[R2],#4	@ R4=A[i] and update pointer in R2
	ADD	R3,R3,R4	@ update Sum
	ADD	R5,R5,#1	@ i++
	BAL	loop
DoneLoop:
	swi	0x11        @ stop execution	

	.data	    	@ Begin a "data" section, i.e. memory definitions
	.align	 	@ align the following on a word boundary
Length:	.word	5	@ length of the array, hardcoded
A:	.word	25, -10, 33, -5, 7  @ 5 words initialized 
	.end  		@ End of program
	