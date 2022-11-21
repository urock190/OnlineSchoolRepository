package com.academy.repository.lectures;

import com.academy.models.lectures.AdditionalMaterial;
import com.academy.superclasses.Repository;

public class AdditionalMaterialRepository extends Repository {
    private static int capacity = 10;
    private static AdditionalMaterial[] additionalMaterials = new AdditionalMaterial[capacity];

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public AdditionalMaterial[] getAll() {
        return additionalMaterials;
    }

    public static void setAdditionalMaterials (AdditionalMaterial additionalMaterial){
        for (int i = 0; i < additionalMaterials.length; i++) {
            if (additionalMaterials[i] == null){
                additionalMaterials[i] = additionalMaterial;
                break;
            }else if (i == capacity-1){
                expandArray();
            }
        }
    }
    private static void expandArray(){
        int newCapacity = capacity;
        capacity = capacity*3/2 + 1;
        AdditionalMaterial[] tmpArray = new AdditionalMaterial[capacity];
        System.arraycopy(additionalMaterials, 0, tmpArray, 0, newCapacity);
        additionalMaterials = tmpArray;
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
        for (int i = 0; i < additionalMaterials.length; i++){
            if (additionalMaterials[i].getID() == ID) additionalMaterials[i] = null;
        }
    }
}
