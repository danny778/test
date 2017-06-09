import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

/**
 * Created by danie on 05/11/2016.
 */
public class Write {
    public static void main(String[] args) throws IOException {
        File newFile = new File("Test2.txt");

        FileWriter fW = new FileWriter(newFile);
        BufferedWriter bW = new BufferedWriter(fW);

        bW.write("Hello");
        bW.flush();

    }
}
