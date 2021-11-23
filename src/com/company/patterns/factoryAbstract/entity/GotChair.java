package com.company.patterns.factoryAbstract.entity;

import com.company.patterns.factoryAbstract.Chair;

public class GotChair implements Chair {
    @Override
    public void production() {
        System.out.println("Production gotic chair");
    }

    @Override
    public void sale() {
        System.out.println("Sale gotic chair");
    }
}
