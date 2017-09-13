package com.vaadin.flow.demo.helloworld.controller.events;

public class CompleteAllTodosEvent {

    boolean completed;

    public CompleteAllTodosEvent(boolean selected) {
        this.completed = selected;
    }

    public boolean isCompleted() {
        return completed;
    }
}
