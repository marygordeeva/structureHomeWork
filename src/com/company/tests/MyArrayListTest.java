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
        element = null;
    }

    @Test
    public void add_newString_returnTrue() {

        boolean isAdded = myArrayList.add("new string");
        String firstAdd = myArrayList.get(elementsCount);

        Assertions.assertThat(firstAdd)
                .isNotEmpty()
                .isEqualTo("new string");
        Assertions.assertThat(isAdded).isTrue();
    }

    @Test
    public void size_returnFive() {
        Assertions.assertThat(myArrayList.size()).isEqualTo(elementsCount);
    }

    @Test
    public void isEmpty_arrayListNotEmpty_returnFalse() {
        Assertions.assertThat(myArrayList.isEmpty()).isFalse();
    }

    @Test
    public void isEmpty_arrayListEmpty_returnTrue() {
        MyArrayList myArList = new MyArrayList();

        Assertions.assertThat(myArList.isEmpty()).isTrue();
    }

    @Test
    public void contains_elementString_returnTrue () {
        boolean isContains = myArrayList.contains(element);

        Assertions.assertThat(isContains).isTrue();
    }

    @Test
    public void contains_elementString_returnFalse () {
        boolean isContains2 = myArrayList.contains("3");

        Assertions.assertThat(isContains2).isFalse();
    }

    @Test
    public void iterator_arrayList_returnTrue() {
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
    public void remove_elementString_returnTrue() {
        boolean isRemove = myArrayList.remove(element);

        Assertions.assertThat(isRemove).isTrue();
        Assertions.assertThat( myArrayList.size()).isEqualTo(elementsCount - 1);
    }

    @Test
    public void remove_anotherElement_returnFalse() {
        boolean isRemove = myArrayList.remove("3");

        Assertions.assertThat(isRemove).isFalse();
    }

    @Test
    public void indexOf_elementString_returnIndex0() {
        int index = myArrayList.indexOf("new string 2");

        Assertions.assertThat(index).isEqualTo(0);
    }

    @Test
    public void indexOf_elementString_returnIndexNegativ() {
        int index = myArrayList.indexOf("new");

        Assertions.assertThat(index).isEqualTo(-1);
    }

    @Test
    public void lastIndexOf_elementString_returnIndex() {
        int index = myArrayList.lastIndexOf("new string 2");

        Assertions.assertThat(index).isEqualTo(elementsCount - 1);
    }

    @Test
    public void listIteratorNext_returnNextElement() {
        ListIterator<String> listIterator = myArrayList.listIterator();

        for (int i = 0; i < elementsCount; i++) {
            boolean isExistNext = listIterator.hasNext();

            if(i == elementsCount - 1){
                Assertions.assertThat(isExistNext).isFalse();
            }else{
                Assertions.assertThat(isExistNext).isTrue();
                int nextIndex = listIterator.nextIndex();
                Assertions.assertThat(nextIndex).isEqualTo(i + 1);
            }

            String nextElement = listIterator.next();
            Assertions.assertThat(nextElement).isEqualTo(myArrayList.get(i));
        }
    }

    @Test
    public void listIteratorPrev_returnPrevElement(){
        ListIterator<String> listIterator = myArrayList.listIterator();

        while(listIterator.hasNext()){
            listIterator.next();
        }

        for (int i = elementsCount - 1; i > -1; i--) {
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
    public void listIteratorRemove() {
        ListIterator<String> listIterator = myArrayList.listIterator();

        listIterator.remove();

        for (int i = 0; i < elementsCount - 1; i++) {
            boolean isExistNext = listIterator.hasNext();

            if(i == elementsCount ){
                Assertions.assertThat(isExistNext).isFalse();
            }

            String nextElement = listIterator.next();
            Assertions.assertThat(nextElement).isEqualTo(myArrayList.get(i));
        }
    }

    @Test
    public void listIteratorSet_elementStringNew() {
        ListIterator<String> listIterator = myArrayList.listIterator();
        listIterator.next();
        String oldValue = listIterator.previous();

        listIterator.set("new string 3");

        listIterator.next();
        String newSetString = listIterator.previous();

        Assertions.assertThat(oldValue).isNotEqualTo(newSetString);
    }

    @Test
    public void listIteratorAdd_newValue() {
        String nextElement;
        ListIterator<String> listIterator = myArrayList.listIterator();

        listIterator.add("newStringIterator");

        for (int i = 0; i < elementsCount + 1; i++) {
            boolean isExistNext = listIterator.hasNext();

            if(isExistNext){
                listIterator.next();
                continue;
            }

            nextElement = listIterator.next();
            Assertions.assertThat(nextElement).isEqualTo("newStringIterator");
        }
    }

    @Test
    public void subList_forIndex2And6_returnList() {
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
    public void addAll_newList_returnTrue() {
        List<String> addAllList = getListAddAll();

        boolean isAdded = myArrayList.addAll(addAllList);

        Assertions.assertThat(isAdded).isTrue();
        Assertions.assertThat(myArrayList.size()).isEqualTo(elementsCount + addAllList.size());
        boolean isContainsAll = myArrayList.containsAll(addAllList);
        Assertions.assertThat(isContainsAll).isTrue();
    }

    @Test
    public void clear_arrayList() {
        myArrayList.clear();

        Assertions.assertThat(myArrayList.size()).isEqualTo(0);
    }

    @Test
    public void get_lastIndex_returnLastValue() {
        String elementGet = myArrayList.get(elementsCount - 1);

        Assertions.assertThat(elementGet).isEqualTo(element);
    }

    @Test
    public void set_stringAndIndex2_returnUpdateValue() {
        String elementSet = myArrayList.set(2, "new string 3");

        Assertions.assertThat(elementSet).isEqualTo("new string 3");
    }

    @Test
    public void set_stringAndIndexOut_returnThrow() {
        Assertions.assertThatThrownBy(() -> myArrayList.set(10, "new string 3"))
                .hasMessageStartingWith("Meaning");
    }

    @Test
    public void retainAll_list_returnTrue() {
        myArrayList.add("1");

        boolean isRetainAll = myArrayList.retainAll(getListRemoveAll());

        Assertions.assertThat(isRetainAll).isTrue();
        Assertions.assertThat(myArrayList.size()).isEqualTo(elementsCount);
    }

    @Test
    public void retainAll_emptyList_returnFalse() {
        myArrayList.add("1");
        List<String> addAllEmptyList = new MyArrayList();

        boolean isRetainAll = myArrayList.retainAll(addAllEmptyList);

        Assertions.assertThat(isRetainAll).isFalse();
        Assertions.assertThat(myArrayList.size()).isEqualTo(elementsCount + 1);
    }

    @Test
    public void removeAll_list_returnTrue() {
        myArrayList.add("removeAllList");
        List<String> removeList = getListRemoveAll();

        boolean isRemoveAll = myArrayList.removeAll(removeList);

        Assertions.assertThat(isRemoveAll).isTrue();
        Assertions.assertThat(myArrayList.size()).isEqualTo(elementsCount - 2);
    }

    @Test
    public void removeAll_emptyList_returnFalse() {
        myArrayList.add("removeAllList");
        List<String> removeList = new MyArrayList();

        boolean isRemoveAll = myArrayList.removeAll(removeList);

        Assertions.assertThat(isRemoveAll).isFalse();
        Assertions.assertThat(myArrayList.size()).isEqualTo(elementsCount + 1);
    }

    @Test
    public void containsAll_list_returnTrue() {
        List<String> listContains = getListAddAll();
        myArrayList.addAll(listContains);

        boolean isContains = myArrayList.containsAll(listContains);

        Assertions.assertThat(isContains).isTrue();
    }

    @Test
    public void containsAll_anotherList_returnFalse() {
        List<String> listNotContains = new MyArrayList();
        listNotContains.add("6");
        listNotContains.add("3");

        boolean isNotContains = myArrayList.containsAll(listNotContains);

        Assertions.assertThat(isNotContains).isFalse();
    }

    @Test
    public void containsAll_emptyList_returnFalse() {
        List<String> listNotContains = new MyArrayList();

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
        removeList.add("new string 2");
        removeList.add("new string 2");

        return removeList;
    }
}