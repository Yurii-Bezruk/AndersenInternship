package striker.studing.collections;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private Object[] array;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        if(initialCapacity >= 0)
            array = new Object[initialCapacity];
        else
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }

    public ArrayList(Collection<? extends E> collection) {
        array = collection.toArray();
        size = collection.size();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //TODO
    @Override
    public boolean contains(Object o) {
        return false;
    }

    //TODO
    @Override
    public Iterator<E> iterator() {
        return null;
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, array.length);
    }

    //TODO
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        ensureCapacity(size + 1);
        array[size++] = e;
        return true;
    }

    public void ensureCapacity(int testCapacity) {
        if (testCapacity > array.length){
            int newCapacity = (array.length * 3) / 2 + 1;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
