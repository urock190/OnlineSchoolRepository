package com.academy.services.lectures;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.ResourceType;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.repository.LectureRepository;
import com.academy.repository.lectures.AdditionalMaterialRepository;
import com.academy.util.Logger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class AdditionalMaterialService {
    private static final Logger LOGGER = new Logger(AdditionalMaterialService.class.getName());
    AdditionalMaterialRepository additionalMaterialRepository = AdditionalMaterialRepository.getInstance();
    LectureRepository lectureRepository = LectureRepository.getInstance();

    public static AdditionalMaterial createAdditionalMaterial() {
        return new AdditionalMaterial();
    }

    public static AdditionalMaterial createAddMaterialFromConsole() {
        LOGGER.debug("Creating new additional material");
        Scanner scanner = new Scanner(System.in);
        int typeNumber = 0;
        ResourceType resourceType = null;
        System.out.println("==========================\nCreate new additional material. Enter the name of additional material");
        String addMatName = scanner.next() + scanner.nextLine();
            do {
                System.out.println("Select the resource type of additional material. Enter 1 - for \"URL\", 2 - for \"VIDEO\", " +
                                   "3 - for \"BOOK\"");
                try {
                    typeNumber = scanner.nextInt();
                } catch (InputMismatchException ex) {
                    LOGGER.error("Incorrect input. Need to solve the problem", ex);
                    scanner.skip(".*");
                }
                switch (typeNumber) {
                    case 1 -> resourceType = ResourceType.URL;
                    case 2 -> resourceType = ResourceType.VIDEO;
                    case 3 -> resourceType = ResourceType.BOOK;
                    default -> System.out.println("Please, enter a number from 1 to 3");
                }
            } while (typeNumber < 1 || typeNumber > 3);
        return new AdditionalMaterial(addMatName, resourceType);
    }

    Consumer<Map<Integer, List<AdditionalMaterial>>> shortInfoCons = integerListMap ->
            integerListMap.forEach((key, value) -> {
                System.out.printf("Lecture id = " + key + '\n');
                for (AdditionalMaterial additionalMaterial : value) {
                    if (additionalMaterial == null) continue;
                    System.out.println("{Additional Material \"" + additionalMaterial.getName() + "\" ID = " + additionalMaterial.getID() + '}');
                }
            });

    public void printGroupedByLectures(){
        additionalMaterialRepository.getAll().forEach((key, value) -> {
            try {
                System.out.println(lectureRepository.getById(key).getName() + ':');
                value.forEach(System.out::println);
            } catch (EntityNotFoundException e) {
                LOGGER.warning(e.getMessage(), e);
            }
        });
    }

    public void shortGroupedByLectures(Map<Integer, List<AdditionalMaterial>> materialsMap){
        shortInfoCons.accept(materialsMap);
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
                                Do you want to print short info about additional materials? Type "yes" to confirm. Type "no" to choose another category.
                                Enter "1" to create new additional material. Enter "2" to get additional material by it's ID. Enter "3" to print full info about additional materials.
                                Type anything else to continue creating lectures.""");
    }
}
