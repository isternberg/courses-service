package controller;

import bootstrap.DatabaseBootstrap;
import model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import repository.CourseRepository;

@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableJpaRepositories("repository")
@EntityScan("model")
public class CoursesRestController {

    @Autowired
    CourseRepository repository;

	@RequestMapping(value = "/courses", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Course> getCourses(){
		return repository.findAll();
	}

    @Bean
    public DatabaseBootstrap databaseBootstrap(){
        return new DatabaseBootstrap();
    }

	public static void main(String[] args) {

		SpringApplication.run(CoursesRestController.class, args);
	}
}
