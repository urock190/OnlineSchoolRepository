package webapi.controllers;

import webapi.models.AdditionalMaterial;
import webapi.models.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webapi.service.AddMatService;

import java.util.List;
import java.util.Map;

@Controller
public class AddMatController {
    private final AddMatService matService;

    @Autowired
    public AddMatController(AddMatService matService) {
        this.matService = matService;
    }

    @GetMapping("/ad-materials")
    public String getAdMaterials(Model model) {
        List<AdditionalMaterial> list = matService.getAllAsList();
        model.addAttribute("list", list);
        return "additional-materials";
    }

    @GetMapping("/mat-instance")
    public String getAdMatInstance(Model model, @RequestParam("ID") int ID) {
        AdditionalMaterial material = matService.getByID(ID);
        model.addAttribute("material", material);
        return "material-instance";
    }

    @GetMapping("/add-new-material")
    public String materialCreatingPage() {
        return "add-new-material";
    }

    @PostMapping("/add-new-material")
    public String saveAddMaterial(AdditionalMaterial material) {
        matService.insert(material);
        return "redirect:/ad-materials";
    }

    @GetMapping("/number-of-ad-mats-by-resource-type")
    public String adMatsNumberByResourceType(Model model) {
        Map<ResourceType, Long> map = matService.numberOfAdMatsByResourceType();
        model.addAttribute("map", map);
        return "number-of-ad-mats-by-resource-type";
    }
}
