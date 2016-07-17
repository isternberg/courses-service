package repository;

import model.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeachersRepository extends PagingAndSortingRepository<Teacher, Long> {
    Teacher findByLastname(@Param("lastname") String lastname);

}
