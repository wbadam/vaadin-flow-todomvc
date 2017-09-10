package com.vaadin.flow.demo.helloworld;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.Todo;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.router.View;

@Tag("section")
public class TodoApp extends HtmlContainer implements View, TodoHandler {

    private List<Todo> todos = new ArrayList<>();

    private MainSection mainSection;

    public TodoApp() {
        setClassName("todoapp");

        add(new Header(this));

        mainSection = new MainSection(todos);
        mainSection.setVisible(false);
        add(mainSection);
    }

    @Override
    public void addTodo(Todo todo) {
        todos.add(todo);
        mainSection.refreshTodoList(todos);
        mainSection.setVisible(true);
    }
}
