package day7.SimpleLinkedLists;

import java.util.Iterator;

public class SimpleLinkedList implements Iterable<Object> {

    Node root;
    int size;
    //Node [] nods;

    public SimpleLinkedList() {
        this.size = 0;
        //Node n = new Node();
        //n.obj = new Node();
    }

    public void addFirst(Object object){
        Node first = new Node();
        first.obj = object;
        //first.ref = root;

        if (root != null){
            first.ref = root;
        }

        setRoot(first);

        updateSize();
    };

    public void addLast(Object object){

        Node newNode = new Node();
        newNode.obj = object;

        if (root == null) {
            setRoot(newNode);
        }else{
            Node last = root;
            Node currentNode = root;
            while (currentNode.ref != null){
                last = currentNode;
                currentNode = currentNode.ref;
            }
            currentNode.ref = newNode;
        }

        updateSize();

    };

    //object - kakoy add, prev - posle kakogo add
    public void addAfter(Object object, Object prev){

        //razmer = 0, razmer = 1, object posledniy, prev net v spiske
        if (root == null || getSize() == 1 || prev == root.obj) {
            throw new IllegalStateException("List does not contain object");
        }

        int chekFind = 0;

        Node findNode = null;
        Node currentNode = root;

//        while (currentNode != prev){
//            currentNode = currentNode.ref;
//
//            if (currentNode.obj ==prev){
//                findNode = currentNode;
//                break;
//            }
//        }

        do {
            if (currentNode.obj ==prev){
                findNode = currentNode;
                break;
            }
            currentNode = currentNode.ref;
        }while(currentNode != null && currentNode.ref != null);

        if (findNode == null){
            throw new IllegalStateException("List does not contain object");
        }

        Node newNode = new Node();
        newNode.obj = object;
        if (findNode.ref != null){
            newNode.ref = findNode.ref;
        }
        findNode.ref = newNode;

        updateSize();

    };

    public int getSize() {
        return this.size;
    }

    private void setRoot(Node root) {
        this.root = root;
    }

    private void updateSize(){
        if (getSize() == 0){
            this.size = 1;
        } else {
            this.size++;
        }
    };

    public void printList(){

        if (getSize() == 0){
            System.out.print("List is empty");
        }

        System.out.print("{ ");
        Node currentNode = root;
        while (currentNode.ref != null){
            System.out.print(currentNode.obj + ", ");
            currentNode = currentNode.ref;
        }

        System.out.print(currentNode.obj);
        System.out.println("} ");

    }

    private class Node {

        Object obj;
        Node ref;

    }

    @Override
    public Iterator<Object> iterator() {
        return new SllIterator();
    }

    class SllIterator implements Iterator<Object>{

        private Node prev;
        private Node currentNode;

        public SllIterator() {
        }

        @Override
        public boolean hasNext() {
            return (currentNode == null && root != null)||(currentNode != null && currentNode.ref != null);
        }

        @Override
        public Object next() {

            if (currentNode == null && root != null){
                currentNode = root;
                return currentNode.obj;
            }
            if(hasNext()){
                prev = currentNode;
                currentNode = currentNode.ref;
                return currentNode.obj;
            }

            throw new IllegalStateException("List has no more elements");
        }

        @Override
        public void remove() {
            if (!hasNext() && prev == null){
                //only one element
                currentNode = null;
                prev = null;
            } else if (!hasNext() && prev != null){
                //last element
                prev.ref = null;
                currentNode = null;
            } else if (hasNext() && prev == null){
                //first element
                root = currentNode.ref;
                currentNode = root;
            } else{
                //middle element
                prev.ref = currentNode.ref;
                currentNode = currentNode.ref;
            }
            size--;
        }
    }

}
