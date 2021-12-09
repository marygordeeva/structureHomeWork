package com.company.patterns.factoryAbstract;

public class GotFactory implements FurnitureFactory{

    public ArmChair createArmChair(){
        return new GotArmChair();
    }

    public Chair createChair(){
        return new GotChair();
    }

    public Sofa createSofa(){
        return new GotSofa();
    }
}
