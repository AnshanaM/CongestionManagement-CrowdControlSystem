// ALL MEASUREMENTS ARE IN METRES
import java.lang.Math;
import java.util.Random;
public class staticDistancing extends spots{

	//init function
	public staticDistancing(String id,String name,boolean isRestricted,double length,double width){
		super(id,name,isRestricted,length,width);
		setMax();
	}
	@Override
	public void setMax(){
		//calculate the max number of people allowed in the area
		// total area of spot/(pi*(r^2)) where r = 1
		super.spotMaxCapacity=(int)(super.spotArea/Math.PI);
		// Random rand=new Random();
		// super.currentCapacity=rand.nextInt(super.spotMaxCapacity);

	}

}