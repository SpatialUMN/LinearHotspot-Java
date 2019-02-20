import java.io.IOException;
import java.util.Arrays;


public class GetHotspots {
	public static String nodePath = "C:/Users/JingWe/Desktop/capstone/node_1000.txt";
	public static String edgePath = "C:/Users/JingWe/Desktop/capstone/new_edge_1500.txt";
	public static String outputActivityFilePrefix = "C:/Users/JingWe/Desktop/capstone/MCRandom/";
	public static int filenumber = 20 ; // number of Monte Carlo Simulation
	public static int nNewActivities = 100; // number of activities to be generated
	public static double pValue = 0.1;
	
	
		
	
public static void main(String[] args) throws IOException {
	Runtime runtime = Runtime.getRuntime();
	MakeRandomSimulationData myMakeRandomSimulationData = new MakeRandomSimulationData();
	myMakeRandomSimulationData.makeNewRandomActivitiesFile();

	AllPairShortestPathXun allPairShortestPathXun = new AllPairShortestPathXun();
	NetworkDataset myNetworkDataset = new NetworkDataset();

	//myNetworkDataset.readActivity(activityPath);
	
	long memory2 = runtime.totalMemory() - runtime.freeMemory();
	System.out.println("memory used2 = " + memory2 + " B");
	for (int nFiles = 0; nFiles < filenumber; nFiles++) {
		myNetworkDataset.readNode(nodePath);
		myNetworkDataset.readEdge(edgePath);
		String newActivitiesPath = outputActivityFilePrefix + nFiles + ".txt";
		myNetworkDataset.readActivity(newActivitiesPath);
		allPairShortestPathXun.computeAllPairShortestPath();
		
		ActivityPairMC myActivityPair = new ActivityPairMC();
		myActivityPair.processAllActivityPair();
		NetworkDataset.activityHashMap.clear();
		NetworkDataset.activityIDList.clear();
		NetworkDataset.numTotalActivities = 0;
		NetworkDataset.edgeHashMap.clear();
		NetworkDataset.edgeIDList.clear();
		NetworkDataset.numTotalEdges = 0;
		NetworkDataset.nodeHashMap.clear();
		NetworkDataset.nodeIDList.clear();
		NetworkDataset.numTotalNodes = 0;
		NetworkDataset.nodeAdjacencyMap.clear();
		shortestPathTreeXun.
		
	}
	Arrays.sort(ActivityPairMC.topdensity);
	System.out.println(ActivityPairMC.topdensity[0]);
	//get shortest path
	//ShortestPathUtilityXun.shortestPathHashMap.get(sourceNodeID+","+sourceNodeID);
}
}
