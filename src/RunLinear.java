import java.io.IOException;

public class RunLinear {
	
	public static String nodePath = "C:/Users/JingWe/Desktop/capstone/node_1000.txt";
	public static String edgePath = "C:/Users/JingWe/Desktop/capstone/new_edge_1500.txt";
	public static String activityPath = "C:/Users/JingWe/Desktop/capstone/newActivity_800.txt";
	//public static Sting ASPHashMapFileString=   (LoadFromFile)(SaveToFile)
	//public static Sting SPTreeHashMapFileString=
	
	
	public static void main(String[] args) throws IOException {
		
		Runtime runtime = Runtime.getRuntime();
//		long memory1 = runtime.totalMemory() - runtime.freeMemory();
//		System.out.println("memory used1 = " + memory1 + " B");
				
		AllPairShortestPathXun allPairShortestPathXun = new AllPairShortestPathXun();
		NetworkDataset myNetworkDataset = new NetworkDataset();
		myNetworkDataset.readNode(nodePath);
		myNetworkDataset.readEdge(edgePath);
		myNetworkDataset.readActivity(activityPath);
		
		long memory2 = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("memory used2 = " + memory2 + " B");

		
		long time1 = System.currentTimeMillis();
		//No need to run this during Monte Carlo
		allPairShortestPathXun.computeAllPairShortestPath();
		long time2 = System.currentTimeMillis();
				
		long memory3 = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("memory used3 = " + memory3 + " B");
		
		
		System.out.println();
		System.out.println("All pair shortest path costs = " + (time2-time1));
		System.out.println("SP tree hashmap size = " + ShortestPathUtilityXun.shortestPathTreeHashMap.size());
		System.out.println("SP path hashmap size = " + ShortestPathUtilityXun.shortestPathHashMap.size());

		
		ActivityPair myActivityPair = new ActivityPair();
		long time3 = System.currentTimeMillis();
		
		myActivityPair.processAllActivityPair(); // use neighbor node filter
		//myActivityPair.processActivityPairEdgePairPruning(); // use active edge pair upper-bound pruning
		//myActivityPair.SPTreePruning(); // use SP tree pruning (TKDE approach)
		
		long memory4 = runtime.totalMemory() - runtime.freeMemory();
		System.out.println("memory used4 = " + memory4 + " B");
		
		long time4 = System.currentTimeMillis();
		System.out.println("activity pair costs = " + (time4-time3));
	}
}