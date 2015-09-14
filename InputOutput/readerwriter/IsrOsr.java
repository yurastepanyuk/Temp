package day11.readerwriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class IsrOsr {

    public static void main(String[] args) throws IOException {

        final PrintStream out = new PrintStream ( new File( "src\\day11\\readerwriter\\data.txt" ) ) ;
        System.setOut ( out ) ;

        System.out.println("qweeertr  rgrg ");

        String fileName = "src\\day11\\readerwriter\\data.txt";
        String strWrite = "Hi JDK Writer";

        JDKWriter fileWriter = new JDKWriter(fileName);
        fileWriter.write(strWrite);

        JDKReader fileReader = new JDKReader(fileName);
        System.out.println(fileReader.reader(fileName));
        System.out.println("qweeertr  rgrg ");

    }

}
