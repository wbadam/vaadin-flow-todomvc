package com.vaadin.flow.todomvc.view;

import java.util.List;

import com.vaadin.flow.todomvc.controller.TodoApplication.Filter;
import com.vaadin.flow.todomvc.model.data.beans.Todo;

public interface TodoView extends HasEventBus {
    void addTodo(Todo todo);
    void setTodos(List<Todo> todos, int countAll, int countActive);
    void removeTodo(Todo todo);
    void setCompleted(Todo todo, boolean completed);
    void setFilter(Filter filter);
}
