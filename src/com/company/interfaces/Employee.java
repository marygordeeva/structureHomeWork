package com.company.interfaces;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Employee {

    private String name;
    private Integer age;
    private Country country;
    private Prof prof;

    private Employee(String name, Integer age, Country country, Prof prof) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.prof = prof;
    }

    public static Employee fromRussia(String name, Integer age) {
        return new Employee(name, age, Country.RUSSIA, Prof.ACCOUNTANT);
    }

    public static Employee fromItaly(String name, Integer age) {
        return new Employee(name, age, Country.ITALY, Prof.ADMINISTRATOR);
    }

    public static Employee fromFrance(String name, Integer age) {
        return new Employee(name, age, Country.FRANCE, Prof.PROGRAMMER);
    }

    public static Employee fromUSA(String name, Integer age) {
        return new Employee(name, age, Country.USA, Prof.PROGRAMMER);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Prof getProf(){return prof;}

    public void setProf(Prof prof){this.prof = prof;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(age, employee.age) && country == employee.country;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, country);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", country=" + country +
                '}';
    }
}

enum Prof{
    PROGRAMMER("programmer_index"),
    ACCOUNTANT("accountant_index"),
    ADMINISTRATOR  ("administrator_index");

    private final String index;

    Prof(String programmer_index) {
        this.index = programmer_index;
    }
    public String getIndex() {
        return index;
    }
}


enum Country {

    RUSSIA("russia_index"),
    ITALY("italy_index"),
    FRANCE("france_index"),
    USA("usa_index");

    private final String index;

    Country(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }
}


class Main {

    public static void main(String[] args) {
        List<Employee> employeeList = makeEmployeeList();
    }

    //        Задача 1. Сгруппировать сотрудников по странам
    private static Map<Country, List<Employee>> groupByCountry(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(v -> v.getCountry()));
    }

    //        Задача 2. Найти всех американских сотрудников
    private static List<Employee> findAllAmericans(List<Employee> employees) {
        return employees.stream()
                .filter(x -> x.getCountry() == Country.USA)
                .collect(Collectors.toList());
    }

    //        Задача 3. Найти самых молодых сотрудников
    private static List<Employee> findAllYoungest(List<Employee> employees) {
        Employee optionalEmployee = employees.stream()
                .min(Comparator.comparingInt(v -> v.getAge()))
                .get();

        return employees.stream()
                .filter(v -> v.getAge().equals(optionalEmployee.getAge()))
                .collect(Collectors.toList());
    }

    //        Задача 3. Найти самых старых сотрудников
    private static List<Employee> findOlder(List<Employee> employees) {
        Employee optionalEmployee = employees.stream()
                .max(Comparator.comparingInt(v -> v.getAge()))
                .get();

        return employees.stream()
                .filter(v -> v.getAge().equals(optionalEmployee.getAge()))
                .collect(Collectors.toList());
    }

    //        Задача 4. Найти самых старых сотрудников в каждой стране
    private static Map<Country, List<Employee>> findAllOlder(List<Employee> employees) {
        Map<Country, List<Employee>> collect = employees.stream()
                .collect(Collectors.groupingBy(Employee::getCountry));

            return null;
    }

    //        Задача 5. Вернуть список стран, в которых проживают сотрудники
    private static List<Country> findAllCountries(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getCountry)
                .distinct()
                .collect(Collectors.toList());
    }

    //        Задача 6. Сгруппировать сотрудников по имени
    private static Map<String, List<Employee>> groupByName(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(v -> v.getName()));
    }

    //        Задача 7. Сгруппировать сотрудников по возрасту
    private static Map<Integer, List<Employee>> groupByAge(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(v -> v.getAge()));
    }

    //        Задача 8. Получить сотрудников из Франции возрастом 18
    private static List<Employee> getEmployeeFromFrance(List<Employee> employees) {
        return employees.stream()
                .filter(b -> b.getAge() >= 18 && b.getCountry() == Country.FRANCE)
                .collect(Collectors.toList());
    }

    //        Задача 9. Вернуть список стран, в которых проживают сотрудники старше 25
    private static List<Country> findAllCountries1(List<Employee> employees) {
        return employees.stream()
                .filter(v -> v.getAge() > 25)
                .map(Employee::getCountry)
                .collect(Collectors.toList());
    }

    private static List<Employee> makeEmployeeList() {
        return List.of(
                Employee.fromFrance("FranceName1", 15),
                Employee.fromFrance("FranceName2", 18),
                Employee.fromFrance("FranceName12", 18),
                Employee.fromFrance("FranceName3", 25),
                Employee.fromFrance("FranceName4", 32),
                Employee.fromFrance("FranceName5", 48),
                Employee.fromRussia("RussiaName1", 15),
                Employee.fromRussia("RussiaName2", 19),
                Employee.fromRussia("RussiaName3", 35),
                Employee.fromRussia("RussiaName4", 32),
                Employee.fromRussia("RussiaName5", 48),
                Employee.fromItaly("ItalyName1", 35),
                Employee.fromItaly("ItalyName2", 18),
                Employee.fromUSA("USAName1", 20),
                Employee.fromUSA("USAName5", 20),
                Employee.fromUSA("USAName2", 12),
                Employee.fromUSA("USAName4", 12),
                Employee.fromUSA("USAName3", 18)
        );
    }
}
