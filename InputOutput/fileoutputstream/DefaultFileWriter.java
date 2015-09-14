package day11.fileoutputstream;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DefaultFileWriter extends FileWriter {

    String name;

    public DefaultFileWriter(String fileName) throws IOException {
        super(fileName);
        this.name = fileName;
    }

    @Override
    public void write(String data) throws IOException {
//        super.write(data);
        try(FileOutputStream fos = new FileOutputStream(name)) {
            fos.write(data.getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
