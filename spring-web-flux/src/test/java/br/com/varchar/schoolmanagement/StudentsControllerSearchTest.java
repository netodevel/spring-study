package br.com.varchar.schoolmanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = StudentController.class)
public class StudentsControllerSearchTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private StudentService studentService;

    @Test
    public void shouldReturnStatusCode200() {
        Flux<Student> fakeReturn = Flux.just(new Student("Jose"), new Student("Joao"));
        Mockito.when(studentService.findAll()).thenReturn(fakeReturn);

        webTestClient.get()
                .uri("/api/students?name=Joao")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void shouldReturnJoao() {
        Flux<Student> fakeReturn = Flux.just(new Student("Joao"));
        Mockito.when(studentService.findStudents(any())).thenReturn(fakeReturn);

        webTestClient.get()
                .uri("/api/students?name=Joao")
                .exchange()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Joao");
    }
}
