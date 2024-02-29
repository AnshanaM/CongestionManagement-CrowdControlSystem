public class visitor{
	private String visitorID;
	private String visitorFullName;
	private String selectedRestrictedSpot;
	private String spotName;
	String contactStatus;

	public visitor(String id,String name,String selID,String spotName){
		this.visitorID=id;
		this.visitorFullName=name;
		this.selectedRestrictedSpot=selID;
		this.spotName=spotName;
	}

	public String getVisitorID(){
		return this.visitorID;
	}
	public String getVisitorFullName(){
		return this.visitorFullName;
	}
	public String getSpotName(){
		return this.spotName;
	}
	public String getSelectedRestrictedSpot(){
		return this.selectedRestrictedSpot;
	}
	public String setContactStatus(int isSafe){
		if (isSafe==0){
			this.contactStatus="Distancing(Safe) Contact";
		}
		else if (isSafe==1){
			this.contactStatus="Casual Contact";
		}
		else if (isSafe==2){
			this.contactStatus="Close Contact";
		}
		return this.contactStatus;
	}
}