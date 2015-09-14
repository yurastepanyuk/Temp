package day11.zip;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class unzip {


    public static void main(String[] args) {

        String nameFile         = "ziping.zip";
        String pathFolderInput  = System.getProperty("user.dir") + "\\src\\day11\\zip";
        String pathFolderOut    = System.getProperty("user.dir") + "\\src\\day11\\zip\\unzip";

        File fileFolderOut = new File(pathFolderOut);

        if (!fileFolderOut.exists()) {
            //fileOut.mkdir();
            Path pathDirectory = Paths.get(pathFolderOut);
            try {
                Files.createDirectories(pathDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(FileInputStream fi   = new FileInputStream(pathFolderInput + "\\" + nameFile);
            ZipInputStream inZip = new ZipInputStream(fi);
            BufferedInputStream bis = new BufferedInputStream(inZip)
                ) {

            ZipEntry ze;
            while ((ze = inZip.getNextEntry()) != null){
                System.out.println("Reading file " + ze);

                File                 fileOut = new File(pathFolderOut + "\\" + ze.getName());
                FileOutputStream     fos     = new FileOutputStream(fileOut);
                BufferedOutputStream bout    = new BufferedOutputStream(fos);

                int i;
                while ((i = bis.read()) != -1){
                    bout.write(i);
                }

                bout.close();
                fos.close();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        FileInputStream fi = new FileInputStream("test.zip");
//        CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
//        ZipInputStream in2 = new ZipInputStream(csumi);
//        BufferedInputStream bis = new BufferedInputStream(in2);
//        ZipEntry ze;
//        while ((ze = in2.getNextEntry()) != null) {
//            System.out.println("Reading file " + ze);
//            int x;
//            while ((x = bis.read()) != -1)
//                System.out.write(x);
//        }
//        System.out.println("Checksum: " + csumi.getChecksum().getValue());
//        bis.close();





    }

}
