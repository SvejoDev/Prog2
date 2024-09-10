package interFace;

//För att använda ett interface/gränssnitt använder jag implements namnet;

public class IfaceTest implements Iface {

	@Override
	public void setText(String text) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		//Skapar en instans av klassen
		
		Iface ift = new IfaceTest();
				System.out.println(ift.getInfo() + "" +ift.word); //med instansen av klassen kan jag använda defaulta metoder samt variablerna
				
;
	}

}
