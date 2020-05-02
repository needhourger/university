class Node<T extends Object>{
    protected T value;
    protected  Node next;

    public Node(){
        this.next=null;
    }

    public Node(T t){
        this.next=null;
        this.value=t;
    }
}

public class LinkedList<T extends Object>{
    private Node<T> head;
    private int size;

    public LinkedList(){
        this.head=null;
        this.size=0;
    }

    public void addFirst(T t){
        Node<T> newNode=new Node<T>(t);
        newNode.next=this.head;
        this.head=newNode;
        this.size++;
    }

    public void addLast(T t){
        Node<T> newNode=new Node<T>(t);

        if (this.head==null){
            this.head=newNode;
        }else {
            Node<T> p = this.head;
            while (p.next != null) p = p.next;
            p.next = newNode;
        }
        this.size++;
    }

    public void insert(T t,int index){
        Node<T> newNode=new Node<T>(t);

        Node<T> p=this.head;
        int i=0;
        while (p.next!=null && i<index-1) {
            i++;
            p=p.next;
        }
        if (i>index-1) System.out.println("index out of range");
        else {
            Node<T> p1=p.next;
            p.next=newNode;
            newNode.next=p1;
            this.size++;
        }
    }

    public void removeFirst(){
        if (this.head!=null){
            this.head=this.head.next;
            this.size--;
        }
        else System.out.println("this linked list is empty");
    }

    public void removeLast(){
        if (this.head!=null){
            Node<T> p=this.head;
            while (p.next.next!=null) p=p.next;
            p.next=null;
            this.size--;
        }
        else System.out.println("the linked list is empty");
    }

    public void remove(T t){
        if (this.head==null){
            System.out.println("this linked list is empty");
            return;
        }
        Node<T> pre=this.head;
        Node<T> p=this.head.next;
        if (pre.value==t){
            this.head=p;
            this.size--;
            return;
        }else {
            while (p.value != t && p.next != null) {
                pre = p;
                p = p.next;
            }
            if (p.value == t) {
                pre.next = p.next;
                this.size--;
                return;
            }
        }
    }

    public boolean contains(T t){
        Node<T> p=this.head;
        if (this.head==null) {
            return false;
        }
        while (p.next!=null){
            if (p.value==t) return true;
            p=p.next;
        }
        return false;
    }

    public int getSize(){
        return this.size;
    }

    public void out(){
        Node<T> p=this.head;
        if (this.head==null){
            System.out.println("the linked list is empty");
            return;
        }
        while (p.next!=null){
            System.out.print(p.value+" ");
            p=p.next;
        }

        System.out.println(p.value);
    }
}