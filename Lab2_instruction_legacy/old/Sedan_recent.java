import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
import java.util.*;
//import java

public class Sedan extends Vehicle {
	private boolean hatchback;
	private int engine;

	//overload constructor method - mine
	public Sedan(String makeInput, FileReader configInput, String pidNumber){

		//set inherited data with super
		super(makeInput, configInput, pidNumber);
		// System.out.println("Creating Sedan constructor");
		//
		// //use getHatchback to call setHatchback
		// setHatchback(getHatchback());
		//
		// System.out.println("Hatchback: " + hatchback);
		// System.out.println("now setting engine");
		//
		// //use getEngine to call setEngine
		// setEngine(getEngine());
		// System.out.println("Engine: " + engine);
		//
		// System.out.println("setting parts");
		// setParts(this.getOptions());

		// System.out.println("print parts");
		//
		//  String[] test;
		//  test = this.getOptions();
		//  for (int i = 0; i <= 7; i++){
		//  	System.out.println(test[i]);
		//  }

	}

	//overload constructor
	public Sedan(String makeInput, String modelInput, FileReader configInput, boolean hatchbackInput, int engineInput){
		//set inherited data with super
		super(makeInput, modelInput, configInput);

		//set data for Sedan
		hatchbackInput = hatchback;
		engineInput = engine;

	}

	//setter for HatchBack property
	public void setHatchback(boolean h){

		//use get parts
		hatchback = h;
	}

	//sets the hatckback option using PID# and config file
	public boolean getHatchback(){

		System.out.println("getting hatchaback");
		//get customOption by calling readModelName (since model name is already set)
		String customOption = readModelName();
		Boolean continueRead = true;  //used to search configFile
		int counter = 0;  //count through configFile
		String line; //store lines in configFileList
		String hatchBackString = null; //
		Boolean hatchBackBool = false;

		//search customOption in config file for:  search from sadan and 5 indexes of list
		//find index of "sedan" first
		while(continueRead){

			//read line from config file starting at counter = 0
			line = this.configFileList.get(counter);

			//pick a spot before custom options are listed
			if (line.equals("Sadan")) {
				continueRead = false;
			}

			//increment counter
			counter++;
		} //end while

		System.out.println(customOption);

		continueRead = true;
		while(continueRead){
			//initialize new array because lines could have different sizes
			String[] lineArray;

			//store line at position in configFile
			line = this.configFileList.get(counter);

			//break stored line string into array
			lineArray = line.split("\\s+");

			//see if first element matches customOption
			if (lineArray[0].equals(customOption)) {
				System.out.println("found custom option");
				//store 3rd element for option result
				hatchBackString = lineArray[2];

				//stop loop
				continueRead = false;
			}
			//if counter + 5 reaches then no option
			counter++;
		} //end while
		// //use counter count to start for loop to search for customOption string
		// //search only 5 lines
		// for(int i = counter; i <= (counter+5); i++){
		//
		// 	//initialize new array because lines could have different sizes
		// 	String[] lineArray;
		//
		// 	//store line at position in configFile
		// 	line = this.configFileList.get(i);
		//
		// 	//break stored line string into array
		// 	lineArray = line.split("\\s+");
		//
		// 	System.out.println("linearray at 0 : " + lineArray[0]);
		//
		// 	//see if first element matches customOption
		// 	if (lineArray[0].equals(customOption)) {
		// 		System.out.println("found custom option");
		// 		//store 3rd element for option result
		// 		hatchBackString = lineArray[2];
		// 	}
		// } //end for

		//determine what boolean to return
		if (hatchBackString.equals("false")) {

			//set boolean to false
			hatchBackBool = false;
		}
		else if (hatchBackString.equals("true")) {

			//set boolean to true
			hatchBackBool = true;
		}
		else{
			System.out.println("Invalid hatchback");
		}
		return hatchBackBool;
	}


	//setter for engine
	public void setEngine(int e){
		engine = e;
	}

	//getter for engine
	public int getEngine(){
		//get customOption by calling readModelName (since model name is already set)
		System.out.println("getting the engine");
		String customOption = readModelName();
		Boolean continueRead = true;  //used to search configFile
		int counter = 0;  //count through configFile
		String line; //store lines in configFileList
		String engineString = null; //
		int engineInt;

		//search customOption in config file for:  search from sadan and 5 indexes of list
		//find index of "sedan" first
		while(continueRead){

			//read line from config file starting at counter = 0
			line = this.configFileList.get(counter);

			//check if string contains the model number in pidNumber (1st element of pidFormatted); if so stop reading
			if (line.equals("Sadan")) {
				continueRead = false;
			}

			//increment counter
			counter++;
		} //end while

		//use counter count to start for loop to search for customOption string
		//search only 5 lines
		for(int i = counter; i <= (counter+5); i++){
			//initialize new array because lines could have different sizes
			String[] lineArray;

			//store line at position in configFile
			line = this.configFileList.get(i);

			//break stored line string into array
			lineArray = line.split("\\s+");

			//see if first element matches customOption
			if (lineArray[0].equals(customOption)) {

				//store 5th element for option result
				engineString = lineArray[5];
			}
		} //end for

		//parse string into a double
		engineInt = Integer.parseInt(engineString);

		return engineInt;
	}

