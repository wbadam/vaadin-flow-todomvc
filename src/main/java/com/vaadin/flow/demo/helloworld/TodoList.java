package com.vaadin.flow.demo.helloworld;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.Todo;
import com.vaadin.flow.html.HtmlContainer;

@Tag(Tag.UL)
public class TodoList extends HtmlContainer {

    private List<TodoListItem> listItems;

    public TodoList(List<Todo> todos) {
        addClassName("todo-list");

        setTodos(todos);
    }

    public void setTodos(List<Todo> todos) {
        // TODO Make it more efficient
        removeAll();
        listItems = new ArrayList<>();
        todos.forEach(todo -> listItems.add(new TodoListItem(todo)));
        listItems.forEach(this::add);
    }
}
