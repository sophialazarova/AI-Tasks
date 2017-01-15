import java.util.ArrayList;
import java.util.HashMap;

public class Node {
	public Feature feature;
	public Object value;
	private boolean isLeaf;
	private String classification;
	public HashMap<String, Node> branches;
	public ArrayList<Entry> samples;
	
	public Node(Object value, Feature feature, HashMap<String, Node> branches) {
		this.feature = feature;
		this.branches = branches;
		this.value = value;
	}
	
	public Node(){
		this.feature = null;
		this.branches = new HashMap<String, Node>();
		this.samples = new ArrayList<Entry>();
	}
	
	public Node(ArrayList<Entry> samples) {
		this.samples = samples;
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
}
