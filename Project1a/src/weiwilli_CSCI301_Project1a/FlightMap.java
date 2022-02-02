package weiwilli_CSCI301_Project1a;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Objects;

public class FlightMap{
	
	private int v;
	private HashMap<String, Integer> hash; //Converts city names into numbers
	private LinkedList<Integer> adj[]; //Adjacency Lists
	
	//This constructor initializes the hash map and adjacency lists and fills the hashmap
	public FlightMap(String[] list)
	{
		v = list.length;
		hash = new HashMap<String, Integer>();
		for(int i=0; i<list.length; i++)
		{
			hash.put(list[i], i);
		}
		
		adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
	}
	
	//Sets up the adjacency list depending on if there exists a flight between cities
	public void addEdge(String source, String destination)
	{
		adj[hash.get(source)].add(hash.get(destination));
	}
	
	//Basic conversion from value of the hashmap back into the key
	//Takes a number and outputs the name of the city
	private String getKey(int s)
	{
		 for (Entry<String, Integer> entry : hash.entrySet()) {
		        if (Objects.equals(s, entry.getValue())) {
		            return entry.getKey();
		        }
		    }
		return null;
	}
	
	//Performs BFS on the graph of cities and finds the path from the origin city s to the target city target
	//Outputs the path of from the city
	public String BFS(int s, String target)
    {
		int t = hash.get(target);
		
		int[] parents = new int[v];
		parents[0] = 0;
		
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[v];
 
        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();
 
        // Mark the current node as visited and enqueue it
        visited[s]=true;
        queue.add(s);
 
        while (queue.size() != 0)
        {
            // Dequeue a vertex from queue and print it
            s = queue.poll();
            //System.out.print(getKey(s) + " ");
 
            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    parents[n] = s;
                    if(n==t)
                    {
                    	String path = getKey(n);
                    	while(n!=0)
                    	{
                    		path = getKey(parents[n]) + path;
                    		n = parents[n];
                    	}
                    	return path;
                    }
                    queue.add(n);
                }
            }
        }
        return "Path Not Found";
        
    }
	
	//Takes in a HashMap that contains the costs of flights between cities and a path between one city to another
	//Outputs the cost it will take to travel across the path
	public int calculate(String path, HashMap<String, Integer> costs)
	{
		if(path.length()<=1) return 0;
		if(path.equals("Path Not Found")) return 0;
		
		int sum = 0;
		for(int i=0; i<path.length()-1; i++)
		{
			sum += costs.get(path.substring(i, i+2));
		}
		return sum;
	}
	

}

//This code for bfs is contributed by Aakash Hasija found on the GeeksforGreeks website on BFS.

