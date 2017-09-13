package com.vaadin.flow.demo.helloworld.model;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.eventbus.EventBus;
import com.vaadin.flow.demo.helloworld.model.data.beans.Todo;
import com.vaadin.flow.demo.helloworld.model.data.TodoDataHandler;

public class TodoModel {

    private final List<Todo> todos;
    private final TodoDataHandler dataHandler;

    public TodoModel() {
        dataHandler = new TodoDataHandler();
        todos = dataHandler.getAll();
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void addTodo(Todo todo) {
        dataHandler.add(todo);
    }

    public void removeTodo(Todo todo) {
        dataHandler.remove(todo);
    }

    public void removeIf(Predicate<Todo> filter) {
        dataHandler.removeAll(
                todos.stream().filter(filter).collect(Collectors.toList()));
    }

    public void markAllAsComplete(boolean complete) {
        todos.forEach(todo -> todo.setCompleted(complete));
    }
}
