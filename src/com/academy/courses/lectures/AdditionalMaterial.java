package com.academy.courses.lectures;

public class AdditionalMaterial {
    private String name;
    private int numberOfArticles;
    private int ID;
    private static int counterOfAddMaterials;

    public AdditionalMaterial(String name, int numberOfArticles) {
        this.name = name;
        this.numberOfArticles = numberOfArticles;
        ID = ++counterOfAddMaterials;
    }
    public AdditionalMaterial(){
        ID = ++counterOfAddMaterials;
    }
    public int getID() {
        return ID;
    }
    public static int getCounterOfAddMaterials() {
        return counterOfAddMaterials;
    }
}
