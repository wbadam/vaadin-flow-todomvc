package com.vaadin.flow.todomvc.model.data;

import java.util.Collection;
import java.util.List;

import com.vaadin.flow.todomvc.view.HasEventBus;

public interface DataHandler <T> extends HasEventBus {

    void add(T item);
    void remove(T item);
    void remove(Collection<T> items);
    void setCompleted(T item, boolean completed);
    void setCompleted(Collection<T> items, boolean completed);
    List<T> getAll();
}
