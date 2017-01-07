import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class solution {

	public static void main(String[] args) {
	    System.out.print("Enter number of frogs (on each side): ");
	    Scanner input = new Scanner(System.in);
	    int frogNumber = input.nextInt();
	    char[] root = new char[2*frogNumber + 1];
	    char[] solution = new char[2*frogNumber + 1];
	    
	    initializeBoards(solution, root, frogNumber);
	    
	    Stack<char[]> stack = new Stack<char[]>();
	    ArrayList<char[]> visitedNodes = new ArrayList<char[]>();
	    stack.push(root.clone());
	
	    do{
	        int i0 = findEmptyField(root);
	        if (i0 > 0 && root[i0-1] == '>' && (!checkIfVisited(swap(root.clone(), i0-1, i0), visitedNodes))){
	        	swap(root, i0-1, i0);
	        	stack.push(root.clone());
		        visitedNodes.add(root.clone());    
		        continue;
	        }
	        
	        else if ( i0 < root.length-1 && root[i0+1] == '<' && (!checkIfVisited(swap(root.clone(), i0+1, i0), visitedNodes))){
	        	swap(root, i0+1, i0);
	        	stack.push(root.clone());
		        visitedNodes.add(root.clone());    
		        continue;
	        }
	        else if (i0 <= root.length-3 && root[i0+2] == '<' && root[i0+1] == '>' && (!checkIfVisited(swap(root.clone(), i0+2, i0), visitedNodes))){
	        	swap(root, i0+2, i0);
	        	stack.push(root.clone());
		        visitedNodes.add(root.clone());    
		        continue;
	        }
	        
	        else if (i0 > 1 && root[i0-2] == '>' && root[i0-1] == '<' && (!checkIfVisited(swap(root.clone(), i0-2, i0), visitedNodes))){
	        	swap(root, i0-2, i0);
	        	stack.push(root.clone());
		        visitedNodes.add(root.clone());    
		        continue;
	        }
	        
	        stack.pop();
	        root=(char[]) stack.peek();
	        root = root.clone();
	    }
	    while(!Arrays.equals(root, solution));
	    
	    printSolution(stack);
	  
	}
	
	
	public static void initializeBoards(char[] solution, char[] root, int numberOfFrogs) {
		for (int i = 0; i < numberOfFrogs; i++){
	          root[i] = '>';  
	          solution[i]='<';
	          
	          root[(2*numberOfFrogs)-i] = '<';
	          solution[(2*numberOfFrogs)-i]='>';
	    }
		
		root[(root.length-1)/2] = '_';
		solution[(root.length-1)/2] = '_';
	}

	public static int findEmptyField(char arr[]){
	    int index = 0;
	    for (int i=0; i <arr.length; i++){
	        if (arr[i]=='_'){
	        	index = i;
	        }
	    }
	    return index;
	}
	
	public static char[] swap(char[] arr, int ind1, int ind2){
	    char temp = arr[ind1];
	    arr[ind1] = arr[ind2];
	    arr[ind2] = temp;
	    return arr;
	}
	
	public static boolean checkIfVisited(char[] target, ArrayList<char[]> visitedList){
	    if(visitedList.isEmpty()){
	    	return false;
	    }
	    
	    for (Object currentItem : visitedList) {
	        if (Arrays.equals(target, (char[]) currentItem)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static void printSolution(Stack<char[]> solution) {
	    Stack<char[]> solutionPath = new Stack<char[]>();
	    while(!solution.empty()){
	    	solutionPath.push(solution.pop());
	    }
	
	    while(!solutionPath.empty()){
	        char[] current = (char[]) solutionPath.pop();
	        for (int i = 0; i < current.length; i++){
	            System.out.print(current[i]);
	        }
	        System.out.println();
	    }  
	}
}