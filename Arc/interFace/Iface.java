package interFace;

public interface Iface {
	
	public static final String word = "ok";
	//Variabler kan endast vara static final för ett interface
	
	//metoder som behöver kompletteras/skrivas i klassen som ärver
	public void setText(String text);
	
	//metoder som är "färdiga" heter deafult metoder
	public default String getInfo() {
		//Här kan jag ha variabler som används inom denna metod
		int tal=2;
		
		String text = Integer.toString(tal) + word;
		
		return text;
	}

}
