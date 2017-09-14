package com.vaadin.flow.todomvc;

import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.html.Anchor;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.html.NativeButton;
import com.vaadin.flow.html.Span;
import com.vaadin.flow.todomvc.components.StyleUtil;
import com.vaadin.flow.todomvc.controller.TodoApplication.Filter;
import com.vaadin.flow.todomvc.controller.events.ClearCompletedTodosEvent;
import com.vaadin.flow.todomvc.view.HasEventBus;
import com.vaadin.shared.ApplicationConstants;
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

    private static class RouterLink extends Anchor {
        public RouterLink(String href, String text) {
            super(href, text);
            getElement()
                    .setAttribute(ApplicationConstants.ROUTER_LINK_ATTRIBUTE,
                            true);
        }
    }

    @Tag(Tag.UL)
    private static class FilterComponent extends HtmlContainer {

        private static final String STYLE_NAME_SELECTED = "selected";

        private final RouterLink all;
        private final RouterLink active;
        private final RouterLink completed;

        public FilterComponent() {
            setClassName("filters");

            all = new RouterLink("/", "All");
            active = new RouterLink("/active", "Active");
            completed = new RouterLink("/completed", "Completed");

            add(new HtmlContainer(Tag.LI, all),
                    new HtmlContainer(Tag.LI, active),
                    new HtmlContainer(Tag.LI, completed));
        }
    }

    private EventBus eventBus;

    private TodoCount todoCount;
    private FilterComponent filter;
    private NativeButton clearCompleted;

    public FooterComponent() {
        setClassName("footer");

        todoCount = new TodoCount();

        clearCompleted = new NativeButton("Clear completed");
        clearCompleted.setClassName("clear-completed");
        clearCompleted.addClickListener(event -> {
            getTodoEventBus().post(new ClearCompletedTodosEvent());
        });

        filter = new FilterComponent();

        add(todoCount, filter, clearCompleted);
    }

    void setTodoCount(int count, int activeCount) {
        todoCount.setCount(activeCount);
        StyleUtil.setVisible(this, count > 0);
        StyleUtil.setVisible(clearCompleted, count - activeCount > 0);
    }

    void setFilter(Filter filter) {
        switch (filter) {
        case ALL:
        default:
            this.filter.all.addClassName(FilterComponent.STYLE_NAME_SELECTED);
            this.filter.active
                    .removeClassName(FilterComponent.STYLE_NAME_SELECTED);
            this.filter.completed
                    .removeClassName(FilterComponent.STYLE_NAME_SELECTED);
            break;
        case ACTIVE:
            this.filter.all
                    .removeClassName(FilterComponent.STYLE_NAME_SELECTED);
            this.filter.active
                    .addClassName(FilterComponent.STYLE_NAME_SELECTED);
            this.filter.completed
                    .removeClassName(FilterComponent.STYLE_NAME_SELECTED);
            break;
        case COMPLETED:
            this.filter.all
                    .removeClassName(FilterComponent.STYLE_NAME_SELECTED);
            this.filter.active
                    .removeClassName(FilterComponent.STYLE_NAME_SELECTED);
            this.filter.completed
                    .addClassName(FilterComponent.STYLE_NAME_SELECTED);
            break;
        }
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
