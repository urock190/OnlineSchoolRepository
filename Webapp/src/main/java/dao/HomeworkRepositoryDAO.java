package dao;

import models.Homework;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepositoryDAO extends ListCrudRepository<Homework, Integer> {

    @Query("DELETE FROM Homework WHERE lectureID = :lectureID")
    void deleteByLectureID(@Param("lectureID") int lectureID);
}
