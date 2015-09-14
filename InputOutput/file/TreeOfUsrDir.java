package day11.file;

import java.io.File;

public class TreeOfUsrDir {

    public static void main(String[] args) {

        String strUserDir = System.getProperty("user.dir");

        File myCatalog = new File(strUserDir);


        String[] list = myCatalog.list();

        for (String curNameCatalog : list){

            File curFile = new File(strUserDir + "\\" + curNameCatalog);

            if (curFile.isDirectory()) {
                System.out.println(curFile.getName());
            }

        }

    }

}
