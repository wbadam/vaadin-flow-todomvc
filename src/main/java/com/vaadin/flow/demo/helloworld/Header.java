package com.vaadin.flow.demo.helloworld;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.html.H1;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Input;
import com.vaadin.flow.router.View;

@Tag("header")
public class Header extends HtmlContainer implements View {
    public Header() {
        setClassName("header");
        add(new H1("todos"));

        Input input = new Input();
        input.addClassName("new-todo");
        input.setPlaceholder("What needs to be done?");
        input.getElement().setAttribute("autofocus", "");
        add(input);
    }
}
