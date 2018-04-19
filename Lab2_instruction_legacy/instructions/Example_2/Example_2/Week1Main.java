public class Week1Main {
	public static void main(String[] args){
		switch (args[0]){
			case "1": 
				example_1();
				break;
			case "2": 
				example_2();
				break;
			case "3": 
				example_3();
				break;
			case "5":
				example_5();
				break;
		}
	}

	/*
	Encapsulation
	*/
	public static void example_1(){
		try{
			Apartment a = new Apartment(1, 1, 101, false);
			System.out.println(a.toString());
			System.out.println(a.balcony);
			a.balcony = true;
			System.out.println(a.toString());
			//System.out.println(a.bedrooms);
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}


	/*
	Inheritance
	*/
	public static void example_2(){
		Apartment a = new Apartment(1, 1, 101, false);
		Penthouse p = new Penthouse(3, 3, 201, false, 2);
		System.out.println(a.toString());
		System.out.println(p.toString());
	}


	/*
	Polymorphism
	*/
	public static void example_3(){
		try{
			Apartment a = new Apartment(1, 1, 101, false);
			Apartment p = new Penthouse(3, 3, 201, false, 2);
			System.out.println(a.toString());
			System.out.println(p.toString());
			System.out.println(a.getClass());
			System.out.println(p.getClass());
			//Penthouse e = new Apartment(1, 1, 101, false);
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}


	/*
	Interfaces
	*/
	public static void example_5(){
		try{
			Apartment l = new LuxuryPenthouse(5, 5, 1001, true, 2);
			System.out.println(l.toString());
		} catch (Exception e){
			System.out.println(e.toString());
		}
	}

}