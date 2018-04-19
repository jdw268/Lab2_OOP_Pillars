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
	private String[] options = new String[8];
	protected String[] parts = new String[10];
	private double price;
	private FileReader config;

	//used to store config file so not have to read-in multipe times
	protected ArrayList<String> configFileList = new ArrayList<String>();

	//overload construtor method - mine
	public Vehicle(String makeInput, FileReader configInput, String pidNumber){

		//call setMake to set private make variable to input
		setMake(makeInput);

		//set the configuration file of the object so can use config file now
		this.config = configInput;

		//create ArrayList to read in the config file
		buildArrayList(this.config);

		//call setModel with pidNumber
		setModel(pidNumber);
		//System.out.println("Return model variable " + this.getModel());

		//call setOptions with pidNumber
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
		String[] pidFormatted = new String[8];  //formatted PID number
		//boolean continueRead = true;  //used for while loop when reading config file
		//String line = null;  //store string when found in config file
		//String[] lineArray = null;  //break line into string array w/ space delimiter
		//int counter = 0;  //count lines in configFileList
		String id;  //variable to store model number from PID#
		int modelIndex = 0;  //variable to store where model number was found in configFileList

		//set string array to a formatted pidNumber using pidFormat method
		pidFormatted = pidFormat(pidNumber);

		//store just the model number from pidFormatted
		id = pidFormatted[0];

		//for each line in configFileList - test it against the pidFormatted first index
		//reference:  Joshua Acklin for loop
		for(int i = 0; i < configFileList.size(); i++){

			//split each confileFileList line and store in array
			String[] index = configFileList.get(i).split(" ");

			//if first element of array (the model#) matches the id - store the index counter
			if(index[0].equalsIgnoreCase(id)) {
				modelIndex = i;
			}
		}  //end for

		//take line at modelIndex and split the string
		String[] modelLine = configFileList.get(modelIndex).split(" ");

		//store the 2nd element as the model
		this.model = modelLine[1];

		System.out.println("Model is: " + this.model);
	} //end setModel

	//getter for object model instance variable
	public String getModel(){
		return model;
	}


	/*
	* Input: String - pidNumber
	* Return: Void
	* Description: setOptions using the inputted pidNumber to set the options array variable
	*/
	public void setOptions(String pidNumber){

		//string array to append with userOptions string array
		String[] optionNames = {"Exterior Color", "Interior Color", "Powertrain", "Seat", "Radio",
		"Tire", "Rim", "Miscellaneous"};

		boolean o = false;
		int index = 0;

		String[] pidFormatted = new String[8];  //formatted PID number
		//set string array to a formatted pidNumber using pidFormat method
		pidFormatted = pidFormat(pidNumber);

		//reference Joshua Acklin
		for (int i = 0; i < configFileList.size(); i++) {
			if(!o){

				//check if at end of optionNames array
				if (index == optionNames.length){
					break;
				}

				//loop until find an optionName
				else if(configFileList.get(i).equalsIgnoreCase(optionNames[index])){
					o = true;
				} else{
					continue;
				}
			} //end outer if

			//found an optionName
			else {
				if ((index + 1 < optionNames.length) && configFileList.get(i).equalsIgnoreCase(optionNames[index + 1])) {

					//if '0' is in configFileList line
					optionNames[index] = optionNames[index] + " : " + "none";
					index++;
				}
					//split each line and match the first element
					else{
					String[] c = configFileList.get(i).split(" ");

					//store if the split line matches the pidNumber index
					if (pidFormatted[index +1].equalsIgnoreCase(c[0])) {
						optionNames[index] = optionNames[index] + " : " + c[1];
						index++;
						o=false;
					}
				}
			}  //end outer else
		} //end for

		if (o){
			optionNames[index] = optionNames[index] + " : none";
		}

		//store the optionNames[index] as the instance options variable
		for(int i = 0; i< optionNames.length; i++){
			this.options[i] = optionNames[i];
			System.out.println(this.options[i]);
		}

	} //end setOptions


	//getter for object's options array
	public String[] getOptions(){
		return options;
	}


	//to be overridden
	public abstract void setParts(String[] p);


	//getter for parts array
	public String[] getParts(){
		return parts;
	}


	//setter for price
	public void setPrice(double p){
		//ues model string, options/parts array,
		//reader, to filter out the prices?
		//use reader to match parts and then price after

		price = p;
	}


	//getter for price
	public double getPrice(){
		return price;
	}


	//public String toString(){

	//}


	/*
	* Input: String - pidNumber
	* Return: String array with formatted pidNumber
	* Description: pidFormat breaks down pidNumber into indexes depending on config file
	*/
	public String[] pidFormat(String pidNumber){
		//use to return pidNumber formatted with 8 elements
		String[] pidFormatter = new String[9];

		//determine config file identiefer to format pid number
		if (pidNumber.charAt(0) == '1'){

			//loop through first 4 digits and put in index 0 the first four digits of pidNumber
			pidFormatter[0] = pidNumber.substring(0,4);

			//loop through remaining indexes and set to chars of pidNumber
			for (int i = 1; i < pidFormatter.length; i++){
				pidFormatter[i] = Character.toString(pidNumber.charAt(i+3));
			}
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
			pidFormatter[8] = pidNumber.substring(13);
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
			pidFormatter[8] = pidNumber.substring(11);
		}  //end else if

		else{
			System.out.println("Invalid option. Exiting program...");
			System.exit(0);
		}

		return pidFormatter;
	} //end pidFormat method

	/*
	* Input: FileReader - object's pass in file reader
	* Return: Void
	* Description: builds private arrayList for object so it can be ready by other methods
	*/
	private void buildArrayList(FileReader configFile){
		String line;  //variable to hold each line of config file
		System.out.println("Entered Build Array List");
		try{
			//instantiate new bufferedreader object from configFile
			//reference:  https://stackoverflow.com/questions/15577688/search-a-file-for-a-string-and-return-that-string-if-found
			BufferedReader configReader = new BufferedReader(configFile);

			//set line to first line of file
			line = configReader.readLine();

			//loop until null
			while (line != null){
				//add line to ArrayList
				this.configFileList.add(line);

				//move to next line
				line = configReader.readLine();
			}

			configReader.close();
		}
		catch(Exception e){
			System.out.println("Error: e");
		}
	} //end buildArrayList method
}
