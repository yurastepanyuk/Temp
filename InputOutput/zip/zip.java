package day11.zip;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static String pathFolderInput  = System.getProperty("user.dir") + "\\src\\day11\\readerwriter";
    public static  String pathFolderOut   = System.getProperty("user.dir") + "\\src\\day11\\zip";

    public static void main(String[] args) throws IOException {

        if (args.length > 0) {
            pathFolderInput = args[0];
        }

        FileOutputStream fout = new FileOutputStream(pathFolderOut + "\\" + "ziping.zip");
        ZipOutputStream zout  = new ZipOutputStream(fout);
        zout.setComment("A test of Java Zipping");
        BufferedOutputStream out = new BufferedOutputStream(zout);

        File fileInputFolder = new File(pathFolderInput);

        String [] listFiles = fileInputFolder.list();

        packZipToList(zout, listFiles, pathFolderInput, null);

        out.close();
    }

    private static void packZipToList(ZipOutputStream zout,  String [] listFiles, String pathInput, String pathOut) throws IOException {

        for (String curNameFile : listFiles) {

            File curFile = new File(pathInput + "\\" + curNameFile);

            String pathOutNew = (pathOut == null)?"" : pathOut + "\\";

            if (curFile.isDirectory()) {

                ZipEntry ze = new ZipEntry(pathOutNew + curNameFile+"\\");
                zout.putNextEntry(ze);
                zout.closeEntry();

                String [] listFilesNext = curFile.list();

                packZipToList(zout, listFilesNext, curFile.getAbsolutePath(), pathOutNew + curNameFile);

                continue;
            }

            BufferedReader in = new BufferedReader(new FileReader(curFile));
            ZipEntry ze = new ZipEntry(pathOutNew + curNameFile);
            zout.putNextEntry(ze);
            String c;
            while ((c = in.readLine()) != null){
                zout.write((int)'\n');
                zout.write(c.getBytes());
            }
            zout.closeEntry();
            in.close();
        }

    }

}
