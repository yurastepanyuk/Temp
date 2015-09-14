package day11.copyfile;

import java.io.*;

public class CopyFile implements AutoCloseable {

    public static void main(String[] args) {

        String nameFile    = "Write.txt";
        String nameFoldIn  = System.getProperty("user.dir") + "\\src\\day11\\file\\NewFolder\\";
        String nameFoldOut = System.getProperty("user.dir") + "\\src\\day11\\copyfile\\";

        File copyFile = new File(nameFoldIn + nameFile);

        copyFile(copyFile, nameFoldOut);


    }

    public static void copyFile(File file, String path) {

        StringBuilder stringBuilder = new StringBuilder();
        String data = null;

        try(FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 256)){
            int i;
            while((i = bufferedInputStream.read()) != -1){
                stringBuilder.append((char) i);
            }
            data = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = file.getName();

        fileName.replace(".","Copy.");

        try(FileOutputStream fileOutStream = new FileOutputStream(path + fileName);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutStream, 256)) {

            fileOutStream.write(data.getBytes());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void close() throws Exception {

    }
}
