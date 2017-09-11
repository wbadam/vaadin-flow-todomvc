package com.vaadin.flow.demo.helloworld;

import com.google.common.eventbus.Subscribe;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.events.AddTodoEvent;
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
//    private final Footer footer; TODO

    public TodoApp() {
        setClassName("todoapp");

        todoModel = new TodoModel();

        header = new Header();
        header.setEventBus(todoModel.getEventBus());

        mainSection = new MainSection(todoModel);
        mainSection.setVisible(false);
        mainSection.setEventBus(todoModel.getEventBus());

        // Register event handler
        todoModel.getEventBus().register(this);
        todoModel.getEventBus().register(mainSection);

        add(header, mainSection);
    }

    @Subscribe
    public void addTodo(AddTodoEvent event) {
        todoModel.addTodo(event.getTodo());
        mainSection.refreshTodoList();
        mainSection.setVisible(true);
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
        mainSection.setVisible(todoModel.getTodos().size() > 0);
    }
}
