package com.vaadin.flow.demo.helloworld;

import java.util.List;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.Todo;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Input;
import com.vaadin.flow.html.Label;

@Tag("section")
public class MainSection extends HtmlContainer {

    private Input toggleAll;
    private TodoList todoList;

    public MainSection(List<Todo> todos) {
        addClassName("main");

        toggleAll = new Input();
        toggleAll.setType("checkbox");
        toggleAll.setClassName("toggle-all");

        Label toggleAllLabel = new Label("Mark all as complete");
        toggleAllLabel.setFor("toggle-all");

        add(toggleAll, toggleAllLabel);

        todoList = new TodoList(todos);
        add(todoList);
    }

    public void refreshTodoList(List<Todo> todos) {
        todoList.setTodos(todos);
    }

    public void setVisible(boolean visible) {
        if (visible) {
            getElement().getStyle().remove("display");
        } else {
            getElement().getStyle().set("display", "none");
        }
    }
}
