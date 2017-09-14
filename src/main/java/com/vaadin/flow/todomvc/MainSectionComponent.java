package com.vaadin.flow.todomvc;

import java.util.List;

import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.todomvc.model.data.beans.Todo;
import com.vaadin.flow.todomvc.components.NativeCheckbox;
import com.vaadin.flow.todomvc.controller.events.CompleteAllTodosEvent;
import com.vaadin.flow.todomvc.view.HasEventBus;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Label;

@Tag("section")
public class MainSectionComponent extends HtmlContainer implements HasEventBus {

    private EventBus eventBus;

    private NativeCheckbox toggleAll;
    private TodoListComponent todoList;

    private int countAll;
    private int countActive;

    public MainSectionComponent() {
        addClassName("main");

        toggleAll = new NativeCheckbox();
        toggleAll.setType("checkbox");
        toggleAll.setClassName("toggle-all");

        Label toggleAllLabel = new Label("Mark all as complete");
        toggleAllLabel.setFor("toggle-all");
        toggleAllLabel.getElement().addEventListener("click", event -> {
            toggleAll.toggle();
            getTodoEventBus()
                    .post(new CompleteAllTodosEvent(toggleAll.isChecked()));
        });

        add(toggleAll, toggleAllLabel);

        todoList = new TodoListComponent();
        add(todoList);
    }

    void setTodos(List<Todo> todos, int countAll, int countActive) {
        this.countAll = countAll;
        this.countActive = countActive;

        todoList.setTodos(todos);

        toggleAll.setChecked(!isIncompleteExists());
    }

    void setCompleted(Todo todo, boolean completed) {
        todoList.setCompleted(todo, completed);

        toggleAll.setChecked(completed && !isIncompleteExists());
    }

    private boolean isIncompleteExists() {
        return countActive > 0;
    }

    @Override
    public void setTodoEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        todoList.setTodoEventBus(eventBus);
    }

    @Override
    public EventBus getTodoEventBus() {
        return eventBus;
    }
}
