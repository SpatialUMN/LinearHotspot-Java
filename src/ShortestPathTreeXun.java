import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Data structure of a shortest path tree
//shortest path algorithms (e.g., Dijkstra's) are also implemented here
public class ShortestPathTreeXun {
	
	String rootNodeId;
	int treeTotalActivities; // number of activities on this tree
	double treeTotalWeight; // total weight of this tree
	HashMap<String, NodeXun> previousNode; // <nodeID, node>
	HashMap<String, EdgeXun> previousEdge; // <nodeID, edge>
	///////HashMap<String, ArrayList<String>> nextNodeIDs; // <nodeID, [node ID]>
	HashMap<String, ArrayList<String>> nextEdgeIDs; // <nodeID, [edge ID]>
	HashMap<String, Double> sumSubTree; // <nodeID, number of activities on the subtree rooted at the node>
	HashMap<String, Double> maxActivitiesNeighborSubTreeOut; // used in SP tree pruning, the max here means the max except the root node

	public ShortestPathTreeXun(){
		// constructor
		this.rootNodeId = null;
		this.treeTotalActivities = 0;
		this.treeTotalWeight = 0;
		this.previousNode = new HashMap<String, NodeXun>();
		this.previousEdge = new HashMap<String, EdgeXun>();
		this.nextEdgeIDs = new HashMap<String, ArrayList<String>>(); // used in SP tree pruning <nodeID, [edgeID]>
		this.sumSubTree = new HashMap<String, Double>();
		this.maxActivitiesNeighborSubTreeOut = new HashMap<String, Double>();

		ShortestPathUtilityXun.unvisitedNodeID.clear();
		ShortestPathUtilityXun.visitedNodeID.clear();
		ShortestPathUtilityXun.nodeWeightHashMap.clear(); // re-used for every shortest path, cleaned every time
	}
	
	void singleSourceShortestPath(String sourceNodeID) {
		// compute single source shortest path tree of a source node		
		
		this.rootNodeId = sourceNodeID;
		ShortestPathUtilityXun.nodeWeightHashMap.put(sourceNodeID, 0.0);
		ShortestPathUtilityXun.unvisitedNodeID.add(sourceNodeID);
		String currentNodeID;
		EdgeXun tmpEdge;
		
		ShortestPathUtilityXun.shortestPathHashMap.put(sourceNodeID+","+sourceNodeID, new PathXun(0, 0, 0));

		while ((currentNodeID = ShortestPathUtilityXun.unvisitedNodeID.poll()) != null) { // poll() includes operation of removal
			//System.out.println("xx");
			ShortestPathUtilityXun.visitedNodeID.add(currentNodeID);
			//System.out.println(this.nextNodeIDs.keySet().toString());
			
			if (currentNodeID.equals(sourceNodeID) == false) { // fill the shortest path hashmap
				PathXun pathXun = new PathXun();

				pathXun.length = ShortestPathUtilityXun.shortestPathHashMap.get(sourceNodeID+","+this.previousNode.get(currentNodeID).nodeID).length + 1;
				pathXun.nActivity = ShortestPathUtilityXun.shortestPathHashMap.get(sourceNodeID+","+this.previousNode.get(currentNodeID).nodeID).nActivity
						+ this.previousEdge.get(currentNodeID).numActivity;
				pathXun.pathWeight = ShortestPathUtilityXun.shortestPathHashMap.get(sourceNodeID+","+this.previousNode.get(currentNodeID).nodeID).pathWeight
						+ this.previousEdge.get(currentNodeID).weight;
				ShortestPathUtilityXun.shortestPathHashMap.put(sourceNodeID + "," + currentNodeID, pathXun);
			}
			
			tmpEdge = previousEdge.get(currentNodeID);
			if (tmpEdge != null) { // null for the source node
				this.treeTotalActivities += tmpEdge.numActivity; // update tree total activities
				this.treeTotalWeight += tmpEdge.weight;
			}
			processNeighborNodes(currentNodeID);
		}
		generateNextEdgeIDs();
		preProcessSumSubTree();
		recursiveSumSubTree(sourceNodeID);
		recursiveMaxNeighborSubTreeOut(sourceNodeID);
	}
	
