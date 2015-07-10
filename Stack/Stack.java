package day7.Stack;

public class Stack {

    Node last;

    public void push(Object obj){
        Node newNode = new Node();
        newNode.value = obj;
        if (last != null) {
            newNode.ref = last;
        }
        last = newNode;
        System.out.println("Added " + obj.toString());
    }

    public Object peek(){
        if (last != null) {
            return last.value;
        } else{
            throw new IllegalStateException("Stack doesn't has more a values");
        }
    }

    public Node peekNode(){
        if (last != null) {
            return last;
        } else{
            return null;
        }
    }

    public Object pop(){

        Node node = peekNode();

        if (node != null) {
            this.last = node.ref;
            return node.value;
        }

        return null;
    }

    public void printStack(){

        Node currentNode;

        currentNode = last;

        while (currentNode != null){
            System.out.println(currentNode.value.toString());
            if (currentNode.ref != null) {
                currentNode = currentNode.ref;
            } else {
                currentNode = null;
            }
        }

    }

    private class Node {

        Object value;
        Node ref;

    }
}
