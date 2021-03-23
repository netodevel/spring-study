package br.com.varchar.schoolmanagement;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Log4j2
@Component
public class StudentsInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        studentRepository.deleteAll()
                .thenMany(
                        Flux.just(new Student("Spring Boot"),new Student( "React"), new Student("Thymeleaf"), new Student("WebFlux"))
                        .flatMap(studentRepository::save)
                )
                .thenMany(studentRepository.findAll())
                .subscribe(student -> log.info("Saving ".concat(student.toString())));
    }
}
