package day11.file;

import java.io.File;

public class PathToFile {

    public static void main(String[] args) {

        File file = new File("test.txt");

        System.out.println("user.dir <" + System.getProperty("user.dir") + ">");
        System.out.println("File.separator <" + File.separator + ">");
        System.out.println("File.pathSeparator <" + File.pathSeparator + ">");

        System.out.print("File.listRoots() <");
        for (File fileCur : File.listRoots()){
            System.out.print(fileCur.getAbsolutePath() + " ");
        }
        System.out.println(">");

        System.out.println("getRelativeFileDir() <" + getRelativeFileDir() + ">");

        System.out.println("getFilePath() <" + PathToFile.getFilePath() + ">");

    }

    public static String getFilePath(){
        String path = "src/day11/file/";
        path.replace("/", File.separator);

        File file = new File(path, PathToFile.class.getSimpleName() + "_.java");

        return file.getAbsolutePath();
    }

    public static String getRelativeFileDir(){
        String path = "src/day11/file/";

        return path.replace("/", File.separator);
    }

}
