package IronManRandomForest;

import java.util.ArrayList;
import java.util.HashMap;

public class MainRun 
{
	public static void main(String[] args)
	{
		System.out.println("Random-Forest generation starts...");

		String traindata = "C:\\Users\\sankalp\\OneDrive\\Eclipse\\BigDataAnalytics\\src\\IronManRandomForest\\KDDTrainSmall.txt";
		String testdata = "C:\\Users\\sankalp\\OneDrive\\Eclipse\\BigDataAnalytics\\src\\IronManRandomForest\\KDDTestSmall.txt";
		int numTrees=10;
		int numThreads=1;
		boolean shouldTestForAccuracy = true;
		boolean shouldTestWithThreads = true;

		// This is the pattern of my raw data files.
		// The pattern given below means the first column is Nominal, then next three are categorical, then next 2 are nominal
		String inputDataLayout= "N,3,C,2,N,C,4,N,C,8,N,2,C,19,N,L,I";

		
		
		// Step 1- Start preparing data from the files to Decision Trees. Instantiate a class that has methods for this purpose. 
		DescribeTrees DT = new DescribeTrees(traindata, inputDataLayout);
		
		// Step 2- Generate training and testing data with the help of above class
		ArrayList<ArrayList<String>> trainDataInstances = DT.prepareInputData(traindata, inputDataLayout);
		ArrayList<ArrayList<String>> testDataList = DT.prepareInputData(testdata, inputDataLayout);


		// Step 3- Create frequency table of how many times each label appeared
		HashMap<String, Integer> labelFreq = new HashMap<String, Integer>();
		for(ArrayList<String> instance : trainDataInstances)
		{
			String label = instance.get(instance.size()-1);
			if(labelFreq.containsKey(label))
				labelFreq.put(label, labelFreq.get(label)+1);
			else
				labelFreq.put(label, 1);				
		}
		int totalUniqueLabels = labelFreq.size();

		// Step 4- Get total number of features in the final dataset
		ArrayList<Character> finalDataLayout = DT.CreateFinalLayout(inputDataLayout);
		int numTotalFeatures = finalDataLayout.size()-1;

		// Step 5- Set the number of attributes to be chosen ( sqrt(M) is set to default ). 
		// If sqrt(N) < 1 then set some default value
		int numChosenAttris = (int) Math.sqrt(numTotalFeatures);		
		int Ms;
		if(numChosenAttris<1)
			Ms = (int)Math.round(Math.log(numTotalFeatures)/Math.log(2)+1);
		else
			Ms=numChosenAttris;
		
		
		// Step 6- Generate random forest
		RandomForest RFC = new RandomForest(finalDataLayout, numTrees,numThreads, numTotalFeatures, Ms, totalUniqueLabels, trainDataInstances, testDataList);
		
		// Step 7- Test for accuracy
		RFC.Start(shouldTestForAccuracy, shouldTestWithThreads);

		
	}
}
