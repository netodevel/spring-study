package br.com.varchar.schoolmanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = StudentController.class)
public class StudentsControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private StudentService studentService;

    @Test
    public void shouldReturnStatusCode200() {
        Flux<Student> fakeReturn = Flux.just(new Student("Jose"), new Student("Joao"));
        Mockito.when(studentService.findAll()).thenReturn(fakeReturn);

        webTestClient.get()
                .uri("/api/students")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void givenCreatedStudent_shouldReturnStatusCode201() {
        Mono<Student> fakeReturn = Mono.just(new Student("Fake Student"));
        Mockito.when(studentService.save(any())).thenReturn(fakeReturn);

        webTestClient.post()
                .uri("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new Student("Fake Student")), Student.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    public void givenCreatedStudent_whenServiceError_shouldReturnStatusCode400() {
        Mockito.when(studentService.save(any())).thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new Student("Fake Student")), Student.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void givenCreatedStudent_whenSuccess_shouldReturnStudentName() {
        Mono<Student> fakeReturn = Mono.just(new Student("Jose"));
        Mockito.when(studentService.save(any())).thenReturn(fakeReturn);

        webTestClient.post()
                .uri("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(new Student("Fake Student")), Student.class)
                .exchange()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Jose");
    }


}
