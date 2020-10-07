package lbuang.Read_n_Write;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteTXT {
  public static File heroFile = null;
    private static FileWriter fileWriter;

    public WriteTXT() {

    }

    public static void createFile() {
        try {
            if (heroFile == null) {
                heroFile = new File("Heroes.txt");
                heroFile.createNewFile();
            }

            fileWriter = new FileWriter(heroFile, true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void writeToFile(String string) {
        try {
            heroFile = new File("Heroes.txt");
            fileWriter = new FileWriter(heroFile, true);

            fileWriter.append(string);
            fileWriter.append('\n');
            fileWriter.close();
            System.out.println(string);
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void closeFile() {
        try {
            if (fileWriter != null)
                fileWriter.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
