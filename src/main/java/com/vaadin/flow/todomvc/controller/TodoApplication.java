package com.vaadin.flow.todomvc.controller;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.flow.todomvc.controller.events.DataLoadedEvent;
import com.vaadin.flow.todomvc.model.data.beans.Todo;
import com.vaadin.flow.todomvc.controller.events.AddTodoEvent;
import com.vaadin.flow.todomvc.controller.events.ClearCompletedTodosEvent;
import com.vaadin.flow.todomvc.controller.events.CompleteAllTodosEvent;
import com.vaadin.flow.todomvc.controller.events.TodoCompletedChangedEvent;
import com.vaadin.flow.todomvc.controller.events.TodoDestroyedEvent;
import com.vaadin.flow.todomvc.model.TodoModel;
import com.vaadin.flow.todomvc.view.TodoView;

public class TodoApplication {

    private final TodoModel todoModel;
    private final TodoView todoView;
    private final EventBus eventBus = new EventBus();

    public TodoApplication(TodoModel todoModel, TodoView todoView) {
        this.todoModel = todoModel;
        this.todoView = todoView;

        todoModel.setTodoEventBus(eventBus);

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

    @Subscribe
    public void dataLoaded(DataLoadedEvent event) {
        todoView.setTodos(todoModel.getTodos());
    }
}
