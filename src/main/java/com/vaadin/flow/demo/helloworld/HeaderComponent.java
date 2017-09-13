package com.vaadin.flow.demo.helloworld;

import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.controller.events.AddTodoEvent;
import com.vaadin.flow.demo.helloworld.view.HasEventBus;
import com.vaadin.flow.html.H1;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Input;

@Tag("header")
public class HeaderComponent extends HtmlContainer implements HasEventBus {

    private Input newTodo;
    private EventBus eventBus;

    public HeaderComponent() {
        setClassName("header");
        add(new H1("todos"));

        newTodo = new Input();
        newTodo.addClassName("new-todo");
        newTodo.setPlaceholder("What needs to be done?");
        newTodo.getElement().setAttribute("autofocus", "");

        newTodo.addChangeListener(event -> {
            String value = newTodo.getValue();
            if (value != null && value.trim().length() > 0) {
                String title = value.trim();
                newTodo.setValue("");

                getTodoEventBus().post(new AddTodoEvent(title));
            }
        });

        add(newTodo);
    }

    @Override
    public void setTodoEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public EventBus getTodoEventBus() {
        return eventBus;
    }
}
