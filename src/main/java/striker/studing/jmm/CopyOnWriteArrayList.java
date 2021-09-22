package striker.studing.jmm;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CopyOnWriteArrayList<E> implements List<E>, Cloneable {
    private static final int DEFAULT_CAPACITY = 10;
    private final Lock lock = new ReentrantLock();

    private Object[] array;
    private int size;

    public CopyOnWriteArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public CopyOnWriteArrayList(int initialCapacity) {
        if(initialCapacity >= 0)
            array = new Object[initialCapacity];
        else
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
    }

    public CopyOnWriteArrayList(Collection<? extends E> collection) {
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
        return new IteratorImpl(array);
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
        lock.lock();
        ensureCapacity(size + 1);
        array[size++] = e;
        lock.unlock();
        return true;
    }

    public void ensureCapacity(int testCapacity) {
        lock.lock();
        if (testCapacity > array.length){
            int newCapacity = (array.length * 3) / 2 + 1;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        else {
            Object[] newArray = new Object[array.length];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
        }
        lock.unlock();
    }

    @Override
    public boolean remove(Object o) {
        lock.lock();
        int index = indexOf(o);
        if (index == -1) {
            lock.unlock();
            return false;
        }
        remove(index);
        lock.unlock();
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
        lock.lock();
        for (E elem : collection) {
            add(elem);
        }
        lock.unlock();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        lock.lock();
        for (E element : collection) {
            add(index, element);
        }
        lock.unlock();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        lock.lock();
        boolean removed = false;
        for (Object element : collection) {
            if (remove(element)){
                removed = true;
            }
        }
        lock
                .unlock();
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        lock.lock();
        CopyOnWriteArrayList<Integer> indexes = new CopyOnWriteArrayList<>();
        for (Object elem : collection) {
            int index = indexOf(elem);
            if (index != -1){
                indexes.add(index);
            }
        }
        if(indexes.size == 0) {
            lock.unlock();
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (! indexes.contains(i)){
                remove(i);
            }
        }
        lock.unlock();
        return true;
    }

    @Override
    public void clear() {
        lock.lock();
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        lock.unlock();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void trimToSize() {
        lock.lock();
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        lock.lock();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyOnWriteArrayList<?> list = (CopyOnWriteArrayList<?>) o;
        return size == list.size &&
                Arrays.equals(array, list.array);
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
        lock.lock();
        E old = get(index);
        ensureCapacity(size);
        array[index] = element;
        lock.unlock();
        return old;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        lock.lock();
        ensureCapacity(size + 1);
        int numMoved = size - index;
        System.arraycopy(array, index, array, index + 1, numMoved);
        array[index] = element;
        size++;
        lock.unlock();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        lock.lock();
        int numMoved = size - index - 1;
        E element = (E) array[index];
        System.arraycopy(array, index + 1, array, index, numMoved);
        array[--size] = null;
        lock.unlock();
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
        return new ListIteratorImpl(array);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIteratorImpl(index, array);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        CopyOnWriteArrayList<E> subList = new CopyOnWriteArrayList<>(0);
        subList.array = Arrays.copyOfRange(array, fromIndex, toIndex);
        subList.size = subList.array.length;
        return subList;
    }
    private class IteratorImpl implements Iterator<E> {
        Object[] array;
        int cursor;
        boolean operated;
        int operatedIndex;

        public IteratorImpl(Object[] array) {
            this.array = array;
        }

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
            CopyOnWriteArrayList.this.remove(operatedIndex);
        }
    }
    private class ListIteratorImpl extends IteratorImpl implements ListIterator<E> {
        public ListIteratorImpl(Object[] array) {
            this(0, array);
        }

        public ListIteratorImpl(int index, Object[] array) {
            super(array);
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
            CopyOnWriteArrayList.this.set(operatedIndex, e);
        }

        @Override
        public void add(E e) {
            if(! operated)
                throw new IllegalStateException();
            operated = false;
            CopyOnWriteArrayList.this.add(operatedIndex, e);
        }
    }
}
