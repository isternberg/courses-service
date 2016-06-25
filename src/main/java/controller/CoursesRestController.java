package controller;

import bootstrap.DatabaseBootstrap;
import model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.CourseRepository;

@RestController
@EnableAutoConfiguration
@EnableJpaRepositories("repository")
@EntityScan("model")
@RequestMapping("/courses")
public class CoursesRestController {

    @Autowired
    CourseRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Course> getCourses(){
		return repository.findAll();
	}


	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Course getCourseById(@PathVariable(value = "id") Long id){
		return repository.findOne(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Course> addCourse(@RequestBody Course course){
		repository.save(course);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Course> deleteCourse(@PathVariable(value = "id") Long id){
		repository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @Bean
    public DatabaseBootstrap databaseBootstrap(){
        return new DatabaseBootstrap();
    }

	public static void main(String[] args) {
		SpringApplication.run(CoursesRestController.class, args);
	}
}
