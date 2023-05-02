package service;

import dao.AddMatRepositoryDAO;
import jakarta.persistence.Tuple;
import models.AdditionalMaterial;
import models.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class AddMatService {
    private final AddMatRepositoryDAO addMatRepositoryDAO;

    @Autowired
    public AddMatService(AddMatRepositoryDAO addMatRepositoryDAO) {
        this.addMatRepositoryDAO = addMatRepositoryDAO;
    }

    @Transactional
    public void insert(AdditionalMaterial material) {
        addMatRepositoryDAO.save(material);
    }

    public void deleteByID(int id) {
        addMatRepositoryDAO.deleteById(id);
    }

    public void deleteByLectureID(int lectureID) {
        addMatRepositoryDAO.deleteByLectureID(lectureID);
    }

    public AdditionalMaterial getByID(int ID){
        return addMatRepositoryDAO.findById(ID).orElse(null);
    }

    public List<AdditionalMaterial> getAllAsList(){
        return addMatRepositoryDAO.findAll();
    }

    public Map<ResourceType, Long> numberOfAdMatsByResourceType(){
        Map<ResourceType, Long> map = new EnumMap<>(ResourceType.class);

        List<Tuple> list = addMatRepositoryDAO.numberOfAdMatsByResourceType();
            for (Tuple t: list) {
                map.put(t.get(0, ResourceType.class), t.get(1, Long.class));
            }
        return map;
    }
}
