package com.vaadin.flow.demo.helloworld.events;

import com.vaadin.flow.demo.helloworld.beans.Todo;

public class AddTodoEvent {
    private Todo todo;

    public AddTodoEvent(Todo todo) {
        this.todo = todo;
    }

    public Todo getTodo() {
        return todo;
    }
}
