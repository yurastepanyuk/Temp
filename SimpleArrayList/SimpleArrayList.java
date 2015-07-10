package day7.SimpleArrayList;

import java.util.Iterator;

/**
 * Created by stepanyuk on 08.07.2015.
 */
public class SimpleArrayList implements Iterable<Object> {

    Object [] arrObj;
    int idxLast;

    public SimpleArrayList() {
        this.arrObj = new Object [10];
        this.idxLast = -1;
    }

    public void add(Object obj){
        if (idxLast+1 < arrObj.length){
            arrObj[++idxLast] = obj;
        }else {
            throw new IllegalStateException("List does not storage space");
        }

    }

    public void remove(int idx){
        if (idx < arrObj.length) {
            arrObj[idx] = null;
            System.out.println("Removed object to index " + idx);
        }else {
            throw new IllegalStateException("List doesn't has index " + idx);
        }
    }

    public void remove(Object obj){
        if (obj instanceof String){
            String strObj = (String)obj;
            int idx = 0;
            boolean objFind = false;
            for (Object object : arrObj) {
                if (object == null) continue;
                String str = (String)object;
                if (str.equals(obj)) {
                    arrObj[idx] = null;
                    objFind = true;
                    System.out.println("Removed object " + (String)obj);
                    break;
                }
                idx++;
            }

            if (objFind == false) {
                throw new IllegalStateException("List doesn't has object " + obj);
            }

        } else{
            throw new IllegalStateException("This List working to object of String");
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
            return (currentIdx + 1 < arrObj.length && idxLast >= 0 && currentIdx + 1 <= idxLast);
        }

        @Override
        public Object next() {

            if (hasNext()) {
                return arrObj[++currentIdx];
            }

            throw new IllegalStateException("List has not more elements");

        }

        @Override
        public void remove() {

            if (currentIdx < 0 || currentIdx >= arrObj.length){
                throw new IllegalStateException("It doesn't deleting element to index " + currentIdx);
            } else{
                arrObj[currentIdx] = null;
            }

        }
    }


}
