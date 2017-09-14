package com.vaadin.flow.todomvc.model;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.common.eventbus.EventBus;
import com.vaadin.flow.todomvc.model.data.DataHandler;
import com.vaadin.flow.todomvc.model.data.beans.Todo;
import com.vaadin.flow.todomvc.view.HasEventBus;

public class TodoModel implements HasEventBus {

    private final List<Todo> todos;
    private final DataHandler<Todo> dataHandler;

    public TodoModel(DataHandler<Todo> dataHandler) {
        this.dataHandler = dataHandler;
        todos = dataHandler.getAll();
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public List<Todo> getTodos(Predicate<Todo> filter) {
        return todos.stream().filter(filter).collect(Collectors.toList());
    }

    public void addTodo(Todo todo) {
        dataHandler.add(todo);
    }

    public void removeTodo(Todo todo) {
        dataHandler.remove(todo);
    }

    public void removeIf(Predicate<Todo> filter) {
        dataHandler.remove(todos.stream().filter(filter)
                .collect(Collectors.toList()));
    }

    public void markAsCompleted(Todo todo, boolean complete) {
        dataHandler.setCompleted(todo, complete);
    }

    public void markAllAsComplete(boolean complete) {
        dataHandler.setCompleted(dataHandler.getAll(), complete);
    }

    @Override
    public void setTodoEventBus(EventBus eventBus) {
        dataHandler.setTodoEventBus(eventBus);
    }

    @Override
    public EventBus getTodoEventBus() {
        throw new UnsupportedOperationException();
    }
}
