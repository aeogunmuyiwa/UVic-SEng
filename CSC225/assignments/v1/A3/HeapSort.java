/* Jakob Roberts
   v00484900
   CSC225 - Assignment 3
   
   HeapSort.java
   CSC 225 - Summer 2014
   Assignment 3 - Template for Heap Sort
   
   This template includes some testing code to help verify the implementation.
   To interactively provide test inputs, run the program with
	java HeapSort

   To conveniently test the algorithm with a large input, create a 
   text file containing space-separated integer values and run the program with
	java HeapSort file.txt
   where file.txt is replaced by the name of the text file.

   B. Bird - 06/13/2014
*/

import java.util.Scanner;
import java.util.Vector;
import java.util.Arrays;
import java.io.File;

//Do not change the name of the HeapSort class
public class HeapSort{
	/* HeapSort(A)
		Sort the array A using heap sort.
		You may add additional functions (or classes) if needed, but the entire program must be
		contained in this file. 

		Do not change the function signature (name/parameters).
	*/
	public static void HeapSort(int[] A){
		int[] temp = new int[A.length+1];
		temp[0] = -1;
		
		/* MAKE HEAP */
		for(int i = 1; i < (A.length+1);i++){
			temp[i] = A[i-1];					/* INSERT */
			int j =i;
			while(temp[j] < temp[j/2] && j > 1){ /* BUBBLE UP */
				int swap = temp[j/2];
				temp[j/2] = temp[j];
				temp[j] = swap;
				j = j/2;		
			}
		}
		
		/* EMPTY HEAP FOR SORT */
		for(int i = 0;temp[1]!=-1; i++){
			A[i] = temp[1];
			temp[1] = temp[temp.length -1 - i];
			temp[temp.length -1 - i] = -1;
			
			int current = 1;
			int next = 0;
			
			while(temp[current] != -1){ /* BUBBLE DOWN */
				if( (current*2) < A.length && temp[current*2] != -1){
					next = current*2;
					if((next+1) < A.length && temp[next+1]    != -1){
						if(temp[next] > temp[next+1]){
							next++;
						}
					}
				}else{
					break;
				}
				/* does swap for bubble down */
				if(temp[current] > temp[next]){
					int swap = temp[current];
					temp[current] = temp[next];
					temp[next] = swap;
				}
				current = next;
			}
		}
	}

	/* main()
	   Contains code to test the HeapSort function. Nothing in this function 
	   will be marked. You are free to change the provided code to test your 
	   implementation, but only the contents of the HeapSort() function above 
	   will be considered during marking.
	*/
	public static void main(String[] args){
		Scanner s;
		if (args.length > 0){
			try{
				s = new Scanner(new File(args[0]));
			} catch(java.io.FileNotFoundException e){
				System.out.printf("Unable to open %s\n",args[0]);
				return;
			}
			System.out.printf("Reading input values from %s.\n",args[0]);
		}else{
			s = new Scanner(System.in);
			System.out.printf("Enter a list of non-negative integers. Enter a negative value to end the list.\n");
		}
		Vector<Integer> inputVector = new Vector<Integer>();

		int v;
		while(s.hasNextInt() && (v = s.nextInt()) >= 0)
			inputVector.add(v);

		int[] array = new int[inputVector.size()];

		for (int i = 0; i < array.length; i++)
			array[i] = inputVector.get(i);

		System.out.printf("Read %d values.\n",array.length);


		long startTime = System.currentTimeMillis();

		HeapSort(array);

		long endTime = System.currentTimeMillis();

		double totalTimeSeconds = (endTime-startTime)/1000.0;

		//Don't print out the values if there are more than 100 of them
		if (array.length <= 100){
			System.out.println("Sorted values:");
			for (int i = 0; i < array.length; i++)
				System.out.printf("%d ",array[i]);
			System.out.println();
		}

		//Check whether the sort was successful
		boolean isSorted = true;
		for (int i = 0; i < array.length-1; i++)
			if (array[i] > array[i+1])
				isSorted = false;

		System.out.printf("Array %s sorted.\n",isSorted? "is":"is not");
		System.out.printf("Total Time (seconds): %.2f\n",totalTimeSeconds);
	}
}