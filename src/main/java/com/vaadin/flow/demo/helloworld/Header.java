package com.vaadin.flow.demo.helloworld;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.Todo;
import com.vaadin.flow.html.H1;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Input;
import com.vaadin.flow.router.View;

@Tag("header")
public class Header extends HtmlContainer implements View {

    private Input newTodo;
    private TodoHandler todoHandler;

    public Header(TodoHandler todoHandler) {
        this.todoHandler = todoHandler;

        setClassName("header");
        add(new H1("todos"));

        newTodo = new Input();
        newTodo.addClassName("new-todo");
        newTodo.setPlaceholder("What needs to be done?");
        newTodo.getElement().setAttribute("autofocus", "");

        newTodo.addChangeListener(event -> {
            if (newTodo.getValue().length() > 0) {
                Todo todo = new Todo(newTodo.getValue());
                todoHandler.addTodo(todo);
                newTodo.setValue("");
            }
        });

        add(newTodo);
    }
}
