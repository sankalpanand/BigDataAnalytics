package Assignment2;
import java.io.*;
import java.util.*;


public class FileReader 
{
	public static ArrayList<Record> buildRecords(String PATH_TO_DATA_FILE) 
	{


		ArrayList<Record> records = new ArrayList<Record>();

		// File readers and streams
		BufferedReader reader = null;
		DataInputStream dis = null;
		FileInputStream fis = null;

		try 
		{ 
			File f = new File(PATH_TO_DATA_FILE);
			fis = new FileInputStream(f); 
			reader = new BufferedReader(new InputStreamReader(fis));;

			// read the first record of the file
			String line;
			Record record = null;
			ArrayList<DiscreteAttribute> attributes;

			while ((line = reader.readLine()) != null) 
			{
				StringTokenizer st = new StringTokenizer(line, ",");
				attributes = new ArrayList<DiscreteAttribute>();
				record = new Record();

				if(Assignment2.NUM_ATTRS != st.countTokens()) 
				{
					throw new Exception("Unknown number of attributes!");
				}

				String conversion = st.nextToken();
				String time = st.nextToken();

				String maxBid = st.nextToken();
				String minBid = st.nextToken();
				String avgBid = st.nextToken();

				String maxAsk = st.nextToken();
				String minAsk = st.nextToken();
				String avgAsk = st.nextToken();

				String maxDelta = st.nextToken();
				String minDelta = st.nextToken();
				String avgDelta = st.nextToken();

				String bidDirection = st.nextToken();
				String askDirection = st.nextToken();

				// Sample input
				// EUR/USD,20140101 21:56:03.117,1.37624,1.37616,1.3762,1.37698,1.37693,1.37696,.0008,.00071,.00076,1,1

				// Add attributes
				attributes.add(new DiscreteAttribute("conversion", conversion));
				attributes.add(new DiscreteAttribute("time", time));

				attributes.add(new DiscreteAttribute("maxBid", maxBid));
				attributes.add(new DiscreteAttribute("minBid", minBid));
				attributes.add(new DiscreteAttribute("avgBid", avgBid));

				attributes.add(new DiscreteAttribute("maxAsk", maxAsk));
				attributes.add(new DiscreteAttribute("minAsk", minAsk));
				attributes.add(new DiscreteAttribute("avgAsk", avgAsk));

				attributes.add(new DiscreteAttribute("maxDelta", maxDelta));
				attributes.add(new DiscreteAttribute("minDelta", minDelta));
				attributes.add(new DiscreteAttribute("avgDelta", avgDelta));

				attributes.add(new DiscreteAttribute("bidDirection", bidDirection));
				attributes.add(new DiscreteAttribute("askDirection", askDirection));			  


				// Form a record from all these attributes
				record.setAttributes(attributes);

				// Add this single record to the records collection
				records.add(record);
			}

		} 
		catch (IOException e) 
		{ 
			System.out.println("Uh oh, got an IOException error: " + e.getMessage()); 
		} 
		catch (Exception e) 
		{
			System.out.println("Uh oh, got an Exception error: " + e.getMessage()); 
		}
		finally 
		{ 
			if (dis != null) 
			{
				try 
				{
					fis.close();
					dis.close();
					reader.close();
				} 
				catch (IOException ioe) 
				{
					System.out.println("IOException error trying to close the file: " + ioe.getMessage()); 
				}
			}
		}

		return records;
	}

}
