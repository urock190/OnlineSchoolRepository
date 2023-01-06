package com.academy.services.lectures;

import com.academy.models.ResourceType;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.repository.lectures.AdditionalMaterialRepository;

import java.util.List;
import java.util.Scanner;

public class AdditionalMaterialService {
    public static void printCounter() {
        System.out.println(AdditionalMaterial.getCounterOfAddMaterials());
    }
    public static AdditionalMaterial createAdditionalMaterial() {
        return new AdditionalMaterial();
    }

    public static AdditionalMaterial createAddMaterialFromConsole() {
        Scanner scanner = new Scanner(System.in);
        int typeNumber;
        ResourceType resourceType = null;
        System.out.println("==========================\nCreate new additional material. Enter the name of additional material");
        String addMatName = scanner.next() + scanner.nextLine();
        do {
            System.out.println("Select the resource type of additional material. Enter 1 - for \"URL\", 2 - for \"VIDEO\", " +
                    "3 - for \"BOOK\"");
            typeNumber = scanner.nextInt();
            switch (typeNumber) {
                case 1 -> resourceType = ResourceType.URL;
                case 2 -> resourceType = ResourceType.VIDEO;
                case 3 -> resourceType = ResourceType.BOOK;
                default -> System.out.println("Please, enter a number from 1 to 3");
            }
        } while (typeNumber < 1 || typeNumber > 3);
        return new AdditionalMaterial(addMatName, resourceType);
    }

    AdditionalMaterialRepository additionalMaterialRepository = AdditionalMaterialRepository.getInstance();
    public void printID(){
        System.out.println("======================\nShort additional materials info:");
        for (List<AdditionalMaterial> list : additionalMaterialRepository.getAll().values()) {
            if (list == null) continue;
            for (AdditionalMaterial additionalMaterial : list) {
                if (additionalMaterial == null) continue;
                System.out.println("{Additional Material \"" + additionalMaterial.getName() + "\" ID = " + additionalMaterial.getID() + '}');
            }
        }
        System.out.println();
    }

    public void printList(List<AdditionalMaterial> list){
        System.out.println("======================\nShort additional materials info:");
            for (AdditionalMaterial additionalMaterial : list) {
                if (additionalMaterial == null) continue;
                System.out.println("{Additional Material \"" + additionalMaterial.getName() + "\" ID = " + additionalMaterial.getID() + '}');
            }
        System.out.println();
    }

    public void findAllFromTheList(List<AdditionalMaterial> list) {
        System.out.println("======================\nFull additional materials info:");
        if (additionalMaterialRepository.isEmpty()) System.out.println("Array is empty.");
        for (AdditionalMaterial additionalMaterial : list) {
                if(additionalMaterial == null) continue;
                System.out.println(additionalMaterial);
        }
    }

    public static void addMaterialMenuTitle() {
        System.out.println("You have choose the category \"Additional Material\"");
        System.out.println("""
                                Do you want to print short info about additional materials? Type "yes" to confirm. Type "no" to choose another category.\s
                                Enter "1" to create new additional material. Enter "2" to get additional material by it's ID. Enter "3" to print full info about additional materials.
                                Type anything else to continue creating lectures.""");
    }
}
