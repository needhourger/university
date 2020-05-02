import java.util.Arrays;
import java.util.Scanner;


public class Part1 {
    public static void main(String[] args) {
        LinkedList<Integer> list=new LinkedList<Integer>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        System.out.println("LinkedList init");


        list.addFirst(4);
        System.out.println("After addFirst 4");
        list.out();

        list.addLast(5);
        System.out.println("After addLast 5");
        list.out();

        list.insert(6,3);
        System.out.println("After add 6 at index of 3");
        list.out();

        list.removeFirst();
        System.out.println("After remove first element");
        list.out();

        list.removeLast();
        System.out.println("After remove last element");
        list.out();

        list.remove((Integer)1);
        System.out.println("After remove integer 1");

        System.out.println("if linkedlist contains 5:"+list.contains(5));
        System.out.println("now the linkedlist size:"+list.getSize());

    }
}
