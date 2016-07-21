package controller;

import model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TeachersService;

@RestController
@EnableJpaRepositories("repository")
@EntityScan("model")
@RequestMapping("/teachers")
@ComponentScan
class TeachersRestController {

    @Autowired
    TeachersService service;

    private static final Logger logger = LoggerFactory.getLogger(TeachersRestController.class);

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Teacher> list(){
        return service.list();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Teacher read(@PathVariable(value = "id") Long id){
        Teacher teacher = service.read(id);
        if (teacher == null){
            logger.debug("Attempt to read a non existing resource was made.");
        }
        return teacher;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Teacher> create(@RequestBody Teacher teacher){
        service.create(teacher);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Teacher> delete(@PathVariable(value = "id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Teacher> update(@PathVariable(value = "id") Long id,
                                          @RequestBody  Teacher teacher){
        service.update(id, teacher);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
