package com.vaadin.flow.demo.helloworld.controller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.flow.demo.helloworld.model.data.beans.Todo;
import com.vaadin.flow.demo.helloworld.controller.events.AddTodoEvent;
import com.vaadin.flow.demo.helloworld.controller.events.ClearCompletedTodosEvent;
import com.vaadin.flow.demo.helloworld.controller.events.CompleteAllTodosEvent;
import com.vaadin.flow.demo.helloworld.controller.events.TodoCompletedChangedEvent;
import com.vaadin.flow.demo.helloworld.controller.events.TodoDestroyedEvent;
import com.vaadin.flow.demo.helloworld.model.TodoModel;
import com.vaadin.flow.demo.helloworld.view.TodoView;

public class TodoApplication {

    private final TodoModel todoModel;
    private final TodoView todoView;
    private final EventBus eventBus = new EventBus();

    public TodoApplication(TodoModel todoModel, TodoView todoView) {
        this.todoModel = todoModel;
        this.todoView = todoView;

        todoView.setTodos(todoModel.getTodos());
        todoView.setTodoEventBus(eventBus);

        // Register event handler
        eventBus.register(this);
    }

    @Subscribe
    public void completedTodosCleared(ClearCompletedTodosEvent event) {
        todoModel.removeIf(Todo::isCompleted);
        todoView.setTodos(todoModel.getTodos());
    }

    @Subscribe
    public void todoCompletedChanged(TodoCompletedChangedEvent event) {
        todoView.setCompleted(event.getTodo(), event.getTodo().isCompleted());
    }

    @Subscribe
    public void addTodo(AddTodoEvent event) {
        todoModel.addTodo(new Todo(event.getTitle()));
        todoView.setTodos(todoModel.getTodos());
    }

    @Subscribe
    public void changeAllCompleted(CompleteAllTodosEvent event) {
        todoModel.markAllAsComplete(event.isCompleted());
        todoView.setTodos(todoModel.getTodos());
    }

    @Subscribe
    public void destroyTodo(TodoDestroyedEvent event) {
        todoModel.removeTodo(event.getTodo());
        todoView.setTodos(todoModel.getTodos());
    }
}
