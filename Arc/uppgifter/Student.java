package uppgifter;

public class Student extends Person{
	private String education;
	private int grade;

	public Student(String name, int age, String education, int grade) {
		super(name, age);
		this.education = education;
		this.grade = grade;
	}
	
	//Setmetod
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	//getmetod
	public int getGrade() {
		return this.grade;
	}
	
	 @Override
	    public void printInfo() {
	        super.printInfo();  // Anropa Person's printInfo
	        System.out.println("Utbildning: " + education);
	        System.out.println("Betyg: " + grade);
	    }
	
}
