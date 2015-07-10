package day7.Stack;

/**
 * Created by stepanyuk on 10.07.2015.
 */
public class Launcher {

    public static void main(String[] args) {
        Stack stack = new Stack();

        stack.push("Pasha");
        stack.push("Yura");
        stack.push("Vitalik");

        System.out.println("//*******************************" +
                "");

        System.out.println("peek " + stack.peek());
        System.out.println("pop " + stack.pop().toString());

        System.out.println("//*******************************" +
                "");


        stack.push("Marina");
        System.out.println("//*******************************" +
                "");

        stack.printStack();

    }


}
