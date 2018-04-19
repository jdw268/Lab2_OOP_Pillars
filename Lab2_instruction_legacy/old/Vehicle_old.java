/*
* Name:  Jillian Irvin
* Class: CIT-244
* Date:  9.18.17
* Description: This class is an abstract class that implements ModelFormat interface.
* It's used to instantiate common characteristics of a vehicle using an inputted PID#.
* The PID# is formatted using the ModelFormat interface for easier data extraction from the vehicle make configuration file.
*/

import java.io.*;
import java.util.ArrayList;

public abstract class Vehicle implements ModelFormat{
	private String make;
	private String model;
	private String[] options;
	private String[] parts;
	private double price;
	private FileReader config;
	private ArrayList<String> configArray = new ArrayList<String>();


	//overload construtor method - mine
	public Vehicle(String makeInput, FileReader configInput, String pidNumber){

		//call setMake to set private make variable to input
		setMake(makeInput);

		//set the configuration file of the object so can use config file now
		this.config = configInput;
		//call setModel with pidNumber

		setModel(pidNumber);

		System.out.println("Calling set Options");
		setOptions(pidNumber);





		//call setOptions

		//after this constructor runs...these private variables can be
		//accessed by the subclasses using the getMethods here
	}


	//**double check this constructor below
	//overload construtor method
	public Vehicle(String makeInput, String modelInput, FileReader configInput){
		//set the FileRead object to input parameter
		config = configInput;

		//call setMake to update String Make
		setMake(makeInput);

		//don't use setModel because it requires PID number
		model = modelInput;
	} //end Vehicle constructor

	public void setMake(String m){
		make = m;
	}

	public String getMake(){
		return make;
	}

	/*
	* Input: String - pidNumber
	* Return: Void
	* Description: setModel using the inputted pidNumber to set the private model variable.
	*/
	public void setModel(String pidNumber){
		//string m here will refer to
		//model = m;
		//String modelFromConfig;  //the model string

		String[] pidFormatted = new String[8];  //formatted PID number
		boolean continueRead = true;  //used for while loop when reading config file
		String line = null;  //store string when found in config file
		String[] lineArray = null;  //break line into string array w/ space delimiter
	//	FileReader tempReader = new FileReader(this.config);

		//set string array to a formatted pidNumber using pidFormat method
		pidFormatted = pidFormat(pidNumber);

		try{
			//instantiate buffer reader object to read configInput file
			//reference:  https://stackoverflow.com/questions/15577688/search-a-file-for-a-string-and-return-that-string-if-found
			BufferedReader configReader = new BufferedReader(this.config);

			System.out.println(configReader.ready());

			while(continueRead){

				//read in line from config file
				line = configReader.readLine();

				//check if string contains the model number in pidNumber (1st element of pidFormatted); if so stop reading
				if (line.contains(pidFormatted[0])){
					continueRead = false;
				}
			} //end while

			//close the file

			configReader.close();
		} //end try
		catch(Exception e){
			System.out.println("Model name not found...");
			System.exit(0);
		}

		//break line string into array
		//reference:  https://stackoverflow.com/questions/6086381/split-string-into-an-array-of-string
		lineArray = line.split("\\s+");

		//second element of line array is the model name
		this.model = lineArray[1];
	}

	public String getModel(){
		return model;
	}

