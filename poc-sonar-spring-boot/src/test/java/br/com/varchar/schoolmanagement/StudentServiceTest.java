package br.com.varchar.schoolmanagement;

import org.junit.Test;
import reactor.core.publisher.Flux;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.util.Assert.notNull;

public class StudentServiceTest {

    private StudentRepository studentRepository = mock(StudentRepository.class);
    private StudentService studentService = new StudentService(studentRepository);

    @Test
    public void shouldReturnFlux() {
        when(studentRepository.findAll())
                .thenReturn(Flux.just(new Student("Jose Vieira"), new Student("Joao Maria")));
        notNull(studentService.findAll());
    }

    @Test
    public void shouldReturnJoseVieira() {
        when(studentRepository.findAll())
                .thenReturn(Flux.just(new Student("Jose Vieira"), new Student("Joao Maria")));

        String expectResult = "Jose Vieira";
        String valueReturned = studentService.findAll().blockFirst().getName();
        assertEquals(expectResult, valueReturned);
    }

}