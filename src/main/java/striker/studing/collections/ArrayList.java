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

    @Override
    public boolean contains(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o))
                return true;
        }
        return false;
    }

    //TODO
    @Override
    public Iterator<E> iterator() {
        return null;
    }


    @Override
    public Object[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] arr) {
        if(arr.length >= size){
            System.arraycopy(array, 0, arr, 0, size);
            return arr;
        }
        return (T[]) Arrays.copyOf(array, size);
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
        int index = indexOf(o);
        if (index == -1)
            return false;
        remove(index);
        return true;
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
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if(index >= size){
            throw new ArrayIndexOutOfBoundsException();
        }
        int numMoved = size - index - 1;
        E element = (E) array[index];
        System.arraycopy(array, index + 1, array, index, numMoved);
        array[--size] = null;
        return element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(o))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(o))
                return i;
        }
        return -1;
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
