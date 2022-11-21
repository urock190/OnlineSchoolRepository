package com.academy.models.lectures;

import com.academy.superclasses.Models;

public class AdditionalMaterial extends Models {
    private int numberOfArticles;
    private static int counterOfAddMaterials;

    @Override
    public String toString() {
        return "AdditionalMaterial (" +
                "name = '" + getName() + '\'' +
                ", numberOfArticles = " + numberOfArticles +
                ')';
    }
    public AdditionalMaterial(String name, int numberOfArticles) {
        this.setName(name);
        this.numberOfArticles = numberOfArticles;
        setID(++counterOfAddMaterials);
    }
    public AdditionalMaterial(){
        setID(++counterOfAddMaterials);
    }

    public void setNumberOfArticles(int numberOfArticles) {
        this.numberOfArticles = numberOfArticles;
    }

    public static void setCounterOfAddMaterials(int counterOfAddMaterials) {
        AdditionalMaterial.counterOfAddMaterials = counterOfAddMaterials;
    }

    public int getNumberOfArticles() {
        return numberOfArticles;
    }

    public static int getCounterOfAddMaterials() {
        return counterOfAddMaterials;
    }
}