package day9.fibonacci;

public class Fibonacci {
	public static void demo() {
		int[] arr = {0,1,2,3,4,5,6,7,8,9,10};
		System.out.println("FIBONACCI NUMBERS: ");		
		System.out.println("Get Fibonacci Using Loop: ");
		for(int num : arr) {
			System.out.println("Fibo of " + num + " is: " + getFibonacciUsingLoop(num));
		}
		System.out.println("Get Fibonacci Using Recursion: ");
		for(int num : arr) {
			System.out.println("Fibo of " + num + " is: " + getFibonacciUsingRecursion(num));
		}	
		System.out.println();
	}
	
	public static int getFibonacciUsingLoop(int num) {
		int prevFibo = 0;
		int curFibo = 1;
		int currentLevel = 1;
		if (num == 0) 
			return 0;
		else if(num == 1)
			return 1;
		else
			++currentLevel;
		//--> O(num-1) = O(num)
		while(currentLevel <= num) {
			int newPrevFibo = curFibo;
			curFibo += prevFibo;
			prevFibo = newPrevFibo;
			++currentLevel;			
		}
		return curFibo;
	}
	
	//f(num) = f(num-1) + f(num-2) -> num-1 + num-2 steps = 2num - 3 steps to reach f(0)
	//--> O(2num-3) = O(num)
	public static int getFibonacciUsingRecursion(int num) {
		if(num == 0)
			return 0;
		else if (num == 1)
			return 1;
		return getFibonacciUsingLoop(num-1) + getFibonacciUsingLoop(num-2); 
	}
	
}
