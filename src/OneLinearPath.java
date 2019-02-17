public class OneLinearPath {
	String StartNodeID;
	String EndNodeID;
	String StartActivityID;
	String EndActivityID;
	double NumberOfActivities;
	double TotalLength;
	double DensityRatio;
	
	
	public OneLinearPath() {		
	}
	
	public OneLinearPath(String StartActivityID, String EndActivityID,String StartNodeID,String EndNodeID,double DensityRatio,double TotalLength,double NumberOfActivities) {
		this.StartNodeID = StartNodeID;
		this.EndNodeID = EndNodeID;
		this.StartActivityID = StartActivityID;
		this.EndActivityID = EndActivityID;
		this.NumberOfActivities = NumberOfActivities;
		this.TotalLength = TotalLength;
		this.DensityRatio = DensityRatio;
		
	}
	
	public static void main(String[] args) {
		
	}
}
