package com.company.tests;

import com.company.entity.MyArrayList;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.ListIterator;

public class MyArrayListTest {

    @Test
    public void add_listIsNotEmpty_returnTrue() {
        MyArrayList myArrayList = getMyArrayList(2, "string 1");

        boolean isAdded = myArrayList.add("new string");

        assertThat(myArrayList).contains("new string");
        assertThat(isAdded).isTrue();
        assertThat(myArrayList.size()).isEqualTo(3);
    }

    @Test
    public void size_listIsNotEmpty_returnCorrectSize() {
        MyArrayList myArrayList = getMyArrayList(3, "string 1");

        assertThat(myArrayList.size()).isEqualTo(3);
    }

    @Test
    public void isEmpty_listIsNotEmpty_returnFalse() {
        MyArrayList myArrayList = getMyArrayList(1, "string 1");

        assertThat(myArrayList.isEmpty()).isFalse();
    }

    @Test
    public void isEmpty_arrayListIsEmpty_returnTrue() {
        MyArrayList myArrayList = new MyArrayList();

        assertThat(myArrayList.isEmpty()).isTrue();
    }

    @Test
    public void contains_listIsNotEmpty_returnTrue() {
        MyArrayList myArrayList = getMyArrayList(1, "string 1");

        assertThat(myArrayList.contains("string 1")).isTrue();
    }

    @Test
    public void contains_elementString_returnFalse() {
        MyArrayList myArrayList = getMyArrayList(1, "string 1");

        assertThat(myArrayList.contains("1")).isFalse();
    }

    @Test
    public void iteratorNext_arrayList_returnCorrectComposition() {
        MyArrayList myArrayList = getMyArrayList(3, "string 1");

        assertThat(myArrayList.iterator()).containsOnly("string 1");
    }

    @Test
    public void remove_listIsNotEmptyAndContainsString_returnTrue() {
        MyArrayList myArrayList = getMyArrayList(3, "string 1");

        boolean isRemove = myArrayList.remove("string 1");

        assertThat(isRemove).isTrue();
        assertThat(myArrayList.size()).isEqualTo(2);
    }

    @Test
    public void remove_listIsNotEmptyAndNotContainsString_returnFalse() {
        MyArrayList myArrayList = getMyArrayList(3, "string 1");

        boolean isRemove = myArrayList.remove("3");

        assertThat(isRemove).isFalse();
        assertThat(myArrayList.size()).isEqualTo(3);
    }

    @Test
    public void indexOf_listIsNotEmptyAndString_returnCorrectIndex() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");

