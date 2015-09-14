package day11.file;

import java.io.*;

public class FileReaderMy {

    public static void main(String[] args) throws IOException {

        String sNameFileIn  = System.getProperty("user.dir") + "\\src\\day11\\file\\CreatePackageWithFile.java";
        String sNameFileOut = System.getProperty("user.dir") + "\\src\\day11\\file\\NewFolder\\Write.txt";

        FileReader fileReader = new FileReader(sNameFileIn);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        FileWriter fileWriter = new FileWriter(sNameFileOut, true);

        String strLine;

        while ((strLine = bufferedReader.readLine()) != null){
            fileWriter.write(strLine);
        }

        fileReader.close();
        fileWriter.close();

    }



}
