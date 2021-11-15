package com.company;

import com.company.MyAnnotations.After;
import com.company.MyAnnotations.Before;
import com.company.MyAnnotations.Test;
import com.company.entity.ClassMethods;

import java.io.File;
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
        clearCountTests();
        Method[] methods = testClass.getMethods();
        try {
            var myClass = testClass.newInstance();
            ClassMethods classTestMethods = new ClassMethods();

            for (Method method : methods) {
                if (!haveAnnotation(method)) {
                    continue;
                }

                fillMethods(classTestMethods, method, Before.class);
                fillMethods(classTestMethods, method, After.class);
                fillMethods(classTestMethods, method, Test.class);
            }
            if (isEmptyListTests(classTestMethods)) {
                return;
            }

            executeTests(myClass, classTestMethods);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        failTests = countTests - successTests;
    }

    public static void runTestsFromPackage(String packageName) {
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

    private static void clearCountTests() {
        countTests = 0;
        successTests = 0;
        failTests = 0;
    }

    private static void invokeMethods(List<Method> methods, Object ob) {
        for (Method method : methods) {
            try {
                method.invoke(ob);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean isEmptyListTests(ClassMethods methods1) {
        countTests = methods1.testMethods.size();

        if (countTests == 0) {
            return true;
        }
        return false;
    }

    private static void executeTests(Object myClass, ClassMethods classMethods) {

        for (Method method : classMethods.testMethods) {
            try {
                if (!classMethods.beforeMethods.isEmpty()) {
                    invokeMethods(classMethods.beforeMethods, myClass);
                }
                method.invoke(myClass);
                successTests++;
                if (!classMethods.afterMethods.isEmpty()) {
                    invokeMethods(classMethods.afterMethods, myClass);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean haveAnnotation(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();

        if (annotations.length == 0) {
            return false;
        }
        return true;
    }

    private static void fillMethods(ClassMethods methods, Method method, Class clazz) {
        Annotation[] typeAnnotated = method.getAnnotationsByType(clazz);
        if (typeAnnotated.length != 0) {

            if (clazz.getName().equals(Before.class)) {
                methods.beforeMethods.add(method);
            } else if (clazz.getName().equals(After.class)) {
                methods.afterMethods.add(method);
            } else if (clazz.getName().equals(Test.class)) {
                methods.testMethods.add(method);
            }
        }
    }
}
