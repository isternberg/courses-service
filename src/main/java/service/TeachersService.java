package service;

import model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import repository.TeachersRepository;

public class TeachersService {
    @Autowired
    TeachersRepository repository;

    public Iterable<Teacher> list(){
        return repository.findAll();
    }

    public Teacher read(Long id){
        return repository.findOne(id);
    }

    public Teacher create(Teacher teacher){
        return repository.save(teacher);
    }

    public void delete(Long id){
        repository.delete(id);
    }

    @Transactional
    public Teacher update(Long id, Teacher update){
        Teacher teacher = repository.findOne(id);
        if( update  != null && teacher != null ) {
            teacher.setFirstname(update.getFirstname());
            teacher.setLastname(update.getLastname());
        }
        return repository.save(teacher);
    }
}