	//override abstract method from vehicle.java
	@Override
	public void setParts(String[] p){
		//set each p string to parts string array
		// for(int i = 0; i < p.length; i++){
		// 	parts[i] = p[i];
		// }
		//string array to append with userOptions string array
		//	String[] optionNames = {"Exterior Color", "Interior Color", "Powertrain", "Seat", "Radio",
		//"Tire", "Rim", "Miscellaneous"};
		Boolean continueRead;
		String line = null;
		String lineArray[] = null;

		int counter;
		String[] partsArray = new String[10];
		String[] copyArray;

		//set 1st element to the model name and price
		//store the modelName in a string
		String modelName = this.getModel();

		continueRead = true;
		counter = 0;

		//search until match in config list
		while (continueRead){

			line = this.configFileList.get(counter);

			//test if line contains modelName
			if(line.contains(modelName)) {
				//match found; stop whlie loop
				continueRead = false;
			}

			//increment counter
			counter++;
		}  //end while

		//break line variable into an array
		lineArray = line.split("\\s+");

		//use StringBuilder to for parts Array 1st element
		StringBuilder partsString1 = new StringBuilder();

		//copy array to another but w/o the first element
		for (int i = 1; i < lineArray.length; i++){
			//copyArray[i-1] = lineArray[i];
			partsString1.append(" " + lineArray[i]);
		}

		//store partsArray1 in partsArray element 1
		partsArray[0] = partsString1.toString();
		//this.parts[0] = partsString1.toString();


		//**repeat similar process but now w/ options array (p)
		for (int i = 0; i < p.length; i++){

			//set line = to first element in options array
			line = p[i];

			//initialize new string array to store split line
			//*********need to use array list instead*********
			//reference:  https://stackoverflow.com/questions/7488643/how-to-convert-comma-separated-string-to-arraylist
			List<String> optionList = Arrays.asList(line.split("\\s+"));
			String[] lineArray2;
			lineArray2 = line.split("\\s+");
		//	optionList = line.split("\\s+");

			//take first element of lineArray and match to configFileList
			continueRead = true;
			counter = 0;
			while (continueRead){
				//System.out.println(lineArray2[0]);
				//test if line contains optionName - Exterior, Interior; etc.
				//if((this.configFileList.get(counter)).contains(lineArray2[0]))
				if((this.configFileList.get(counter)).contains(optionList.get(0))) {
					//match found; stop whlie loop
					continueRead = false;
				}
				//increment counter
				counter++;
			}  //end while


			/**
			* now find last element of lineArray2 - options array split in configfile list
			*use counter position to start search from (based on optionName found)
			*/
			//if 'none' was set then don't continue matching for line
			if ((lineArray2[(lineArray2.length)-1]).equals("none")) {
				//partsArray[i+1] = p[i];
				this.parts[i+1] = p[i];
			}

			//then find the match
			else{
				continueRead = true;
				while (continueRead){

					//loop from counter until you find option set
					if ((this.configFileList.get(counter)).contains(lineArray2[(lineArray2.length)-1])) {
						//match found
						System.out.println(lineArray2[(lineArray2.length)-1]);
						continueRead = false;
					}

					counter++;
				}  //end while

				//store the line just found
				line = this.configFileList.get(counter-1);

				//split this line and don't take 1st element
				lineArray2 = line.split("\\s+");
				optionList.clear();
				optionList = Arrays.asList(line.split("\\s+"));

				//use StringBuilder to for parts Array 1st element
				StringBuilder partsString2 = new StringBuilder();

				System.out.println("linearray 2 length is " + lineArray2.length);

				//copy array to string builder but w/o the first element
				for (int y = 1; y < lineArray2.length; y++){

					//copyArray[i-1] = lineArray[i];
					partsString2.append(" " + lineArray2[y]);
				}

				//store string builder in partsArray starting at 2nd element
				partsArray[i+1] = partsString2.toString();
				//this.parts[i+1] = partsString2.toString();
			} // end else
			System.out.println(partsArray[i+1]);

			//clear the arraylist to rebuild it for next option category
			optionList.clear();
		} //end initial for loop for options array to parts array

		//repeat similar process but now w/ customOption


		//p = array of options
		// String[] test = new String[8];
		// test = this.getOptions();
		// for (int i = 0; i <= 7; i++){
		// 	System.out.println(test[i]);
		// 	test[i] = this.parts[i];
		// }
	}  //end setParts

	/*
	* Input: String - pidNumber
	* Return: String
	* Description: this method reads the object's model name and passes back its custom option
	*/
	public String readModelName(){

		String modelName = this.getModel();  //variable to store object's model
		String customOptions;  //string to hold options for hatchback and engine types
		int position;  //hold substring location

		System.out.println(modelName);

		//search the name for substring "Sadan" and add 5 for other options
		position = modelName.indexOf("Sadan") + 5;

		//store substring starting from positin to end of string
		customOptions = modelName.substring(position);

		return customOptions;
	}

}
