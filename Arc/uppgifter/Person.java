package uppgifter;

public class Person {
    private String name;
    private int age;
     
    /** Konstruktor */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    /** Åtkomstmetod, set-metod */
    public void setAge(int age) {
        this.age = age;
    }
    
    /** Åtkomstmetod, get-metod */
    public int getAge() {
        return this.age;
    }
    
    public void printInfo() {
        System.out.println("Namn: " + name);
        System.out.println("Ålder: " + age);
    }
}
