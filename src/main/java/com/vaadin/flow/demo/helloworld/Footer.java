package com.vaadin.flow.demo.helloworld;

import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.components.StyleUtil;
import com.vaadin.flow.demo.helloworld.events.AddTodoEvent;
import com.vaadin.flow.demo.helloworld.events.MarkAllAsCompleteToggledEvent;
import com.vaadin.flow.demo.helloworld.events.TodoCompletedChangedEvent;
import com.vaadin.flow.demo.helloworld.events.TodoDestroyedEvent;
import com.vaadin.flow.demo.helloworld.model.TodoModel;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.NativeButton;
import com.vaadin.flow.html.Span;
import com.vaadin.flow.router.View;
import com.vaadin.ui.Text;

@Tag("footer")
public class Footer extends HtmlContainer implements View {

    private static class TodoCount extends Span {
        private static final String COUNTER_TEXT_SINGULAR = " item left";
        private static final String COUNTER_TEXT_PLURAL = " items left";

        private final HtmlContainer counter = new HtmlContainer(Tag.STRONG);
        private final Text counterText = new Text(COUNTER_TEXT_PLURAL);

        private int count;

        public TodoCount() {
            setClassName("todo-count");

            setCount(0);
            add(counter, counterText);
        }

        public void setCount(int count) {
            this.count = count;
            refresh();
        }

        private void refresh() {
            counter.setText(String.valueOf(count));
            counterText.setText(count == 0 || count > 1 ? COUNTER_TEXT_PLURAL
                    : COUNTER_TEXT_SINGULAR);
        }
    }

    private TodoModel todoModel;

    private TodoCount todoCount;
    private NativeButton clearCompleted;

    public Footer(TodoModel todoModel) {
        setClassName("footer");

        this.todoModel = todoModel;

        todoCount = new TodoCount();
        updateTodoCount();

        clearCompleted = new NativeButton("Clear completed");
        clearCompleted.setClassName("clear-completed");
        clearCompleted.addClickListener(event -> {

        });

        add(todoCount, clearCompleted);
    }

    private void updateTodoCount() {
        todoCount.setCount(getActiveTodoCount());
        StyleUtil.setVisible(this, todoModel.getTodos().size() > 0);
    }

    private int getActiveTodoCount() {
        return (int) todoModel.getTodos().stream()
                .filter(todo -> !todo.isCompleted()).count();
    }

    @Subscribe
    public void todoAdded(AddTodoEvent event) {
        updateTodoCount();
    }

    @Subscribe
    public void todoRemoved(TodoDestroyedEvent event) {
        updateTodoCount();
    }

    @Subscribe
    public void todoCompleted(TodoCompletedChangedEvent event) {
        updateTodoCount();
    }

    @Subscribe
    public void markAllCompleteChanged(MarkAllAsCompleteToggledEvent event) {
        updateTodoCount();
    }
}
