import java.io.IOException;

public class AllPairShortestPathXun {
	public static double theta = 5; // statistic measurement threshold
	public static boolean ifLoadFromFile = false;
	
	void computeAllPairShortestPath() throws IOException{
		// compute all pair shortest path of a network
		
		if (ifLoadFromFile == false) { // compute ASP
			for (int i = 0; i < NetworkDataset.numTotalNodes; i++) {		
				NodeXun rootNode = NetworkDataset.nodeHashMap.get(NetworkDataset.nodeIDList.get(i));
				if (rootNode.ifActiveNode == true) { // only compute SP-trees that are rooted at active nodes
					ShortestPathUtilityXun.shortestPathTreeHashMap.put(rootNode.nodeID, new ShortestPathTreeXun());
					ShortestPathUtilityXun.shortestPathTreeHashMap.get(rootNode.nodeID).singleSourceShortestPath(rootNode.nodeID);					
				}
			}
			SaveToFile saveToFile = new SaveToFile();
			saveToFile.writeASPHashMap();
			saveToFile.writeSPTreeHashMap();
		}
		
		else { // load ASP from file
			for (int i = 0; i < NetworkDataset.numTotalNodes; i++) {		
				NodeXun rootNode = NetworkDataset.nodeHashMap.get(NetworkDataset.nodeIDList.get(i));
				if (rootNode.ifActiveNode == true) { // only compute SP-trees that are rooted at active nodes
					ShortestPathUtilityXun.shortestPathTreeHashMap.put(rootNode.nodeID, new ShortestPathTreeXun());
				}
			}
			LoadFromFile loadFromFile = new LoadFromFile();
			loadFromFile.readASPHashMap();
			loadFromFile.readSPTreeHashMap();
//			SaveToFile saveToFile = new SaveToFile();
//			saveToFile.writeASPHashMap();
//			saveToFile.writeSPTreeHashMap();
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		
	}
}
