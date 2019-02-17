import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


// save shortest path hashmap and shortest path tree hashmap to files
public class SaveToFile {
	
	String ASPHashMapFileString = "C:/Users/JingWe/Desktop/capstone/asp.txt";
	String SPTreeHashMapFileString = "C:/Users/JingWe/Desktop/capstone/sptree.txt";
	
	void writeASPHashMap () throws IOException {

		BufferedWriter bwASPHashMap = new BufferedWriter(new FileWriter(this.ASPHashMapFileString));
		
		for (String ID : ShortestPathUtilityXun.shortestPathHashMap.keySet()) {
			bwASPHashMap.write(ID+"#");// "# means the split label of ID and Path"
			PathXun path = ShortestPathUtilityXun.shortestPathHashMap.get(ID);
			bwASPHashMap.write(path.length+"#"+path.pathWeight+"#"+path.nActivity+"\n");
		}
		
		bwASPHashMap.close();
	}
	
	void writeSPTreeHashMap () throws IOException {
		
		BufferedWriter bwSPTreeHashMap = new BufferedWriter(new FileWriter(this.SPTreeHashMapFileString));
		
		ShortestPathTreeXun tmpSPTreeXun;
		for (String treeID : ShortestPathUtilityXun.shortestPathTreeHashMap.keySet()) {
			tmpSPTreeXun = ShortestPathUtilityXun.shortestPathTreeHashMap.get(treeID);
			bwSPTreeHashMap.write(treeID+"#");
			bwSPTreeHashMap.write(tmpSPTreeXun.treeTotalActivities+"#");
			bwSPTreeHashMap.write(tmpSPTreeXun.treeTotalWeight+"#");
			
			for (String previousNodeID : tmpSPTreeXun.previousNode.keySet()) {
				bwSPTreeHashMap.write(previousNodeID+":");
				bwSPTreeHashMap.write(tmpSPTreeXun.previousNode.get(previousNodeID).nodeID+";");
			}
			bwSPTreeHashMap.write("#");
			
			for (String previousEdgeID : tmpSPTreeXun.previousEdge.keySet()) {
				bwSPTreeHashMap.write(previousEdgeID+":");
				bwSPTreeHashMap.write(tmpSPTreeXun.previousEdge.get(previousEdgeID).edgeID+";");
			}
			bwSPTreeHashMap.write("#");
			
			for (String nextEdgeIDsID : tmpSPTreeXun.nextEdgeIDs.keySet()) {
				bwSPTreeHashMap.write(nextEdgeIDsID+":");
				for (int i = 0; i < tmpSPTreeXun.nextEdgeIDs.get(nextEdgeIDsID).size(); i++) {
					bwSPTreeHashMap.write(tmpSPTreeXun.nextEdgeIDs.get(nextEdgeIDsID).get(i) + "%");
				}
				bwSPTreeHashMap.write(";");
			}
			bwSPTreeHashMap.write("#");
			
			for (String sumSubTreeID : tmpSPTreeXun.sumSubTree.keySet()) {
				bwSPTreeHashMap.write(sumSubTreeID + ":");
				bwSPTreeHashMap.write(tmpSPTreeXun.sumSubTree.get(sumSubTreeID)+";");
			}
			bwSPTreeHashMap.write("#");
			
			for (String maxActivitiesNeighborSubTreeOutID : tmpSPTreeXun.maxActivitiesNeighborSubTreeOut.keySet()) {
				bwSPTreeHashMap.write(maxActivitiesNeighborSubTreeOutID + ":");
				bwSPTreeHashMap.write(tmpSPTreeXun.maxActivitiesNeighborSubTreeOut.get(maxActivitiesNeighborSubTreeOutID) + ";");
			}
			bwSPTreeHashMap.write("#");
			bwSPTreeHashMap.write("\n");
		}
				
		bwSPTreeHashMap.close();
	}
	
	
	public static void main(String[] args) {
	}
}
