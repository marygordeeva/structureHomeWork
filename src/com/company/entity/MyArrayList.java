package com.company.entity;

import java.util.*;

public class MyArrayList<E> implements List<E> {

    private int size = 0;
    private int DEFAULT_CAPACITY = 16;
    E[] elements;

    public MyArrayList(int capacity) {
        this.elements =(E[]) new Object[capacity];
    }

    public MyArrayList() {
        this.elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < size(); i++) {
            out += " " + elements[i];
        }
        return out;
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
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] == null) {
                break;
            }
            if (elements[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public Object next() {
                Object element = elements[cursor];
                cursor++;
                return element;
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, elements.length);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(elements, elements.length);
    }

    @Override
    public boolean add(E element) {
        ++size;
        if (size >= this.elements.length) {
            elements = grow();
        }

        elements[size - 1] = element;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (this.isEmpty()) {
            return false;
        }

        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        System.arraycopy(elements,
                index + 1,
                elements,
                index,
                size - index);

        size = size - 1;

        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        size++;
        int length = this.elements.length;

        if (size >= length) {
            elements = grow();
        }

        System.arraycopy(elements,
                index,
                elements,
                index + 1,
                size - index);
        elements[index] = element;
    }

    @Override
    public E remove(int index) {

        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        Object elementDelete = elements[index];
        System.arraycopy(elements,
                index + 1,
                elements,
                index,
                size - index);

        size = size - 1;
        return (E) elementDelete;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(o)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = size - 1; i > -1; i--) {
            if (elements[i].equals(o)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<>() {
            int cursor = 0;
            int sizeItr = size;
            Object[] elementsItr = elements;

            @Override
            public boolean hasNext() {
                return cursor != sizeItr;
            }

            @Override
            public E next() {
                cursor++;
                return (E) elementsItr[cursor - 1];
            }

            @Override
            public boolean hasPrevious() {
                return cursor - 1 > 0;
            }

            @Override
            public E previous() {
                cursor--;
                return (E) elementsItr[cursor];
            }

            @Override
            public int nextIndex() {
                if (cursor++ >= sizeItr) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                return cursor;
            }

            @Override
            public int previousIndex() {
                if (cursor-- < 0) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                return cursor;
            }

            @Override
            public void remove() {
                System.arraycopy(elementsItr, cursor, elementsItr, cursor - 1, sizeItr - cursor - 1);
                sizeItr--;
            }

            @Override
            public void set(Object s) {
                elementsItr[cursor] = s;
            }

            @Override
            public void add(Object s) {
                elementsItr[sizeItr] = s;
                sizeItr++;
            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return new ListIterator<>() {
            int cursor = index;
            int sizeItr = size;
            Object[] elementsItr = elements;

            @Override
            public boolean hasNext() {
                return cursor != sizeItr;
            }

            @Override
            public E next() {
                cursor++;
                return (E) elementsItr[cursor - 1];
            }

            @Override
            public boolean hasPrevious() {
                return cursor - 1 > 0;
            }

            @Override
            public E previous() {
                cursor--;
                return (E) elementsItr[cursor];
            }

            @Override
            public int nextIndex() {
                if (cursor++ >= sizeItr) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                return cursor;
            }

            @Override
            public int previousIndex() {
                if (cursor-- < 0) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                return cursor;
            }

            @Override
            public void remove() {
                System.arraycopy(elementsItr, cursor, elementsItr, cursor - 1, sizeItr - cursor - 1);
                sizeItr--;
            }

            @Override
            public void set(Object o) {
                elementsItr[cursor] = o;
            }

            @Override
            public void add(Object o) {
                elementsItr[sizeItr] = o;
                sizeItr++;
            }
        };
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        Object[] massive = new Object[fromIndex - toIndex];
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        if (fromIndex == toIndex) {
            return (List<E>) Arrays.asList(massive);
        }

        for (int i = 0; i < massive.length; i++) {
            massive[i] = elements[fromIndex];
            fromIndex++;
        }

        return (List<E>) Arrays.asList(massive);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return true;
        }

        if (size + c.size() >= this.elements.length) {
            grow(size + c.size() + 1);
        }

        System.arraycopy(c,
                0,
                elements,
                size,
                c.size());
        size = size + c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) {
            return true;
        }

        if (size + c.size() >= this.elements.length) {
            grow(size + c.size() + 1);
        }

        System.arraycopy(elements,
                index,
                elements,
                index + c.size(),
                size - index);

        System.arraycopy(c,
                0,
                elements,
                index,
                c.size());

        size = size + c.size();
        return true;
    }

    @Override
    public void clear() {
        if (this.isEmpty()) {
            return;
        }

        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        return (E) elements[index];
    }

    @Override
    public E set(int index, E element) {
        elements[index] = element;
        return element;
    }

    @Override
    public boolean retainAll(Collection c) {
        if (c.size() == 0) {
            return false;
        }

        Object[] arrRemove = c.toArray();
        MyArrayList<E> newArList = new MyArrayList<E>();
        for (int j = 0; j < size; j++) {
            boolean isFound = false;
            for (int i = 0; i < arrRemove.length; i++) {
                if (arrRemove[i] == elements[j]) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                newArList.add((E) elements[j]);
            }
        }

        elements = newArList.elements;
        size = newArList.size;

        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        Object[] arrRemove = c.toArray();
        for (int i = 0; i < c.size(); i++) {
            remove(arrRemove[i]);
        }

        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        Object[] arr = c.toArray();
        for (Object o : arr) {
            if (o == null) {
                break;
            }
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    private E[] grow() {
        int newSize = getNewSize();
        return (E[]) Arrays.copyOf(elements, newSize);
    }

    private Object[] grow(int newSize) {
        return Arrays.copyOf(elements, newSize);
    }

    private int getNewSize() {
        return size + size / 2;
    }
}
