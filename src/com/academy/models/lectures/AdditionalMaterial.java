package com.academy.models.lectures;

import com.academy.models.Models;
import com.academy.models.ResourceType;

import static com.academy.models.Lecture.getCounterOfLectures;

public class AdditionalMaterial extends Models {
    private int lectureID;
    private static int counterOfAddMaterials;
    private ResourceType resourceType;

    @Override
    public String toString() {
        return "AdditionalMaterial (" + resourceType +
                ", name = '" + getName() + '\'' +
                ", ID = " + getID() + ", lectureID = " + lectureID +
                ')';
    }
    public AdditionalMaterial(String name, ResourceType resourceType) {
        this.setName(name);
        this.resourceType = resourceType;
        setID(++counterOfAddMaterials);
        lectureID = getCounterOfLectures();
    }
    public AdditionalMaterial(){
        setID(++counterOfAddMaterials);
        lectureID = getCounterOfLectures();
    }

    public static void setCounterOfAddMaterials(int counterOfAddMaterials) {
        AdditionalMaterial.counterOfAddMaterials = counterOfAddMaterials;
    }

    public static int getCounterOfAddMaterials() {
        return counterOfAddMaterials;
    }

    public int getLectureID() {
        return lectureID;
    }

    public void setLectureID(int lectureID) {
        this.lectureID = lectureID;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }
}