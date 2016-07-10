package bootstrap;

import model.Course;
import model.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CourseRepository;
import repository.TeachersRepository;

import java.math.BigDecimal;

public class DatabaseBootstrap implements InitializingBean {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    TeachersRepository teachersRepository;

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


        if(courseRepository.findByTitle("Music Theory") == null){
            Course musicTheory = new Course("Music Theory", new BigDecimal(99.99));
            musicTheory.setTeacher(johnSmith);
            courseRepository.save(musicTheory);
            log.info("Music Theory created.");
        }
        if(courseRepository.findByTitle("Jazz Improvisation") == null){
            Course jazzImpro = new Course("Jazz Improvisation", new BigDecimal(58.99));
            courseRepository.save(jazzImpro);
            log.info("Jazz Improvisation created.");
        }

        log.info("Bootstrapping finished.");
    }
}
