package webapi.dao;

import webapi.models.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepositoryDAO extends ListCrudRepository<Teacher, Integer> {

    @Query(value = "SELECT * FROM teachers WHERE last_name regexp '^[A-MА-М]'", nativeQuery = true)
    List<Teacher> teachersWithLastNameToTheLetterN();
}
