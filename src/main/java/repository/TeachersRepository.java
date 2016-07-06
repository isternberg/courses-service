package repository;

import model.Teacher;
import org.springframework.data.repository.CrudRepository;

//public interface TeachersRepository extends PagingAndSortingRepository<Teacher, Long> {
public interface TeachersRepository extends CrudRepository<Teacher, Long> {
    Teacher findByLastname(String lastname);
}
