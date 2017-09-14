package com.vaadin.flow.todomvc.model.data.beans;

import java.util.UUID;

public class Todo {
    private String id;
    private String text;
    private boolean completed;

    public Todo(String text) {
        this(UUID.randomUUID().toString(), text, false);
    }

    public Todo(String id, String text, boolean completed) {
        this.id = id;
        this.text = text;
        this.completed = completed;
    }

    private Todo() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
