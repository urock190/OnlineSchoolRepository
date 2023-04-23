package java.com.academy.service;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.*;
import com.academy.models.lectures.AdditionalMaterial;
import com.academy.models.lectures.Homework;
import com.academy.repository.CourseRepository;
import com.academy.repository.LectureRepository;
import com.academy.repository.PersonRepository;
import com.academy.repository.lectures.AdditionalMaterialRepository;
import com.academy.repository.lectures.HomeworkRepository;
import com.academy.services.CourseService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class CourseServiceTest {
    private CourseService courseService;
    private static List<Course> courses;
    private static List<Lecture> lectures;
    private static List<Person> students;
    private static List<Person> teachers;
    private static List<Homework> homeworks;
    private static List<AdditionalMaterial> materials;
    @Mock private CourseRepository courseRepository;
    @Mock private LectureRepository lectureRepository;
    @Mock private PersonRepository personRepository;
    @Mock private HomeworkRepository homeworkRepository;
    @Mock private AdditionalMaterialRepository materialRep;

    @BeforeAll
    static void initAll(){
        courses = new ArrayList<>(List.of(new Course("first", new Person(Role.TEACHER, "A.","B."),
                        new Person(Role.STUDENT, "A.","C.")),
                new Course("second", new Person(Role.TEACHER, "B.","C."), new Person(Role.STUDENT, "B.","D.")),
                new Course("third", new Person(Role.TEACHER, "C.","D."), new Person(Role.STUDENT, "C.","J."))));
        lectures = new ArrayList<>(List.of(new Lecture("math", 2, new Homework[]{new Homework()}, new AdditionalMaterial(),
                LocalDateTime.now()), new Lecture("literature", 3, new Homework[]{new Homework()}, new AdditionalMaterial(),
                LocalDateTime.of(2023,6,12,15,0))));
        students = new ArrayList<>(courses.stream().map(Course::getStudent).toList());
        teachers = new ArrayList<>(courses.stream().map(Course::getTeacher).toList());
        homeworks = new ArrayList<>(List.of(new Homework("hw1",2,"do something #1"),
                new Homework("hw2",3,"do something #2")));
        materials = new ArrayList<>(List.of(new AdditionalMaterial("Test book", ResourceType.BOOK), new AdditionalMaterial(
                "www.test.org", ResourceType.URL), new AdditionalMaterial("Test video", ResourceType.VIDEO)));
    }

    @BeforeEach
    void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void ReturnsCourseInstanceIfMethodIsStubbed(){
        CourseService mockService = mock(CourseService.class);
        when(mockService.createCourseFromConsole()).thenReturn(courses.get(0));
        assertEquals("first", mockService.createCourseFromConsole().getName());
        assertEquals("A.", mockService.createCourseFromConsole().getTeacher().getName());
        assertEquals("C.", mockService.createCourseFromConsole().getStudent().getLastName());
    }

    @Test
    void PrintsCoursesIDsFromGivenListIfStaticGetInstanceIsMocked(){
        try (MockedStatic<CourseRepository> repositoryStatic = mockStatic(CourseRepository.class)){
            repositoryStatic.when(CourseRepository::getInstance).thenReturn(courseRepository);
            when(courseRepository.getAll()).thenReturn(courses);

            courseService = new CourseService();
            courseService.printID();
        }
    }

    @Test
    void PrintLecturesFromGivenListIfStaticGetInstanceIsMocked(){
        try(MockedStatic<LectureRepository> lectureRepMockedStatic = mockStatic(LectureRepository.class)){
            lectureRepMockedStatic.when(LectureRepository::getInstance).thenReturn(lectureRepository);
            when(lectureRepository.getByCourseId(anyInt())).thenReturn(lectures);

            courseService = new CourseService();
            courseService.printLecturesOfThisCourse(courses.get(0));
        } catch (EntityNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void PrintStudentsFromGivenListIfStaticGetInstanceIsMocked(){
        try(MockedStatic<PersonRepository> personRepMockedStatic = mockStatic(PersonRepository.class)){
            personRepMockedStatic.when(PersonRepository::getInstance).thenReturn(personRepository);
            when(personRepository.getStudentsByCourseId(anyInt())).thenReturn(students);

            courseService = new CourseService();
            courseService.printStudentsOfThisCourse(courses.get(1));
        } catch (EntityNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void PrintTeachersFromGivenListIfStaticGetInstanceIsMocked(){
        try(MockedStatic<PersonRepository> personRepMockedStatic = mockStatic(PersonRepository.class)){
            personRepMockedStatic.when(PersonRepository::getInstance).thenReturn(personRepository);
            when(personRepository.getTeachersByCourseId(anyInt())).thenReturn(teachers);

            courseService = new CourseService();
            courseService.printTeachersOfThisCourse(courses.get(1));
        } catch (EntityNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void PrintHomeworksFromGivenListIfStaticGetInstanceIsMocked(){
        try(MockedStatic<HomeworkRepository> homeworkRepMockedStatic = mockStatic(HomeworkRepository.class)){
            homeworkRepMockedStatic.when(HomeworkRepository::getInstance).thenReturn(homeworkRepository);
            when(homeworkRepository.getByCourseId(anyInt())).thenReturn(homeworks);

            courseService = new CourseService();
            courseService.printHomeworksOfThisCourse(courses.get(1));
        } catch (EntityNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void PrintAddMatsFromGivenListIfStaticGetInstanceIsMocked(){
        try(MockedStatic<AdditionalMaterialRepository> addMatRepMockedStatic = mockStatic(AdditionalMaterialRepository.class)){
            addMatRepMockedStatic.when(AdditionalMaterialRepository::getInstance).thenReturn(materialRep);
            when(materialRep.getByCourseId(anyInt())).thenReturn(materials);

            courseService = new CourseService();
            courseService.printAddMatsOfThisCourse(courses.get(2));
        } catch (EntityNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @AfterAll
    static void clearAll(){
        courses.clear(); lectures.clear(); students.clear(); teachers.clear();
        homeworks.clear(); materials.clear();
    }
}
