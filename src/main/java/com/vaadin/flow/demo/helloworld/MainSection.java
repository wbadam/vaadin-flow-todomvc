package com.vaadin.flow.demo.helloworld;

import java.util.Arrays;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Input;
import com.vaadin.flow.html.Label;

@Tag("section")
public class MainSection extends HtmlContainer {

    private Input toggleAll;

    public MainSection() {
        addClassName("main");

        toggleAll = new Input();
        toggleAll.setType("checkbox");
        toggleAll.setClassName("toggle-all");

        Label toggleAllLabel = new Label("Mark all as complete");
        toggleAllLabel.setFor("toggle-all");

        add(toggleAll, toggleAllLabel);

//        add(new TodoList(Arrays.asList(new TodoListItem())));
    }
}
