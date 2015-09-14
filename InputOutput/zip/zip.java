package day11.zip;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class zip {

    public static void main(String[] args) throws IOException {

        String pathFolderInput = System.getProperty("user.dir") + "\\src\\day11\\readerwriter";
        String pathFolderOut   = System.getProperty("user.dir") + "\\src\\day11\\zip";


        if (args.length > 0) {
            pathFolderInput = args[0];
        }

        FileOutputStream fout = new FileOutputStream(pathFolderOut + "\\" + "ziping.zip");
        ZipOutputStream zout  = new ZipOutputStream(fout);
        zout.setComment("A test of Java Zipping");
        BufferedOutputStream out = new BufferedOutputStream(zout);

        File fileInputFolder = new File(pathFolderInput);

        String [] listFiles = fileInputFolder.list();

        for (String curNameFile : listFiles) {


            File curFile = new File(pathFolderInput + "\\" + curNameFile);

            BufferedReader in = new BufferedReader(new FileReader(curFile));
            ZipEntry ze = new ZipEntry(curNameFile);
            zout.putNextEntry(ze);
            String c;
            while ((c = in.readLine()) != null){
                zout.write((int)'\n');
                zout.write(c.getBytes());
            }
            zout.closeEntry();
            in.close();
        }

        out.close();


    }

}
