package abstraktArv;

public abstract class absSuper {
	//Klassen är ej för objekt utan för arv
	protected int tal;
	protected String text;
	
	//konstruktor
	public absSuper(int tal, String text) {
		this.tal = tal;
		this.text = text;
		
	}
	//Abstrakt metod, måste kompleteras i subklasse
	public abstract void print();
	
}
