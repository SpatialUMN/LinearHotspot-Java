import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

// a few utility functions 
public class ShortestPathUtilityXun {
	
    private final static Comparator<String> weightComparator = new Comparator<String>() {
	    public int compare(String node1ID, String node2ID) {	        
	        double weightNode1 = getWeight(node1ID);
	        double weightNode2 = getWeight(node2ID);	
	        if (weightNode1 > weightNode2) return 1;	        
	        else if (weightNode1  < weightNode2) return -1;
	        else return 0;	                       
	    }
    };	
	
	static PriorityQueue<String> unvisitedNodeID = new PriorityQueue<String>(10000, weightComparator);
	static ArrayList<String> visitedNodeID = new ArrayList<String>();	
	static HashMap<String, Double> nodeWeightHashMap = new HashMap<String, Double>(); // make these 3 data structures static to make them re-used,this hashmap is used to store the temporary weight SP computing
	static HashMap<String, ShortestPathTreeXun> shortestPathTreeHashMap = new HashMap<String, ShortestPathTreeXun>(); // <source node ID, shortestPathTree>
	static HashMap<String, PathXun> shortestPathHashMap = new HashMap<String, PathXun>(); // all pair shortest path table, <nodeID1+","+nodeId2, PathXun>
	
    static void setWeight(String nodeID, double weight) {
    	// weight value to the nodeWeightHashmap
    	ShortestPathUtilityXun.unvisitedNodeID.remove(nodeID);
    	ShortestPathUtilityXun.nodeWeightHashMap.put(nodeID, new Double(weight));
    	ShortestPathUtilityXun.unvisitedNodeID.add(nodeID);
    }
    
    static double getWeight(String nodeID){
    	// given a node ID, return the its weight to the source node
    	if (ShortestPathUtilityXun.nodeWeightHashMap.get(nodeID) == null) {
    		return Double.MAX_VALUE;
    	}
    	else {
    		return ShortestPathUtilityXun.nodeWeightHashMap.get(nodeID).doubleValue();
    	}
    }
    
    public static void main(String[] args) {
		
	}
}
