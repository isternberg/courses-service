package controller;

import bootstrap.DatabaseBootstrap;
import exception.CourseNotFoundException;
import model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@EnableAutoConfiguration
@EnableJpaRepositories("repository")
@EntityScan("model")
@RequestMapping("/courses")
public class CoursesRestController {

    @Autowired
    CourseRepository repository;

	private static final Logger logger = LoggerFactory.getLogger(CoursesRestController.class);

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Course> list(){
		return repository.findAll();
	}


	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Course read(@PathVariable(value = "id") Long id){
		Course course = repository.findOne(id);
		if (course == null){
			logger.debug("Attempt to read a non existing resource was made.");
			throw new CourseNotFoundException("Course with id " + id + " not found.");
		}
		return course;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Course> create(@RequestBody Course course){
		repository.save(course);
		return new ResponseEntity<>(course, HttpStatus.OK);
	}

	@RequestMapping(value="/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Course> delete(@PathVariable(value = "id") Long id){
		repository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK); //TODO: should this be void?
	}

	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Course> update(@PathVariable(value = "id") Long id, @RequestBody  Course course){
		repository.update(id, course);
		return new ResponseEntity<>(HttpStatus.OK); //TODO: should this be void?
	}

	//Fixme controller advice
	@ExceptionHandler(CourseNotFoundException.class)
	public void handleCourseNotFoundException(CourseNotFoundException exception,
											  HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
	}

    @Bean
    public DatabaseBootstrap databaseBootstrap(){
        return new DatabaseBootstrap();
    }

	public static void main(String[] args) {
		SpringApplication.run(CoursesRestController.class, args);
	}
}