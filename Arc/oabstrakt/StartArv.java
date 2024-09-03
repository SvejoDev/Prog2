package oabstrakt;

public class StartArv {

	public static void main(String[] args) {
		///Skapar instanser av klasserna
		superClass sc = new superClass("Super");
		
		superClass subC = new subClass("Sub");
		
		superClass subC2 = new subClass2("Sub2");
		
		//superClass Array
		superClass[] array = new superClass[3];
		array[0] = sc;
		array[1] = subC;
		array[2] = subC2;
		
		for(int i = 0; i < array.length; i++) {
			array[i].print();
		}
	}

}
