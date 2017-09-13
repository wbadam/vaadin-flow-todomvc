package com.vaadin.flow.demo.helloworld.model.data;

import java.util.List;

public interface DataHandler <T> {

    void add(T todo);
    void remove(T todo);
    void setCompleted(T todo, boolean completed);
    List<T> getAll();
}
