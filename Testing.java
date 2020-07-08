import java.util.Random;


/**Program Testing implements Single Thread and MultiThreading by extending the current class and compares
  *the difference in performance by contrasting the time in millisecond it takes to complete the summation of an
  *array with 200 million random numbers(1-10 inclusive) and returns the total amount.



/*Public class extends Thread. This also could have been done by implementing Runnable*/
public class Testing extends Thread {
	
	private int array [];
	private int max;
	private int min;
	private static int sum;
	public static void main(String[] args) {
		
		/*Making array that holds 200 million numbers*/
		int [] array = new int [200000000];
		
		/*Creating a random object to then initialize the values in the array
		  with random numbers 1 through 10 */
		Random random = new Random();
		for(int i = 0; i <array.length; i++) {
			array[i] = random.nextInt(10) + 1;
		}
		
		/*Take the current time before execution of program*/
		long startTime = System.currentTimeMillis();
	
		/*Run program for single thread.*/

		
		 /*I decided to run this program 100 for both single thread and mutli thread to show case the difference
		  of what it means for a program to be running and computation of large numbers many times
		  for the purposes of this assignment however i commented out the 2 for loop*/
		
		/*for(int i = 0; i < 100; i++){
			Testing.singleThreadSum(array);
		}*/
		
		Testing.singleThreadSum(array);
		
		/*Taking the startTime variable we subtract the current time after execution of the
		  single thread program running with the start time(before the program runs).*/
		System.out.println("Time for single thread: " + (System.currentTimeMillis()- startTime) + " ms");
		
		/*Same thing done for the multiThread method.*/
		startTime = System.currentTimeMillis();
		/*for(int i = 0; i < 100; i++){
			Testing.multiThreadSumReturn(array);
		}*/
		
		Testing.multiThreadSumReturn(array);
		System.out.println("Time for multi thread: " + (System.currentTimeMillis()- startTime) + " ms");
		
	}
	
	/*Class that takes in an array and min and max index for said array*/
	public Testing (int array [], int min, int max){
		
		/*We declare the variables here to be use later throughout our code as we access this class.*/
		this.array = array;
		this.min = min;
		this.max = Math.min(max, array.length);

	}
	
	/*Helper method that returns the variable sum that contains the total calculated amount.*/
	public int returnSum() {
		return sum;
	}
	
	/*Run method also calls the sumReturn() method that gets computed values of our array.*/
	public void run() {
		sum = sumReturn(array, min, max);
	}
	
	/*singleThreadSum takes in a array as parameter and returns the total sum of the array.*/
	public static int singleThreadSum(int array []) {

		/*Declare variable to return value of the sum of the array.*/
		int total = 0;
		
		/*Enhance for loop adds up values in array and stores it in the total variable.*/
		for(int holder: array ) {
			total = total + holder;
		}
		
		return total;
	}
	
	/*mutliThreadSum takes in array as parameter and integer variable representing
	  numbers of threads to then does the same thing as sinlgeThreadSum but instead with multiple threads 
	  and returns the total sum of the array.*/
	public static int multiThreadSum(int array []) {
		
		/* We declare a variable name CPU that will calculate the total number of processor available.*/
		/* The available processor method returns the number of processors
		   available to the java virtual machine. So for my computer which runs a 6 core Ryzen CPU it will return 6.*/
		int CPU = Runtime.getRuntime().availableProcessors();
		
		/* With this we then calculate how many threads we will need by finding the ceiling. This will length will be use throughout.*/
		int length = (int)Math.ceil(array.length / CPU);
		
		/*Variable total to return total sum.*/
		int total = 0;
		
		/*Array created of class Testing. Array is initialize with value of CPU*/
		Testing [] sum = new Testing[CPU];
		
		/*We make a Thread object array called thread that is initialize by the CPU variable.*/
		Thread [] thread = new Thread[CPU];
		
		/*For loop goes through the amount of available process(CPU)*/
		for(int i = 0; i < CPU; i++) {
			
			/*Thread object array sum is initialize with the class Testing with the three parameters
			  we use length * i as the min parameter since as we iterate with i are low parameter will be the lowest since
			  our length is getting the ceiling of the array length over the CPU(available processors).
			  max values of is the min of two values (i + 1) * length and the array's length.*/
			sum[i] = new Testing(array, i * length, Math.min((i + 1) * length, array.length));
			
			/*thread object thread is initialize and is creating our thread as it iterates over sum array index in the for loop*/
			thread[i] = new Thread(sum[i]);
			
			/*Thread is started with the start() method*/
			thread[i].start();
		}
		
		/*Try catch loop implement because of the use of join() method*/
		try {
			
		/*Enhance For iterates through thread t1 and joins all of our thread that are executing*/
			for(Thread t1: thread) {
				t1.join();
			}
		}catch (InterruptedException e) {}
		
		/*Enhance For loop iterates through array sum */
		for(Testing arraySum: sum) {
			
			/*variable total is storing arraySum values as it calls returnSum which is our helper method that returns the array total sum*/
			total += arraySum.returnSum();
		}
		return total;
	}
	
	/*Method is main multiThreadSumReturn method that will be called in main with array as parameter passed.*/
	public static int multiThreadSumReturn(int [] array) {
		
		return multiThreadSum(array);
	}
	
	/*This is a helper method to help compute the sum of the array with index (min, max).*/
	public static int sumReturn(int array [], int min, int max) {
		int res = 0;
		for(int i = min; i < max; i++) {
			res+= array[i];
		}
		return res;
	}

}


