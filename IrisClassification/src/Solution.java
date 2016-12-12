import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

class Item {		
	double sepalLength;
	double sepalWidth;
	double petalLength;
	double petalWidth;
	String className;
	
	 Item(double sepalLength, double sepalWidth,
			double petalLength, double petalWidth, String className) {
			this.sepalLength = sepalLength;
			this.sepalWidth = sepalWidth;
			this.petalLength = petalLength;
			this.petalWidth = petalWidth;
			this.className = className;
	}
}

public class Solution {
	
	public static void main(String[] args) throws IOException {
		ArrayList<Item> dataSet = parseDataFile();
		ArrayList<Item> testData = extractTestDataSet(dataSet);
		for (int i = 0; i < testData.size(); i++) {
			String prediction = getPredictionForItem(dataSet, testData.get(i));
			System.out.println(i+". Prediction -> " + prediction );
			System.out.println("Real value -> " + testData.get(i).className);
			System.out.println("***" + (prediction.equals(testData.get(i).className) ? "CORRECT":"WRONG") + "***");
			System.out.println("--------------------------------------------------");
		}
		
	}
	
	public static ArrayList<Item> parseDataFile() throws IOException {
		ArrayList<Item> result = new ArrayList<Item>();
		
	    FileReader in = new FileReader("iris-data.txt");
	    BufferedReader br = new BufferedReader(in);
	    
		String line;
		while ((line = br.readLine()) != null) {
		    String[] parsed = line.split(",");
		    result.add(new Item(Double.parseDouble(parsed[0]),
		    		Double.parseDouble(parsed[1]), Double.parseDouble(parsed[2]), 
		    		Double.parseDouble(parsed[3]), parsed[4]));
		}
		
		return result;
	}
	
	public static ArrayList<Item> extractTestDataSet(ArrayList<Item> originalSet) {
		ArrayList<Item> result = new ArrayList<Item>();
		Random randomGenerator = new Random();
		for (int i = 0; i < 20; i++){
			int index = randomGenerator.nextInt(originalSet.size());
			result.add(originalSet.get(index));
			originalSet.remove(index);
		}
		
		return result;
	}
	
	public static double calculateDistance(Item first, Item second) {
		double sepalWidth = Math.pow((first.sepalWidth - second.sepalWidth), 2);
		double sepalLength = Math.pow((first.sepalLength - second.sepalLength), 2);
		double petalWidth = Math.pow((first.petalWidth - second.petalWidth), 2);
		double petalLength = Math.pow((first.petalLength - second.petalLength), 2);
		
		double sum = sepalWidth + sepalLength + petalWidth + petalLength;
		return Math.sqrt(sum);
	}
	
	public static List<Item> getKNearestNeighbours(ArrayList<Item> trainingSet, Item testInstance, int k) {
		TreeMap<Double, Item> res = new TreeMap<Double, Item>();
		
		for (int i =0; i < trainingSet.size(); i++) {
			double distance = calculateDistance(testInstance, trainingSet.get(i));
			res.put(distance, trainingSet.get(i));
		}
		
		for(int i = 0; i < res.keySet().size(); i++) {
			res.keySet().toArray();
		}
		
		List<Item> sorted = new ArrayList<Item>(res.values());
		return sorted.subList(0, k-1);
	}
	
	public static String getPredictionForItem(ArrayList<Item> trainingSet, Item testInstance) {
		String prediction;
		List<Item> neighbours = getKNearestNeighbours(trainingSet, testInstance, 3);
		prediction = getMostVotedClass(neighbours); 
		
		return prediction;
	}
	
	public static String getMostVotedClass(List<Item> neighbours) {
		TreeMap<Integer, String> res = new TreeMap<Integer, String>();
		int irisCentosa = 0;
		int irisVersicolour = 0;
		int irisVirginica = 0;
		
		for (int i = 0; i < neighbours.size(); i ++) {
			Item current = neighbours.get(i);
			if (current.className.equals("Iris-setosa")) {
				irisCentosa++;
			}
			else if (current.className.equals("Iris-versicolor")) {
				irisVersicolour++;
			}
			else {
				irisVirginica++;
			}
		}
		
		res.put(irisCentosa, "Iris-setosa");
		res.put(irisVersicolour, "Iris-versicolor");
		res.put(irisVirginica, "Iris-virginica");
		List<String> sorted = new ArrayList<String>(res.values());
		return sorted.get(1);
	}
}
