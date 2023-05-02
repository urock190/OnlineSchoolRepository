package dao;

import jakarta.persistence.Tuple;
import models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepositoryDAO extends JpaRepository<Student, Integer> {
    @Query(value = """
        SELECT last_name, name, (SELECT COUNT(*)
        FROM school_schema.student_course_relation
        WHERE student_id = students.student_id) AS courses_number
        FROM school_schema.students
        GROUP BY last_name, name, courses_number
        HAVING courses_number > 0
        ORDER BY last_name;""", nativeQuery = true)
    List<Tuple> getGroupedByCoursesNumberAndSortedByLastName();
}
