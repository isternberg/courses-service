package controller;


import bootstrap.DatabaseBootstrap;
import exception.LectureNotFoundException;
import model.AdvancedLecture;
import model.Lecture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.LecturesService;
import validation.LecturesValidator;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@RestController
//@EnableAutoConfiguration
//@EntityScan("model")
//@RequestMapping("/courses")
//@ComponentScan({"validation", "service"})
@RestController
@EnableAutoConfiguration
@EnableJpaRepositories("repository")
@EntityScan("model")
@RequestMapping("/lectures")
@ComponentScan({"validation", "service"})
public class LecturesRestController {

    @Autowired
    LecturesService service;

    @Autowired
    LecturesValidator validator; // TODO validation takes place here or at service?

    private static final Logger logger = LoggerFactory.getLogger(LecturesRestController.class);

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Lecture> list(){
        return service.list();
    }


    //TODO: check
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Lecture read(@PathVariable(value = "id") Long id){
        Lecture lecture = service.read(id);
        if (lecture == null){
            logger.debug("Attempt to read a non existing resource was made.");
            throw new LectureNotFoundException("Lecture with id " + id + " not found.");

        }
        return lecture;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Lecture> create(@RequestBody Lecture lecture){
        service.create(lecture);
        return new ResponseEntity<>(lecture, HttpStatus.CREATED);
    }

    //TODO: what happens when deleting a perquisite?
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Lecture> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Lecture> update(@PathVariable(value = "id") Long id,
                                          @RequestBody Lecture lecture){
		if (lecture instanceof AdvancedLecture && !validator.validate((AdvancedLecture) lecture)){
//			throw new CyclicRequirementException();
			return new ResponseEntity<>(HttpStatus.FAILED_DEPENDENCY);
		}
        service.update(id, lecture);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Fixme controller advice
    @ExceptionHandler(LectureNotFoundException.class)
    public void handleCourseNotFoundException(LectureNotFoundException exception,
                                              HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @Bean
    public DatabaseBootstrap databaseBootstrap(){
        return new DatabaseBootstrap();
    }

    public static void main(String[] args) {
        SpringApplication.run(LecturesRestController.class, args);
    }
}


