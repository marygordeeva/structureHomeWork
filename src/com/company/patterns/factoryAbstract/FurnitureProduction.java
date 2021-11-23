package com.company.patterns.factoryAbstract;

public class FurnitureProduction {
    Sofa sofa;
    Chair chair;
    Armchair armchair;

    public FurnitureProduction(FurnitureFactory furnitureFactory){
        sofa = furnitureFactory.createSofa();
        chair = furnitureFactory.createChair();
        armchair = furnitureFactory.createArmchair();
    }

    public void production(){
        sofa.production();
        chair.production();
        armchair.production();
    }

    public void sale(){
        chair.sale();
    }
}
