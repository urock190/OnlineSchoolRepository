package java.com.academy.repository;

import com.academy.exceptions.EntityNotFoundException;
import com.academy.models.Course;
import com.academy.models.Person;
import com.academy.models.Role;
import com.academy.repository.CourseRepository;
import com.academy.services.SimpleIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CourseRepositoryTest {
    private final CourseRepository courseRepository = mock(CourseRepository.class);
    private List<Course> courses;

    @BeforeEach
    void init(){
        courses = new ArrayList<>(List.of(new Course("first", new Person(Role.TEACHER, "A.","B."),
                        new Person(Role.STUDENT, "A.","C.")),
                new Course("second", new Person(Role.TEACHER, "B.","C."), new Person(Role.STUDENT, "B.","D.")),
                new Course("third", new Person(Role.TEACHER, "C.","D."), new Person(Role.STUDENT, "C.","J."))));
    }

    @Test
    void getAll(){
        assertTrue(courseRepository.getAll().isEmpty());
        when(courseRepository.getAll()).thenReturn(courses);
        assertTrue(courseRepository.getAll().containsAll(courses));
        assertFalse(courseRepository.getAll().isEmpty());
    }

    @Test
    void size(){
        assertEquals(0, courseRepository.size());
        when(courseRepository.size()).thenReturn(5);
        assertEquals(5, courseRepository.size());
        assertFalse(courseRepository.size() != 5);
    }

    @Test
    void isEmpty(){
        when(courseRepository.isEmpty()).thenReturn(true);
        assertTrue(courseRepository.isEmpty());
    }

    @Test
    void add(){
        doAnswer(invocationOnMock -> courses.add(new Course("fourth", new Person(Role.TEACHER, "F.","F"),
                new Person(Role.STUDENT, "J.","J.")))).
                when(courseRepository).add(any(Course.class));
        courseRepository.add(courses.get(0));
        verify(courseRepository).add(any(Course.class));
        assertEquals("fourth", courses.get(3).getName());
        assertEquals(4, courses.size());
    }

    @Test
    void get(){
        when(courseRepository.get(0)).thenReturn(courses.get(0));
        assertEquals("first", courseRepository.get(0).getName());
        assertNull(courseRepository.get(1));
        verify(courseRepository, times(2)).get(anyInt());
    }

    @Test
    void remove(){
        doAnswer(invocationOnMock -> courses.remove(1)).when(courseRepository).remove(anyInt());
        courseRepository.remove(70);
        verify(courseRepository).remove(anyInt());
        assertNotEquals("second", courses.get(1).getName());
        assertEquals("third", courses.get(1).getName());
        assertEquals(2, courses.size());
    }

    @Test
    void findAll(){
        doAnswer(invocationOnMock -> courseRepository.iterator()).when(courseRepository).findAll();
        courseRepository.findAll();
        verify(courseRepository).iterator();
    }

    @Test
    void getByID() throws EntityNotFoundException {
        when(courseRepository.getById(1)).thenReturn(courses.get(2));
        assertEquals("third", courseRepository.getById(1).getName());
        assertNull(courseRepository.getById(2));
        verify(courseRepository, times(2)).getById(anyInt());
    }

    @Test
    void deleteByID(){
        doAnswer(invocationOnMock -> {int arg = invocationOnMock.getArgument(0); assertEquals(1, arg);
        return null;}).when(courseRepository).deleteById(anyInt());
        courseRepository.deleteById(1);
    }

    @Test
    void ReturnsInstanceOfSimpleIteratorWhenTheMethodIsCalled(){
        when(courseRepository.iterator()).thenCallRealMethod();
        assertInstanceOf(SimpleIterator.class, courseRepository.iterator());
    }
}
