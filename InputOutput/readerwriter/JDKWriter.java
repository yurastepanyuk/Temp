package day11.readerwriter;

import java.io.*;

public class JDKWriter implements AutoCloseable {

    String nameFile;

    public JDKWriter(String fileName) throws IOException {
        this.nameFile = fileName;
    }


    public void write(String str) throws IOException {

        try(
//                FileOutputStream fos = new FileOutputStream(nameFile, true);
//            OutputStreamWriter writer = new OutputStreamWriter(fos);
//            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            FileWriter fileWriter = new FileWriter(nameFile, true);
            ) {

            fileWriter.write('\t');
            fileWriter.write(str);

        } catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {

    }
}
