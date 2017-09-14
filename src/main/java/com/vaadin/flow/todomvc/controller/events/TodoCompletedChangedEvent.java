package com.vaadin.flow.todomvc.controller.events;

import com.vaadin.flow.todomvc.model.data.beans.Todo;

public class TodoCompletedChangedEvent {

    private final Todo todo;

    public TodoCompletedChangedEvent(Todo todo) {
        this.todo = todo;
    }

    public Todo getTodo() {
        return todo;
    }
}
