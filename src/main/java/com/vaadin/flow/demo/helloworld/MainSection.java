package com.vaadin.flow.demo.helloworld;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Input;
import com.vaadin.flow.html.Label;

@Tag("section")
public class MainSection extends HtmlContainer {

    private Input toggleAll;

    public MainSection() {
        addClassName("main");

        setVisible(false);

        toggleAll = new Input();
        toggleAll.setType("checkbox");
        toggleAll.setClassName("toggle-all");

        Label toggleAllLabel = new Label("Mark all as complete");
        toggleAllLabel.setFor("toggle-all");

        add(toggleAll, toggleAllLabel);

//        add(new TodoList(Arrays.asList(new TodoListItem())));
    }

    public void setVisible(boolean visible) {
        if (visible) {
            getElement().getStyle().remove("display");
        } else {
            getElement().getStyle().set("display", "none");
        }
    }
}
