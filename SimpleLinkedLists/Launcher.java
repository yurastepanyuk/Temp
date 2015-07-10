package day7.SimpleLinkedLists;

import java.util.Iterator;

/**
 * Created by stepanyuk on 02.07.2015.
 */
public class Launcher {

    public static void main(String[] args) {
        System.err.println("Test all add methods");
        System.out.println("");

        SimpleLinkedList sll = new SimpleLinkedList();
        sll.addFirst("test 1");
        sll.addFirst("test 2");
        sll.addFirst("test 3");

        System.out.print(sll.getSize() + " ");
        sll.printList();

        String mark = new String("mark");
        sll.addLast(mark);
        sll.addLast("test 0");
        System.out.print(sll.getSize() + " ");
        sll.printList();

        String middle = new String("middle");
        sll.addAfter(middle, mark);
        System.out.print(sll.getSize() + " ");
        sll.printList();

        sll.addAfter("middle (-1)", mark);
        System.out.print(sll.getSize() + " ");
        sll.printList();

        System.out.println();
        System.err.println("Test add after, list size == 1");
        System.out.println();

        sll = new SimpleLinkedList();
        sll.addLast(mark);
        System.out.print(sll.getSize() + " ");
        sll.printList();

//        sll.addAfter("last",mark);
//        System.out.print(sll.getSize() + " ");
//        sll.printList();

        System.out.println("");
        System.err.println("Iterator");
        sll = new SimpleLinkedList();
        sll.addFirst("t 1");
        sll.addFirst("t 2");
        sll.addFirst("t 3");

//        for (Object obj : sll) {
//            String strSll = (String)obj;
//            System.out.println(strSll);
//        }

        for (Iterator<Object> it = sll.iterator(); it.hasNext();) {
            Object o = it.next();
            System.out.println(o);
        }

        System.out.println("");
        System.err.println("Remove");
        sll = new SimpleLinkedList();
        sll.addFirst("t 1");
        sll.addFirst("t 2");
        sll.addFirst("t 3");

        for (Iterator<Object> it = sll.iterator(); it.hasNext();) {
            it.next();
            it.remove();
            break;
        }
        sll.printList();

    }




}
