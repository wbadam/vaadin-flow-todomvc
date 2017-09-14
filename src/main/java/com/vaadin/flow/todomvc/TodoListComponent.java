package com.vaadin.flow.todomvc;

import java.util.ArrayList;
import java.util.List;

import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.todomvc.model.data.beans.Todo;
import com.vaadin.flow.todomvc.view.HasEventBus;
import com.vaadin.flow.html.HtmlContainer;

@Tag(Tag.UL)
public class TodoListComponent extends HtmlContainer implements HasEventBus {

    private List<TodoListItem> listItems;
    private EventBus eventBus;

    public TodoListComponent() {
        addClassName("todo-list");
    }

    void setTodos(List<Todo> todos) {
        removeAll();
        listItems = new ArrayList<>();
        todos.forEach(todo -> {
            TodoListItem listItem = new TodoListItem(todo);
            listItem.setTodoEventBus(getTodoEventBus());
            listItems.add(listItem);
        });
        listItems.forEach(this::add);
    }

    void setCompleted(Todo todo, boolean completed) {
        listItems.stream().filter(item -> todo.equals(item.getTodo()))
                .forEach(item -> item.setCompleted(completed));
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
