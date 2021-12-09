package com.company.patterns.factoryAbstract;

public interface FurnitureFactory {
    ArmChair createArmChair();
    Chair createChair();
    Sofa createSofa();
}
