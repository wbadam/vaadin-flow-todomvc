package com.vaadin.flow.demo.helloworld.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.vaadin.flow.demo.helloworld.beans.Todo;

public class TodoDataHandler implements DataHandler<Todo> {

    private List<Todo> todos = new ArrayList<>();

    @Override
    public void add(Todo todo) {
        todos.add(todo);
    }

    @Override
    public void remove(Todo todo) {
        todos.remove(todo);
    }

    public void removeAll(Collection<Todo> removableTodos) {
        todos.removeAll(removableTodos);
    }

    @Override
    public void setCompleted(Todo todo, boolean completed) {
        todo.setCompleted(completed);
    }

    @Override
    public List<Todo> getAll() {
        return Collections.unmodifiableList(todos);
    }
}
