package br.com.varchar.schoolmanagement;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {

    Flux<Student> findAll();

    Flux<Student> findByNameLike(String name);
}
