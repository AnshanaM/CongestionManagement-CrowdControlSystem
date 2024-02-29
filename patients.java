// ALL MEASUREMENTS ARE IN METRES
import java.util.Random;
public class patients{
	private String spotID;
	private String patientName;
	int visitors;

	public patients(String id,String name){
		this.spotID=id;
		this.patientName=name;
		Random rand=new Random();
		this.visitors=rand.nextInt(3);
	}

	public String getSpotID(){
		return this.spotID;
	}

	public String getPatientName(){
		return this.patientName;
	}

	public void display(){
		System.out.println("\n<System> Spot ID: "+this.spotID+"\n         Patient Name: "+this.patientName+"\n         Current Visitors: "+this.visitors);
	}
}