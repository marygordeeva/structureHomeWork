package com.company.patterns.proxyDb;

public class CarProxy implements CarProduction{

    CarService carService;

    CarProxy(CarService carService){
        this.carService = carService;
    }

    @Override
    public Car createCar() {
        Car car = carService.createCar();
        car.setSpoiler(true);
        return car;
    }
}
