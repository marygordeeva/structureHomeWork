package com.company.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class MyArrayList implements Collection<String>{

    private int cursor = 0;
    private int size = 0;
    private int defaultCapacity = 16;
    String[] elements;

    public MyArrayList(int capacity) {
        this.elements = new String[capacity];
    }

    public MyArrayList() {
        this.elements = new String[defaultCapacity];
    }

    @Override
    public String toString(){
        String out = "";
        for(int i = 0; i< size(); i++){
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
        return size ==0;
    }

    @Override
    public boolean contains(Object o) {
        for (int i=0; i < elements.length;i++ ){
            if(elements[i].equals(o)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public Object next() {
                if(cursor >= size){
                    return null;
                }
                cursor++;
                return elements[cursor-1];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) elements;
    }

    @Override
    public boolean add(String str) {
        if (size + 1 >= this.elements.length) {
            elements = grow();
            addInternal(str);
            return true;
        }

        addInternal(str);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(this.isEmpty()){
            return false;
        }

        boolean isExist = false;
        int index = 0;
        for (int i=0; i < elements.length;i++ ){
            if(elements[i] == o){
                isExist = true;
                index = i;
                break;
            }
        }

        if(!isExist){
            return false;
        }

        System.arraycopy(elements,
                index + 1,
                elements,
                index,
                size -1);
        size = size - 1;

        return true;
    }

    public boolean add(int index, String str){
        if(index > size || index < 0){
            throw new ArrayIndexOutOfBoundsException();
        }

        if(size + 1 >= this.elements.length){
            elements = grow();
            System.arraycopy(elements,
                    index,
                    elements,
                    index+1,
                    size - index);
           elements[index] = str;
           size++;
            return true;
        }

        System.arraycopy(elements,
                index,
                elements,
                index+1,
                size - index);
        elements[index] = str;
        size++;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        if(c.isEmpty()){
            return true;
        }

        if(size + c.size() >= this.elements.length){
            grow(size + c.size() +1);
           String[] arr2 = (String[]) c.toArray();
            System.arraycopy(arr2,
                    0,
                    elements,
                    size,
                    c.size());
           return true;
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
    public void clear() {
        if(this.isEmpty()){
            return;
        }

        for (int i = 0; i < size; i++){
            elements[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean retainAll(Collection c) {
        if(c.size() == 0){
            return false;
        }

        String[] arrRemove = (String[]) c.toArray();
        MyArrayList newArList = new MyArrayList();
        for(int j = 0; j < size; j++) {
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
        for(String str:arr){
            if(!this.contains(str)){
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

    private void addInternal(String str) {
        elements[size] = str;
        size++;
    }
}
