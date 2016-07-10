package service;

import model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.CoursesRepository;

@Service
public class CoursesService {

    @Autowired
    CoursesRepository repository;


    public Iterable<Course> list(){
        return repository.findAll();
    }

    public Course read(Long id){
        return repository.findOne(id);
    }

    public Course create(Course course){
        return repository.save(course);
    }

    public void delete(Long id){
        repository.delete(id);
    }

    @Transactional
    public Course update(Long id, Course update){
        Course course = repository.findOne(id);
        if( update  != null && course != null ) {
            course.setTitle(update.getTitle());
            course.setPrice(update.getPrice());
        }
        return repository.save(course);
    }


}

