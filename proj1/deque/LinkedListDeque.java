package deque;

import java.util.Iterator;
public class LinkedListDeque<T>implements Deque<T>, Iterable<T> {

    class Node {
        private Node() {
            value=null;
            prev=next=null;
        }

        public Node(Node prev, Node next, T value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }

        private Node prev;
        private Node next;
        private T value;
        private String v;
    }

    private Node sentinel;

    private int quesize=0;
    public LinkedListDeque() {
        sentinel = new Node();
        sentinel.prev=sentinel.next=sentinel;
    }
    public int size(){
        return quesize;
    }

    public boolean isEmpty(){
        return size()==0;
    }

    public void addFirst(T item){
        Node itemNode =new Node(sentinel,sentinel.next,item);//创建一个新的节点值
        sentinel.next.prev = itemNode;
        sentinel.next = itemNode;
        quesize++;
    }
    public void addLast(T item) {

        Node itemNode = new Node(sentinel.prev,sentinel,item);
        sentinel.prev.next=itemNode;
        sentinel.prev=itemNode;
        quesize++;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node p, int index) {
        if (p == sentinel) {
            return null;
        }
        if (index == 0) {
            return (T) p.value;
        }
        return getRecursiveHelper(p.next, index - 1);
    }



    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty()) {
            return null;
        }
        Node tmp = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

       quesize = (quesize == 0) ? quesize : quesize - 1;
        return (T) tmp.value;

    }

    public T removeLast(){

        if(isEmpty()){
            return null;
        }
        Node tmp = sentinel.prev;
        sentinel.prev=sentinel.prev.prev;
        sentinel.prev.next = sentinel;

        quesize = (quesize == 0) ? quesize : quesize - 1;
        return (T) tmp.value;
    }

    public T get(int index) {

        if (index < 0 || index > quesize - 1) {
            return null;
        }

        Node lastNode = sentinel.next;

        while (index > 0) {
            lastNode = lastNode.next;
            index--;
        }

        return lastNode.value;
    }
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node ptr;
        LinkedListDequeIterator() {
            ptr = sentinel.next;
        }
        public boolean hasNext() {
            return (ptr != sentinel);
        }
        public T next() {
            T item = (T) ptr.value;
            ptr = ptr.next;
            return item;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<?> lld = (LinkedListDeque<?>) o;
        if (lld.size() != quesize) {
            return false;
        }
        for (int i = 0; i < quesize; i++) {
            if (lld.get(i) != get(i)) {
                return false;
            }
        }
        return true;
    }

}
