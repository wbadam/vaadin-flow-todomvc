package com.vaadin.flow.demo.helloworld.model.data;

import java.util.Collection;
import java.util.List;

import com.vaadin.flow.demo.helloworld.view.HasEventBus;

public interface DataHandler <T> extends HasEventBus {

    void add(T item);
    void remove(T item);
    void remove(Collection<T> items);
    void setCompleted(T item, boolean completed);
    List<T> getAll();
}
