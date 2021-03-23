package com.example.pocwiremock.resource;

import com.example.pocwiremock.FeignBuilder;
import com.example.pocwiremock.clients.FooClient;
import com.example.pocwiremock.domain.Foo;
import com.example.pocwiremock.helper.RestTemplateHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FooController {

    private RestTemplateHelper restTemplateHelper = new RestTemplateHelper();

    @GetMapping("/foo")
    public ResponseEntity<List<Foo>> index() {
        FooClient fooClient = new FeignBuilder().createClient(FooClient.class, "http://localhost:8081");
        return new ResponseEntity<>(fooClient.findAll(), HttpStatus.OK);
    }

    @GetMapping("/bar")
    public ResponseEntity<List<Foo>> indexBar() {
        FooClient fooClient = new FeignBuilder().createClient(FooClient.class, "http://localhost:8081");
        return new ResponseEntity<>(fooClient.findAllBar(), HttpStatus.OK);
    }

    @PostMapping("/create-foo")
    public ResponseEntity<Foo> indexBar(@RequestBody Foo foo) {
        Foo fooCreated = restTemplateHelper.post(Foo.class, "http://localhost:8081/create", foo);
        return new ResponseEntity<>(fooCreated, HttpStatus.CREATED);
    }

    @GetMapping("/rest-foo")
    public ResponseEntity<List<Foo>> restFoo() {
        List<Foo> forNow = restTemplateHelper.get( List.class, "http://localhost:8081/foo");
        return new ResponseEntity<>(forNow, HttpStatus.OK);
    }

    @GetMapping("/rest-foo-params/{id}")
    public ResponseEntity<List<Foo>> restFooWithParams(@PathVariable("id") Integer id) {
        List<Foo> forNow = restTemplateHelper.get(List.class, "http://localhost:8081/foo/{id}", id);
        return new ResponseEntity<>(forNow, HttpStatus.OK);
    }

}
