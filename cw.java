// ALL MEASUREMENTS ARE IN METRES
import java.util.Random;
import java.lang.Math;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class cw{
	public static void main(String[] args){
		Scanner input = new Scanner(System.in);//initializing scanner object as input
		boolean exitSys=false; //for terminating the system

		while(true){
			boolean exit=false;//declare exit flag as false

			//declare all restricted spots
			staticDistancing outPatientVisitorWaitingArea = new staticDistancing("1","Out-patient Visitor Waiting Area",true,10,15);
			staticDistancing inPatientVisitorWaitingArea = new staticDistancing("2","In-patient Visitor Waiting Area",true,8,12);
			staticDistancing icuPatientVisitorWaitingArea = new staticDistancing("3","ICU-patient Visitor Waiting Area",true,5,8);
			staticDistancing pharmacy = new staticDistancing("4","Pharmacy",true,10,15);
			staticDistancing erVisitorWaitingArea = new staticDistancing("5","Emergency Room Visitor Waiting Area",true,8,12);

			//initializing the restricted spots
			staticDistancing[] restrictedSpots = new staticDistancing[5];
			restrictedSpots[0]=outPatientVisitorWaitingArea;
			restrictedSpots[1]=inPatientVisitorWaitingArea;
			restrictedSpots[2]=icuPatientVisitorWaitingArea;
			restrictedSpots[3]=pharmacy;
			restrictedSpots[4]=erVisitorWaitingArea;

			//to terminate the program, the staff must enter EXIT_SYSTEM in capitals as the visitorID
			System.out.println("<System> Staff, to terminate the program, enter 'EXIT_SYSTEM' as the visitor ID.");
			//getting patients information from staff when system starts up
			System.out.println("\n<System> Loading patients information from database");
			System.out.println("\n<System> Enter number of patients: ");
			System.out.printf("<Staff> ");
			int count=input.nextInt();
			input.nextLine();
			//declaring array for patient wards
			patients[] patients = new patients[count];

			while (true){
				//staff interaction with system
				//get patients records from database
				for(int i=0;i<count;i++){
					//get data
					System.out.println("\n<System> Enter patient's ward spotID:");
					System.out.printf("<Staff> ");
					String spotID=input.nextLine();
					System.out.println("<System> Enter patient name:");
					System.out.printf("<Staff> ");
					String patientName=input.nextLine();
					patients patient = new patients(spotID,patientName); //creating patient instance
					patients[i]=patient;//assiging an instance of patient to an array 
				}
				System.out.println("\n<System> Loaded patient details.\n<System> System is ready to be used by visitors.");

				clearScreen();//clear the cmd screen so that the patients data that was entered is not visible to the visitors
				pause(1000);//pause the program for 1 second

				// visitor interaction with system begins here
				//display welcome message
				System.out.println("\n\n<System> Welcome to Notts Hospital");
				pause(2000);//pause the program for 2 seconds

				//getting visitors data
				System.out.println("\n<System> Enter visitor ID: ");
				System.out.printf("<Visitor> ");
				String visitorID=input.nextLine();

				//checking whether to terminate system
				if (visitorID.equals("EXIT_SYSTEM")){
					System.out.println("\nTerminating the system.");
					exitSys=true;
					break;
				}

				System.out.println("<System> Enter visitor full name: ");
				System.out.printf("<Visitor> ");
				String visitorName=input.nextLine();
				String visitorGender;


				while(true){
		    		System.out.println("<System> Enter your gender (F - female and M - male) ");
		    		System.out.printf("<Visitor> ");
		    		visitorGender=input.nextLine().toUpperCase();
		    		if (visitorGender.equals("F")){
		    			break;
		    		}
		    		if (visitorGender.equals("M")){
		    			break;
		    		}
		    		else{
		    			System.out.println("<System> Invalid gender, please enter a valid gender.");
		    			continue;
				    }
			    }

			    pause(2000);//pause the program for 2 seconds
			    clearScreen();//clearing the screen again to prevent current visitors information to be visible later visitors

				while(true){//get correct choice

					//input prompt
					System.out.println("\n<System> Do you want to \n         (1) visit a patient \n         (2) wait \n         (3) visit the pharmacy \n         (4) exit the hospital?");
					System.out.println("<System> Please enter 1, 2 or 3:");
					System.out.printf("<Visitor> ");
					//getting choice input
					int choice=input.nextInt();
					input.nextLine();//clearing the newline character

					//choice is to visit a patient so get patient name from visitor and check if present in the array
					//initialising to 2, to check if patient name is registered at the hospital, will toggle to 0 or 1 if available or not
					int patientAvailability=2;

					//choice is to visit a patient
					if (choice==1){
						System.out.println("\n<System> Enter name of patient you would like to visit:");
						System.out.printf("<Visitor> ");
						String name=input.nextLine();

						//checking if patients name is in array
						for (int i=0;i<count;i++){
							//present in patients array and is available
							if (((patients[i].getPatientName())).equals(name) && patients[i].visitors<2){
								//calling patient class display method to display patient details
								patients[i].display();
								System.out.println("\n<System> You may now visit patient "+patients[i].getPatientName()+" in spot "+patients[i].getSpotID());
								patientAvailability=0;
								// dynamicdistancing function call to get visitors details and check the distance
								checkDynamicDistancing(visitorID,visitorName,visitorGender,patients[i].getSpotID(),patients[i].getPatientName()+"'s ward");
							}
							//present in patients array but not available beacause maximum capacity is reached
							if (patients[i].getPatientName().equals(name) && patients[i].visitors==2){
								System.out.println("\n<System> The patient you wish to visit has maximum visitors in their room, please visit later.");
								patientAvailability=1;			
							}
						}

						//visitor can visit patients room if available else check if visitor choice is to exit, wait or visit patient	
						if (patientAvailability==1){
							continue;
						}
						if (patientAvailability==2){
							System.out.println("<System> Patient with name "+name+" is not registered in this hospital.");
							continue;//continue to the next iteration
						}

					}//choice is to wait
					else if (choice==2){
						//checking for valid choices
						while(true){
							//get place to wait, check if present in restrictedSpots array and check if available
							System.out.println("\n<System> Enter \n         (1) Out-patient Visitor Waiting Area \n         (2) In-patient Visitor Waiting Area \n         (3) ICU-patient Visitor Waiting Area \n         (4) Emergency Room Visitor Waiting Area");
							System.out.println("<System> Please enter 1, 2, 3 or 4:");
							System.out.printf("<Visitor> ");

							//checking input and matching the integer waitchoice variable
							int waitChoice=-1;
							switch (input.nextInt()){
								case 1:
									input.nextLine();
									waitChoice=0;
									break;
								case 2:
									input.nextLine();
									waitChoice=1;
									break;
								case 3:
									input.nextLine();
									waitChoice=2;
									break;
								case 4:
									//pharmacy is skipped here since its not under waiting area
									input.nextLine();
									waitChoice=4;
									break;
								default:
									//waitchoice remains -1
									input.nextLine();
									System.out.println("\n<System> Invalid waiting area, please enter again");
									break;
							}

							//if waitchoice is still -1 (default case in the switch statement)
							if (waitChoice!=-1){

								//reached max capacity
								if (restrictedSpots[waitChoice].currentCapacity==restrictedSpots[waitChoice].spotMaxCapacity){
									System.out.println("\n<System> "+restrictedSpots[waitChoice].getSpotName()+" is full");

								}else{
									//atleast 1 visitor is permitted, max capacity is not reached
									System.out.println("\n<System> You may enter "+restrictedSpots[waitChoice].getSpotName());
									// dynamic distancing function call to get visitors details and check the distance
									checkDynamicDistancing(visitorID,visitorName,visitorGender,restrictedSpots[waitChoice].getSpotID(),restrictedSpots[waitChoice].getSpotName());
								}

								//break out of the loop if a valid choice is entered else get choice again
								break;
							}//checking if choice is valid

							else{
								System.out.println("<System> Invalid choice, please enter a valid choice");
							}

						}// getting valid choice
					}

					//check availability of pharmacy
					else if(choice==3){
						if (restrictedSpots[3].currentCapacity==restrictedSpots[3].spotMaxCapacity){
							//reached max capacity
							System.out.println("\n"+restrictedSpots[3].getSpotName()+" is full");

						}else{
							//atleast 1 visitor is permitted
							System.out.println("\nYou may enter "+restrictedSpots[3].getSpotName());
							// dynamic distancing function call to get visitors details and check the distance
							checkDynamicDistancing(visitorID,visitorName,visitorGender,restrictedSpots[3].getSpotID(),restrictedSpots[3].getSpotName());
						}
					}
					//checking if visitor wants to exit hospital
					else if (choice==4){

						//set flag exit variable to true
						exit=true;
						System.out.println("\nExiting the hospital");
						//breaking out of choice while loop
						break;

					}
					//if not any of the above choices, invalid choice entered
					else{
						System.out.println("\nInvalid choice entered. Please enter 1, 2 or 3.");
						//go back to loop and get input again
						continue;
					}
				}
				//check if exit flag is set to true
				if (exit==true){
					//if exit flag is set to true, then exit the major while loop
					continue;
				}
			}//while choice is valid
			if (exitSys==true){
				break;
			}
		}//while exit flag is false
	}//main args

	//check distance function to be called when visitor has been granted permission to a waiting area or patients ward
	public static void checkDynamicDistancing(String visID,String visName,String gender,String choiceSpotID,String choiceSpotName){

		//initalising an input scanner instance
		Scanner in = new Scanner(System.in);

		//declaring variables to display data
		String visitorID=visID;
		String visitorName=visName;

		//creating an instance of visitor and passing in the retrieved data as parameters
		visitor visitor=new visitor(visitorID,visitorName,choiceSpotID,choiceSpotName);

		while(true){
			//get distances
			System.out.println("\n<System> Enter left distance:");
			System.out.printf("<Visitor> ");
			double left=in.nextDouble();
			in.nextLine();

			System.out.println("\n<System> Enter right distance:");
			System.out.printf("<Visitor> ");
			double right=in.nextDouble();
			in.nextLine();

			System.out.println("\n<System> Enter front distance:");
			System.out.printf("<Visitor> ");
			double front=in.nextDouble();
			in.nextLine();

			System.out.println("\n<System> Enter back distance:");
			System.out.printf("<Visitor> ");
			double back=in.nextDouble();
			in.nextLine();

			// creating an instance of dynamic distance and passing the distances
			//to check if safe, casual or close contact
			dynamicDistancing distance=new dynamicDistancing(left,right,front,back);

			// getting status from visitor class with return value of check distance method as parameter
			String status=visitor.setContactStatus(distance.checkDistances(gender));

			//output visitor status
			System.out.println("\n<System> Contact Status: "+status);
			pause(2000);

			//checking if distance is safe
			if(status.equals("Distancing(Safe) Contact")){
				break;
			}
		}

		pause(1000);//pause the program for 1 second
		System.out.println("\n<System> Visitor Details");
		System.out.println("         Visitor ID: "+visID);
		System.out.println("         Visitor Name: "+visName);
		System.out.println("         Gender: "+gender);
		System.out.println("         Selected Restricted Spot ID: "+choiceSpotID);
		System.out.println("         Selected Restricted Spot Name: "+choiceSpotName);

		//getting current date and time
		System.out.println("         Date: "+java.time.LocalDate.now());
		//splicing the time to get only the hours and minutes
		Date d=new Date();
		System.out.println("         Time: "+d.getHours()+":"+d.getMinutes());
		//getting amount of time the visitor will stay in the selected spot
		System.out.println("<System> Enter an approximate duration of time you will spend in the current spot (in minutes): ");
		System.out.printf("<Visitor> ");
		int time=in.nextInt();
		in.nextLine();

		//converting time from minutes to milliseconds
		System.out.println("<System> Visitor is waiting");
		pause(time*60*1000);
	}


	//function for clearing cmd screen for security purposes
	public static void clearScreen(){
		try{
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	//pausing the program when waiting
	public static void pause(int time){
		try{
			//time here is in milliseconds
			Thread.sleep(time);
		}catch(Exception e){
			System.out.println("Error in program sleep");
		}
	}

}//main class