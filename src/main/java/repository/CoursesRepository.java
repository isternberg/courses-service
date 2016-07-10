package repository;

import model.Course;
import model.Lecture;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CoursesRepository extends PagingAndSortingRepository<Course, Long> {

    Lecture findByTitle(String course);

    List<Lecture> findByTitleContaining(@Param("title") String title);
}
