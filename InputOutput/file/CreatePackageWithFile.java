package day11.file;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreatePackageWithFile {

    public static void main(String[] args) throws IOException {
        //String nameFile = "c:\\Users\\stepanyuk\\workspace\\NewFolder\\Write.txt";
        String sUserDir     = System.getProperty("user.dir");
        String sNameFile    = sUserDir + "\\src\\day11\\file\\NewFolder\\Write.txt";

        File newFile = new File(sNameFile);
        File parentDir = newFile.getParentFile();
        if (parentDir != null) {
            parentDir.mkdir();
        }
        FileWriter fileWriter = new FileWriter(sNameFile);

        String addString = "This is my Writer";

        fileWriter.write(addString);

        fileWriter.close();

    }
}
