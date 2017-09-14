package com.vaadin.flow.todomvc.controller.events;

import com.vaadin.flow.todomvc.model.data.beans.Todo;

public class TodoDestroyedEvent {

    private final Todo todo;

    public TodoDestroyedEvent(Todo todo) {
        this.todo = todo;
    }

    public Todo getTodo() {
        return todo;
    }
}
