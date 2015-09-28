package day11.simplelist;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.util.Iterator;

public class SimpleList<T> implements Iterable<Object> {

    private         T               typeOfData;

    private         int             idxLast;

    private final   String          DATA = "src\\" + SimpleList.class.getName().replace(".","\\").replace(SimpleList.class.getSimpleName(), "") + "DataList.db";
    private         FileWriter      outFileWriter;

    public SimpleList() throws IOException {

        FileOutputStream outFileWriter   = new FileOutputStream(DATA);
        outFileWriter.close();

        this.idxLast = -1;
    }

    public void add(Object obj) throws IOException {

        if (getLenghtData() >= 10) {
            throw new IllegalStateException("List does not storage space");
        }

        outFileWriter   = new FileWriter(DATA, true);

        ++idxLast;
        outFileWriter.write(String.valueOf(idxLast) + ";");
        outFileWriter.write((String) obj + ";");
        outFileWriter.write((String) obj.getClass().getSimpleName() + ";");
        outFileWriter.write((int)'\n');
        outFileWriter.flush();

        outFileWriter.close();

    }

    public void remove(int idx){
        removeDataByIndex(idx);
        optimizationData();
    }

    private void optimizationData() {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

        try(FileReader fileReader                   = new FileReader(DATA);
            BufferedReader inputBufferedFileReader  = new BufferedReader(fileReader)) {

            String strData;

            int mustIdx = 0;

            while ((strData = inputBufferedFileReader.readLine()) != null){

                String curData = strData.substring(2);
                if (curData.isEmpty()) {
                    idxLast--;
                    continue;
                }

                int currentIdx = Integer.valueOf(strData.substring(0, 1));

                if (currentIdx != mustIdx) {
                    byteOutputStream.write((String.valueOf(mustIdx) +  ";").getBytes());
                    byteOutputStream.write(curData.getBytes());
                    byteOutputStream.write((byte)'\n');

                } else {

                    byteOutputStream.write(strData.getBytes());
                    byteOutputStream.write((byte)'\n');
                }

                mustIdx++;

            }

            outFileWriter = new FileWriter(DATA, false);
            byte[] tempData = byteOutputStream.toByteArray();
            for (int i = 0; i < tempData.length; i++) {
                outFileWriter.write((char) tempData[i]);
            }
            outFileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int getLenghtData(){

        String strData;
        int lenght = 0;
        try(FileReader fileReader                   = new FileReader(DATA);
            BufferedReader inputBufferedFileReader  = new BufferedReader(fileReader)) {
            while ((strData = inputBufferedFileReader.readLine()) != null){
                lenght++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lenght;
    }

    private Object[] getDataByIndex(int idx){

        String strData;
        Object [] dataFromString = new Object[2];

        try(FileReader fileReader                   = new FileReader(DATA);
            BufferedReader inputBufferedFileReader  = new BufferedReader(fileReader) ) {
            while ((strData = inputBufferedFileReader.readLine()) != null){

                if (Integer.valueOf(strData.substring(0, 1)) == idx) {

                    dataFromString[0] = Integer.valueOf(strData.substring(0, 1));

                    int nunEndOfData = strData.indexOf(";", 2);
                    int nunEndOfType = strData.indexOf(";", nunEndOfData+1);
                    if (nunEndOfData == -1) {
                        dataFromString[1] = null;
                        break;
                    }
                    String strValue = strData.substring(2, nunEndOfData);
                    String TypeOfData = strData.substring(nunEndOfData+1, nunEndOfType);

                    Class cls;
                    Object valueData;
                    cls = String.class;
                    valueData = cls.cast(strValue);
                    dataFromString[1] = valueData;

                    break;

                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataFromString;
    }

    private void removeDataByIndex(int idx){

        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

        try(FileReader fileReader                   = new FileReader(DATA);
            BufferedReader inputBufferedFileReader  = new BufferedReader(fileReader)) {

            String strData;

            while ((strData = inputBufferedFileReader.readLine()) != null){

                if (Integer.valueOf(strData.substring(0, 1)) == idx) {
                    byteOutputStream.write(strData.substring(0,2).getBytes());
                    byteOutputStream.write((byte)'\n');

                } else {

                    byteOutputStream.write(strData.getBytes());
                    byteOutputStream.write((byte)'\n');
                }

            }

            outFileWriter = new FileWriter(DATA, false);
            byte[] tempData = byteOutputStream.toByteArray();
            for (int i = 0; i < tempData.length; i++) {
                outFileWriter.write((char) tempData[i]);
            }
            outFileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Iterator<Object> iterator() {
        return new MyIterator();
    }

    class MyIterator implements Iterator<Object>{

        int currentIdx = -1;

        @Override
        public boolean hasNext() {
            return (currentIdx + 1 < getLenghtData()&& idxLast >= 0 && currentIdx + 1 <= idxLast);
        }

        @Override
        public Object next() {

            if (hasNext()) {
                Object [] dataFronString = getDataByIndex(++currentIdx);

                return (dataFronString == null && dataFronString.length > 0) ?null:dataFronString[1];

            }

            throw new IllegalStateException("List has not more elements");

        }

        @Override
        public void remove() {

            if (currentIdx < 0 || currentIdx >= getLenghtData()){
                throw new IllegalStateException("It doesn't deleting element to index " + currentIdx);
            } else{
                removeDataByIndex(currentIdx);
            }

        }
    }

}
