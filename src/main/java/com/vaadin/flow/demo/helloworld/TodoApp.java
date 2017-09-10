package com.vaadin.flow.demo.helloworld;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.router.View;

@Tag("section")
public class TodoApp extends HtmlContainer implements View {

    public TodoApp() {
        setClassName("todoapp");

        add(new Header());
        add(new Main());
    }
}
