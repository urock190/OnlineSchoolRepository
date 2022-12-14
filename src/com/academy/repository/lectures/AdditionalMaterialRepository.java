package com.academy.repository.lectures;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.lectures.AdditionalMaterial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdditionalMaterialRepository {
    private static AdditionalMaterialRepository instance;
    private Map<Integer, List<AdditionalMaterial>> additionalMaterials;

    private AdditionalMaterialRepository() {
         additionalMaterials = new HashMap<>();
    }

    public static AdditionalMaterialRepository getInstance(){
        if (instance == null) instance = new AdditionalMaterialRepository();
        return instance;
    }

    public int size(){
        return additionalMaterials.size();
    }
    public boolean isEmpty(){
        return additionalMaterials.isEmpty();
    }

    public void put (int lectureID, List<AdditionalMaterial> materialsList) {
        additionalMaterials.put(lectureID, materialsList);
    }
    public void putIfAbsent (int lectureID, List<AdditionalMaterial> materialsList) {
        additionalMaterials.putIfAbsent(lectureID, materialsList);
    }
    public void remove (int lectureID) {
        additionalMaterials.remove(lectureID);
    }
    public Map<Integer, List<AdditionalMaterial>> getAll() {
        return additionalMaterials;
    }
    public List<AdditionalMaterial> get (int lectureID){
        return additionalMaterials.get(lectureID);
    }

    public void deleteById(int lectureID, int ID){
        get(lectureID);
        boolean success = false;
        for (int i = 0; i < get(lectureID).size(); i++){
            if (get(lectureID).get(i) == null) continue;
            if (get(lectureID).get(i).getID() == ID) {get(lectureID).remove(i); success = true;}
        }
        if (success) System.out.println("Additional material ID = " + ID + " has been successfully removed from lecture with ID = " + lectureID + '.');
        else System.out.println("Additional material not found.");
    }

    public AdditionalMaterial getById (int ID) throws EntityNotFoundException {
        for (List<AdditionalMaterial> list : additionalMaterials.values()) {
            if (list == null) continue;
            for (AdditionalMaterial additionalMaterial : list){
                if (additionalMaterial == null) continue;
                if (additionalMaterial.getID() == ID) return additionalMaterial;
            }
        }
        throw new EntityNotFoundException("There's no additional material with such ID");
    }

    public void addById (int lectureID, int ID) throws EntityNotFoundException {
        get(lectureID).add(getById(ID));
        System.out.println("Additional material with ID = " + ID + " has been successfully added to the lecture with ID = " + lectureID + '.');
        deleteById(getById(ID).getLectureID(), ID);
        getById(ID).setLectureID(lectureID);
    }

    public void findAll() {
        System.out.println("======================\nFull additional materials info:");
        if (isEmpty()) System.out.println("Array is empty.");
        for (List<AdditionalMaterial> list : additionalMaterials.values()) {
            if (list == null) continue;
            for (AdditionalMaterial additionalMaterial : list) {
                if(additionalMaterial == null) continue;
                System.out.println(additionalMaterial);
            }
        }
    }

    public List<AdditionalMaterial> toAddMaterialsList(){
        List <AdditionalMaterial> newList = new ArrayList<>();
        for (List<AdditionalMaterial> list : additionalMaterials.values()) {
            if (list == null) continue;
            for (AdditionalMaterial additionalMaterial : list) {
                if (additionalMaterial == null) continue;
                newList.add(additionalMaterial);
            }
        } return newList;
    }
}
