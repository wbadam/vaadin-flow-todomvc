package com.vaadin.flow.demo.helloworld;

import java.util.List;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.Todo;
import com.vaadin.flow.demo.helloworld.components.NativeCheckbox;
import com.vaadin.flow.demo.helloworld.events.ClearCompletedEvent;
import com.vaadin.flow.demo.helloworld.events.MarkAllAsCompleteToggledEvent;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Label;

@Tag("section")
public class MainSection extends HtmlContainer {

    private EventBus eventBus;

    private NativeCheckbox toggleAll;
    private TodoList todoList;

    public MainSection(List<Todo> todos) {
        addClassName("main");

        toggleAll = new NativeCheckbox();
        toggleAll.setType("checkbox");
        toggleAll.setClassName("toggle-all");
        toggleAll.getElement().addPropertyChangeListener("checked", event -> {
            eventBus.post(new MarkAllAsCompleteToggledEvent(toggleAll.isChecked()));
        });

        Label toggleAllLabel = new Label("Mark all as complete");
        toggleAllLabel.setFor("toggle-all");
        toggleAllLabel.getElement().addEventListener("click", event -> {
            toggleAll.toggle();
        });

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

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Subscribe
    public void clearMarkAsCompleteToggle(ClearCompletedEvent event) {
        toggleAll.setChecked(false);
    }
}
