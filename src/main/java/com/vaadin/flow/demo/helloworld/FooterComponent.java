package com.vaadin.flow.demo.helloworld;

import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.components.StyleUtil;
import com.vaadin.flow.demo.helloworld.controller.events.ClearCompletedTodosEvent;
import com.vaadin.flow.demo.helloworld.view.HasEventBus;
import com.vaadin.flow.html.Anchor;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.NativeButton;
import com.vaadin.flow.html.Span;
import com.vaadin.ui.Text;

@Tag("footer")
public class FooterComponent extends HtmlContainer implements HasEventBus {

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

        private final Anchor all;
        private final Anchor active;
        private final Anchor completed;

        public Filter() {
            setClassName("filters");

            all = new Anchor("/", "All");
            active = new Anchor("/active", "Active");
            completed = new Anchor("/completed", "Completed");

            add(new HtmlContainer(Tag.LI, all),
                    new HtmlContainer(Tag.LI, active),
                    new HtmlContainer(Tag.LI, completed));
        }
    }

    private EventBus eventBus;

    private TodoCount todoCount;
    private Filter filter;
    private NativeButton clearCompleted;

    public FooterComponent() {
        setClassName("footer");

        todoCount = new TodoCount();

        clearCompleted = new NativeButton("Clear completed");
        clearCompleted.setClassName("clear-completed");
        clearCompleted.addClickListener(event -> {
            getTodoEventBus().post(new ClearCompletedTodosEvent());
        });

        filter = new Filter();

        add(todoCount, filter, clearCompleted);
    }

    void setTodoCount(int count, int activeCount) {
        todoCount.setCount(activeCount);
        StyleUtil.setVisible(this, count > 0);
        StyleUtil.setVisible(clearCompleted, count - activeCount > 0);
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
