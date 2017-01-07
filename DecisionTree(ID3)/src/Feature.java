import java.util.List;

public class Feature {
	public String name;
	public List<String> possibleValues;
	
	public Feature(String name, List<String> values) {
		this.name = name;
		this.possibleValues = values;
	}
	
}
