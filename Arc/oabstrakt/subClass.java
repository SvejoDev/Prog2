package oabstrakt;

public class subClass extends superClass{
	
	public subClass(String text) {
		super(text);
	}
	
	public void print() {
		System.out.println(text + getTxt()); //Vi kan använda privata variabler endast indirekt via privata metoder
	}

}
