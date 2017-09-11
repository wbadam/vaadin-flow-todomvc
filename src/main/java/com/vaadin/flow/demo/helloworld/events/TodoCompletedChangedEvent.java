package com.vaadin.flow.demo.helloworld.events;

import com.vaadin.flow.demo.helloworld.beans.Todo;

public class TodoCompletedChangedEvent {

    private final Todo todo;

    public TodoCompletedChangedEvent(Todo todo) {
        this.todo = todo;
    }

    public Todo getTodo() {
        return todo;
    }
}
