package dao;

import jakarta.persistence.Tuple;
import models.AdditionalMaterial;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddMatRepositoryDAO extends ListCrudRepository<AdditionalMaterial, Integer> {

    @Query("DELETE FROM AdditionalMaterial am WHERE am.lectureID = :lectureID")
    void deleteByLectureID(@Param("lectureID") int lectureID);

    @Query("SELECT resourceType, COUNT(resourceType) AS quantity FROM AdditionalMaterial " +
            "GROUP BY resourceType")
    List<Tuple> numberOfAdMatsByResourceType();
}