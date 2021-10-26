package com.company.entity;

import java.util.*;

public class MyArrayList implements List<String> {

    private int size = 0;
    private int DEFAULT_CAPACITY = 16;
    String[] elements;

    public MyArrayList(int capacity) {
        this.elements = new String[capacity];
    }

    public MyArrayList() {
        this.elements = new String[DEFAULT_CAPACITY];
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
    public Iterator<String> iterator() {
        return new Iterator() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public Object next() {
                String element = elements[cursor];
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
    public boolean add(String str) {
        ++size;
        if (size >= this.elements.length) {
            elements = grow();
        }

        elements[size - 1] = str;
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
    public void add(int index, String str) {
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
        elements[index] = str;
    }

    @Override
    public String remove(int index) {

        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        String elementDelete = elements[index];
        System.arraycopy(elements,
                index + 1,
                elements,
                index,
                size - index);

        size = size - 1;
        return elementDelete;
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
    public ListIterator<String> listIterator() {
        return new ListIterator<>() {
            int cursor = 0;
            int sizeItr = size;
            String[] elementsItr = elements;

            @Override
            public boolean hasNext() {
                return cursor != sizeItr;
            }

            @Override
            public String next() {
                cursor++;
                return elementsItr[cursor - 1];
            }

            @Override
            public boolean hasPrevious() {
                return cursor - 1 > 0;
            }

            @Override
            public String previous() {
                cursor--;
                return elementsItr[cursor];
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
            public void set(String s) {
                elementsItr[cursor] = s;
            }

            @Override
            public void add(String s) {
                elementsItr[sizeItr] = s;
                sizeItr++;
            }
        };
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return new ListIterator<>() {
            int cursor = index;
            int sizeItr = size;
            String[] elementsItr = elements;

            @Override
            public boolean hasNext() {
                return cursor != sizeItr;
            }

            @Override
            public String next() {
                cursor++;
                return elementsItr[cursor - 1];
            }

            @Override
            public boolean hasPrevious() {
                return cursor - 1 > 0;
            }

            @Override
            public String previous() {
                cursor--;
                return elementsItr[cursor];
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
            public void set(String s) {
                elementsItr[cursor] = s;
            }

            @Override
            public void add(String s) {
                elementsItr[sizeItr] = s;
                sizeItr++;
            }
        };
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        String[] massive = new String[fromIndex - toIndex];
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException();
        }
        if (fromIndex == toIndex) {
            return Arrays.asList(massive);
        }

        for (int i = 0; i < massive.length; i++) {
            massive[i] = elements[fromIndex];
            fromIndex++;
        }

        return Arrays.asList(massive);
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        if (c.isEmpty()) {
            return true;
        }

        if (size + c.size() >= this.elements.length) {
            grow(size + c.size() + 1);
        }

        String[] arr2 = (String[]) c.toArray();
        System.arraycopy(arr2,
                0,
                elements,
                size,
                c.size());
        size = size + c.size();
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        if (c.isEmpty()) {
            return true;
        }

        if (size + c.size() >= this.elements.length) {
            grow(size + c.size() + 1);
        }

        String[] arr2 = (String[]) c.toArray();

        System.arraycopy(elements,
                index,
                elements,
                index + c.size(),
                size - index);

        System.arraycopy(arr2,
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
    public String get(int index) {
        return elements[index];
    }

    @Override
    public String set(int index, String element) {
        elements[index] = element;
        return element;
    }

    @Override
    public boolean retainAll(Collection c) {
        if (c.size() == 0) {
            return false;
        }

        String[] arrRemove = (String[]) c.toArray();
        MyArrayList newArList = new MyArrayList();
        for (int j = 0; j < size; j++) {
            boolean isFound = false;
            for (int i = 0; i < arrRemove.length; i++) {
                if (arrRemove[i] == elements[j]) {
                    isFound = true;
                    break;
                }
            }
            if (isFound) {
                newArList.add(elements[j]);
            }
        }

        elements = newArList.elements;
        size = newArList.size;

        return true;
    }

    @Override
    public boolean removeAll(Collection c) {
        String[] arrRemove = (String[]) c.toArray();
        for (int i = 0; i < c.size(); i++) {
            remove(arrRemove[i]);
        }

        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        String[] arr = (String[]) c.toArray();
        for (String str : arr) {
            if (str == null) {
                break;
            }
            if (!this.contains(str)) {
                return false;
            }
        }
        return true;
    }

    private String[] grow() {
        int newSize = getNewSize();
        return Arrays.copyOf(elements, newSize);
    }

    private String[] grow(int newSize) {
        return Arrays.copyOf(elements, newSize);
    }

    private int getNewSize() {
        return size + size / 2;
    }
}
