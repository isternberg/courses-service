package service;

import model.Lecture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.LecturesRepository;

@Service
public class LecturesService {

    @Autowired
    LecturesRepository repository;


    public Iterable<Lecture> list(){
        return repository.findAll();
    }

    public Lecture read(Long id){
        return repository.findOne(id);
    }

    public Lecture create(Lecture lecture){
        return repository.save(lecture);
    }

    public void delete(Long id){
        repository.delete(id);
    }

    @Transactional
    public Lecture update(Long id, Lecture update){
        Lecture lecture = repository.findOne(id);
        if( update  != null && lecture != null ) {
            lecture.setTitle(update.getTitle());
            lecture.setPrice(update.getPrice());
        }
        return repository.save(lecture);
    }


}

