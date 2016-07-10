package repository;

import model.Teacher;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TeachersRepository extends PagingAndSortingRepository<Teacher, Long> {
    Teacher findByLastname(String lastname);

}
