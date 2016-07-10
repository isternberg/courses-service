package bootstrap;

import model.Course;
import model.Lecture;
import model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CoursesRepository;
import repository.LecturesRepository;
import repository.TeachersRepository;

import java.math.BigDecimal;

public class DatabaseBootstrap implements InitializingBean {

    @Autowired
    TeachersRepository teachersRepository;

    @Autowired
    LecturesRepository lecturesRepository;


    @Autowired
    CoursesRepository coursesRepository;

    private static Logger log = LoggerFactory.getLogger(DatabaseBootstrap.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        Teacher johnSmith = new Teacher("John", "Smith");
        Teacher mariaChrist = new Teacher("Maria", "Christ");



        if(teachersRepository.findByLastname("Smith") == null){
            teachersRepository.save(johnSmith);
            log.info("Teacher John Smith was created.");
        }

        if(teachersRepository.findByLastname("Christ") == null){
            teachersRepository.save(mariaChrist);
            log.info("Teacher Maria Christ was created.");
        }


        if(lecturesRepository.findByTitle("Music Theory") == null){
            Lecture musicTheory = new Lecture("Music Theory", new BigDecimal(99.99));
            musicTheory.setTeacher(johnSmith);
            lecturesRepository.save(musicTheory);
            log.info("Music Theory created.");
        }
        if(lecturesRepository.findByTitle("Jazz Improvisation") == null){
            Lecture jazzImpro = new Lecture("Jazz Improvisation", new BigDecimal(58.99), mariaChrist);
            lecturesRepository.save(jazzImpro);
            log.info("Jazz Improvisation created.");
        }


        if(coursesRepository.findByTitle("Music Theory") == null){
            Course musicTheory = new Course("Music Theory", new BigDecimal(99.99));
            musicTheory.setTeacher(johnSmith);
            coursesRepository.save(musicTheory);
            log.info("Music Theory created.");
        }
        if(coursesRepository.findByTitle("Jazz Improvisation") == null){
            Course jazzImpro = new Course("Jazz Improvisation", new BigDecimal(58.99), mariaChrist);
            coursesRepository.save(jazzImpro);
            log.info("Jazz Improvisation created.");
        }



        log.info("Bootstrapping finished.");
    }
}
