public abstract class Apartment {
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

	public abstract int calculate_rent();

	public abstract int calculate_sqfeet();
}