import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class LoadFromFile {

	String ASPHashMapFileString = "C:/Users/JingWe/Desktop/capstone/asp.txt";
	String SPTreeHashMapFileString = "C:/Users/JingWe/Desktop/capstone/sptree.txt";
		
	void readASPHashMap () throws IOException {

		BufferedReader brASPHashMap = new BufferedReader(new FileReader(this.ASPHashMapFileString));
		String line = null;
		
		while ((line = brASPHashMap.readLine()) != null) {
			String [] rowContent = line.split("#");
			ShortestPathUtilityXun.shortestPathHashMap.put(rowContent[0], new PathXun(Integer.parseInt(rowContent[1])
					, Double.parseDouble(rowContent[2]), Double.parseDouble(rowContent[3])));
		}

		brASPHashMap.close();
	}
		
	void readSPTreeHashMap () throws IOException {
		
		BufferedReader brSPTreeHashMap = new BufferedReader(new FileReader(this.SPTreeHashMapFileString));
		String line = null;
		
		ShortestPathTreeXun tmpSPTreeXun;
		
		while ((line = brSPTreeHashMap.readLine()) != null) {

			String [] content = line.split("#"); // content [0~7]
			tmpSPTreeXun = ShortestPathUtilityXun.shortestPathTreeHashMap.get(content[0]);
			tmpSPTreeXun.treeTotalActivities = Integer.parseInt(content[1]);
			tmpSPTreeXun.treeTotalWeight = Double.parseDouble(content[2]);
				
			String [] contentPreviousNode = content[3].split(";");
			for (int j = 0; j < contentPreviousNode.length; j++) {
				int index = contentPreviousNode[j].indexOf(':');
				//System.out.println(contentPreviousNode[j].substring(0, index)+": "+contentPreviousNode[j].substring(index+1));
				tmpSPTreeXun.previousNode.put(contentPreviousNode[j].substring(0, index)
						, NetworkDataset.nodeHashMap.get(contentPreviousNode[j].substring(index+1)));
			}
			
			String [] contentPreviousEdge = content[4].split(";");
			for (int j = 0; j < contentPreviousEdge.length; j++) {
				int index = contentPreviousEdge[j].indexOf(':');
				tmpSPTreeXun.previousEdge.put(contentPreviousEdge[j].substring(0, index)
						, NetworkDataset.edgeHashMap.get(contentPreviousEdge[j].substring(index+1)));
			}
			
			String [] contentNextEdgeIDs = content[5].split(";");
			for (int j = 0; j < contentNextEdgeIDs.length; j++) {
				int index = contentNextEdgeIDs[j].indexOf(':');
				String [] array = contentNextEdgeIDs[j].substring(index+1).split("%");
				//System.out.println(array[0].length());
				tmpSPTreeXun.nextEdgeIDs.put(contentNextEdgeIDs[j].substring(0, index), new ArrayList<String>());
				for (int k = 0; k < array.length; k++) {
					if (array[k].length() > 0) {
						tmpSPTreeXun.nextEdgeIDs.get(contentNextEdgeIDs[j].substring(0, index))
						.add(array[k]);
					}
				}
			}
			
			String [] contentSumSubTree = content[6].split(";");
			for (int j = 0; j < contentSumSubTree.length; j++) {
				int index = contentSumSubTree[j].indexOf(':');
				tmpSPTreeXun.sumSubTree.put(contentSumSubTree[j].substring(0, index)
						, Double.parseDouble(contentSumSubTree[j].substring(index+1)));
			}
			
			String [] contentMaxActivities = content[7].split(";");
			for (int j = 0; j < contentMaxActivities.length; j++) {
				int index = contentMaxActivities[j].indexOf(':');
				//System.out.println(contentMaxActivities[j].substring(0,index)+": "+contentMaxActivities[j].substring(index+1));
				tmpSPTreeXun.maxActivitiesNeighborSubTreeOut.put(contentMaxActivities[j].substring(0,index)
						, Double.parseDouble(contentMaxActivities[j].substring(index+1)));
			}		
		}
		brSPTreeHashMap.close();
	}
	
	
	public static void main(String[] args) {
		

	}
}
