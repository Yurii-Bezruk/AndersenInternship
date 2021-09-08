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


    @Override
    public Iterator<E> iterator() {
        return new IteratorImpl();
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
        for (Object o : c){
            if (! contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E elem : collection) {
            add(elem);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        for (E element : collection) {
            add(index, element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean removed = false;
        for (Object element : collection) {
            if (remove(element)){
                removed = true;
            }
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (Object elem : collection) {
            int index = indexOf(elem);
            if (index != -1){
                indexes.add(index);
            }
        }
        if(indexes.size == 0)
            return false;
        for (int i = 0; i < size; i++) {
            if (! indexes.contains(i)){
                remove(i);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        return (E) array[index];
    }

    @Override
    public E set(int index, E element) {
        E old = get(index);
        array[index] = element;
        return old;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        ensureCapacity(size + 1);
        int numMoved = size - index;
        System.arraycopy(array, index, array, index + 1, numMoved);
        array[index] = element;
        size++;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
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
        return new ListIteratorImpl();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIteratorImpl(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        ArrayList<E> subList = new ArrayList<>(0);
        subList.array = Arrays.copyOfRange(array, fromIndex, toIndex);
        subList.size = subList.array.length;
        return subList;
    }
    private class IteratorImpl implements Iterator<E> {
        int cursor;
        boolean operated;
        int operatedIndex;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if(cursor >= size)
                throw new IndexOutOfBoundsException();
            operated = true;
            operatedIndex = cursor;
            return (E) array[cursor++];
        }

        @Override
        public void remove() {
            if(! operated)
                throw new IllegalStateException();
            operated = false;
            ArrayList.this.remove(operatedIndex);
        }
    }
    private class ListIteratorImpl extends IteratorImpl implements ListIterator<E> {
        public ListIteratorImpl() {
            this(0);
        }

        public ListIteratorImpl(int index) {
            if(index < 0 || index > size)
                throw new IndexOutOfBoundsException();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor >= 0;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E previous() {
            if(cursor < 0 || cursor == size)
                throw new IndexOutOfBoundsException();
            operated = true;
            operatedIndex = cursor;
            return (E) array[cursor--];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor;
        }

        @Override
        public void set(E e) {
            if(! operated)
                throw new IllegalStateException();
            operated = false;
            ArrayList.this.set(operatedIndex, e);
        }

        @Override
        public void add(E e) {
            if(! operated)
                throw new IllegalStateException();
            operated = false;
            ArrayList.this.add(operatedIndex, e);
        }
    }
}
