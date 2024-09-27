package files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;

public class FileManagement {
	//instansvariable
	File file;
	Cooler[] collection;	//skapar en array för objekt av klassen cooler
	

	public static void main(String[] args) {
		//Skapar instanser av den aktuella klassen
		FileManagement start = new FileManagement();
		
	}
	
	//konstruktor
	public FileManagement() {
		createFile("testarfil.txt");
		writeToFile("Det här är en klassificerad fil");
		saveObjects();
		
		
		
	}
	//Metod för att skapa en fil:
	private boolean createFile(String filename) {
		try {
		file = new File(filename);
		System.out.println("filen skapad:" +file.getAbsolutePath());
		return true;
		}catch( Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	//Metod för att skriva text till fil
	private void writeToFile(String data) {
		try {
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		Writer w = new BufferedWriter(osw);
		w.write(data);
		w.close();
		}catch(Exception e) {}
		
	}
	//Metod för att såara objekt
	private void saveObjects() {
		//Skapar objekt i array:en collection
		collection = new Cooler[5];
		for(Cooler a : collection) {
			a = new Cooler();
		}
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(collection);
			oos.close();
			oos.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void loadObjects() {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			collection = (Cooler[])ois.readObject(); //Casta som rätt sorts objekt
			ois.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
