public class Entry {
	public String ageRange;
	public String menopauseValue;
	public String tumorSizeRange;
	public String invNodesRange;
	public String nodeCaps;
	public String degMalig;
	public String breast;
	public String breastQuad;
	public String irradiate;
	public String category;
	
	public Entry(String ageRange, String menopauseValue, String tumorSizeRange, String invNodesRange,
			String nodeCaps, String degMalig, String breast, String breastQuad,  String irradiate, String category) {
		this.ageRange = ageRange;
		this.menopauseValue = menopauseValue;
		this.tumorSizeRange = tumorSizeRange;
		this.invNodesRange = invNodesRange;
		this.nodeCaps = nodeCaps;
		this.degMalig = degMalig;
		this.breast = breast;
		this.breastQuad = breastQuad;
		this.irradiate = irradiate;
		this.category = category;
	}
}
