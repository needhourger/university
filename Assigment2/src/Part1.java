import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Part1 {
    public static void out(LinkedList<Integer> list){
        for (Integer i:list) System.out.print(i+" ");
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList<Integer> list=new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("LinkedList init");
        out(list);

        list.addFirst(4);
        System.out.println("After addFist 4");
        out(list);

        list.addLast(5);
        System.out.println("After addLast 5");
        out(list);

        list.add(3,6);
        System.out.println("After add 6 at index of 3");
        out(list);

        list.removeFirst();
        System.out.println("After remove first element");
        out(list);

        list.removeLast();
        System.out.println("After remove last element");
        out(list);

        list.remove((Integer)1);
        System.out.println("After remove integer 1");
        out(list);

        list.remove(1);
        System.out.println("After remove element at index of 1");
        out(list);

        System.out.println("if linkedlist contains 5:"+list.contains(5));
        System.out.println("now the linkedlist size:"+list.size());

    }
}
