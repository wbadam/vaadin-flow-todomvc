package com.vaadin.flow.demo.helloworld;

import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.beans.Todo;
import com.vaadin.flow.demo.helloworld.components.StyleUtil;
import com.vaadin.flow.demo.helloworld.events.AddTodoEvent;
import com.vaadin.flow.demo.helloworld.events.ClearCompletedEvent;
import com.vaadin.flow.demo.helloworld.events.MarkAllAsCompleteToggledEvent;
import com.vaadin.flow.demo.helloworld.events.TodoDestroyedEvent;
import com.vaadin.flow.demo.helloworld.model.TodoModel;
import com.vaadin.flow.html.HtmlContainer;
import com.vaadin.flow.router.View;

@Tag("section")
public class TodoApp extends HtmlContainer implements View{

    private final TodoModel todoModel;

    private final Header header;
    private final MainSection mainSection;
    private final Footer footer;

    public TodoApp() {
        setClassName("todoapp");

        todoModel = new TodoModel();

        header = new Header();
        header.setEventBus(todoModel.getEventBus());

        mainSection = new MainSection(todoModel);
        StyleUtil.setVisible(mainSection, false);
        mainSection.setEventBus(todoModel.getEventBus());

        footer = new Footer(todoModel);
        footer.setEventBus(todoModel.getEventBus());

        // Register event handler
        todoModel.getEventBus().register(this);
        todoModel.getEventBus().register(mainSection);
        todoModel.getEventBus().register(footer);

        add(header, mainSection, footer);
    }

    @Subscribe
    public void addTodo(AddTodoEvent event) {
        todoModel.addTodo(event.getTodo());
        mainSection.refreshTodoList();
        StyleUtil.setVisible(mainSection, true);
    }

    @Subscribe
    public void changeAllCompleted(MarkAllAsCompleteToggledEvent event) {
        todoModel.markAllAsComplete(event.isSelected());
        mainSection.refreshTodoList();
    }

    @Subscribe
    public void destroyTodo(TodoDestroyedEvent event) {
        todoModel.removeTodo(event.getTodo());
        mainSection.refreshTodoList();
        StyleUtil.setVisible(mainSection, todoModel.getTodos().size() > 0);
    }

    @Subscribe
    public void clearCompleted(ClearCompletedEvent event) {
        todoModel.removeIf(Todo::isCompleted);
        mainSection.refreshTodoList();
        StyleUtil.setVisible(mainSection, todoModel.getTodos().size() > 0);
    }
}
