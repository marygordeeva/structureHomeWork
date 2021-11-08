package com.company;

import com.company.entity.MyArrayList;

import java.util.ArrayList;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        MyArrayList array1 = new MyArrayList();
        MyArrayList array2 = new MyArrayList(20);

        fillFirstArray(array1);
        fillSecondArray(array2);

        System.out.println("array1: " + array1 + " Size: " + array1.size());
        System.out.println("______________________________________________");
        System.out.println("array2: " + array2 + " Size: " + array1.size());
        System.out.println("______________________________________________");

        array1.remove("4");
        System.out.println("delete '4' result from array1 " + array1);
        System.out.println("______________________________________________");
        array1.add("4");

        if (array1.isEmpty()) {
            System.out.println("array1 is empty");
            System.out.println("______________________________________________");
        }

        MyArrayList arrayEmpty = new MyArrayList();
        if (arrayEmpty.isEmpty()) {
            System.out.println("arrayEmpty is empty");
            System.out.println("______________________________________________");
        }

        if (array1.contains("first")) {
            System.out.println("array1 contains 'first'");
            System.out.println("______________________________________________");
        }

        System.out.println("Out with next result: ");
        Iterator itr = array1.iterator();
        while (itr.hasNext()) {
            System.out.print(" " + itr.next());
        }
        System.out.println();
        System.out.println("______________________________________________");

        String[] arr = (String[]) array1.toArray();
        System.out.println("Out from array result: ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                System.out.print(" " + arr[i]);
            }
        }
        System.out.println();
        System.out.println("______________________________________________");

        array1.add(2, "10");
        System.out.println("array1 add '10' result: " + array1);
        System.out.println("______________________________________________");

        MyArrayList newArrayForAdd = new MyArrayList();
        newArrayForAdd.add("12");
        newArrayForAdd.add("15");
        array1.addAll(newArrayForAdd);
        System.out.println("add all from newArrayForAdd: " + newArrayForAdd + " result : " + array1);
        System.out.println("______________________________________________");

        array2.clear();
        System.out.println("clear array2 result: " + array2);
        System.out.println("______________________________________________");

        fillSecondArray(array2);

        array1.retainAll(array2);
        System.out.println("result array1.retainAll(array2):  " + array1);
        System.out.println("______________________________________________");

        array1.add("56");
        array1.add("85");
        array1.add("80");

        array1.removeAll(array2);
        System.out.println("result array1.removeAll(array2): " + array1);
        System.out.println("______________________________________________");

        boolean isContainsAll = array1.containsAll(array2);
        System.out.println("result array1.containsAll(array2) = " + isContainsAll);
        System.out.println("______________________________________________");

        MyArrayList array3ContainsAll = new MyArrayList();
        array3ContainsAll.add("56");
        array3ContainsAll.add("85");
        array3ContainsAll.add("80");

        boolean isContainsAll2 = array1.containsAll(array3ContainsAll);
        System.out.println("result array1.containsAll(array3ContainsAll) = " + isContainsAll2);
        System.out.println("______________________________________________");

        String deleteElement = array3ContainsAll.remove(0);
        System.out.println("result array3ContainsAll.remove(0) = " + array3ContainsAll);
        System.out.println("result delete element = " + deleteElement);
        System.out.println("______________________________________________");

        int index = array3ContainsAll.indexOf("80");
        System.out.println("result array3ContainsAll.indexOf('80') = " + index);
        System.out.println("______________________________________________");

        array3ContainsAll.add("80");
        array3ContainsAll.add("80");
        int indexLast = array3ContainsAll.lastIndexOf("80");
        System.out.println("result array3ContainsAll.lastIndexOf('80') = " + indexLast);
        System.out.println("______________________________________________");

    }

    private static void fillSecondArray(MyArrayList array2) {
        array2.add("first");
        array2.add("second");
        array2.add("4");
        array2.add("7");
    }

    private static void fillFirstArray(MyArrayList array1) {
        array1.add("first");
        array1.add("second");
        array1.add("3");
        array1.add("4");
    }
}