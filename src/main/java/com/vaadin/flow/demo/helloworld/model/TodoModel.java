package com.vaadin.flow.demo.helloworld.model;

import java.util.List;

import com.google.common.eventbus.EventBus;
import com.vaadin.flow.demo.helloworld.beans.Todo;
import com.vaadin.flow.demo.helloworld.data.TodoDataHandler;

public class TodoModel {

    private final List<Todo> todos;
    private final TodoDataHandler dataHandler;
    private final EventBus eventBus = new EventBus();

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

    public void markAllAsComplete(boolean complete) {
        todos.forEach(todo -> todo.setCompleted(complete));
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
