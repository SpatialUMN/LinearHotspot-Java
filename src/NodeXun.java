//node information
public class NodeXun {

	String nodeID;
	double longitude;
	double latitude;
	boolean ifActiveNode;
	double maxActivitiesNeighborIn; // store the max number of activities on an adjacent edge of this node
	double maxActivitiesNeighborOut; // out from this node, similar definition as the "in" one
	
	public NodeXun() {		
	}
	
	public NodeXun(String nodeID, double longitude, double latitude, boolean ifActiveNode) {
		this.nodeID = nodeID;
		this.longitude = longitude;
		this.latitude = latitude;
		this.ifActiveNode = ifActiveNode;
		this.maxActivitiesNeighborIn = 0;
		this.maxActivitiesNeighborOut = 0;
	}
	
	public static void main(String[] args) {
		
	}
}
