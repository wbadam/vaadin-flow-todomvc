package com.vaadin.flow.demo.helloworld.controller.events;

public class AddTodoEvent {
    private final String title;

    public AddTodoEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
