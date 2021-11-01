package com.company.tests;

import com.company.entity.MyArrayList;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyArrayListTest {

    MyArrayList myArrayList;
    String element = "new string 2";
    int elementsCount = 5;

    @BeforeMethod
    public void setUp() {
        myArrayList = new MyArrayList();
        addElements(elementsCount, myArrayList);
    }

    @AfterMethod
    public void tearDown() {
        myArrayList = null;
    }

    @Test
    public void testAdd() {
        boolean isAdded = myArrayList.add("new string");
        String firstAdd = myArrayList.get(elementsCount);
        Assertions.assertThat(firstAdd)
                .isNotEmpty()
                .isEqualTo("new string");
        Assertions.assertThat(isAdded).isTrue();
    }

    @Test
    public void testSize() {
        Assertions.assertThat(myArrayList.size()).isEqualTo(elementsCount);
    }

    @Test
    public void testIsEmpty() {
        Assertions.assertThat(myArrayList.isEmpty()).isFalse();
    }

    @Test
    public void testContains() {
        boolean isContains = myArrayList.contains(element);
        boolean isContains2 = myArrayList.contains("3");
        Assertions.assertThat(isContains).isTrue();
        Assertions.assertThat(isContains2).isFalse();
    }

    @Test
    public void testIterator() {
        myArrayList.add("string 1");
        myArrayList.add("string 3");
        Iterator<String> stringIterator = myArrayList.iterator();

        for (int i = 0; i < elementsCount + 2; i++){
            boolean isExistNext = stringIterator.hasNext();
            Assertions.assertThat(isExistNext).isTrue();

            String nextElement = stringIterator.next();
            Assertions.assertThat(nextElement).isEqualTo(myArrayList.get(i));
        }
    }

    @Test
    public void testRemove() {
        boolean isRemove = myArrayList.remove(element);
        Assertions.assertThat(isRemove).isTrue();
        Assertions.assertThat( myArrayList.size()).isEqualTo(elementsCount - 1);
    }

    @Test
    public void testIndexOf() {
        int index = myArrayList.indexOf("new string 2");
        Assertions.assertThat(index).isEqualTo(0);
    }

    @Test
    public void testLastIndexOf() {
        int index = myArrayList.lastIndexOf("new string 2");
        Assertions.assertThat(index).isEqualTo(elementsCount - 1);
    }

    @Test
    public void testListIteratorNextAndPrev() {
        myArrayList.add("string 1");
        myArrayList.add("string 3");
        ListIterator<String> listIterator = myArrayList.listIterator();

        for (int i = 0; i < elementsCount + 2; i++) {
            boolean isExistNext = listIterator.hasNext();

            if(i == elementsCount + 1){
                Assertions.assertThat(isExistNext).isFalse();
            }else{
                Assertions.assertThat(isExistNext).isTrue();
                int nextIndex = listIterator.nextIndex();
                Assertions.assertThat(nextIndex).isEqualTo(i + 1);
            }

            String nextElement = listIterator.next();
            Assertions.assertThat(nextElement).isEqualTo(myArrayList.get(i));
        }

        for (int i = elementsCount + 1; i > -1; i--) {
            boolean isExistPre = listIterator.hasPrevious();
            if (i == 0) {
                Assertions.assertThat(isExistPre).isFalse();
                continue;
            } else {
                Assertions.assertThat(isExistPre).isTrue();
                int preIndex = listIterator.previousIndex();
                Assertions.assertThat(preIndex).isEqualTo(i - 1);
            }

            String preElement = listIterator.previous();
            Assertions.assertThat(preElement).isEqualTo(myArrayList.get(i - 1));
        }
    }

    @Test
    public void testListIteratorRemoveAndSet() {
        myArrayList.add("string 1");
        myArrayList.add("string 3");
        String lastValueOld = null;
        String lastValueNew;
        ListIterator<String> listIterator = myArrayList.listIterator();

        listIterator.remove();

        for (int i = 0; i < elementsCount + 1; i++) {
            boolean isExistNext = listIterator.hasNext();

            if(i == elementsCount ){
                Assertions.assertThat(isExistNext).isFalse();
            }

            String nextElement = listIterator.next();
            Assertions.assertThat(nextElement).isEqualTo(myArrayList.get(i));
            lastValueOld = myArrayList.get(i);
        }

        listIterator.set("new string 3");
        listIterator.previous();
        lastValueNew = listIterator.next();

        Assertions.assertThat(lastValueOld).isNotEqualTo(lastValueNew);
    }

    @Test
    public void testListIteratorAdd() {
        myArrayList.add("string 1");
        myArrayList.add("string 3");
        ListIterator<String> listIterator = myArrayList.listIterator();

        listIterator.remove();

        for (int i = 0; i < elementsCount + 1; i++) {
            boolean isExistNext = listIterator.hasNext();

            if(i == elementsCount ){
                Assertions.assertThat(isExistNext).isFalse();
            }

            String nextElement = listIterator.next();
            Assertions.assertThat(nextElement).isEqualTo(myArrayList.get(i));
        }
    }

    @Test
    public void testSubList() {
        myArrayList.add("string 1");
        List<String> listElements = myArrayList.subList(2,6);
        Assertions.assertThat(listElements.size()).isEqualTo(4);
        for (int i = 0; i < listElements.size(); i++){
            String f = listElements.get(i);

            if(i != listElements.size() - 1){
                Assertions.assertThat(f).isEqualTo(element);
                continue;
            }

            Assertions.assertThat(f).isEqualTo("string 1");
        }
    }

    @Test
    public void testAddAll() {
        List<String> addAllList = getListAddAll();
        boolean isAdded = myArrayList.addAll(addAllList);
        Assertions.assertThat(isAdded).isTrue();
        Assertions.assertThat(myArrayList.size()).isEqualTo(elementsCount + addAllList.size());

        boolean isContainsAll = myArrayList.containsAll(addAllList);
        Assertions.assertThat(isContainsAll).isTrue();
    }

    @Test
    public void testClear() {
        myArrayList.clear();
        Assertions.assertThat(myArrayList.size()).isEqualTo(0);
    }

    @Test
    public void testGet() {
        String elementGet = myArrayList.get(elementsCount - 1);
        Assertions.assertThat(elementGet).isEqualTo(element);
    }

    @Test
    public void testSet() {
        myArrayList.set(2, "new string 3");
        String elementSet = myArrayList.get(2);
        Assertions.assertThat(elementSet).isEqualTo("new string 3");
    }

    @Test
    public void testRetainAll() {
        myArrayList.add("1");

        boolean isRetainAll = myArrayList.retainAll(getListRetainAll());

        Assertions.assertThat(isRetainAll).isTrue();
    }

    @Test
    public void testRemoveAll() {
        myArrayList.add("addAllList");
        Assertions.assertThat(myArrayList.size()).isEqualTo(elementsCount + 1);

        List<String> removeList = getListRemoveAll();

        boolean isRemoveAll = myArrayList.removeAll(removeList);

        Assertions.assertThat(isRemoveAll).isTrue();
    }

    @Test
    public void testContainsAll() {
        List<String> listContains = getListAddAll();
        myArrayList.addAll(listContains);
        boolean isContains = myArrayList.containsAll(listContains);
        Assertions.assertThat(isContains).isTrue();

        List<String> listNotContains = new MyArrayList();
        listNotContains.add("6");
        listNotContains.add("3");
        boolean isNotContains = myArrayList.containsAll(listNotContains);
        Assertions.assertThat(isNotContains).isFalse();
    }

    private void addElements(int elementsCount, MyArrayList listAdded) {
        for (int i = 0; i < elementsCount; i++){
            listAdded.add(element);
        }
    }

    private List<String> getListAddAll() {
        List<String> addList = new MyArrayList();
        addList.add("1");
        addList.add("2");
        addList.add("3");
        addList.add("4");
        return addList;
    }

    private List<String> getListRemoveAll() {
        List<String> removeList = new MyArrayList();
        removeList.add("new string 2");

        return removeList;
    }

    private List<String> getListRetainAll() {
        List<String> removeList = new MyArrayList();
        removeList.add("new string 2");

        return removeList;
    }
}