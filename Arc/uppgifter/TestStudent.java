package uppgifter;

public class TestStudent {
    public static void main(String[] args) {
        // Skapa en Student-instans
        Student student = new Student("Anna", 22, "Datavetenskap", 90);
        
        // Testa printInfo-metoden
        student.printInfo();
        
        // Ändra betyget och skriv ut det igen
        student.setGrade(95);
        System.out.println("Uppdaterat betyg: " + student.getGrade());
        
        // Skriv ut info igen för att verifiera ändringen
        student.printInfo();
    }
}

