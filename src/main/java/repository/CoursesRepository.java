package repository;

import model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursesRepository extends PagingAndSortingRepository<Course, Long> {

    Course findByTitle(String course);

    /**
     * For Example:
     * http://localhost:8080/courses/search/findByTitleContaining?title=Jazz
     */
    List<Course> findByTitleContaining(@Param("title") String title);
}
