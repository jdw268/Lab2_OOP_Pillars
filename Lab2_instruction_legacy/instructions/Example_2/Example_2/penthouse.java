class Penthouse extends Apartment{
	private int pools;

	public int getPools(){
		return pools;
	}
	public void setPools(int p){
		pools = p;
	}
    Penthouse(int rooms, int bath, int num, boolean bal, int p){
    	super(rooms, bath, num, bal);
    	pools = p;
    }

    public int calculate_rent(){
		return (getBedrooms() * 250) + (getBathrooms() * 300) + 5000;
	}

	public int calculate_sqfeet(){
		return (getBedrooms() * 400) + (getBathrooms() * 125) + 1200;
	}

	public String toString(){
		String r = "Apartment Number: " + getNumber() + "\nBedrooms: " +  getBedrooms() + " Bathrooms: ";
		r = r +  getBathrooms() + " Balcony: " + ((balcony) ? "Yes" : "No") + " Pools: " + pools;
		return r + "\nSquare Feet: " + calculate_sqfeet() + " Monthly Rent: $" + calculate_rent() + ".00";
	}

}