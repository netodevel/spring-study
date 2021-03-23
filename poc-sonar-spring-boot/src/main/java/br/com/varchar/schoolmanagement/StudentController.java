package br.com.varchar.schoolmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/api/students")
    public Flux<Student> index(@RequestParam(value = "name", required = false) String name){
        return studentService.findStudents(name);
    }

    @PostMapping("/api/students")
    public Mono<ResponseEntity<Student>> create(@Valid @RequestBody Student student) {

        return studentService.save(student)
                .map(s -> ResponseEntity.status(201).body(s))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

}
