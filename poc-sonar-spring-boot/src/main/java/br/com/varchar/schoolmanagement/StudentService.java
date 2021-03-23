package br.com.varchar.schoolmanagement;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Flux<Student> findByName(String name) {
        return this.studentRepository.findByNameLike(name);
    }

    public Flux<Student> findAll(){
        return studentRepository.findAll();
    }

    public Mono<Student> save(Student student) {
        return studentRepository.save(student);
    }

    public Flux<Student> findStudents(String name) {
        if (name != null && !name.isEmpty()) return findByName(name);
        return findAll();
    }
}
