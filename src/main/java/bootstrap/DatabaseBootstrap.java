package bootstrap;

import model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CourseRepository;

import java.math.BigDecimal;
import java.util.Collections;

public class DatabaseBootstrap implements InitializingBean {

    @Autowired
    CourseRepository repository;

    private static Logger log = LoggerFactory.getLogger(DatabaseBootstrap.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        if(repository.findByTitle("Music Theory") == null){
            Course musicTheory = new Course("Music Theory", Collections.emptyList(), new BigDecimal(99.99));
            repository.save(musicTheory);
            log.info("Music Theory created.");
        }

        log.info("Bootstrapping finished."); //FIXME why not shown?
        log.info("***TESTTTTTTTTTTTTT!!!!!!"); //FIXME why not shown?
    }
}
