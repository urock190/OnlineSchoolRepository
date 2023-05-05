package webapi.dao;

import jakarta.persistence.Tuple;
import webapi.models.Lecture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepositoryDAO extends ListCrudRepository<Lecture, Integer> {

    @Query("""
            SELECT l.name, (select count(*) from AdditionalMaterial am
            where am.lectureID = l.ID) as materials_number
            FROM Lecture l
            where date(l.lectureDate) < {d '2024-01-01'}
            order by l.lectureDate""")
    List<Tuple> getUntil2024sortedByLectureDate();

    @Query("""
        FROM Lecture l
        where cast(l.creationDate as biginteger) = (select min(cast(creationDate as biginteger)) from Lecture)
        order by (select count(*) from Homework h where h.lectureID = l.ID) desc
        limit 1""")
    Lecture getEarliestLectureWithMostHomeworks();
}
