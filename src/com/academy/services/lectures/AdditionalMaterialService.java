package com.academy.services.lectures;

import com.academy.courses.lectures.AdditionalMaterial;

public class AdditionalMaterialService {
    public static void printCounter(){
        System.out.println(AdditionalMaterial.getCounterOfAddMaterials());
    }
    public static AdditionalMaterial createAdditionalMaterial() {
        return new AdditionalMaterial();
    }
}