	public void setOptions(String pidNumber){

		System.out.println("Entering setOptions...");
		FileReader tempReader = this.config;
		//string array to append with userOptions string array
		String[] optionNames = {"Exterior Color: ", "Interior Color: ", "Powertrain: ", "Seat Type: ", "Radio Type: ",
		"Tire Type: ", "Rim Size: ", "Miscellaneous: "};

		String[] pidFormatted = new String[8];  //formatted PID number
		boolean continueRead = true;  //used for while loop when reading config file
		String line = null;  //store string when found in config file
		String[] lineArray = null;  //break line into string array w/ space delimiter
		Boolean matchNotFound;
		//parse pid with pidformat
		//options =...
		//set each o string to options string array
		//char vehicleTypeChar = null;
		int configIdentifier = 0;
		int[] optionsArray;  //for pid formatter
		int position; //store index of optionName when found

		//set string array to a formatted pidNumber using pidFormat method
		pidFormatted = pidFormat(pidNumber);

		//read config file into Array List

		//read file into an array list
		//references:  https://stackoverflow.com/questions/36440723/reading-text-file-into-arraylist
		//https://stackoverflow.com/questions/14973566/find-the-index-in-an-arraylist-that-contains-a-string
		ArrayList<String> configArray = new ArrayList<String>();

		System.out.println("Initialized Array list...");

		try{
			BufferedReader configReader = new BufferedReader(tempReader);
			//set mark
			configReader.mark(100000);

			while(continueRead){

				//read line from config file
				line = configReader.readLine();


				System.out.println(line);
				continueRead = false;
				configReader.reset();
				configReader.close();
				this.config.close();
			//	configReader.reset();

				//this.config.close();
			}


			// String configLine = configReader.readLine();
			//
			// System.out.println(configLine);
			//
			// while(configLine != null){
			// 	System.out.println("creating configLine array list");
			//
			// 	configArray.add(configLine);
			// 	configLine = configReader.readLine();
			// }

		}
		catch(IOException e){
			System.out.println(e);
		}



		//loop through optionNames to find match for each option
		for(int i = 0; i < optionNames.length; i++){

			//find position in ArrayList for optionName
			position = configArray.indexOf(optionNames[i]);

			System.out.println(optionNames[i] + position);


			//search until position reaches next option name or end of ArrayList
			//to catch if index past end of configArray list
			// try{
			// 	while(position < (configArray.indexOf(optionNames[i+1]))) {
			//
			// 		//break each arraylist string of position into array
			// 		lineArray = configArray[position].split("\\s+");
			//
			//
			// 		//see if 1st element (option #) matches the option in pidNumber element
			// 		if (lineArray[0].equals(pidNumber[i+1])){
			// 			//match found...update optionsArray with option
			// 			optionsArray[i] = optionNames[i] + lineArray[1];
			// 		}
			//
			// 		//try next position index
			// 		position++;
			// 	}
			// } //end try
			// catch(Exception e){
			// 	System.out.println("reached end of list");
			// }
		}//end for




		// 	//now search from this position + 1 against until next option name or end of list
		// 	for (int y = position+1; (y < optionNames[i+1]) || (y == (configArray.size() -1) ); y++){
		//
		// 		//break each arraylist string of position into array
		// 		lineArray = configArray[y].split(" ");
		//
		// 		//see if 1st element (option #) matches the option in pidNumber element
		// 		if (lineArray[0].equals(pidNumber[i+1])){
		// 			//match found...update optionsArray with option
		// 			optionsArray[i] = optionNames[i] + lineArray[1];
		// 		}
		// 	} //end inner for
		// }  //end outer for
		// try{
		// 	//instantiate buffer reader object to read configInput file
		// 	//reference:  https://stackoverflow.com/questions/15577688/search-a-file-for-a-string-and-return-that-string-if-found
		// 	BufferedReader configReader = new BufferedReader(this.config);
		//
		// 	for(int i = 0; i < pidFormatted.length; i++){
		// 		String matchOptionName = optionNames[i];
		// 		Boolean continueRead = true;
		// 		Boolean optionNotFound = true;
		// 		String line = "";
		// 		String[] lineArray;
		//
		// 		//continue to read each line until match found
		// 		while(continueRead){
		// 			line = configReader.readLine();
		//
		// 			//if line contains option then next loop
		// 			if (line.contains(matchOptionName)){
		// 				optionNotFound = true;
		// 				//stop reading against the OptionName
		// 				continueRead = false;
		//
		// 				//now find the option selected
		// 				while(optionNotFound){
		// 					line = configReader.readLine();
		// 					if (line.contains(pidFormatted[i+1])){
		// 						//find the option then return the string after option
		// 					}
		//
		// 				}
		// 			}
		// 		}
		// 		//read file until match option name
		//
		//
		// 	}
		// 	while(continueRead){
		//
		// 		//read in line from config file
		// 		line = configReader.readLine();
		//
		// 		//check if string contains the model number in pidNumber (1st element of pidFormatted); if so stop reading
		// 		if (line.contains(pidFormatted[0])){
		// 			continueRead = false;
		// 		}
		// 	} //end while

			//close the file
		// 	configReader.close();
		// } //end try
		// catch(Exception e){
		// 	System.out.println("Model name not found...");
		// 	System.exit(0);
		// }
		// //determine configIdentifier to pass to PID format
		// //try{
		// 	//check first digit of PID# for config file identifer
		// 			if (pidNumber.charAt(0) == '1'){
		// 				configIdentifier = 1;
		// 			}
		// 			else if(pidNumber.charAt(0) == '2'){
		// 				configIdentifier = 2;
		// 			}
		// 			else if(pidNumber.charAt(0) == '3'){
		// 				configIdentifier = 3;
		// 			}
		// 			else{
		// 				System.out.println("Invalid option. Exiting program...");
		// 				System.exit(0);
		// 				//request();  -- will this lead to stack overflow?
		// 			}
		// 		//}  //end try
		// 		//catch(IOException e){
		// 			//System.err.println("error: " + e);
		// 		//}
		//
		// 	//call PID formatter to return array of options
		// 	options = pidFormat(configIdentifier);

		}


