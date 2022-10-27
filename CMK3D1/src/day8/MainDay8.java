package day8;

public class MainDay8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		doSquareRootExample();
		doRomanToIntExample();
		doGetMatchedStringExample();
		doSortExample();	
	}
	
	public static void doSquareRootExample() {
		double input = 999999999;
		double myResult = getSquareRoot(input);
		double exactResult = Math.sqrt(input);
		System.out.println("My SquareRoot result is: " + myResult);
		System.out.println("Exact result is: " + exactResult);
		// myResult - 0.01 <= Math.sqrt <= myResult + 0.01
		System.out.println("Is my result correct: " + (myResult - exactResult <= 0.01 && exactResult - myResult <= 0.01));		
	}
	
	public static void doRomanToIntExample() {
		String roman1 = "III";
		String roman2 = "IV";
		String roman3 = "IX";
		String roman4 = "LVIII";
		String roman5 = "MCMXCIV";
		
		System.out.println(roman1 + " = " + romanToInt(roman1));
		System.out.println(roman2 + " = " + romanToInt(roman2));
		System.out.println(roman3 + " = " + romanToInt(roman3));
		System.out.println(roman4 + " = " + romanToInt(roman4));
		System.out.println(roman5 + " = " + romanToInt(roman5));		
	}
	
	public static void doGetMatchedStringExample() {
		String[] strs1 = {"flower", "flow", "flight"};
		String[] strs2 = {"dog", "racecar", "car"};
		
		System.out.print("String 1: ");
		for(String str: strs1) {
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.println("longest matched string: " + getMatchedString(strs1));
		System.out.print("String 2: ");
		for(String str: strs2) {
			System.out.print(str + " ");
		}
		System.out.println();
		System.out.println("longest matched string: " + getMatchedString(strs2));			
	}
	
	public static void doSortExample() {
		int[] numArr = {1,2,2,0,0,1,2,2,1};
		System.out.print("Original array: ");
		for(int num: numArr) {
			System.out.print(num + " ");
		}
		System.out.println();
		System.out.print("Sorted array: ");
		for(int num: sortArray(numArr)) {
			System.out.print(num + " ");
		}		
	}

	public static double getSquareRoot(double input) {	
		double upperLimit = input;
		double lowerLimit = 0;
		if(input < 1) {
			upperLimit = 1;
			lowerLimit = input;
		}
		else if (input == 0 || input == 1)
			return input;
		while (true) {
			/*
			 * when N = m^2 > 1
			 * 0 1 2 .. m .. m^2
			 * if we keep calculating mid point = (upper + lower) / 2 and keep updating new upper or new lower by comparing to m, 
			 * every time we calculate mid point the size N will be reduced by 2:
			 * 1st time: N/1
			 * 2nd time: N/2
			 * 3rd time: N/4
			 * ..		 N/m
			 * 
			 * so the total step to divide the from size N to approximately 1 is log2(N) + 1
			 * after that the range is now slightly differently in lower < m < upper, so it will take some constant times to find the exact middle number
			 * => total time complexity log2(N)
			 */
			double middle = getMiddleNumber(upperLimit, lowerLimit);
			System.out.println("Lower: " + lowerLimit + " - Middle: " + middle + " - Upper: " + upperLimit);
			if ((int) middle * (int) middle == input)
				return (int) middle;

			if (isCorrectSqrtNum(middle, input) == 0)
				return middle;

			if (isCorrectSqrtNum(middle, input) == -1)
				lowerLimit = middle;
			else
				upperLimit = middle;
		}
	}

	public static int isCorrectSqrtNum(double sqrtNumber, double number) {
		// (a-0.01)^2 = a^2 - 0.02a + 0.0001 <= number <= a^2 + 0.02a + 0.001 = (a+0.01)^2
		if ((sqrtNumber * sqrtNumber) <= (number + 0.02 * sqrtNumber - 0.0001)
				&& (number - 0.02 * sqrtNumber - 0.0001) <= sqrtNumber * sqrtNumber) {
			return 0;
		} else if (sqrtNumber * sqrtNumber < number)
			return -1;
		else
			return 1;
	}

	public static double getMiddleNumber(double upperLimit, double lowerLimit) {
		return (upperLimit + lowerLimit) / 2;
	}
	
	public static int romanToInt(String s) {
		int index = 0;
		int number = 0;
		//always need to access all characters in string s so time complexity is O(n)
		while (index < s.length()) {
			switch (s.charAt(index)) {
			case 'M':
				number += 1000;
				++index;
				break;
			case 'D':
				number += 500;
				++index;
				break;
			case 'C':
				if (index + 1 < s.length()) {
					switch (s.charAt(index + 1)) {
					case 'M':
						number += 800;
						++index;
						break;
					case 'D':
						number += 300;
						++index;
						break;
					}
				}
				number += 100;
				++index;
				break;
			case 'L':
				number += 50;
				++index;
				break;
			case 'X':
				if (index + 1 < s.length()) {
					switch (s.charAt(index + 1)) {
					case 'C':
						number += 80;
						++index;
						break;
					case 'L':
						number += 30;
						++index;
						break;
					}
				}
				number += 10;
				++index;
				break;
			case 'V':
				number += 5;
				++index;
				break;
			case 'I':
				if (index + 1 < s.length()) {
					switch (s.charAt(index + 1)) {
					case 'X':
						number += 8;
						++index;
						break;
					case 'V':
						number += 3;
						++index;
						break;
					}
				}
				number += 1;
				++index;
				break;
			default:
				break;
			}
		}
		return number;
	}
	
	/*
	 * words.length = N
	 * the idea is to keep dividing N element until we have either 1 pair of strings left (compare those 2 strings) or 1 single string left (return that whole string)
	 * if some of the prefix letters in those 2 match, we return the prefix letters; we return immediately as soon as a non-matched character is found; if 0 character matched, return ""
	 * N: call getMatchedString 1 time N0
	 * N/2 N/2: call getMatchedString 2 times N1
	 * N/4 N/4 N/4 N/4: call getMatchedString 4 times N2
	 * N/8 N/8 N/8 N/8 N/8 N/8 N/8 N/8: call getMatchedString 8 times N3
	 * ...
	 * N/N: call getMatchedString N times
	 * so after log2(N) times, N is divided into N/N and it takes 2^0 + 2^1 + ... N/2 steps to call getMatchedString
	 * and for each pair created, it takes 1 step to call compare2String, so total it will take 2^0 + 2^1 + ... N/2 steps to call compare2String
	 * so totally it will take 2^1 + 2^2 +... N steps to find the longest common substring in the provided string
	*/
	public static String getMatchedString(String[] words){
		if(words.length == 1)
			return words[0];
		else if(words.length == 2){
			return compare2String(words[0],words[1]);
		}
		
		int firstHalfSize = words.length/2;
		int secondHalfSize = words.length/2;
		if(words.length % 2 != 0)
			secondHalfSize += 1;
		String[] firstHalfWords = new String[firstHalfSize];
		String[] secondHalfWords = new String[secondHalfSize];
		for(int i = 0; i < firstHalfSize; i++){
			firstHalfWords[i] = words[i];
		}
		for(int i = 0; i < secondHalfSize; i++){
			secondHalfWords[i] = words[i + firstHalfSize];
		}	
		return compare2String(getMatchedString(firstHalfWords),getMatchedString(secondHalfWords));
	}

	public static String compare2String(String a, String b){
		if(a.equals("") || b.equals(""))
			return "";
		String matchedStr = "";
		int i = 0;
		while(i < a.length() && i < b.length()){
			if(a.charAt(i) == b.charAt(i))
				matchedStr += a.charAt(i);
			else
				break;
			++i;
		}
		return matchedStr;
	}
	/*
	 * given N = numbers.length, we keep dividing until we have a pair of numbers to compare, or a single number to return; 
	 * as we need to sort 2 sub arrays later so we keep a single integer number in an array [ ]
	 * so same as the previous exercise, it will take 2^1 + 2^2 +... N steps to divide and sort the whole array.
	 */
	
	public static int[] sortArray(int[] numbers){
		if(numbers.length == 1)
			return numbers;
		else if(numbers.length == 2)
			return sortSubArrays(new int[]{numbers[0]}, new int[]{numbers[1]});
		
		int firstHalfSize = numbers.length / 2;
		int secondHalfSize = numbers.length / 2;
		if(numbers.length % 2 != 0)
			secondHalfSize += 1;
		int[] firstHalfNumbers = new int[firstHalfSize];
		int[] secondHalfNumbers = new int[secondHalfSize];
		for(int i = 0; i < firstHalfSize; i++){
			firstHalfNumbers[i] = numbers[i];
		}

		for(int i = 0; i < secondHalfSize; i++){
			secondHalfNumbers[i] = numbers[i + firstHalfSize];
		}
		return sortSubArrays(sortArray(firstHalfNumbers),sortArray(secondHalfNumbers));
	}

	public static int[] sortSubArrays(int[] first, int[] second){
		int[] sortedNumbers = new int[first.length + second.length];
		int currentFirstIndex = 0;
		int currentSecondIndex = 0;
		int currentSortedIndex = 0;
		while(currentFirstIndex < first.length && currentSecondIndex < second.length)
		{
			if(first[currentFirstIndex] <= second[currentSecondIndex]){
				sortedNumbers[currentSortedIndex++] = first[currentFirstIndex++];
			}else{
				sortedNumbers[currentSortedIndex++] = second[currentSecondIndex++];		
			}
		}
	    
	    while(currentFirstIndex < first.length){
	        sortedNumbers[currentSortedIndex++] = first[currentFirstIndex++];
	    }
	    while(currentSecondIndex < second.length){
	        sortedNumbers[currentSortedIndex++] = second[currentSecondIndex++];	
	    }
	    
		return sortedNumbers;
	}    	
	

}
