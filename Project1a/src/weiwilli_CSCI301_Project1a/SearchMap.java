package weiwilli_CSCI301_Project1a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class SearchMap {
	
	public static void main(String[] args)
	{
		System.out.println("Start");
		ArrayList<String> initList = new ArrayList<String>();
		HashMap<String, Integer> costs = new HashMap<String, Integer>();
		ArrayList<String> start = new ArrayList<String>();
		ArrayList<String> destination = new ArrayList<String>();
		
		
		//String inputFile = "input.txt";
		String inputFile = args[0];

				
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
			String taskString = bufferedReader.readLine();
			
			boolean source = true;
			
			while(taskString!=null)
			{
				String[] attributes = taskString.split(" ");
				
				if(source)
				{
					initList.add(attributes[0]);
					source = false;
				}
				else
				{
					boolean exists = false;
					for(int i=0; i<initList.size(); i++)
					{
						if(attributes[0].equals(initList.get(i)))
						{
							exists = true;
						}
					}
					if(!exists) initList.add(attributes[0]);
					
					
					exists = false;
					for(int i=0; i<initList.size(); i++)
					{
						if(attributes[1].equals(initList.get(i)))
						{
							exists = true;
						}
					}
					if(!exists) initList.add(attributes[1]);
					
					String concat = attributes[0] + attributes[1];
					costs.put(concat, Integer.parseInt(attributes[2]));
					
					start.add(attributes[0]);
					destination.add(attributes[1]);
				}
				
				
				taskString = bufferedReader.readLine();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("No file found, please try another file :(");
		} catch(NullPointerException e){	
			System.out.println("Null Pointer Error, please try another file.");
		} catch (IOException e) {
			System.out.println("There was an IO Exception, please try another file");
		}
		
		
		String[] list = new String[initList.size()];
		for(int i=0; i<initList.size(); i++)
		{
			list[i] = initList.get(i);
		}
		
		FlightMap g = new FlightMap(list);
		
		for(int i=0; i<start.size(); i++)
		{
			g.addEdge(start.get(i), destination.get(i));
		}
		
		try {
			  //File file = new File("src/weiwilli_CSCI301_Project1a/" + "output.txt");
			  File file = new File(args[1]);
		      FileWriter fw = new FileWriter(file);
		      PrintWriter pw = new PrintWriter(fw);

		      for(int i=1; i<list.length; i++)
				{
					String x = g.BFS(0, list[i]);
					String y = list[i] + " " + x + " " + g.calculate(x, costs);
					pw.write(y);
					pw.write("\n");
				}
		      
		      pw.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	
		
		
		
	}
}
