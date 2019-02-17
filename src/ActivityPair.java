import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ActivityPair {
	
	static HashMap<String, ArrayList<String>> possibleEdgeTable = new HashMap<String, ArrayList<String>>(); // only used in SP tree pruning (nodeID, [edgeID])
	
	// returns the index of the smallest number among four inputs
	int minOfFour (double zero, double one, double two, double three){
		double temp1 = Math.min(zero, one);
		double temp2 = Math.min(two, three);
		if (Math.min(temp1, temp2) == zero ) return 0;
		if (Math.min(temp1, temp2) == one ) return 1;
		if (Math.min(temp1, temp2) == two ) return 2;
		if (Math.min(temp1, temp2) == three ) return 3;
		return -1;
	}
	
	// similar to minOfFour(), but return the value instead of index
	double minOfFourValue (double zero, double one, double two, double three) {
		double temp1 = Math.min(zero, one);
		double temp2 = Math.min(two, three);
		return Math.min(temp1, temp2);
	}
	
	double maxOfFourValue (double zero, double one, double two, double three) {
		double temp1 = Math.max(zero, one);
		double temp2 = Math.max(two, three);
		return Math.max(temp1, temp2);
	}
	
	// returns true if two input edges are just reversed
	boolean ifReversedEdge(String edgeID1, String edgeID2) {
		if ((NetworkDataset.edgeHashMap.get(edgeID1).startNodeID.equals(NetworkDataset.edgeHashMap.get(edgeID2).endNodeID))
			&& (NetworkDataset.edgeHashMap.get(edgeID1).endNodeID.equals(NetworkDataset.edgeHashMap.get(edgeID2).startNodeID))){
			return true;
		}
		else {
			return false;
		}
	}
	
	void processAllActivityPair() {
		
		double weight1 = 0, weight2 = 0;
		double weight3 = 0, weight4 = 0;
		String startNode1ID = null; // node 1 of start edge
		String startNode2ID = null; // node 2 of start edge
		String endNode1ID = null; // node 1 of end edge
		String endNode2ID = null; // node 2 of end edge
		double statisticMeasureValue = -1;
		//Monte Carlo
		
		
		for ( String edgeKeysStart : NetworkDataset.edgeHashMap.keySet() ) { // 2-level loop to enumerate all pairs of active edges
			if (NetworkDataset.edgeHashMap.get(edgeKeysStart).ifActiveEdge == true) {	
		
				startNode1ID = NetworkDataset.edgeHashMap.get(edgeKeysStart).startNodeID;
				startNode2ID = NetworkDataset.edgeHashMap.get(edgeKeysStart).endNodeID;
				
				for (String edgeKeysEnd : NetworkDataset.edgeHashMap.keySet() ) {
					if ((edgeKeysEnd == edgeKeysStart)
							|| (NetworkDataset.edgeHashMap.get(edgeKeysEnd).ifActiveEdge == false)
							|| (ifReversedEdge(edgeKeysStart, edgeKeysEnd) == true)) {
						continue;
					}
					
					//System.out.println("start: " + edgeKeysStart+", end: " + edgeKeysEnd);
					endNode1ID = NetworkDataset.edgeHashMap.get(edgeKeysEnd).startNodeID;
					endNode2ID = NetworkDataset.edgeHashMap.get(edgeKeysEnd).endNodeID;
					weight1 = ShortestPathUtilityXun.shortestPathHashMap.get(startNode1ID+","+endNode1ID).pathWeight; // weight of the 4 shortest path 
					weight2 = ShortestPathUtilityXun.shortestPathHashMap.get(startNode1ID+","+endNode2ID).pathWeight;
					weight3 = ShortestPathUtilityXun.shortestPathHashMap.get(startNode2ID+","+endNode1ID).pathWeight;
					weight4 = ShortestPathUtilityXun.shortestPathHashMap.get(startNode2ID+","+endNode2ID).pathWeight;
					
					for (int i = 0; i < NetworkDataset.edgeHashMap.get(edgeKeysStart).numActivity; i++) { // i-th activity on start edge
						for (int j = 0; j < NetworkDataset.edgeHashMap.get(edgeKeysEnd).numActivity; j++) {  //j-th activity on end edge (activity on edge are ordered by their distance to start node of the edge)

							double partialWeight1 = NetworkDataset.edgeHashMap.get(edgeKeysStart).activity.get(i).weightToNode1; // activity i to its start
							double partialWeight2 = NetworkDataset.edgeHashMap.get(edgeKeysStart).activity.get(i).weightToNode2; // activity i to its end
							double partialWeight3 = NetworkDataset.edgeHashMap.get(edgeKeysEnd).activity.get(j).weightToNode1; // activity j to its start
							double partialWeight4 = NetworkDataset.edgeHashMap.get(edgeKeysEnd).activity.get(j).weightToNode2; // activity j to its end
							String activity1 = NetworkDataset.edgeHashMap.get(edgeKeysStart).activity.get(i).activityID; // activity 1
							String activity2 = NetworkDataset.edgeHashMap.get(edgeKeysEnd).activity.get(j).activityID; // activity 2
							
							
							int index = minOfFour(weight1 + partialWeight1 + partialWeight3
									, weight2 + partialWeight1 + partialWeight4
									, weight3 + partialWeight2 + partialWeight3
									, weight4 + partialWeight2 + partialWeight4);
							
							if (index == 0) { // from start edge node 1, to end edge node 1
								statisticMeasureValue = StatisticMeasure.densityRatio(ShortestPathUtilityXun.shortestPathHashMap.get(startNode1ID+","+endNode1ID).nActivity+i+j+2, weight1 + partialWeight1 + partialWeight3);
								System.out.println(activity1+";"+activity2+";"+startNode1ID+";"+endNode1ID+","+statisticMeasureValue+";"+(double)(weight1 + partialWeight1 + partialWeight3)+";"+(double)(ShortestPathUtilityXun.shortestPathHashMap.get(startNode1ID+","+endNode1ID).nActivity+i+j+2));
							}
							else if (index == 1) {  // from start edge node 1, to end edge node 2
								statisticMeasureValue = StatisticMeasure.densityRatio(ShortestPathUtilityXun.shortestPathHashMap.get(startNode1ID+","+endNode2ID).nActivity+i+1+NetworkDataset.edgeHashMap.get(edgeKeysEnd).numActivity-j, weight2 + partialWeight1 + partialWeight4);
								System.out.println(activity1+";"+activity2+";"+startNode1ID+";"+endNode2ID+","+statisticMeasureValue+";"+(double)(weight2 + partialWeight1 + partialWeight4)+";"+(double)(ShortestPathUtilityXun.shortestPathHashMap.get(startNode1ID+","+endNode2ID).nActivity+i+1+NetworkDataset.edgeHashMap.get(edgeKeysEnd).numActivity-j));
							}
							else if (index == 2) {   // from start edge node 2, to end edge node 1
								statisticMeasureValue = StatisticMeasure.densityRatio(ShortestPathUtilityXun.shortestPathHashMap.get(startNode2ID+","+endNode1ID).nActivity+NetworkDataset.edgeHashMap.get(edgeKeysStart).numActivity-i+j+1, weight3 + partialWeight2 + partialWeight3);
								System.out.println(activity1+";"+activity2+";"+startNode2ID+";"+endNode1ID+","+statisticMeasureValue+";"+(double)(weight3 + partialWeight2 + partialWeight3)+";"+(double)(ShortestPathUtilityXun.shortestPathHashMap.get(startNode2ID+","+endNode1ID).nActivity-i+j+1));
							
							}
							else if (index == 3) {  // from start edge node 2, to end edge node 2
								statisticMeasureValue = StatisticMeasure.densityRatio(ShortestPathUtilityXun.shortestPathHashMap.get(startNode2ID+","+endNode2ID).nActivity+NetworkDataset.edgeHashMap.get(edgeKeysStart).numActivity-i+NetworkDataset.edgeHashMap.get(edgeKeysEnd).numActivity-j, weight4 + partialWeight2 + partialWeight4);					
								System.out.println(activity1+";"+activity2+";"+startNode2ID+";"+endNode2ID+","+statisticMeasureValue+";"+(double)(weight4 + partialWeight2 + partialWeight4)+";"+(double)(ShortestPathUtilityXun.shortestPathHashMap.get(startNode2ID+","+endNode2ID).nActivity+NetworkDataset.edgeHashMap.get(edgeKeysStart).numActivity-i+NetworkDataset.edgeHashMap.get(edgeKeysEnd).numActivity-j));
							}
							//System.out.println("statistic measure value = " + statisticMeasureValue);
							//Monte Carlo simulation:
							
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		}
	}
