package com.vaadin.flow.demo.helloworld;

import java.util.List;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.components.NativeCheckbox;
import com.vaadin.flow.demo.helloworld.events.ClearCompletedEvent;
import com.vaadin.flow.demo.helloworld.events.MarkAllAsCompleteToggledEvent;
import com.vaadin.flow.demo.helloworld.events.TodoCompletedChangedEvent;
import com.vaadin.flow.demo.helloworld.model.TodoModel;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Label;

@Tag("section")
public class MainSection extends HtmlContainer {

    private EventBus eventBus;

    private final TodoModel todoModel;

    private NativeCheckbox toggleAll;
    private TodoList todoList;

    public MainSection(TodoModel todoModel) {
        addClassName("main");

        this.todoModel = todoModel;

        toggleAll = new NativeCheckbox();
        toggleAll.setType("checkbox");
        toggleAll.setClassName("toggle-all");

        Label toggleAllLabel = new Label("Mark all as complete");
        toggleAllLabel.setFor("toggle-all");
        toggleAllLabel.getElement().addEventListener("click", event -> {
            toggleAll.toggle();
            eventBus.post(
                    new MarkAllAsCompleteToggledEvent(toggleAll.isChecked()));
        });

        add(toggleAll, toggleAllLabel);

        todoList = new TodoList(todoModel);
        add(todoList);
    }

    public void refreshTodoList() {
        todoList.refresh();
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

    @Subscribe
    public void todoCompletedChanged(TodoCompletedChangedEvent event) {
        boolean notAllCompleted = todoModel.getTodos().stream()
                .anyMatch(todo -> !todo.isCompleted());
        toggleAll.setChecked(!notAllCompleted);
    }
}
