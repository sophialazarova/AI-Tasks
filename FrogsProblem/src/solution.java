import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Frogs {
	public static void main(String[] args) {
	    System.out.print("Enter number of frogs (on each side): ");
	    Scanner input = new Scanner(System.in);
	    int frogNumber = input.nextInt();
	    List<Integer> initial =buildInitialState(frogNumber);
        DFS(initial, frogNumber);
	}
	
    private static List<Integer> buildInitialState(int numberOfFrogs) {
        List<Integer> state = new ArrayList<>();
        for (int i = 0; i < numberOfFrogs * 2 + 1; i++) {
            if (i < numberOfFrogs)
                state.add(-1);
            else if (i == numberOfFrogs)
                state.add(0);
            else state.add(1);
        }
        return state;
    }

    private static boolean DFS(List<Integer> state, int positionOfZero) {
        if (isGoal(state, positionOfZero)) {
            return true;
        } else {
            for (List<Integer> nextState : getStates(state, positionOfZero)) {
                if (DFS(nextState, nextState.indexOf(0))) {
                    printState(state);
                    return true;
                }
            }
        }
        return false;
    }

    private static void printState(List<Integer> state) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer integer : state) {
            if (integer == -1)
                stringBuilder.append('<');
            else if (integer == 1)
                stringBuilder.append('>');
            else stringBuilder.append('_');
        }
        System.out.println(stringBuilder.toString());
    }

    private static boolean isGoal(List<Integer> state, int positionOfZero) {
        if (positionOfZero != (state.size() - 1) / 2)
            return false;
        for (int i = 0; i < state.size(); i++) {
            if (i < positionOfZero) {
                if (state.get(i) == -1)
                    return false;
            } else if (state.get(i) == 1)
                return false;
        }
        printState(state);
        return true;
    }

    private static List<List<Integer>> getStates(List<Integer> state, int positionOfZero) {
        List<List<Integer>> possibleStates = new ArrayList<>();
        int possibleZeroPosition = positionOfZero - 1;
        if (possibleZeroPosition >= 0) {
            if (state.get(possibleZeroPosition) == -1) {
                possibleStates.add(swapPlaces(state, positionOfZero, possibleZeroPosition, -1));
            }
        }
        possibleZeroPosition = positionOfZero - 2;
        if (possibleZeroPosition >= 0) {
            if (state.get(possibleZeroPosition) == -1 && state.get(positionOfZero-1) == 1) {
                possibleStates.add(swapPlaces(state, positionOfZero, possibleZeroPosition, -1));
            }
        }
        possibleZeroPosition = positionOfZero + 1;
        if (possibleZeroPosition < state.size())
            if (state.get(possibleZeroPosition) == 1) {
                possibleStates.add(swapPlaces(state, positionOfZero, possibleZeroPosition, 1));

            }

        possibleZeroPosition = positionOfZero + 2;
        if (possibleZeroPosition < state.size())
            if (state.get(possibleZeroPosition) == 1 && state.get(positionOfZero+1) == -1) {
                possibleStates.add(swapPlaces(state, positionOfZero, possibleZeroPosition, 1));
            }
        return possibleStates;
    }

    private static List<Integer> swapPlaces(List<Integer> state, int positionOfZero, int newPositionOfZero, int swapSymbol) {
        ArrayList<Integer> newState = new ArrayList<>(state);
        newState.set(newPositionOfZero, 0);
        newState.set(positionOfZero, swapSymbol);
        return newState;
    }
}
