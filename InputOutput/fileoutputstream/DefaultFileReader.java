package day11.fileoutputstream;

import java.io.*;

public class DefaultFileReader implements Closeable {

    private String        nameOfFile;
    private StringBuilder stringBuilder = new StringBuilder();
    private String        strResult;

    public DefaultFileReader(String fileName) throws FileNotFoundException {
        this.nameOfFile = fileName;
    }

    public int reads() throws IOException {

        try(FileInputStream fileInputStream = new FileInputStream(nameOfFile)) {
            int i;
            while ((i = fileInputStream.read()) != -1){
                stringBuilder.append((char) i);
            }
            strResult = stringBuilder.toString();
        } catch (IOException e){
            e.printStackTrace();
        }

        return 1;
    }

    public String getStrResult(){
        return strResult;
    }

    @Override
    public void close() throws IOException {

    }
}
