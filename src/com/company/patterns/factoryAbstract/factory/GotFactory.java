package com.company.patterns.factoryAbstract.factory;

import com.company.patterns.factoryAbstract.*;
import com.company.patterns.factoryAbstract.entity.GotArmChair;
import com.company.patterns.factoryAbstract.entity.GotChair;
import com.company.patterns.factoryAbstract.entity.GotSofa;

public class GotFactory implements FurnitureFactory {
    @Override
    public Sofa createSofa() {
        return new GotSofa();
    }

    @Override
    public Chair createChair() {
        return new GotChair();
    }

    @Override
    public Armchair createArmchair() {
        return new GotArmChair();
    }
}
