import java.util.ArrayList;
import java.util.HashMap;

public class Node {
	public Feature feature;
	public Object value;
	private boolean isLeaf;
	private String classification;
	public HashMap<String, Node> branches;
	public ArrayList<Entry> samples;
	private ArrayList<String> outputValues;
	
	public Node(Object value, Feature feature, HashMap<String, Node> branches, ArrayList<String> output) {
		this.feature = feature;
		this.branches = branches;
		this.value = value;
		this.outputValues = output;
	}
	
	public Node(ArrayList<String> output){
		this.feature = null;
		this.branches = new HashMap<String, Node>();
		this.samples = new ArrayList<Entry>();
		this.outputValues = output;
	}
	
	public Node(ArrayList<Entry> samples, ArrayList<String> output) {
		this.samples = samples;
		this.outputValues = output;
	}
	
	public boolean getIsLeaf() {
		if (this.samples.size() == 0){
			return true;
		}
		
		return false;
	}
	
	private void setClassification()
	{
		if(samples.isEmpty())
		{
			classification = getMajorityClassification();
			return;
		}
		
		if(isLeaf)
			classification = samples.get(0).category;
	}
	
	private void setNodeType()
	{
		if(samples.isEmpty())
		{
			isLeaf = true;
		}
		
		else
		{	
			isLeaf = false;
		}
	}
	
	public String getMajorityClassification()
	{
		int categoryA = 0;
		int categoryB = 0;
		for(Entry e: samples)
		{
			if(e.category.equalsIgnoreCase(outputValues.get(0)))
				categoryA++;
			else
				categoryB++;
		}
		
		if(categoryA > categoryB)
			return outputValues.get(0);
		
		else
			return outputValues.get(1);

	}
}
