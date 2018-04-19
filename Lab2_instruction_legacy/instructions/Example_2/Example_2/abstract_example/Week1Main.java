public class Week1Main {
	public static void main(String[] args){
		example_4();
	}

	/*
	Abstraction
	*/
	public static void example_4(){
		try{
			Penthouse p = new Penthouse(1, 1, 101, false, 2);
			System.out.println(p.toString());
			Apartment a = new Penthouse(1, 1, 301, false, 3);
			System.out.println(a.toString());
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}


}
