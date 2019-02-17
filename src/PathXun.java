public class PathXun {
	
	//ArrayList<EdgeXun> edgeSequence;
	int length; // number of edge on this edge
	double pathWeight; // number of total weight on this edge
	double nActivity; // number of activities on this edge
	
	public PathXun() {
		this.length = 0;
		this.pathWeight = 0;
		this.nActivity = 0;
		//this.edgeSequence = new ArrayList<EdgeXun>();
	}
	
	public PathXun(int length, double pathWeight, double nActivity) {
		this.length = length;
		this.pathWeight = pathWeight;
		this.nActivity = nActivity;
	}
	
	public static void main(String[] args) {
	
	}
}
