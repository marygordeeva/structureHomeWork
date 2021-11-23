package com.company.patterns.factoryAbstract;

import com.company.patterns.factoryAbstract.Armchair;
import com.company.patterns.factoryAbstract.Chair;
import com.company.patterns.factoryAbstract.Sofa;

public interface FurnitureFactory {
    Sofa createSofa();

    Chair createChair();

    Armchair createArmchair();
}
