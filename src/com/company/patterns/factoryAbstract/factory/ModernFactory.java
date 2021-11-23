package com.company.patterns.factoryAbstract.factory;

import com.company.patterns.factoryAbstract.*;
import com.company.patterns.factoryAbstract.entity.ModernArmchair;
import com.company.patterns.factoryAbstract.entity.ModernChair;
import com.company.patterns.factoryAbstract.entity.ModernSofa;

public class ModernFactory implements FurnitureFactory {
    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Armchair createArmchair() {
        return new ModernArmchair();
    }
}
