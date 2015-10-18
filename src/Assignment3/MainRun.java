package Assignment3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MainRun {
  public static void main(String[] args)
  {
		
		String trainData = "D:\\Carnegie Mellon University\\Sem 4\\Big Data Analytics\\Processed.csv";
		String testdata = "D:\\Carnegie Mellon University\\Sem 4\\Big Data Analytics\\Processed.csv";
		int numTrees = 1;	
		
		DescribeTrees DT = new DescribeTrees();
		
		// Read training data
		ArrayList<ArrayList<String>> Train = DT.CreateInputCateg(trainData);
		
		// Read testing data
		ArrayList<ArrayList<String>> Test = DT.CreateInputCateg(testdata);
		
		// Number of attributes. Here we have 13 - 2 = 11
		int M = Train.get(0).size() - 2;
		
		// Number of Attributes to be chosen
		int numAttris = (int) Math.sqrt(M);
		
		// Number of selected attributes
		int Ms;
		
		if(numAttris < 1)
			Ms = (int) Math.round(Math.log(M)/Math.log(2)+1);
		else
			Ms = numAttris;
		
		// Number of branches at each node. We keep 2 here.
		int C = 2;
		
		RandomForest RFC = new RandomForest(numTrees, M, Ms, C, Train, Test);		
		RFC.Start();
	}
}
