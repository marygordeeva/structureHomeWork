package com.company.entity;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ClassMethods {

    public ArrayList<Method> afterMethods;
    public ArrayList<Method> beforeMethods;
    public ArrayList<Method> testMethods;

    public ClassMethods(){
        afterMethods = new ArrayList<>();
        beforeMethods = new ArrayList<>();
        testMethods = new ArrayList<>();
    }

}
