package johan;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Filehandler {
private static final String SAVE_FILE = "vending_machine_state";

public static void saveState(VMLogik logic) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
    	oos.writeObject(logic);
    	System.out.println("Varuautomat status sparad");
    }catch (IOException e) {
    	System.err.println("Fel med att spara vauatuomat status" + e.getMessage());
    }
}
public static VMLogik loadState() {
	try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))){
		VMLogik logic = (VMLogik) ois.readObject();
    	System.out.println("Varuatomat data laddat lyckat");
    	return logic;
		}catch (IOException | ClassNotFoundException e) {
		System.err.println("Fel med att ladda varuautomat data" + e.getMessage());
		return new VMLogik();

		}
}

}

//TODO implementera CSV inläsningslogik
