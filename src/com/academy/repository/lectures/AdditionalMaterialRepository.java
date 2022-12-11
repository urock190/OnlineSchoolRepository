package com.academy.repository.lectures;

import com.academy.models.lectures.AdditionalMaterial;
import com.academy.repository.Repository;
import com.academy.services.RepositoryService;

public class AdditionalMaterialRepository extends Repository {
    private static int capacity = 10;
    private static AdditionalMaterial[] additionalMaterials = new AdditionalMaterial[capacity];
    private static RepositoryService <AdditionalMaterial> materialRepositoryService = new RepositoryService<>(additionalMaterials);

    public int size(){
        return materialRepositoryService.size();
    }

    public boolean isEmpty(){
        return materialRepositoryService.isEmpty();
    }

    public AdditionalMaterial get (int index){
        return materialRepositoryService.get(index);
    }
    public void addAdditionalMaterials (AdditionalMaterial additionalMaterial){
        if (getAll()[capacity-1] != null) expandArray();
        materialRepositoryService.add(additionalMaterial);
    }

    public void addAdditionalMaterials (int index, AdditionalMaterial additionalMaterial) {
        if (getAll()[capacity-1] != null) expandArray();
        materialRepositoryService.add(index, additionalMaterial);
    }
    public void remove (int index) {
        materialRepositoryService.remove(index);
    }

    public AdditionalMaterial[] getAll() {
        return materialRepositoryService.getElements();
    }

    private void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        AdditionalMaterial[] tmpArray = new AdditionalMaterial[capacity];
        System.arraycopy(getAll(), 0, tmpArray, 0, newCapacity);
        materialRepositoryService.setElements(tmpArray);
    }

    @Override
    public AdditionalMaterial getById (int ID){
        for (AdditionalMaterial additionalMaterial : getAll()){
            if (additionalMaterial == null) continue;
            if (additionalMaterial.getID() == ID) return additionalMaterial;
        }
        return null;
    }
    @Override
    public void deleteById(int ID){
        for (int i = 0; i < size(); i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
}
