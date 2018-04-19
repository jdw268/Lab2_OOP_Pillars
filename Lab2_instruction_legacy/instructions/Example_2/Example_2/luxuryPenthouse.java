class LuxuryPenthouse extends Penthouse implements PenthouseServices{

    LuxuryPenthouse(int rooms, int bath, int num, boolean bal, int p){
    	super(rooms, bath, num, bal, p);
    }

    public int calculate_rent(){
		return (getBedrooms() * 250) + (getBathrooms() * 300) + 5000 + calculate_hoa() + calculate_maid() + calculate_butler();
	}

	public int calculate_hoa(){
		return 150;
	}

	public int calculate_maid(){
		return 2 * calculate_sqfeet();
	}

	public int calculate_butler(){
		return 1500 + (500 * getPools());
	}

}