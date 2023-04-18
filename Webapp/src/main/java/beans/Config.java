package beans;

import dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class Config {
    @Autowired
    private Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("DRIVER"));
        dataSource.setUrl(environment.getRequiredProperty("URL"));
        dataSource.setUsername(environment.getRequiredProperty("USER"));
        dataSource.setPassword(environment.getRequiredProperty("PASSWORD"));

        return dataSource;
    }

    @Bean
    @Scope("singleton")
    public AddMatRepositoryDAO addMatRepositoryDAO() {
        return new AddMatRepositoryDAO(dataSource());
    }

    @Bean
    @Scope("singleton")
    public CourseRepositoryDAO courseRepositoryDAO() {
        return new CourseRepositoryDAO(dataSource());
    }

    @Bean
    @Scope("singleton")
    public HomeworkRepositoryDAO homeworkRepositoryDAO() {
        return new HomeworkRepositoryDAO(dataSource());
    }

    @Bean
    @Scope("singleton")
    public LectureRepositoryDAO lectureRepositoryDAO() {
        return new LectureRepositoryDAO(dataSource());
    }

    @Bean
    @Scope("singleton")
    public StudentRepositoryDAO studentRepositoryDAO() {
        return new StudentRepositoryDAO(dataSource());
    }

    @Bean
    @Scope("singleton")
    public TeacherRepositoryDAO teacherRepositoryDAO() {
        return new TeacherRepositoryDAO(dataSource());
    }
}
