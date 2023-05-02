package dao;

import models.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepositoryDAO extends ListCrudRepository<Teacher, Integer> {

    //Get teachers filtered by the first letter of their last name. All up to the letter N (or Ukrainian 'H') exclusively.
    @Query(value = "SELECT * FROM teachers WHERE last_name regexp '^[A-MА-М]'", nativeQuery = true)
    List<Teacher> teachersWithLastNameToTheLetterN();
}
