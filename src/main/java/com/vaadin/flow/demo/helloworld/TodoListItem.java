package com.vaadin.flow.demo.helloworld;

import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.Todo;
import com.vaadin.flow.html.Div;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.Input;
import com.vaadin.flow.html.Label;
import com.vaadin.flow.html.NativeButton;

@Tag(Tag.LI)
public class TodoListItem extends HtmlContainer {

    private static String STYLE_NAME_COMPLETED = "completed";
    private static String STYLE_NAME_EDITING = "editing";

    private static class ListItemView extends Div {

        private Input toggle;
        private Label label;
        private NativeButton destroy;

        public ListItemView(String text) {
            addClassName("view");

            toggle = new Input();
            toggle.setType("checkbox");
            toggle.addClassName("toggle");
            toggle.addChangeListener(event -> {
                ((TodoListItem) getParent().get()).toggleCompleted();
            });
            add(toggle);

            label = new Label(text);
            add(label);

            destroy = new NativeButton();
            destroy.addClassName("destroy");
            add(destroy);
        }
    }

    private Input edit;
    private Todo todo;

    public TodoListItem(Todo todo) {
        this.todo = todo;

        ListItemView view = new ListItemView(todo.getText());
        add(view);

        updateStyleName();

        edit = new Input();
        edit.addClassName("edit");
        add(edit);
    }

    private void toggleCompleted() {
        todo.setCompleted(!todo.isCompleted());
        updateStyleName();
    }

    private void updateStyleName() {
        if (todo.isCompleted()) {
            addClassName(STYLE_NAME_COMPLETED);
        } else {
            removeClassName(STYLE_NAME_COMPLETED);
        }
    }
}