	public String[] getOptions(){
		return options;
	}

	//to be overridden
	public abstract void setParts(String[] p);

	public String[] getParts(){
		return parts;
	}

	public void setPrice(double p){
		//ues model string, options/parts array,
		//reader, to filter out the prices?
		//use reader to match parts and then price after

		price = p;
	}

	public double getPrice(){
		return price;
	}

	//public String toString(){

	//}

	//can i change pidFormat from int intput to string?
	//yes pass in as pid #
	public String[] pidFormat(String pidNumber){
		//use to return pidNumber formatted with 8 elements
		String[] pidFormatter = new String[8];

		//determine config file identiefer to format pid number
		//try{
		//check first digit of PID# for config file identifer
		//check for ThisAuto.config
		if (pidNumber.charAt(0) == '1'){

			//loop through first 4 digits and put in index 0 the first four digits of pidNumber
			pidFormatter[0] = pidNumber.substring(0,4);

			//loop through remaining indexes and set to chars of pidNumber
			for (int i = 1; i < pidFormatter.length; i++){
				pidFormatter[i] = Character.toString(pidNumber.charAt(i+3));
			}
			//pidFormatter = {4, 5, 6, 7, 8, 9, 10, 11, 12};
		}  //end if

		//check for ThatAuto.config  - 15 digits
		else if(pidNumber.charAt(0) == '2'){

			//loop through first 6 digits and put in index 0 the first six digits of pidNumber
			pidFormatter[0] = pidNumber.substring(0,6);

			//loop through all but last index and set to chars of pidNumber
			for (int i = 1; i < pidFormatter.length - 1; i++){
				pidFormatter[i] = Character.toString(pidNumber.charAt(i+5));
			}

			//set last two digits of pidNumber to last index of pidFormatter
			pidFormatter[7] = pidNumber.substring(13);
		}  //end else if

		//check for OtherAuto.config
		else if(pidNumber.charAt(0) == '3'){
			//loop through first 4 digits and put in index 0 the first four digits of pidNumber
			pidFormatter[0] = pidNumber.substring(0,4);

			//loop through all but last index and set to chars of pidNumber
			for (int i = 1; i < pidFormatter.length - 1; i++){
				pidFormatter[i] = Character.toString(pidNumber.charAt(i+3));
			}

			//set last four digits to last index of pidFormatter
			pidFormatter[7] = pidNumber.substring(11);
		}  //end else if

		else{
			System.out.println("Invalid option. Exiting program...");
			System.exit(0);
			//request();  -- will this lead to stack overflow?
		}
		// if (m == 1){
		// 	pidFormatter = {4, 5, 6, 7, 8, 9, 10, 11, 12};
		// }
		// else if (m==2){
		// 	pidFormatter = {6, 7, 8, 9, 10, 11, 12, 13, 15};
		// }
		// else{
		// 	pidFormatter = {4,5,6,7,8,9,10,11,16};
		// }
		return pidFormatter;
	} //end pidFormat method
}
