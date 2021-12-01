package com.company.patterns.proxyDb;

public class CarService implements CarProduction{
    @Override
    public Car createCar() {
        return new Car("CSM");
    }
}
