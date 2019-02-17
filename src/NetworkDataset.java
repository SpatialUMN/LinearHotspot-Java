import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


// read data

public class NetworkDataset {
	
	static HashMap<String, NodeXun> nodeHashMap; // <nodeID, NodeXun>
	static HashMap<String, EdgeXun> edgeHashMap; // <edgeID, edgeXun>
	static HashMap<String, ActivityXun> activityHashMap; // <activityID, ActivityXun>
	static HashMap<String, ArrayList<String>> nodeAdjacencyMap; // <nodeID, [edgeID]>
	
	static ArrayList<String> nodeIDList; // put node IDs in a list
	static ArrayList<String> edgeIDList; // put edge IDs in a list
	static ArrayList<String> activityIDList; // put activity IDs in a list
	
	static int numTotalNodes = 0;
	static int numTotalEdges = 0;
	static int numTotalActivities = 0;
	static double totalWeight = 0;
	
	void readNode(String nodePath) throws IOException {
		// read nodes into data structure
		nodeHashMap = new HashMap<String, NodeXun>();
		nodeIDList = new ArrayList<String>();
		nodeAdjacencyMap = new HashMap<String, ArrayList<String>>(); // <nodeID, [edgeID]>
		
		String splitBy = ";";
		String line ="";
		BufferedReader br = new BufferedReader(new FileReader(nodePath));
		
		while ((line = br.readLine()) != null) {
			String [] rowContent = line.split(splitBy);
			nodeIDList.add(rowContent[0]);
			nodeHashMap.put(rowContent[0], new NodeXun(rowContent[0], Double.parseDouble(rowContent[1]), Double.parseDouble(rowContent[2]), false));
			nodeAdjacencyMap.put(rowContent[0], new ArrayList<String>());
		}		
		br.close();
	}
	
	void readEdge(String edgePath) throws IOException {
		// read edges into data structure
		edgeHashMap = new HashMap<String, EdgeXun>();
		edgeIDList = new ArrayList<String>();
		totalWeight = 0;
		
		String splitBy = ";";
		String line ="";
		BufferedReader br = new BufferedReader(new FileReader(edgePath));
		
		while ((line = br.readLine()) != null) {
			String [] rowContent = line.split(splitBy);
			edgeIDList.add(rowContent[0]);
			edgeHashMap.put(rowContent[0], new EdgeXun(rowContent[0], rowContent[1], rowContent[2], Double.parseDouble(rowContent[3]), false));
			nodeAdjacencyMap.get(rowContent[1]).add(rowContent[0]);
			totalWeight += Double.parseDouble(rowContent[3]);
		}	
		br.close();
	}
	
	void readActivity(String activityPath) throws IOException {
		// read activities into data structure
		activityHashMap = new HashMap<String, ActivityXun>();
		activityIDList = new ArrayList<String>();
		//activeEdgeIDList = new ArrayList<String>();
		
		String splitBy = ";";
		String line ="";
		BufferedReader br = new BufferedReader(new FileReader(activityPath));
		
		while ((line = br.readLine()) != null) {
			String [] rowContent = line.split(splitBy);
			activityIDList.add(rowContent[0]);
			ActivityXun tmpActivityXun = new ActivityXun(rowContent[0], rowContent[3], Double.parseDouble(rowContent[1]), Double.parseDouble(rowContent[2]));
			activityHashMap.put(rowContent[0], tmpActivityXun);
			edgeHashMap.get(rowContent[3]).activity.add(tmpActivityXun);
			edgeHashMap.get(rowContent[3]).numActivity++;
			edgeHashMap.get(rowContent[3]).ifActiveEdge = true; // set active_edge
			//activeEdgeIDList.add(rowContent[3]);
			nodeHashMap.get(edgeHashMap.get(rowContent[3]).startNodeID).ifActiveNode = true; // set active_node
			nodeHashMap.get(edgeHashMap.get(rowContent[3]).endNodeID).ifActiveNode = true;
		}
		br.close();
		numTotalNodes = nodeHashMap.size();
		numTotalEdges = edgeHashMap.size();
		numTotalActivities = activityHashMap.size();
		
		// sort activities on active_edge according their distance to the edge's start node (ascending order)
		for (int i = 0; i < edgeIDList.size(); i++) {
			if (edgeHashMap.get(edgeIDList.get(i)).ifActiveEdge == true) {
				for (int j = 0; j < edgeHashMap.get(edgeIDList.get(i)).numActivity; j++) {
					for (int k = j+1; k < edgeHashMap.get(edgeIDList.get(i)).numActivity; k++) {
						if (edgeHashMap.get(edgeIDList.get(i)).activity.get(j).weightToNode1 > edgeHashMap.get(edgeIDList.get(i)).activity.get(k).weightToNode1) {
							//System.out.println(edgeHashMap.get(edgeIDList.get(i)).edgeID);
							ActivityXun swapActivityXun = edgeHashMap.get(edgeIDList.get(i)).activity.get(j);
							edgeHashMap.get(edgeIDList.get(i)).activity.set(j, edgeHashMap.get(edgeIDList.get(i)).activity.get(k));
							edgeHashMap.get(edgeIDList.get(i)).activity.set(k, swapActivityXun);
						}
					}
				}
				if (nodeHashMap.get(edgeHashMap.get(edgeIDList.get(i)).startNodeID).maxActivitiesNeighborOut < edgeHashMap.get(edgeIDList.get(i)).numActivity) {
					nodeHashMap.get(edgeHashMap.get(edgeIDList.get(i)).startNodeID).maxActivitiesNeighborOut = edgeHashMap.get(edgeIDList.get(i)).numActivity;
				}
				if (nodeHashMap.get(edgeHashMap.get(edgeIDList.get(i)).endNodeID).maxActivitiesNeighborIn < edgeHashMap.get(edgeIDList.get(i)).numActivity) {
					nodeHashMap.get(edgeHashMap.get(edgeIDList.get(i)).endNodeID).maxActivitiesNeighborIn = edgeHashMap.get(edgeIDList.get(i)).numActivity;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {

	}
}
