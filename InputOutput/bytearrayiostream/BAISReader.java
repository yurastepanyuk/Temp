package day11.bytearrayiostream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BAISReader implements AutoCloseable {

    public static void main(String[] args) {

        byte[] bytes = new byte[]{10,-65,23,127,-95,-101,60,2};

        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        BAISReader baisReader = new BAISReader();
        baisReader.printStreamData(inputStream);

        Path path = Paths.get(System.getProperty("user.dir") + "\\src\\day11\\file", "CreatePackageWithFile.java");
        //path = Paths.get("d:\\Work\\", "1cLocal.db3");
        //InputStream inputStr = null;

        try(InputStream inputStr = Files.newInputStream(path);
            BufferedInputStream bis = new BufferedInputStream(inputStr);
            ){
            baisReader.printStreamData(bis);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public BAISReader() {
    }

    private void printStreamData(ByteArrayInputStream byteArrayInputStream){
        System.out.println("");
        System.out.println("printStreamData(ByteArrayInputStream byteArrayInputStream)");

        System.out.print("byteArrayInputStream = [");
        int i;

        while ((i = byteArrayInputStream.read()) != -1){
            System.out.print(i + " ");
        }
        System.out.print("]");
    }

    private void printStreamData(InputStream inputStream){
        int i;
        long currentStart = System.currentTimeMillis();
        System.out.println("");
        try {
            int k=1;
            while ((i = inputStream.read()) != -1){

                //k++;
                if (k == 1) {
                    System.out.print((char)i);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Time of working: " + (System.currentTimeMillis() - currentStart));
        System.out.print("I am read");

    }

    @Override
    public void close() throws Exception {

    }
}
