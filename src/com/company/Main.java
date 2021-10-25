package com.company;

import com.company.entity.MyArrayList;

public class Main {

    public static void main(String[] args) {
        MyArrayList array1 = new MyArrayList();
        MyArrayList array2 = new MyArrayList(20);

        fillFirstArray(array1);
        fillSecondArray(array2);

        System.out.println("array1: " + array1 + " Size: " + array1.size());
        System.out.println("array2: " + array2 + " Size: " + array1.size());

        array1.remove("4");
        System.out.println("delete '4' result from array1 " + array1);
        array1.add("4");

        if(array1.isEmpty()){
            System.out.println("array1 is empty");
        }

        MyArrayList arrayEmpty = new MyArrayList();
        if(arrayEmpty.isEmpty()){
            System.out.println("arrayEmpty is empty");
        }

        if(array1.contains("first")){
            System.out.println("array1 contains 'first'");
        }

        System.out.print("Out with next result: ");
        while(array1.iterator().hasNext()){
            System.out.print(" " + array1.iterator().next());
        }

        String[] arr = (String[]) array1.toArray();
        System.out.print("Out from array result: ");
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != null){
                System.out.print(" " + arr[i]);
            }
        }

        array1.add(2, "10");
        System.out.println("array1 add '10' result: " + array1);

        MyArrayList newArrayForAdd = new MyArrayList();
        newArrayForAdd.add("12");
        newArrayForAdd.add("15");
        array1.addAll(newArrayForAdd);
        System.out.println("result add all from newArrayForAdd: " + newArrayForAdd + "result add: " + array1.toString());

        array2.clear();
        System.out.println("clear array2 result: " + array2);

        fillSecondArray(array2);

        array1.retainAll(array2);
        System.out.println("result array1.retainAll(array2):  " + array1);

        array1.add("56");
        array1.add("85");
        array1.add("80");

        array1.removeAll(array2);
        System.out.println("result array1.removeAll(array2): " + array1);

        boolean isContainsAll = array1.containsAll(array2);
        System.out.println("result array1.containsAll(array2) = " + isContainsAll);

        MyArrayList array3ContainsAll = new MyArrayList();
        array3ContainsAll.add("first");
        array3ContainsAll.add("second");
        array3ContainsAll.add("4");

        boolean isContainsAll2 = array1.containsAll(array3ContainsAll);
        System.out.println("result array1.containsAll(array3ContainsAll) = " + isContainsAll2);
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