package controller;


import exception.CourseNotFoundException;
import exception.CyclicRequirementException;
import exception.IllegalCourseDeleteException;
import model.AdvancedCourse;
import model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CoursesService;
import validation.CourseValidator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@EnableAutoConfiguration
@EnableJpaRepositories("repository")
@EntityScan("model")
@RequestMapping("/courses")
@ComponentScan({"validation", "service"})
public class CoursesRestController {


    @Autowired
    CoursesService service;


    @Autowired
    CourseValidator validator;

    private static final Logger logger = LoggerFactory.getLogger(CoursesRestController.class);

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Course> list(){
        return service.list();
    }


    //TODO: check
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Course read(@PathVariable(value = "id") Long id){
        Course course = service.read(id);
        if (course == null){
            logger.debug("Attempt to read a non existing resource was made.");
            throw new CourseNotFoundException("Lecture with id " + id + " not found.");
        }
        return course;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Course> create(@RequestBody Course course){
        service.create(course);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    //TODO: what happens when deleting a perquisite?
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Course> delete(@PathVariable(value = "id") Long id){

        Course toDeleteCourse = service.read(id);
        Iterable<Course> allLectures = service.list();
        if (validator.mayDelete(toDeleteCourse, allLectures)){
            service.delete(id);
        } else {
            throw new IllegalCourseDeleteException("May not delete course that is a prerequisite");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Course> update(@PathVariable(value = "id") Long id,
                                          @RequestBody Course course){
		if (course instanceof AdvancedCourse && !validator.mayAdd((AdvancedCourse) course)){
			throw new CyclicRequirementException();
		}
        service.update(id, course);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public void handleCourseNotFoundException(CourseNotFoundException exception,
                                              HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(CyclicRequirementException.class)
    public void handleCyclicRequirementException(CyclicRequirementException exception,
                                              HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(IllegalCourseDeleteException.class)
    public void handleCyclicRequirementException(IllegalCourseDeleteException exception,
                                                 HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

}


