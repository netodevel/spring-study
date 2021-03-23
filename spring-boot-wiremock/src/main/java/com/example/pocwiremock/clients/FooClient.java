package com.example.pocwiremock.clients;

import com.example.pocwiremock.domain.Foo;
import feign.RequestLine;

import java.util.List;

public interface FooClient {

    @RequestLine("GET /foo")
    List<Foo> findAll();

    @RequestLine("GET /bar")
    List<Foo> findAllBar();

    @RequestLine("POST /create")
    Foo create(Foo foo);

}
