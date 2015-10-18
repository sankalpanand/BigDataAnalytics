package Assignment2;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Assignment2 
{
	// Specify how many features + labels are there in the input file
	public static int NUM_ATTRS = 13; 
	
	public static ArrayList<String> attributeMap;
	public static ArrayList<Integer> usedAttributes = new ArrayList<Integer>();

	public static void main(String[] args) 
	{
		// Step 1: Populate all the features and labels in the ArrayList 
		populateAttributeMap();

		DecisionTree forexTree = new DecisionTree();
		ArrayList<Record> records;		
		LearningSet learningSet = new LearningSet();

		// Step 2: Read all the instances. Store them in records collection.
		String path = "D:\\Carnegie Mellon University\\Sem 4\\Big Data Analytics\\Processed.csv";
		records = FileReader.buildRecords(path); // buildTransactionsObject(path);

		// Step 3: Create a root node
		TreeNode root = new TreeNode();

		// Step 4: Add all the records to this root
		for(Record record : records) 
		{
			root.getData().add(record);
		}

		// Step 5: Build decision tree
		forexTree.buildTree(records, root, learningSet);
		
		
		traverseTree(records.get(12), root);
		return;
	}

	public static void populateAttributeMap() 
	{
		attributeMap = new ArrayList<String>();
		attributeMap.add("conversion");
		attributeMap.add("timeStamp");

		attributeMap.add("maxBid");
		attributeMap.add("minBid");
		attributeMap.add("averageBid");

		attributeMap.add("maxAsk");
		attributeMap.add("minAsk");
		attributeMap.add("averageAsk");

		attributeMap.add("maxDelta");
		attributeMap.add("minDelta");
		attributeMap.add("averageDelta");

		attributeMap.add("bidDirection");
		attributeMap.add("askDirection");
	}

	public static void traverseTree(Record r, TreeNode root) 
	{
		while(root.children != null) 
		{
			double nodeValue = 0;
			for(int i = 0; i < r.attributes.size(); i++) 
			{
				if(r.attributes.get(i).name.equalsIgnoreCase(root.testAttribute.name)) 
				{
					nodeValue = r.attributes.get(i).value;
					break;
				}
			}
			for(int i = 0; i < root.children.length; i++) 
			{
				if(nodeValue == root.children[i].testAttribute.value) 
				{
					traverseTree(r, root.children[i]);
				}
			}
		}

		System.out.println("Tree traversal done!");
		return;
	}

	public static boolean isAttributeUsed(int attribute) 
	{
		if(usedAttributes.contains(attribute)) 
		{
			return true;
		}

		else 
		{
			return false;
		}
	}

	public static ArrayList<Record> buildTransactionsObject(String path) 
	{
		BufferedReader reader = null;
		DataInputStream dis = null;
		ArrayList<Record> records = new ArrayList<Record>();

		try 
		{ 
			File f = new File(path);
			FileInputStream fis = new FileInputStream(f); 
			reader = new BufferedReader(new InputStreamReader(fis));;

			// read the first record of the file
			String line;
			Record r = null;
			while ((line = reader.readLine()) != null) 
			{
				r = new Record(line.split(","));
				records.add(r);
			}
		} 

		catch (Exception e) 
		{
			e.printStackTrace(); 
		}

		finally 
		{ 
			if (dis != null) 
			{
				try 
				{
					dis.close();
				} 
				catch (IOException e) 
				{
					System.out.println("IOException while trying to close the file: " + e.getMessage()); 
				}
			}
		}
		return records;
	}

}
