package controller;

import exception.CourseNotFoundException;
import model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.TeachersRepository;

@RestController
@EnableAutoConfiguration
@EnableJpaRepositories("repository")
@EntityScan("model")
@RequestMapping("/teachers")
class TeachersRestController {

    @Autowired
    TeachersRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(TeachersRestController.class);

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Teacher> list(){
        return repository.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Teacher read(@PathVariable(value = "id") Long id){
        Teacher teacher = repository.findOne(id);
        if (teacher == null){
            logger.debug("Attempt to read a non existing resource was made.");
            throw new CourseNotFoundException("Teacher with id " + id + " not found.");
        }
        return teacher;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher){
        repository.save(teacher);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Teacher> delete(@PathVariable(value = "id") Long id){
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK); //TODO: should this be void?
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Teacher> update(@PathVariable(value = "id") Long id,
                                          @RequestBody  Teacher teacher){
        //TODO: repository.update(id, teacher);
        return new ResponseEntity<>(HttpStatus.OK); //TODO: should this be void?
    }

}
