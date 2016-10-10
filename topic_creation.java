import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class topic_creation {
	File topic_creation(String filename) {
		File to_publish = new File(filename);
		if (!to_publish.exists()) {
			try {
				to_publish.createNewFile();
				System.out.println("\nFile Created Successfully");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("\nError in Creating File");
			}
		}

		return to_publish;

	}
}
