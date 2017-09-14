package com.vaadin.flow.todomvc;

import java.util.List;

import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.todomvc.model.data.beans.Todo;
import com.vaadin.flow.todomvc.components.StyleUtil;
import com.vaadin.flow.todomvc.view.TodoView;
import com.vaadin.flow.html.HtmlContainer;

@Tag("section")
public class TodoAppComponent extends HtmlContainer implements TodoView {

    private final HeaderComponent headerComponent;
    private final MainSectionComponent mainSectionComponent;
    private final FooterComponent footerComponent;

    private EventBus eventBus;

    private List<Todo> todos;

    public TodoAppComponent() {
        setClassName("todoapp");

        headerComponent = new HeaderComponent();

        mainSectionComponent = new MainSectionComponent();
        StyleUtil.setVisible(mainSectionComponent, false);

        footerComponent = new FooterComponent();

        add(headerComponent, mainSectionComponent, footerComponent);
    }

    @Override
    public void addTodo(Todo todo) {

    }

    @Override
    public void setTodos(List<Todo> todos) {
        this.todos = todos;
        mainSectionComponent.setTodos(todos);
        StyleUtil.setVisible(mainSectionComponent, todos.size() > 0);
        footerComponent.setTodoCount(todos.size(),
                (int) todos.stream().filter(todo -> !todo.isCompleted())
                        .count());
    }

    @Override
    public void removeTodo(Todo todo) {

    }

    @Override
    public void setCompleted(Todo todo, boolean completed) {
        mainSectionComponent.setCompleted(todo, completed);
        footerComponent.setTodoCount(todos.size(),
                (int) todos.stream().filter(t -> !t.isCompleted()).count());
    }

    @Override
    public void setTodoEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        headerComponent.setTodoEventBus(eventBus);
        mainSectionComponent.setTodoEventBus(eventBus);
        footerComponent.setTodoEventBus(eventBus);

        // Register event handler
        eventBus.register(this);
    }

    @Override
    public EventBus getTodoEventBus() {
        return eventBus;
    }
}
