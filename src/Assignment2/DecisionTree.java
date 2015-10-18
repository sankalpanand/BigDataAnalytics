package Assignment2;
import java.util.*;

public class DecisionTree 
{
	/*
	This Decision Tree class contains all our code for building our decision tree. 
	Note that each level, we choose the attribute that presents the best gain for that node. 
	The gain is simply the expected reduction in the entropy of achieved by learning the state of the random variable A. 
	*/
	public TreeNode buildTree(ArrayList<Record> records, TreeNode root, LearningSet learningSet) 
	{
		int bestAttribute = -1;
		double bestGain = 0;
		root.setEntropy(Entropy.calculateEntropy(root.getData()));

		if (root.getEntropy() == 0) 
		{
			return root;
		}

		for (int i = 0; i < Assignment2.NUM_ATTRS - 2; i++) 
		{
			// Go further only if this attribute is unused
			if (!Assignment2.isAttributeUsed(i)) 
			{
				double entropy = 0;
				ArrayList<Double> entropies = new ArrayList<Double>();
				ArrayList<Integer> setSizes = new ArrayList<Integer>();

				for (int j = 0; j < Assignment2.NUM_ATTRS - 2; j++) 
				{
					ArrayList<Record> subset = subset(root, i, j);
					setSizes.add(subset.size());

					if (subset.size() != 0) 
					{
						entropy = Entropy.calculateEntropy(subset);
						entropies.add(entropy);
					}
				}

				double gain = Entropy.calculateGain(root.entropy, entropies, setSizes, root.data.size());

				if (gain > bestGain) 
				{
					bestAttribute = i;
					bestGain = gain;
				}
			}
		}
		if (bestAttribute != -1) 
		{
			int setSize = 2;
			root.testAttribute = (new DiscreteAttribute(Assignment2.attributeMap.get(bestAttribute), 0+""));
			root.children = new TreeNode[setSize];
			root.isUsed = true;
			Assignment2.usedAttributes.add(bestAttribute);

			for (int j = 0; j < setSize; j++) {
				root.children[j] = new TreeNode();
				root.children[j].parent = root;
				root.children[j].data = subset(root, bestAttribute, j);
				root.children[j].testAttribute.name = "";
				root.children[j].testAttribute.value = j;
			}

			for (int j = 0; j < setSize; j++) 
			{
				buildTree(root.children[j].data, root.children[j], learningSet);
			}

			root.data = null;
		} 
		else 
		{
			return root;
		}

		return root;
	}

	public ArrayList<Record> subset(TreeNode root, int attr, int value) 
	{
		ArrayList<Record> subset = new ArrayList<Record>();

		// Do this for all the data instances of this tree 
		for (int i = 0; i < root.getData().size(); i++) 
		{
			// Take out the i'th instance
			Record record = root.getData().get(i);

			// If the attribute value of the i'th instance equals the passed value, 
			// add it to the subset
			if (record.getAttributes().get(attr).value.equals(value)) 
			{
				subset.add(record);
			}
		}
		return subset;
	}

}
