package com.vaadin.flow.demo.helloworld;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.Todo;
import com.vaadin.flow.demo.helloworld.components.StyleUtil;
import com.vaadin.flow.demo.helloworld.events.AddTodoEvent;
import com.vaadin.flow.demo.helloworld.events.ClearCompletedEvent;
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

    @Tag(Tag.UL)
    private static class Filter extends HtmlContainer {

        private final HtmlContainer all;
        private final HtmlContainer active;
        private final HtmlContainer completed;

        public Filter() {
            setClassName("filters");

            all = new HtmlContainer(Tag.A);
            all.setText("All");
            all.getElement().setAttribute("href", "/");

            active = new HtmlContainer(Tag.A);
            active.setText("Active");
            active.getElement().setAttribute("href", "/active");

            completed = new HtmlContainer(Tag.A);
            completed.setText("Completed");
            completed.getElement().setAttribute("href", "/completed");

            add(new HtmlContainer(Tag.LI, all),
                    new HtmlContainer(Tag.LI, active),
                    new HtmlContainer(Tag.LI, completed));
        }
    }

    private TodoModel todoModel;
    private EventBus eventBus;

    private TodoCount todoCount;
    private Filter filter;
    private NativeButton clearCompleted;

    public Footer(TodoModel todoModel) {
        setClassName("footer");

        this.todoModel = todoModel;

        todoCount = new TodoCount();

        clearCompleted = new NativeButton("Clear completed");
        clearCompleted.setClassName("clear-completed");
        clearCompleted.addClickListener(event -> {
            eventBus.post(new ClearCompletedEvent());
            updateTodoCount();
        });

        filter = new Filter();

        updateTodoCount();

        add(todoCount, filter, clearCompleted);
    }

    private void updateTodoCount() {
        todoCount.setCount(getActiveTodoCount());
        StyleUtil.setVisible(this, todoModel.getTodos().size() > 0);

        StyleUtil.setVisible(clearCompleted, getCompletedTodoCount() > 0);
    }

    private int getActiveTodoCount() {
        return (int) todoModel.getTodos().stream()
                .filter(todo -> !todo.isCompleted()).count();
    }

    private int getCompletedTodoCount() {
        return (int) todoModel.getTodos().stream().filter(Todo::isCompleted)
                .count();
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

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }
}
