package com.vaadin.flow.demo.helloworld.events;

import com.vaadin.flow.demo.helloworld.beans.Todo;

public class TodoDestroyedEvent {

    private final Todo todo;

    public TodoDestroyedEvent(Todo todo) {
        this.todo = todo;
    }

    public Todo getTodo() {
        return todo;
    }
}
