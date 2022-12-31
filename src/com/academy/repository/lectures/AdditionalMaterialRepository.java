package com.academy.repository.lectures;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Models;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.repository.Repository;
import com.academy.services.SimpleIterator;

import java.util.ArrayList;
import java.util.List;

public class AdditionalMaterialRepository implements Repository {
    private static AdditionalMaterialRepository instance;
    private List<AdditionalMaterial> additionalMaterials;

    private AdditionalMaterialRepository() {
         additionalMaterials = new ArrayList<>();
    }

    public static AdditionalMaterialRepository getInstance(){
        if (instance == null) instance = new AdditionalMaterialRepository();
        return instance;
    }
    @Override
    public int size(){
        return additionalMaterials.size();
    }
    @Override
    public boolean isEmpty(){
        return additionalMaterials.isEmpty();
    }

    @Override
    public void add(Models model) {
        additionalMaterials.add((AdditionalMaterial) model);
    }

    @Override
    public void add(int index, Models model) {
        additionalMaterials.add(index, (AdditionalMaterial) model);
    }
    @Override
    public AdditionalMaterial get (int index){
        return additionalMaterials.get(index);
    }

    @Override
    public void remove (int index) {
        additionalMaterials.remove(index);
    }

    @Override
    public void findAll() {
        System.out.println("======================\nFull additional materials info:");
        SimpleIterator<AdditionalMaterial> iterator = iterator();
        int i = 0;
        while (iterator.hasNext()) {
                AdditionalMaterial additionalMaterial = iterator.next();
                if (additionalMaterial == null) {i++; continue;}
                System.out.println(additionalMaterial);
        }
        if (i == size()) System.out.println("Array is empty.");
    }

    @Override
    public List<AdditionalMaterial> getAll() {
        return additionalMaterials;
    }

    @Override
    public AdditionalMaterial getById (int ID) throws EntityNotFoundException {
        for (AdditionalMaterial additionalMaterial : additionalMaterials){
            if (additionalMaterial == null) continue;
            if (additionalMaterial.getID() == ID) return additionalMaterial;
        }
        throw new EntityNotFoundException("There's no additional material with such ID");
    }
    @Override
    public void deleteById(int ID){
        for (int i = 0; i < size(); i++){
            if (additionalMaterials.get(i) == null) continue;
            if (additionalMaterials.get(i).getID() == ID) additionalMaterials.remove(i);
        }
    }

    @Override
    public SimpleIterator<AdditionalMaterial> iterator() {
        return new SimpleIterator<>(additionalMaterials);
    }
}
