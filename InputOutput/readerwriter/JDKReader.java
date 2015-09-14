package day11.readerwriter;

import java.io.*;

public class JDKReader extends FileReader implements AutoCloseable {

    String nameFile;

    public JDKReader(String fileName) throws FileNotFoundException {
        super(fileName);
        this.nameFile = fileName;
    }

    public String reader(String str) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();

        try(FileInputStream fis = new FileInputStream(nameFile);
            InputStreamReader reader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(reader)
        ) {
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null){
                stringBuilder.append(strLine);
            }

            return stringBuilder.toString();

        }

    }
}
