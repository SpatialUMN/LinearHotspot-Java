import java.util.ArrayList;


// edge information
public class EdgeXun {
	
	String edgeID;
	String startNodeID;
	String endNodeID;
	double weight;
	int numActivity; // number of activities on this edge
	ArrayList<ActivityXun> activity; // activity IDs of the activities on this edge 
	boolean ifActiveEdge;
	
	public EdgeXun (){
		this.weight = 0;
		this.numActivity = 0;
		this.activity = new ArrayList<ActivityXun>();
		this.ifActiveEdge = false;
	}
	
	public EdgeXun (String edgeID, String startNodeID, String endNodeID, double weight, boolean ifActiveEdge) {
		this.edgeID = edgeID;
		this.startNodeID = startNodeID;
		this.endNodeID = endNodeID;
		this.weight = weight;
		this.numActivity = 0;
		this.activity = new ArrayList<ActivityXun>();
		this.ifActiveEdge = ifActiveEdge;
	}
	
	public static void main(String[] args) {
		String aString = "abc";
		String bString = "abc";
		
		aString = "xyz";
		System.out.println(aString);
		System.out.println(bString);
	}
	
}
