package day11.fileoutputstream;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException {

        String fileName = "src\\day11\\fileoutputstream\\data.txt";
        FileWriter fileWriter = new DefaultFileWriter(fileName);
        fileWriter.write("I am love programming");
        //fileWriter.write("I love programing", fileName);

        DefaultFileReader fileReader = new DefaultFileReader(fileName);
        fileReader.reads();
        System.out.println(fileReader.getStrResult());

        fileWriter = new DefaultFileWriter(fileName);
        fileWriter.write("I am love too");


    }




}


