package com.company.patterns.factoryAbstract;

public class MainAbstract {

    public void main(){
        FurnitureFactory furFactory = new GotFactory();
        ArmChair  armC = furFactory.createArmChair();
        Chair chair = furFactory.createChair();
        Sofa sofa = furFactory.createSofa();

        armC.sew();
        chair.production();
        sofa.production();
    }
}
