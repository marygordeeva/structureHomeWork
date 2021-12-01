package com.company.patterns.proxyDb;

public class Car {

    String model;
    Boolean isSpoiler;

    Car(String model){
        this.model = model;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setSpoiler(Boolean isSpoiler){
        this.isSpoiler = isSpoiler;
    }

    public String getModel(){
        return this.model;
    }

    public Boolean getSpoiler(){
        return this.isSpoiler;
    }
}
