package com.company.patterns.factoryAbstract;

import com.company.patterns.factoryAbstract.factory.GotFactory;
import com.company.patterns.factoryAbstract.factory.ModernFactory;

public class AbstractFactoryMain {
    public void main(String[] args) {
        FurnitureProduction furnitureProduction = configure();
        furnitureProduction.production();
        furnitureProduction.sale();
    }

    private FurnitureProduction configure() {
        FurnitureProduction furnitureProduction;
        FurnitureFactory factory;
        String input = System.console().readLine();

        if (input.equals("modern")) {
            factory = new ModernFactory();
            furnitureProduction = new FurnitureProduction(factory);
            return furnitureProduction;
        }
        factory = new GotFactory();
        furnitureProduction = new FurnitureProduction(factory);

        return furnitureProduction;
    }
}
