package com.vaadin.flow.demo.helloworld;

import java.util.List;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.html.HtmlContainer;

@Tag(Tag.UL)
public class TodoList extends HtmlContainer {
    List<TodoListItem> listItems;

    public TodoList(List<TodoListItem> listItems) {
        addClassName("todo-list");

        this.listItems = listItems;
        listItems.forEach(this::add);
    }
}
