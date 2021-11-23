package com.company.patterns.factoryAbstract.entity;

import com.company.patterns.factoryAbstract.Sofa;

public class GotSofa implements Sofa {
    @Override
    public void production() {
        System.out.println("Production gotic sofa");
    }
}