	void processNeighborNodes(String nodeID) {
		ArrayList<String> outEdgeID;
		outEdgeID = NetworkDataset.nodeAdjacencyMap.get(nodeID);
		if (outEdgeID != null) {
			for (int i = 0; i < outEdgeID.size(); i++) {
				String neighborNodeID = NetworkDataset.edgeHashMap.get(outEdgeID.get(i)).endNodeID;			
				if(ShortestPathUtilityXun.visitedNodeID.contains(neighborNodeID)) {
					continue;
				}
				double lowestWeight = ShortestPathUtilityXun.getWeight(nodeID) + NetworkDataset.edgeHashMap.get(outEdgeID.get(i)).weight;
				if (lowestWeight < ShortestPathUtilityXun.getWeight(neighborNodeID)) {
					ShortestPathUtilityXun.setWeight(neighborNodeID, lowestWeight);
					this.previousNode.put(neighborNodeID, NetworkDataset.nodeHashMap.get(nodeID));
					this.previousEdge.put(neighborNodeID, NetworkDataset.edgeHashMap.get(outEdgeID.get(i)));
				}
			}
		}
	}

	void generateNextEdgeIDs() {
		
		for (int i = 0; i < NetworkDataset.numTotalNodes; i++) {
			this.nextEdgeIDs.put(NetworkDataset.nodeIDList.get(i), new ArrayList<String>());
		}
			
		for (String ID : this.previousNode.keySet()){
			this.nextEdgeIDs.get(this.previousNode.get(ID).nodeID).add(this.previousEdge.get(ID).edgeID);
		}
		//System.out.println("size: " +this.nextEdgeIDs.get("4").size() + ",  "+this.nextEdgeIDs.get("2").get(0));		
	}
	
	// called before the "recursiveSumSubTree()", to transfer the number of activities from edge to node
	void preProcessSumSubTree (){

		for (int i = 0; i < NetworkDataset.numTotalNodes; i++) {
			double tmp = 0;
			for (int j = 0; j < this.nextEdgeIDs.get(NetworkDataset.nodeIDList.get(i)).size(); j++) {
				tmp += NetworkDataset.edgeHashMap.get(this.nextEdgeIDs.get(NetworkDataset.nodeIDList.get(i)).get(j)).numActivity;
			}
			this.sumSubTree.put(NetworkDataset.nodeIDList.get(i), new Double(tmp));
		}
	}
	
	// recursively called, each node stores the sum number of activities of the subtree rooted at it.
	double recursiveSumSubTree (String currentNodeID){
		
		if (this.sumSubTree.get(currentNodeID) == 0) {
			return 0;
		}
		//double oldValue = this.sumSubTree.get(currentNodeID).doubleValue();
		double tmp = this.sumSubTree.get(currentNodeID).doubleValue();
		for (int i = 0; i < this.nextEdgeIDs.get(currentNodeID).size(); i++) {
			tmp += recursiveSumSubTree(NetworkDataset.edgeHashMap.get(this.nextEdgeIDs.get(currentNodeID).get(i)).endNodeID);
		}
		this.sumSubTree.put(currentNodeID, new Double(tmp));
		return tmp;
	}
	
	// recursively called, find the max number of activities on outgoing edges in the subtree rooted at currentNodeID
	double recursiveMaxNeighborSubTreeOut (String currentNodeID) {
		
		double tmp = NetworkDataset.nodeHashMap.get(currentNodeID).maxActivitiesNeighborOut;

		if (this.nextEdgeIDs.get(currentNodeID).size() == 0) { // leaf nodes
			this.maxActivitiesNeighborSubTreeOut.put(currentNodeID, new Double(tmp));
			return tmp;
		}
		
		double tmp2;
		for (int i = 0; i < this.nextEdgeIDs.get(currentNodeID).size(); i++) {
			tmp2 = recursiveMaxNeighborSubTreeOut(NetworkDataset.edgeHashMap.get(this.nextEdgeIDs.get(currentNodeID).get(i)).endNodeID);
			if (tmp < tmp2) {
				tmp = tmp2;
			}
		}
		this.maxActivitiesNeighborSubTreeOut.put(currentNodeID, tmp);
		return tmp;
	}
	
	public static void main(String[] args) throws IOException {

	}
}
