package com.academy.models.lectures;

import com.academy.models.Models;
import com.academy.models.ResourceType;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;

import static com.academy.models.Lecture.getCounterOfLectures;

public class AdditionalMaterial extends Models implements Comparable<AdditionalMaterial>, Serializable {
    @Serial
    private static final long serialVersionUID = 123454648635304L;
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
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    /**Compares two additional materials by their ID.*/
    @Override
    public int compareTo(AdditionalMaterial o) {
        return getID() - o.getID();
    }
    /**Comparator for sorting the list by lecture's ID*/
    public static Comparator<AdditionalMaterial> lectureIDComparator = Comparator.comparingInt(addMat -> addMat.lectureID);
    /**Comparator for sorting the list by resource type*/
    public static Comparator<AdditionalMaterial> resourceTypeComparator = Comparator.comparing(o -> (o.resourceType));
}