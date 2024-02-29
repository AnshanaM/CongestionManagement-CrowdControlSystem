// ALL MEASUREMENTS ARE IN METRES
import java.lang.Math;
public class spots{

	//declaring attributes
	private String spotID;
	private String spotName;
	double spotArea;//calculated from
	int spotMaxCapacity;
	int currentCapacity;
	boolean isRestricted;


	//constructor overloading where, the length and width of the spot is either entered as a double or int value
	public spots(String id,String name,boolean isRestricted,double length,double width){

		this.spotID=id;
		this.spotName=name;
		this.spotArea=length*width;
		this.isRestricted=isRestricted;
		this.currentCapacity=(int)(Math.random()*(spotMaxCapacity+1));

	}
	public spots(String id,String name,boolean isRestricted,int length,int width){

		this.spotID=id;
		this.spotName=name;
		this.spotArea=length*width;
		this.isRestricted=isRestricted;
		this.currentCapacity=(int)(Math.random()*(spotMaxCapacity+1));

	}
	public void setMax(){
		//initialising max capacity
		this.spotMaxCapacity=1;
	}
	public String getSpotName(){
		return this.spotName;
	}
	public String getSpotID(){
		return this.spotID;
	}
}