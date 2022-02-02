package weiwilli_CSCI301_Project1a;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;
import org.junit.Before;

public class TestFlightMap {

	private FlightMap g;
	private HashMap<String, Integer> costs;
	@Before
	public void setup()
	{
		String[] list = {"P", "W", "R", "X", "Q", "W", "S", "T", "Y", "Z"};
		
        g = new FlightMap(list);
 
        g.addEdge("P", "W");
        g.addEdge("P", "R");
        g.addEdge("R", "X");
        g.addEdge("Q", "X");
        g.addEdge("W", "S");
        g.addEdge("S", "T");
        g.addEdge("T", "W");
        g.addEdge("W", "Y");
        g.addEdge("Y", "Z");
        g.addEdge("Y", "R");
        
        costs = new HashMap<String, Integer>();
        
        costs.put("PW", 200);
        costs.put("PR", 300);
        costs.put("RX", 200);
        costs.put("QX", 375);
        costs.put("WS", 250);
        costs.put("ST", 300);
        costs.put("TW", 350);
        costs.put("WY", 500);
        costs.put("YZ", 450);
        costs.put("YR", 600);
        
	}
	
	@Test
	public void testing()
	{
		assertEquals(g.BFS(0, "R"), "PR");
		assertEquals(g.BFS(0, "X"), "PRX");
		assertEquals(g.BFS(0, "W"), "PW");
		assertEquals(g.BFS(0, "S"), "PWS");
		assertEquals(g.BFS(0, "T"), "PWST");
		assertEquals(g.BFS(0, "Y"), "PWY");
		assertEquals(g.BFS(0, "Z"), "PWYZ");
		
		assertEquals(g.calculate("PR", costs), 300);
		assertEquals(g.calculate("PRX", costs), 500);
		assertEquals(g.calculate("PW", costs), 200);
		assertEquals(g.calculate("PWS", costs), 450);
		assertEquals(g.calculate("PWST", costs), 750);
		assertEquals(g.calculate("PWY", costs), 700);
		assertEquals(g.calculate("PWYZ", costs), 1150);
	}
	
}
