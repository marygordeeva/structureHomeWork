package com.company;

import com.company.MyAnnotations.After;
import com.company.MyAnnotations.Before;
import com.company.MyAnnotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class AnnotationRun {

    public static int countTests;
    public static int successTests;
    public static int failTests;

    public static void runTests(Class testClass) {
        countTests = 0;
        successTests = 0;
        failTests = 0;
        Method[] methods = testClass.getMethods();
        try {
            var myClass = testClass.newInstance();

            List<Method> after = new LinkedList<>();
            List<Method> before = new LinkedList<>();
            List<Method> methodsTests = new LinkedList<>();

            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();

                if (annotations.length == 0) {
                    continue;
                }

                Annotation[] typeAnnotated = method.getAnnotationsByType(Before.class);
                if (typeAnnotated.length != 0) {
                    before.add(method);
                }

                Annotation[] typeAnnotatedAfter = method.getAnnotationsByType(After.class);
                if (typeAnnotatedAfter.length != 0) {
                    after.add(method);
                }

                Annotation[] typeAnnotatedTest = method.getAnnotationsByType(Test.class);
                if (typeAnnotatedTest.length != 0) {
                    methodsTests.add(method);
                }
            }
            countTests = methodsTests.size();

            if (countTests == 0) {
                return;
            }

            for (Method method : methodsTests) {
                try {
                    if (!before.isEmpty()) {
                        runMethods(before, myClass);
                    }
                    method.invoke(myClass);
                    successTests++;
                    if (!after.isEmpty()) {
                        runMethods(after, myClass);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        failTests = countTests - successTests;
    }

    public static void runTestsObject(String packageName) throws IOException {
        try {
            List<Class> classes = new LinkedList<>();
            var classLoader = ClassLoader.getSystemClassLoader();
            String name = packageName.replace(".", "/");

            var url = classLoader.getResource(name);

            File folder = new File(url.toURI());
            var files = folder.listFiles();

            for (File fileFromFolder : files) {
                String nameFile = fileFromFolder.getName();

                if (nameFile.endsWith(".class")) {
                    var clazz = Class.forName(packageName + "." + nameFile.substring(0, nameFile.lastIndexOf('.')));

                    classes.add(clazz);
                }
            }

            for (Class cl : classes) {
                runTests(cl);
            }
        } catch (URISyntaxException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void runMethods(List<Method> methods, Object ob){
        for (Method method : methods){
            try {
                method.invoke(ob);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
