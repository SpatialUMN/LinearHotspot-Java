
// activities on the study network
public class ActivityXun {

	String activityID;
	String edgeID;
	double longitude;
	double latitude;
	double weightToNode1;
	double weightToNode2;
	
	public ActivityXun() {
	}
	
	public ActivityXun(String activityID, String edgeID, double longitude, double latitude) {
		this.activityID = activityID;
		this.edgeID = edgeID;
		this.longitude = longitude;
		this.latitude = latitude;
		
		this.computeWeight();
	}
	
	// computes the activity's weights two its 2 adjacent nodes
	void computeWeight(){ 
		// to start node
		double lonStart = NetworkDataset.nodeHashMap.get(NetworkDataset.edgeHashMap.get(this.edgeID).startNodeID).longitude;
		double latStart = NetworkDataset.nodeHashMap.get(NetworkDataset.edgeHashMap.get(this.edgeID).startNodeID).latitude;
		double lonEnd = NetworkDataset.nodeHashMap.get(NetworkDataset.edgeHashMap.get(this.edgeID).endNodeID).longitude;
		double latEnd = NetworkDataset.nodeHashMap.get(NetworkDataset.edgeHashMap.get(this.edgeID).endNodeID).latitude;
		//double edgeLength = Math.sqrt( (lonStart-lonEnd)*(lonStart-lonEnd) + (latStart-latEnd)*(latStart-latEnd) );
		double edgeWeight = NetworkDataset.edgeHashMap.get(this.edgeID).weight;
		
		double weightToNode1Temp = Math.sqrt((lonStart-this.longitude)*(lonStart-this.longitude) + (latStart-this.latitude)*(latStart-this.latitude));
		double weightToNode2Temp = Math.sqrt((lonEnd-this.longitude)*(lonEnd-this.longitude) + (latEnd-this.latitude)*(latEnd-this.latitude));	
		
		this.weightToNode1 = edgeWeight * weightToNode1Temp / (weightToNode1Temp+weightToNode2Temp);
		this.weightToNode2 = edgeWeight * weightToNode2Temp / (weightToNode1Temp+weightToNode2Temp);
	}
	
	public static void main(String[] args) {
	
	}
}
