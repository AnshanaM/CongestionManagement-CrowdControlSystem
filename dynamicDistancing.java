// ALL MEASUREMENTS ARE IN METRES
import java.lang.Math;
public class dynamicDistancing{

	//declaring attributes
	double left,right,front,back;

	//init function
	public dynamicDistancing(double l,double r,double f,double b){
		this.left=l;
		this.right=r;
		this.front=f;
		this.back=b;
	}

	public int checkDistances(String gender){
		int returnVal=0;
		if (left>=1 && right>=1 && front>=1 && back>=1){
			// safe distancing
			System.out.println("\nYou are safe in dynamic distancing!");
			returnVal=0;
		}
		double steps;
		//casual contact
		if (left<1 && left>=0.5){
			if (gender.equals("M")){
				steps=(1-left)/0.76;
			}
			else{
				steps=(1-left)/0.67;
			}
			System.out.println("\nYou are in casual contact, please move "+Math.ceil(steps)+" steps right");
			returnVal=1;
		}
		if (right<1 && right>=0.5){
			if (gender.equals("M")){
				steps=(1-right)/0.76;
			}
			else{
				steps=(1-right)/0.67;
			}
			System.out.println("\nYou are in casual contact, please move "+Math.ceil(steps)+" steps left");
			returnVal=1;
		}
		if (front<1 && front>=0.5){
			if (gender.equals("M")){
				steps=(1-front)/0.76;
			}
			else{
				steps=(1-front)/0.67;
			}
			System.out.println("\nYou are in casual contact, please move "+Math.ceil(steps)+" steps back");
			returnVal=1;
		}
		if (back<1 && back>=0.5){
			if (gender.equals("M")){
				steps=(1-back)/0.76;
			}
			else{
				steps=(1-back)/0.67;
			}
			System.out.println("\nYou are in casual contact, please move "+Math.ceil(steps)+" steps front");
			returnVal=1;
		}
		//close contact
		if (left<0.5){
			if (gender.equals("M")){
				steps=(1-left)/0.76;
			}
			else{
				steps=(1-left)/0.67;
			}
			System.out.println("\nYou are in close contact, please move "+Math.ceil(steps)+" steps right");
			returnVal=2;
		}
		if (right<0.5){
			if (gender.equals("M")){
				steps=(1-right)/0.76;
			}
			else{
				steps=(1-right)/0.67;
			}
			System.out.println("\nYou are in close contact, please move "+Math.ceil(steps)+" steps left");
			returnVal=2;
		}
		if (front<0.5){
			if (gender.equals("M")){
				steps=(1-front)/0.76;
			}
			else{
				steps=(1-front)/0.67;
			}
			System.out.println("\nYou are in close contact, please move "+Math.ceil(steps)+" steps back");
			returnVal=2;
		}
		if (back<0.5){
			if (gender.equals("M")){
				steps=(1-back)/0.76;
			}
			else{
				steps=(1-back)/0.67;
			}
			System.out.println("\nYou are in close contact, please move "+Math.ceil(steps)+" steps front");
			returnVal=2;
		}
		return returnVal;
	}

}