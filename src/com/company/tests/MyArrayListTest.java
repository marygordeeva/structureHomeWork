package com.company.tests;

import com.company.entity.MyArrayList;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.Assert.*;

public class MyArrayListTest {

    MyArrayList myArrayList;

    @BeforeMethod
    public void setUp() {
        myArrayList = new MyArrayList();
    }

    @AfterMethod
    public void tearDown() {
        myArrayList = null;
    }

    @Test
    public void testToString() {
    }

    @Test
    public void testSize() {

    }

    @Test
    public void testIsEmpty() {
    }

    @Test
    public void testContains() {
    }

    @Test
    public void testIterator() {
    }

    @Test
    public void testToArray() {
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testIndexOf() {
    }

    @Test
    public void testLastIndexOf() {
    }

    @Test
    public void testListIterator() {
    }

    @Test
    public void testSubList() {
    }

    @Test
    public void testAddAll() {
    }

    @Test
    public void testClear() {
    }

    @Test
    public void testGet() {
    }

    @Test
    public void testSet() {
    }

    @Test
    public void testRetainAll() {
    }

    @Test
    public void testRemoveAll() {
    }

    @Test
    public void testContainsAll() {
    }
}