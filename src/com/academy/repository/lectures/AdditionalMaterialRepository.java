package com.academy.repository.lectures;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Models;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.repository.Repository;
import com.academy.services.RepositoryService;

public class AdditionalMaterialRepository implements Repository {
    private static int capacity = 10;
    private static AdditionalMaterial[] additionalMaterials = new AdditionalMaterial[capacity];
    private static RepositoryService <AdditionalMaterial> materialRepositoryService = new RepositoryService<>(additionalMaterials);

    @Override
    public int size(){
        return materialRepositoryService.size();
    }
    @Override
    public boolean isEmpty(){
        return materialRepositoryService.isEmpty();
    }

    @Override
    public void add(Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        materialRepositoryService.add((AdditionalMaterial) model);
    }

    @Override
    public void add(int index, Models model) {
        if (getAll()[capacity-1] != null) expandArray();
        materialRepositoryService.add(index, (AdditionalMaterial) model);
    }
    @Override
    public AdditionalMaterial get (int index){
        return materialRepositoryService.get(index);
    }

    @Override
    public void remove (int index) {
        materialRepositoryService.remove(index);
    }

    @Override
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
    public AdditionalMaterial getById (int ID) throws EntityNotFoundException {
        for (AdditionalMaterial additionalMaterial : getAll()){
            if (additionalMaterial == null) continue;
            if (additionalMaterial.getID() == ID) return additionalMaterial;
        }
        throw new EntityNotFoundException("There's no additional material with such ID");
    }
    @Override
    public void deleteById(int ID){
        for (int i = 0; i < size(); i++){
            if (getAll()[i] == null) continue;
            if (getAll()[i].getID() == ID) getAll()[i] = null;
        }
    }
}
