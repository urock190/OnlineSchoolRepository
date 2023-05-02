package dao;

import models.Course;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepositoryDAO extends ListCrudRepository<Course, Integer> {}
