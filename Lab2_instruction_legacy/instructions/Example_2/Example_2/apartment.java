class Apartment {
	private int bedrooms;
	private int bathrooms;
	private int number;
	public boolean balcony;


	Apartment(int rooms, int bath, int num, boolean bal){
		bedrooms = rooms;
		bathrooms = bath;
		number = num;
		balcony = bal;
	}

	public int getBedrooms(){
		return bedrooms;
	}

	public void setBedrooms(int b){
		bedrooms = b;
	}

	public int getBathrooms(){
		return bathrooms;
	}

	public void setBathrooms(int b){
		bathrooms = b;
	}

	public int getNumber(){
		return number;
	}

	public void setNumber(int n){
		number = n;
	}

	public int calculate_rent(){
		return (bedrooms * 150) + (bathrooms * 200) + ((balcony) ? 150 : 0);
	}

	public int calculate_sqfeet(){
		return (bedrooms * 200) + (bathrooms * 25) + 500 + ((balcony) ? 20 : 0);
	}

	public String toString(){
		String r = "Apartment Number: " + number + "\nBedrooms: " +  bedrooms + " Bathrooms: ";
		r = r +  bathrooms + " Balcony: " + ((balcony) ? "Yes" : "No") + "\nSquare Feet: ";
		return r + calculate_sqfeet() + " Monthly Rent: $" + calculate_rent() + ".00";

	}
}