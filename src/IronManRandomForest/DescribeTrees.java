package IronManRandomForest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DescribeTrees 
{
	//method to take the txt fle as input and pass those values to random forests
	BufferedReader BR = null;
	String path;
	String layout;

	public DescribeTrees(String path, String layout)
	{
		this.path=path;
		this.layout = layout;
	}

	// This method 
	public ArrayList<ArrayList<String>> prepareInputData(String path, String layout)
	{
		ArrayList<ArrayList<String>> allInstanceData = new ArrayList<ArrayList<String>>();

		try 
		{
			String sCurrentLine;
			BR = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = BR.readLine()) != null) 
			{
				// This assumes that the file is a valid CSV
				if(sCurrentLine!=null)
				{
					String[] attributes = sCurrentLine.split(",");
					ArrayList<String> singleInstanceData =new ArrayList<String>();
					for(String attribute : attributes)
						singleInstanceData.add(attribute);

					allInstanceData.add(singleInstanceData);
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (BR != null)
					BR.close();
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}

		// Verify the layout of the file- Check the number of layout parameters with the given in the input file 
		char[] inputDataLayout = CreateLayout(layout);
		if(inputDataLayout.length != allInstanceData.get(0).size())
		{
			System.out.print("Data Layout is incorrect. Parameter length given- "+ inputDataLayout.length + " but data length read- " + allInstanceData.get(0).size());
			return null;
		}

		/* Data file cleaning takes place here */
		/* 1- Remove ignored features from the data set */
		/* 2- Move single label to the end */

		// Create an array list out of all the characters in the layout
		ArrayList<Character> listDataLayout  = new ArrayList<Character>();
		for(char c: inputDataLayout)
			listDataLayout.add(c);

		// If an i'th character is to be ignored, we should remove it from our allInstanceData as well.
		for(int i=0; i< listDataLayout.size();i++)
		{
			if(listDataLayout.get(i)=='I')
			{
				// Remove it from the arraylist
				listDataLayout.remove(i);

				// Also remove its value from all the instances
				for(ArrayList<String> singleInstanceData : allInstanceData)
					singleInstanceData.remove(i);

				i=i-1;
			}
		}

		// If any labels are coming in between, move them to the last
		for(int i=0; i< listDataLayout.size(); i++)
		{
			if( listDataLayout.get(i)=='L')
			{
				for(ArrayList<String> singleInstanceData : allInstanceData)
				{
					// Swap this labels position with the last position
					String swap = singleInstanceData.get(i);
					singleInstanceData.set(i, singleInstanceData.get(singleInstanceData.size()-1));
					singleInstanceData.set(singleInstanceData.size()-1, swap);
				}
			}

			break;
		}

		return allInstanceData;
	}

	/**
	 * Breaks the run length code for data layout
	 * N-Nominal/Number/Real
	 * C-Categorical/Alphabetical/Numerical
	 * I-Ignore Attribute
	 * L-Label - last of the fields
	 *
	 * @param dataInfo
	 * @return
	 */
	public char[] CreateLayout(String dataIn)
	{
		ArrayList<Character> LaySet = new ArrayList<Character>();

		LaySet.add('N');
		LaySet.add('C');
		LaySet.add('I');
		LaySet.add('L');

		// Create a char array from CSV String of parameters
		char[] charLay  = dataIn.trim().toCharArray();
		ArrayList<Character> layo = new ArrayList<Character>();
		for(char ch: charLay )
		{
			if(ch!=',')
				layo.add(ch);
		}

		charLay  = new char[layo.size()];
		for(int i=0;i<layo.size();i++)
			charLay [i] = layo.get(i);

		layo.clear();

		ArrayList<Integer> number = new ArrayList<Integer>();
		for(int i=0;i< charLay.length;i++)
		{
			if(LaySet.contains(charLay [i]))
			{
				if(convertonumb(number)<=0)
					layo.add(charLay [i]);
				else
				{
					for(int j=0;j<convertonumb(number);j++)
					{
						layo.add(charLay [i]);
					}
				}
				number.clear();
			}
			else
				number.add(Character.getNumericValue(charLay [i]));					
		}

		charLay  = new char[layo.size()];
		for(int i=0;i<layo.size();i++)
			charLay [i] = layo.get(i);
		return charLay ;
	}





	// Creates final list that Forest will use as reference
	public ArrayList<Character> CreateFinalLayout(String dataIn) 
	{
		ArrayList<Character> LaySet = new ArrayList<Character>();

		LaySet.add('N');
		LaySet.add('C');
		LaySet.add('I');
		LaySet.add('L');

		// Create a char array from CSV String of parameters
		char[] charLay = dataIn.toCharArray();
		ArrayList<Character> layo = new ArrayList<Character>();
		for(char ch: charLay)
		{
			if(ch != ',')
				layo.add(ch);
		}

		charLay = new char[layo.size()];
		for(int i=0;i<layo.size();i++)
			charLay[i] = layo.get(i);

		layo.clear();

		ArrayList<Integer> number = new ArrayList<Integer>();
		for(int i=0; i < charLay.length; i++)
		{
			// If layset contains N,C,I or L
			if(LaySet.contains(charLay[i]))
			{
				if(convertonumb(number)<=0)
					layo.add(charLay[i]);
				else
				{
					for(int j=0; j<convertonumb(number); j++)
					{
						layo.add(charLay[i]);
					}
				}
				number.clear();
			}

			// If layset does not contains N,C,I or L. It means it is the numeric value.
			// Convert it into number.
			else
			{
				int numI = Character.getNumericValue(charLay[i]);				
				number.add(numI);
			}
		}

		for(int i=0; i<layo.size(); i++)
		{
			if(layo.get(i)=='I' || layo.get(i)=='L')
			{
				layo.remove(i);
				i=i-1;
			}
		}
		layo.add('L');
		System.out.println("Final Data Layout Parameters " + layo);
		return layo;
	}

	// converts arraylist to numbers
	private int convertonumb(ArrayList<Integer> number) 
	{
		int numb=0;
		if(number!=null)
		{
			for(int i=0; i<number.size(); i++)
			{
				numb = numb*10 + number.get(i);
			}
		}
		return numb;
	}
}
