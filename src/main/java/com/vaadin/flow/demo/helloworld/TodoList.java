package com.vaadin.flow.demo.helloworld;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.model.TodoModel;
import com.vaadin.flow.html.HtmlContainer;

@Tag(Tag.UL)
public class TodoList extends HtmlContainer {

    private final TodoModel todoModel;
    private List<TodoListItem> listItems;

    public TodoList(TodoModel todoModel) {
        addClassName("todo-list");

        this.todoModel = todoModel;

        refresh();
    }

    public void refresh() {
        // TODO Make it more efficient
        removeAll();
        listItems = new ArrayList<>();
        todoModel.getTodos().forEach(todo -> {
            TodoListItem listItem = new TodoListItem(todo);
            listItem.setEventBus(todoModel.getEventBus());
            listItems.add(listItem);
        });
        listItems.forEach(this::add);
    }
}
