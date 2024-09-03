package abstraktArv;

public class AbsSub extends absSuper{

	public AbsSub(int tal, String text) {
		super(tal, text);
		// TODO Auto-generated constructor stub
	}

	//Kompletterar den asbtrakta metoden print
	@Override
	public void print() {
		System.out.println(text);
	}

}
