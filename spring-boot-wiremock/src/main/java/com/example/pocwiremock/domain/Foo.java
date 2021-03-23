package com.example.pocwiremock.domain;

public class Foo {

    public Integer id;
    public String bar;

    public Foo(){
    }

    public Foo(String bar) {
        this.bar = bar;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

