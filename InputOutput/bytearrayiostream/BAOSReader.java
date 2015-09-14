package day11.bytearrayiostream;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.util.Arrays;

public class BAOSReader {

    public static void main(String[] args) {
        byte[] bytes = new byte[]{0,-10,121,13,6,-5};

        ByteOutputStream byteOutputStream = new ByteOutputStream();

        for (int i = 0; i < bytes.length; i++) {
            byteOutputStream.write(bytes[i]);
        }

        System.out.println(Arrays.toString(byteOutputStream.toByteArray()));

    }

}
