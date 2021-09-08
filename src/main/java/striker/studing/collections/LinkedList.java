package striker.studing.collections;

import java.util.*;

public class LinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (E elem : this) {
            if (elem.equals(o)){
                return true;
            }
        }
        return false;
    }
    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
    }
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Iterator<E> iterator = iterator();
        for (int i = 0; i < size; i++) {
            arr[i] = iterator.next();
        }
        return arr;
    }
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] arr) {
        if(arr.length < size){
            arr = (T[]) new Object[size];
        }
        Iterator<E> iterator = iterator();
        for (int i = 0; i < size; i++) {
            arr[i] = (T) iterator.next();
        }
        return arr;
    }

    @Override
    public boolean add(E e) {
        if(head == null){
            head = new Node<>(e, null, null);
            size++;
        }
        else if(size == 1){
            tail = new Node<>(e, head, head);
            size++;
        }
        else {
            Node<E> newNode = new Node<>(e, tail, head);
            head.previous = newNode;
            tail.next = newNode;
            tail = newNode;
            size++;
        }
        return true;
    }
    @Override
    public boolean remove(Object o) {
        Node<E> cursor = head;
        for (int i = 0; i < size; i++) {
            if(cursor.data.equals(o)){
                removeNode(cursor);
                return true;
            }
            cursor = cursor.next;
        }
        return false;
    }
    private E removeNode(Node<E> node){
        Node<E> prev = node.previous;
        Node<E> next = node.next;
        prev.next = next;
        next.previous = prev;
        node.previous = null;
        node.next = null;
        size--;
        return node.data;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if(! contains(o)){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E o : c) {
            add(o);
        }
        return true;
    }
    private Node<E> getNodeByIndex(int index){
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node<E> cursor = head;
        for (int i = 0; i <= index; i++) {
            cursor = cursor.next;
        }
        return cursor;
    }
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        Node<E> onIndex = getNodeByIndex(index);
        for (E elem : c) {
            add(onIndex, elem);
            onIndex = onIndex.next;
        }
        return true;
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;
        for (Object elem : c) {
            if(remove(elem)){
                removed = true;
            }
        }
        return removed;
    }
    @Override
    @SuppressWarnings("unchecked")
    public boolean retainAll(Collection<?> collection) {
        LinkedList<Object> containedElements = new LinkedList<>();
        for (Object elem : collection) {
            Node<E> node = findNode(elem);
            if (node != null){
                containedElements.add(elem);
            }
        }
        if(containedElements.size == 0)
            return false;
        clear();
        for (Object element : containedElements) {
            add((E) element);
        }
        return true;
    }
    @Override
    public void clear() {
        head = null;
        tail = null;
    }
    @Override
    public E get(int index) {
        return getNodeByIndex(index).data;
    }
    @Override
    public E set(int index, E element) {
        Node<E> onIndex = getNodeByIndex(index);
        E oldData = onIndex.data;
        onIndex.data = element;
        return oldData;
    }
    @Override
    public void add(int index, E element) {
        add(getNodeByIndex(index), element);
    }
    private void add(Node<E> onIndex, E element) {
        Node<E> previous = onIndex.previous;
        Node<E> newNode = new Node<>(element, previous, onIndex);
        previous.next = newNode;
        onIndex.previous = newNode;
        size++;
    }
    @Override
    public E remove(int index) {
        return removeNode(getNodeByIndex(index));
    }
    @Override
    public int indexOf(Object o) {
        Node<E> cursor = head;
        for (int i = 0; i < size; i++) {
            if(cursor.data.equals(o)){
                return i;
            }
            cursor = cursor.next;
        }
        return -1;
    }
    private Node<E> findNode(Object o) {
        Node<E> cursor = head;
        for (int i = 0; i < size; i++) {
            if(cursor.data.equals(o)){
                return cursor;
            }
            cursor = cursor.next;
        }
        return null;
    }
    @Override
    public int lastIndexOf(Object o) {
        Node<E> cursor = tail;
        for (int i = size - 1; i >= 0; i--) {
            if(cursor.data.equals(o)){
                return i;
            }
            cursor = cursor.previous;
        }
        return -1;
    }
    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl();
    }
    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIteratorImpl(index);
    }
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        int count = toIndex - fromIndex;
        Node<E> head = getNodeByIndex(fromIndex);
        Node<E> cursor = head;
        for (int i = 0; i < toIndex; i++) {
            cursor = cursor.next;
        }
        head.previous = cursor;
        cursor.next = head;
        LinkedList<E> subList = new LinkedList<>();
        subList.head = head;
        subList.tail = cursor;
        subList.size = count;
        return subList;
    }
    static class Node<E> {
        E data;
        Node<E> next;
        Node<E> previous;

        public Node(E data, Node<E> next, Node<E> previous) {
            this.data = data;
            this.next = next;
            this.previous = previous;
        }
    }
    class IteratorImpl implements Iterator<E> {
        Node<E> cursor = head;
        int cursorIndex;
        boolean operated = true;
        Node<E> operatedNode;

        @Override
        public boolean hasNext() {
            return cursorIndex < size;
        }

        @Override
        public E next() {
            if(cursorIndex >= size)
                throw new IndexOutOfBoundsException();
            operated = true;
            operatedNode = cursor;
            cursor = cursor.next;
            cursorIndex++;
            return cursor.data;
        }
        @Override
        public void remove() {
            if(! operated)
                throw new IllegalStateException();
            LinkedList.this.removeNode(operatedNode);
        }
    }
    class ListIteratorImpl extends IteratorImpl implements ListIterator<E> {

        public ListIteratorImpl() {

        }
        public ListIteratorImpl(int index) {
            cursor = getNodeByIndex(index);
            cursorIndex = index;
        }


        @Override
        public boolean hasPrevious() {
            return cursorIndex >= 0;
        }

        @Override
        public E previous() {
            if(cursorIndex < 0)
                throw new IndexOutOfBoundsException();
            operated = true;
            operatedNode = cursor;
            cursor = cursor.previous;
            cursorIndex--;
            return cursor.data;
        }

        @Override
        public int nextIndex() {
            return cursorIndex;
        }

        @Override
        public int previousIndex() {
            return cursorIndex;
        }

        @Override
        public void set(E e) {
            if(! operated)
                throw new IllegalStateException();
            operated = false;
            operatedNode.data = e;
        }

        @Override
        public void add(E e) {
            if(! operated)
                throw new IllegalStateException();
            operated = false;
            LinkedList.this.add(cursorIndex - 1, e);
        }
    }
}
