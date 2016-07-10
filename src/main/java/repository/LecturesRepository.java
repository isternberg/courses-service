package repository;

import model.Lecture;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LecturesRepository extends PagingAndSortingRepository<Lecture, Long> {

    Lecture findByTitle(String lecture);

    List<Lecture> findByTitleContaining(@Param("title") String title);
}
