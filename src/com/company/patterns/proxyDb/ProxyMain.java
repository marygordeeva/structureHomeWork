package com.company.patterns.proxyDb;

public class ProxyMain {
    public void main(String[] args){
        CarService carService = new CarService();

        CarProxy carProxy = new CarProxy(carService);

        Car car = carService.createCar();
        Car carWithSpoiler = carProxy.createCar();
    }
}
