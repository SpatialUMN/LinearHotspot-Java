import java.io.IOException;

public class RunLinear {
	
	public static String nodePath = "path/to/node/file.txt";
	public static String edgePath = "path/to/edge/file.txt";
	public static String activityPath = "path/to/activity/file.txt";
	
	
	public static void main(String[] args) throws IOException {
		
		Runtime runtime = Runtime.getRuntime();
		
		AllPairShortestPathXun allPairShortestPathXun = new AllPairShortestPathXun();
		NetworkDataset myNetworkDataset = new NetworkDataset();
		myNetworkDataset.readNode(nodePath);
		myNetworkDataset.readEdge(edgePath);
		myNetworkDataset.readActivity(activityPath);
	
		long time1 = System.currentTimeMillis();

		allPairShortestPathXun.computeAllPairShortestPath();
		long time2 = System.currentTimeMillis();


		System.out.println("All pair shortest path costs = " + (time2-time1));
		System.out.println("SP tree hashmap size = " + ShortestPathUtilityXun.shortestPathTreeHashMap.size());
		System.out.println("SP path hashmap size = " + ShortestPathUtilityXun.shortestPathHashMap.size());

		
		ActivityPair myActivityPair = new ActivityPair();
		long time3 = System.currentTimeMillis();
		
		myActivityPair.processAllActivityPair(); // use neighbor node filter
		
		long time4 = System.currentTimeMillis();
		System.out.println("activity pair costs = " + (time4-time3));
	}
}
