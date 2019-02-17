
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MapMatching {

	static String nodePath = "/Users/xuntang/Desktop/linear_hotspot_hennepin/Hennepin_County_Crash_System_GIS_Data/extracted/readyForASP/hennepin_nodes.txt";
	static String edgePath = "/Users/xuntang/Desktop/linear_hotspot_hennepin/Hennepin_County_Crash_System_GIS_Data/extracted/readyForASP/hennepin_edges.txt";
	static String activityPath = "/Users/xuntang/Desktop/linear_hotspot_hennepin/Hennepin_County_Crash_System_GIS_Data/extracted/readyForASP/hennepin_activities.txt";
	static String activityPathWrite = "/Users/xuntang/Desktop/mapmatched_activities.txt";
	
	
	static public void mapMatching () throws IOException {
		
		ArrayList<Double> nodeLonList = new ArrayList<Double>();
		ArrayList<Double> nodeLatList = new ArrayList<Double>();
		
		ArrayList<Integer> edgeStartNodeIDList = new ArrayList<Integer>();
		ArrayList<Integer> edgeEndNodeIDList = new ArrayList<Integer>();
		ArrayList<Double> edgeWeightList = new ArrayList<Double>();
		ArrayList<Double> edgeParameterAList = new ArrayList<Double>();
		ArrayList<Double> edgeParameterCList = new ArrayList<Double>();
		
		ArrayList<Double> activityLonList = new ArrayList<Double>();
		ArrayList<Double> activityLatList = new ArrayList<Double>();
		ArrayList<Integer> activityBelongEdgeIDList = new ArrayList<Integer>();
		ArrayList<Double> activityLonNewList = new ArrayList<Double>(); // output projected coordinates
		ArrayList<Double> activityLatNewList = new ArrayList<Double>();
		
		BufferedReader brNode = new BufferedReader(new FileReader(nodePath));
		BufferedReader brEdge = new BufferedReader(new FileReader(edgePath));
		BufferedReader brActivity = new BufferedReader(new FileReader(activityPath));
		
		String line = null;
		
		brNode.readLine();
		while ( (line = brNode.readLine()) != null ) {
			String [] rowContent = line.split(";");
			nodeLonList.add(Double.parseDouble(rowContent[1]));
			nodeLatList.add(Double.parseDouble(rowContent[2]));
		}
		
		brEdge.readLine();
		while ( (line = brEdge.readLine()) != null ) {
			String [] rowContent = line.split(";");
			edgeStartNodeIDList.add(Integer.parseInt(rowContent[1]));
			edgeEndNodeIDList.add(Integer.parseInt(rowContent[2]));
			edgeWeightList.add(Double.parseDouble(rowContent[3]));
		}
		
		brActivity.readLine();
		while ( (line = brActivity.readLine()) != null ) {
			String [] rowContent = line.split(";");
			activityLonList.add(Double.parseDouble(rowContent[1]));
			activityLatList.add(Double.parseDouble(rowContent[2]));  // in the original file, lon, lat
		}		
		
		brNode.close();
		brEdge.close();
		brActivity.close();
		
		int nNodes = nodeLonList.size();
		int nEdges = edgeStartNodeIDList.size();
		int nActivities = activityLonList.size();
		
		System.out.println("nNodes = "+nNodes+", nEdges = "+ nEdges+", nActivities = "+nActivities);

		for (int i = 0; i < nActivities; i++) {
			double minDistanceSquare = 9999999.0;
			double newX = 0, newY = 0;
			double xOriginal = activityLonList.get(i);
			double yOriginal = activityLatList.get(i);
			int minDistanceEdgeIndex = 0;
			for (int j = 0; j < nEdges; j++) {
				double x1 = nodeLonList.get(edgeStartNodeIDList.get(j)-1);
				double y1 = nodeLatList.get(edgeStartNodeIDList.get(j)-1);
				double x2 = nodeLonList.get(edgeEndNodeIDList.get(j)-1);
				double y2 = nodeLatList.get(edgeEndNodeIDList.get(j)-1);

				double xProjected = (xOriginal*(x2-x1)*(x2-x1)+yOriginal*(y2-y1)*(x2-x1)
						+ (x1*y2-x2*y1)*(y2-y1)) / ((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
				double yProjected = (xOriginal*(x2-x1)*(y2-y1)+yOriginal*(y2-y1)*(y2-y1)
						+ (x2*y1-x1*y2)*(x2-x1)) / ((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
				
				boolean x1IsSmaller = true; // true if x1 <= x2
				double xMin = x1;
				double xMax = x2;
				if (x1 > x2) {
					xMin = x2;
					xMax = x1;
					x1IsSmaller = false;
				}
				
				if (xProjected < xMin) {
					if (x1IsSmaller == true) {
						xProjected = x1;
						yProjected = y1;
					}
					else {
						xProjected = x2;
						yProjected = y2;
					}
				}
				else if (xProjected > xMax) {
					if (x1IsSmaller == true) {
						xProjected = x2;
						yProjected = y2;
					}
					else {
						xProjected = x1;
						yProjected = y1;
					}
				}
				double distanceSquare = (xOriginal-xProjected)*(xOriginal-xProjected) + (yOriginal-yProjected)*(yOriginal-yProjected);
				if (distanceSquare < minDistanceSquare) {
					minDistanceSquare = distanceSquare;
					minDistanceEdgeIndex = j;
					newX = xProjected;
					newY = yProjected;
				}
			}
			activityBelongEdgeIDList.add(minDistanceEdgeIndex+1);
			activityLonNewList.add(newX);
			activityLatNewList.add(newY);

		}
		BufferedWriter bwActivity = new BufferedWriter(new FileWriter(activityPathWrite));
		for (int i = 0; i < nActivities; i++) {
			bwActivity.write((i+1)+";"+String.format("%.7f", activityLonNewList.get(i))+";"+String.format("%.7f", activityLatNewList.get(i))+";"+activityBelongEdgeIDList.get(i)+"\n");
		}
		bwActivity.close();
	}
	
	
	public static void main(String[] args) throws IOException {
		mapMatching();
	}
}
