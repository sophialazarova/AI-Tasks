import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Solution {

	static int counter = 0;
	
	static int max(int a, int b) {
		int result = (a > b)? a : b;
		//System.out.println(result);
		return result;
	}
	
	static int knapSack(int maxWeight, int weights[], int values[], int numberOFObjects)
	{
		if (numberOFObjects == 0 || maxWeight == 0)
			return 0;
		
		if (weights[numberOFObjects-1] > maxWeight) {
			return knapSack(maxWeight, weights, values, numberOFObjects-1);
		}
		else { 
			return max( values[numberOFObjects-1] + knapSack(maxWeight-weights[numberOFObjects-1], weights, values, numberOFObjects-1), knapSack(maxWeight, weights, values, numberOFObjects-1));
		}
	}

	public static void main(String args[]) throws IOException {
		System.out.println("Enter maximum weight: ");
		
		Scanner in = new Scanner(System.in);
		int maxWeight = in.nextInt();
		
		System.out.println("Enter number of objects: ");
		
		int numberOfObjects = in.nextInt();
		int values[] = new int[numberOfObjects];
		int weights[] = new int[numberOfObjects];
		
		System.out.println("Enter objects on separate lines: ");
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		int j = 0;
		while ((line = stdin.readLine()) != null && line.length()!= 0) {
		    String[] input = line.split(" ");
		    values[j] = Integer.parseInt(input[0]);
			weights[j] = Integer.parseInt(input[1]);
			j++;
		}

		System.out.println("Working..");
		System.out.println(knapSack(maxWeight, weights, values, numberOfObjects));
		in.close();
	}
}

