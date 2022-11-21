package com.academy.services.lectures;

import com.academy.models.lectures.AdditionalMaterial;

public class AdditionalMaterialService {
    public static void printCounter(){
        System.out.println(AdditionalMaterial.getCounterOfAddMaterials());
    }
    public static AdditionalMaterial createAdditionalMaterial() {
        return new AdditionalMaterial();
    }
}
