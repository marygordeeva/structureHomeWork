package com.company.patterns.factoryAbstract.entity;

import com.company.patterns.factoryAbstract.Armchair;

public class ModernArmchair implements Armchair {
    @Override
    public void production() {
        System.out.println("Production modern armchair");
    }
}
