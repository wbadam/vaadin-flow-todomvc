package com.vaadin.flow.demo.helloworld;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.TodoItem;
import com.vaadin.flow.html.Div;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Input;
import com.vaadin.flow.html.Label;
import com.vaadin.flow.html.NativeButton;

@Tag(Tag.LI)
public class TodoListItem extends HtmlContainer {

    private static String STYLE_NAME_COMPLETED = "completed";
    private static String STYLE_NAME_EDITING = "editing";

    public static class ListItemView extends Div {

        private Input toggle;
        private Label label;
        private NativeButton destroy;

        public ListItemView(String text) {
            addClassName("view");

            toggle = new Input();
            toggle.setType("checkbox");
            toggle.addClassName("toggle");
            add(toggle);

            label = new Label(text);
            add(label);

            destroy = new NativeButton();
            destroy.addClassName("destroy");
            add(destroy);
        }
    }

    private Input edit;
    private TodoItem todo;

    public TodoListItem(TodoItem todo) {
        this.todo = todo;

        ListItemView view = new ListItemView(todo.getText());
        add(view);

        if (todo.isCompleted()) {
            setClassName(STYLE_NAME_COMPLETED);
        }

        edit = new Input();
        edit.addClassName("edit");
        add(edit);
    }
}
