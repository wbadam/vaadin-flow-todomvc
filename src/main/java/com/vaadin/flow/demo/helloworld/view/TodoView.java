package com.vaadin.flow.demo.helloworld.view;

import java.util.List;

import com.vaadin.flow.demo.helloworld.model.data.beans.Todo;

public interface TodoView extends HasEventBus {
    void addTodo(Todo todo);
    void setTodos(List<Todo> todos);
    void removeTodo(Todo todo);
    void setCompleted(Todo todo, boolean completed);
}