        assertThat(myArrayList.indexOf("new string 2")).isEqualTo(0);
    }

    @Test
    public void indexOf_listIsNotEmptyAndNotContainsString_returnNotCorrectIndex() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");

        assertThat(myArrayList.indexOf("new")).isEqualTo(-1);
    }

    @Test
    public void lastIndexOf_listIsNotEmptyAndString_returnCorrectIndex() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");

        assertThat(myArrayList.lastIndexOf("new string 2")).isEqualTo(2);
    }

    @Test
    public void listIterator_listIsNotEmpty_returnCorrectElementsValue() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");

        assertThat(myArrayList.listIterator()).containsOnly("string 1");
    }

    @Test
    public void listIteratorRemove_listIsNotEmptyAndListIterator_returnListIteratorNotContainsRemoveString() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");
        myArrayList.add("1");
        ListIterator<String> listIterator = myArrayList.listIterator();

        listIterator.remove();

        assertThat(listIterator).containsOnly("new string 2");
    }

    @Test
    public void listIteratorSet_listIsNotEmptyAndListIterator_returnCorrectStringValue() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");
        ListIterator<String> listIterator = myArrayList.listIterator();
        listIterator.next();

        listIterator.set("new string 3");

        listIterator.next();
        assertThat("new string 3").isEqualTo(listIterator.previous());
    }

    @Test
    public void listIteratorAdd_listIsNotEmptyAndListIterator_returnCorrectStringValue() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");
        ListIterator<String> listIterator = myArrayList.listIterator();

        listIterator.add("newStringIterator");

        assertThat("newStringIterator").isEqualTo(getLastElementListIterator(listIterator));
    }

    @Test
    public void subList_forIndex2And4_returnCorrectList() {
        MyArrayList myArrayList = getMyArrayList(6, "new string 2");

        List<String> listElements = myArrayList.subList(2, 4);

        assertThat(listElements.size()).isEqualTo(2);
        assertThat(listElements).containsOnly("new string 2");
    }

    @Test
    public void addAll_twoListIsNotEmpty_returnTrue() {
        MyArrayList myArrayList = getMyArrayList(6, "new string 2");
        List<String> addAllList = getListAddAll();

        boolean isAdded = myArrayList.addAll(addAllList);

        assertThat(isAdded).isTrue();
        assertThat(myArrayList.size()).isEqualTo(10);
        assertThat(myArrayList.containsAll(addAllList)).isTrue();
    }

    @Test
    public void clear_arrayListIsNotEmpty_returnListIsEmpty() {
        MyArrayList myArrayList = getMyArrayList(2, "new string 2");

        myArrayList.clear();

        assertThat(myArrayList).isEmpty();
    }

    @Test
    public void get_listIsNotEmptyAndIndex_returnCorrectStringValue() {
        MyArrayList myArrayList = getMyArrayList(2, "new string 2");

        assertThat(myArrayList.get(1)).isEqualTo("new string 2");
    }

    @Test
    public void set_listIsNotEmptyAndValueAndIndex_returnCorrectStringValue() {
        MyArrayList myArrayList = getMyArrayList(2, "new string 2");

        assertThat(myArrayList.set(1, "new string 3")).isEqualTo("new string 3");
    }

    @Test
    public void set_listIsNotEmptyAndValueAndIndexOut_returnThrow() {
        MyArrayList myArrayList = getMyArrayList(2, "new string 2");

        assertThatThrownBy(() -> myArrayList.set(10, "new string 3"))
                .hasMessageStartingWith("Meaning");
    }

    @Test
    public void retainAll_twoListIsNotEmpty_returnTrue() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");
        myArrayList.add("1");

        boolean isRetainAll = myArrayList.retainAll(getListRemoveAll());

        assertThat(isRetainAll).isTrue();
        assertThat(myArrayList.size()).isEqualTo(3);
        assertThat(myArrayList).containsOnly("new string 2");
    }

    @Test
    public void retainAll_listIsNotEmptyAndListIsEmpty_returnFalse() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");
        List<String> addAllEmptyList = new MyArrayList();

        assertThat(myArrayList.retainAll(addAllEmptyList)).isFalse();
        assertThat(myArrayList.size()).isEqualTo(3);
    }

    @Test
    public void removeAll_twoListIsNotEmpty_returnTrue() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");
        myArrayList.add("removeAllList");
        List<String> removeList = getListRemoveAll();

        assertThat(myArrayList.removeAll(removeList)).isTrue();
        assertThat(myArrayList.size()).isEqualTo(1);
    }

    @Test
    public void removeAll_listIsNotEmptyAndListIsEmpty_returnFalse() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");
        List<String> removeList = new MyArrayList();

        boolean isRemoveAll = myArrayList.removeAll(removeList);

        assertThat(isRemoveAll).isFalse();
        assertThat(myArrayList.size()).isEqualTo(3);
    }

    @Test
    public void containsAll_twoListIsNotEmptyWithContains_returnTrue() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");
        List<String> listContains = getListAddAll();
        myArrayList.addAll(listContains);

        assertThat(myArrayList.containsAll(listContains)).isTrue();
    }

    @Test
    public void containsAll_twoListIsNotEmptyNotContains_returnFalse() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");
        List<String> listNotContains = new MyArrayList();
        listNotContains.add("6");
        listNotContains.add("3");

        assertThat(myArrayList.containsAll(listNotContains)).isFalse();
    }

    @Test
    public void containsAll_listIsNotEmptyAndListIsEmpty_returnFalse() {
        MyArrayList myArrayList = getMyArrayList(3, "new string 2");
        List<String> listEmpty = new MyArrayList();

        assertThat( myArrayList.containsAll(listEmpty)).isFalse();
    }

    private String getLastElementListIterator(ListIterator listIterator){
        String lastElement = null;
        while(listIterator.hasNext()){
            lastElement = (String) listIterator.next();
        }
        return lastElement;
    }

    private MyArrayList getMyArrayList(int elementsCount, String element) {
        MyArrayList myArrayList = new MyArrayList();
        for (int i = 0; i < elementsCount; i++) {
            myArrayList.add(element);
        }

        return myArrayList;
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