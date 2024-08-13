package intro;

public class OopRep {
	int tal = 5;

		public OopRep(){
			int tal = 5;
			String text = "";
			int [] array = new int[6];
			
			//metodkall
			testInst(text);


		}

	private void testInst(String text) {
		Inst[] obj = new Inst[3];
		obj[0] = new Inst(text, 4.4, 4.2);
		System.out.println(obj[0].omkr());
		// TODO Auto-generated method stub
		
		//testa den andra konstruktorn
		obj[1] = new Inst("Kvadrat", tal);
		try {
			for(int i = 0; i < obj.length; i++) {
				System.out.println(obj[i].getShape());
			}
			
		}catch(Exception e) {};
		
	}
	public static void main(String[] args) {
		OopRep start = new OopRep();
	}

}
