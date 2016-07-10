package repository;

import model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// // FIXME: why searching doesnt work?

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    Course findByTitle(String title);

    List<Course> findByTitleContaining(@Param("title") String title);


    default Course update(Long id, Course update){ //TODO: is this cool to implemt it here,
                                                        // or should I use a service?
        Course course = findOne(id);
        if( update.getTitle() != null ) {
            course.setTitle(update.getTitle());
            course.setPrice(update.getPrice());
        }
        return save(course);
    }
}
