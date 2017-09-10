package com.vaadin.flow.demo.helloworld;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.TodoItem;
import com.vaadin.flow.html.HtmlContainer;

@Tag(Tag.UL)
public class TodoList extends HtmlContainer {
    List<TodoListItem> listItems;

    public TodoList(List<TodoItem> todos) {
        addClassName("todo-list");

        listItems = new ArrayList<>();

        todos.forEach(todo -> listItems.add(new TodoListItem(todo)));

        listItems.forEach(this::add);
    }
}
