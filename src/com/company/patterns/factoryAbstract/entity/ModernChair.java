package com.company.patterns.factoryAbstract.entity;

import com.company.patterns.factoryAbstract.Chair;

public class ModernChair implements Chair {
    @Override
    public void production() {
        System.out.println("Production modern chair");
    }

    @Override
    public void sale() {
        System.out.println("Sale modern chair");
    }
}
