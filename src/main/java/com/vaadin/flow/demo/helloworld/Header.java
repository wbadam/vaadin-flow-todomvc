package com.vaadin.flow.demo.helloworld;

import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.Todo;
import com.vaadin.flow.demo.helloworld.events.AddTodoEvent;
import com.vaadin.flow.html.H1;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Input;
import com.vaadin.flow.router.View;

@Tag("header")
public class Header extends HtmlContainer implements View {

    private Input newTodo;
    private EventBus eventBus;

    public Header() {
        setClassName("header");
        add(new H1("todos"));

        newTodo = new Input();
        newTodo.addClassName("new-todo");
        newTodo.setPlaceholder("What needs to be done?");
        newTodo.getElement().setAttribute("autofocus", "");

        newTodo.addChangeListener(event -> {
            String value = newTodo.getValue();
            if (value != null && value.trim().length() > 0) {
                Todo todo = new Todo(value.trim());
                newTodo.setValue("");

                eventBus.post(new AddTodoEvent(todo));
            }
        });

        add(newTodo);
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
