/*
* Name:  Jillian Irvin
* Class: CIT-244
* Date:  9.18.17
* Description: AnyCarManufactor is the main file of your application. Main will read in the configuration file and initiate the application.
*/

import java.util.Scanner;
import java.io.*;

class AnyCarManufactor {
	/*
	* Input: String Array args – Command line arguments
	* Return: Void
	* Description: Main Method for initiating AnyCar application. When called will read line by line AnyCar.config file and append to global variable anyCarConfig. Finally, Main will call request to initiate the interaction with the user.
	*/
	public static void main(String[] args){
		request();
	}


	/*
	* Input: None
	* Return: Void
	* Description: Requests user to input PID(String) and calls buildVehicle to start building each vehicle. Request will continue to prompt the user until the user inputs -1, then request will return.
	*/
	public static void request(){
		boolean continueUserInput = true; //variable to hold if user wants to continue entering PID#s
		boolean invalidPID;  //to check validity of user input
		String userPIDInput;  //variable to store PID#
		Scanner keyboard = new Scanner(System.in);  //create Scanner object for user input

		//prompt user to enter 12-digit PID number until they end with -1
		while (continueUserInput){

			//initialize userPIDInput
			userPIDInput = "";
			//initialize invalidPID to true for each new start
			invalidPID = true;

			//continue to prompt user for PID# until quits or valid one
			while (invalidPID){

				//prompt user
				System.out.print("Enter a PID number (-1 to exit): ");
				userPIDInput = keyboard.nextLine();

				if (userPIDInput.equals("-1")){
					//exit while loops and program
					System.out.println("Exiting program");
					System.exit(0);
				}
				else{ //check PID validity
					//if returns true then continue to prompt user
					//if returns false then exit invalidPID loop and continue with userContinue loop
					invalidPID = validInput(userPIDInput);
				}
			}  //end invalidPID while loop

			//call method to build vehicle
			buildVehicle(userPIDInput);

		}  //end continueUserInput whlie loop
	}  //end request method


	/*
	* Input: String pid - Product ID number
	* Return: Void
	* Description: Create Vehicle object and call getters and setters for make, model, options, parts, and price, and print the Vehicle object using it’s toString method, and return
	* The first digit is the make and exhibits the following behavior
	*               1: ThisAuto
	*               2: ThatAuto
	*               3: OtherAuto
	* The second is vehicle type and exhibits the following behavior:
	*               1: Sadan
	*               2: Coupe
	*               3: Minivan
	*               4: SUV
	*               5: Truck
	*/
	public static void buildVehicle(String id){
		//String configFileName;
		char vehicleTypeChar;
		String makeInput = "";
		Vehicle userCar = null;

		FileReader configFile = null;
		//int to pass to makeInput for pidFormat interface (vehicle.java)
		//int makeConfig;

		//make character into integer
		//reference from https://stackoverflow.com/questions/4968323/java-parse-int-value-from-a-char
		//makeConfig = Character.getNumericValue(id.charAt(0));
		//set makeConfig int to 1st character in PID#
		//makeConfig = Integer.parseInt(id.charAt(0));
		//System.out.println(makeConfig);

		try{
			//check first digit of PID# for config file
			if (id.charAt(0) == '1'){
				// 1= ThisAuto.Config
				//configFileName = "ThisAuto.config";
				configFile = new FileReader("ThisAuto.config");
				makeInput = "ThisAuto";

			}
			else if(id.charAt(0) == '2'){
				// 2= ThatAuto.Config
				//configFileName = "ThatAuto.config";
				configFile = new FileReader("ThatAuto.config");
				makeInput = "ThatAuto";
			}
			else if(id.charAt(0) == '3'){
				// 3= OtherAuto.Config
				//	configFileName = "OtherAuto.config";
				configFile = new FileReader("OtherAuto.config");
				makeInput = "OtherAuto";
			}
			else{
				System.out.println("Invalid option. Exiting program...");
				System.exit(0);
				//request();  -- will this lead to stack overflow?
			}
		}  //end try
		catch(IOException e){
			System.err.println("error: " + e);
		}

		//get second digit of PID#
		vehicleTypeChar = id.charAt(1);

		//determine the type of vehicle to make (sedan, coupe, minivan, suv, truck)
		switch (vehicleTypeChar){

			//if case matches to vehicle type; instantiate that vehicle type
			case '1':
			//create Sedan with options to be updated with setter methods and config file
			userCar = new Sedan(makeInput, configFile, id);
			break;

			case '2':
			//create Coupe with options to be updated with setter methods and config file
			userCar = new Coupe(makeInput, configFile, id);
			break;

			case '3':
			//create Minivan with options to be updated with setter methods and config file
			userCar = new Minivan(makeInput, configFile, id);
			break;

			case '4':
			//create SUV with options to be updated with setter methods and config file
			userCar = new SUV(makeInput, configFile, id);
			break;

			case '5':
			//create Truck with options to be updated with setter methods and config file
			userCar = new Truck(makeInput, configFile, id);
			break;

			default:
			System.out.println("Invalid vehicle type. Exiting program...");
			System.exit(0);
			break;
		}  //end switch

	} //end buildVehicle

	/*
	* Input: String user entered PID#
	* Return: boolean
	* Description: Checks that user's PID # is valid.
	* The boolean return determines if the user should be prompted again
	*/
	public static boolean validInput(String pid){
		boolean invalidInput = false;  //initiate to false - let error checks update
		Long testUserInput;
		try{
			//parse string input to long
			testUserInput = Long.parseLong(pid);

			//check there are 12 numbers entered
			if (pid.length() < 12){
				//let user know
				System.out.println("You entered an invlaid PID number.");

				//if not, then set boolean to true (for invalid)
				invalidInput = true;
			}
		}
		catch (NumberFormatException e){
			//tell user invalid input
			System.out.println("PID# must only include integers.");

			//update boolean to true for invalid
			invalidInput = true;
		}
		return invalidInput;
	}  //end validInput method
}
