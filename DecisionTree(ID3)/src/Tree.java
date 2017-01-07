//import java.util.ArrayList;
//
//public class Tree {
//	private Node root;
//	private ArrayList<Tree> children = new ArrayList<Tree>();
//	
//	public  Tree()
//	{
//		
//	}
//	
//	public Tree(Node n)
//	{
//		root = n;
//	}
//	
//	/**
//	 * Method to add a subtree to this decision tree
//	 * @param dt
//	 */
//	public void addTree(Tree dt)
//	{
//		children.add(dt);
//	}
//	
//	/**
//	 * Returns the root node of this tree
//	 * @return the root node
//	 */
//	public Node getRootNode()
//	{
//		return root;
//	}
//	
//	/**
//	 * Returns the number of non-leaf nodes within this tree
//	 * @return
//	 */
//	public int getNumberOfNodes()
//	{
//		int total = 0;
//		for(Tree dt: children)
//		{
//			for(int i = 0; i < dt.getChildren().size(); i++)
//			{
//				if(!dt.getChildren().get(i).getRootNode().isLeaf())
//				{
//					total++;
//				}
//			}
//		}
//		return total;
//	}
//	
//	/**
//	 * Returns an arrayList of the children of this decision tree
//	 * @return
//	 */
//	public ArrayList<Tree> getChildren()
//	{
//		return children;
//	}
//	
//	/**
//	 * Determines if this tree correctly classifies a given example
//	 * @param tree tree to test
//	 * @param e example to classify
//	 * @return 1 if classified correctly, 0 if incorrectly
//	 */
////	public double classify(Tree tree, Example e)
////	{
////		double result = 0;
////		//If the node is a leaf node just compare the classifications
////		if(tree.getRootNode().isLeaf())
////		{
////			if(tree.getRootNode().getClassification().equalsIgnoreCase(e.getClassification()))
////				result =  1.0;
////			else
////				result = 0.0;
////		}
////		
////		//If the node is a leaf node but doesn't have a split value declared 
////		//use the majority classification and compare
////		if(tree.getRootNode().getSplitValue() == null)
////		{
////			if(tree.getRootNode().getMajorityClassification().equalsIgnoreCase(e.getClassification()))
////				result =  1.0;
////			else
////				result = 0.0;
////		}
////		
////	
////		else
////		{
////			//Determine the path for a continuous function
////			if(tree.getRootNode().getFeature().isContinuous())
////			{
////				
////				double exampleVal = Double.parseDouble(e.getValues().get(tree.getRootNode().getFeature().getIndex()));
////				double t = Double.parseDouble(tree.getRootNode().getSplitValue());
////				
////					if(exampleVal > t) 
////						result = classify(tree.getChildren().get(0), e);
////					else
////						result = classify(tree.getChildren().get(1), e);
////			}
////			
////			//Determine the path for a discrete feature
////			else
////			{
////				int featIndex = tree.getRootNode().getFeature().getIndex();
////				String exampleVal = e.getValues().get(tree.getRootNode().getFeature().getIndex());
////				//FInd which path tocontinue down by selecting the correct child node
////				if(tree.getRootNode().getSplitValue().equalsIgnoreCase(exampleVal))
////				{
////					for(Tree x: tree.getChildren())
////					{
////						if(x.getRootNode().getExamples().get(0).getValues().get(featIndex).equalsIgnoreCase(exampleVal))
////						{
////							result = classify(x, e);
////						}
////					}
////				}
////				
////				else
////					result = 0.0;
////					
////			}
////
////		}
////		
////		return result;
////	}
//}