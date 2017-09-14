package com.vaadin.flow.todomvc.controller;

import java.util.List;

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

    public enum Filter {
        ALL, ACTIVE, COMPLETED
    }

    private final TodoModel todoModel;
    private final TodoView todoView;
    private final EventBus eventBus = new EventBus();

    private Filter filter;

    public TodoApplication(TodoModel todoModel, TodoView todoView) {
        this.todoModel = todoModel;
        this.todoView = todoView;

        todoModel.setTodoEventBus(eventBus);
        todoView.setTodoEventBus(eventBus);

        // Register event handler
        eventBus.register(this);
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
        setTodos();
        todoView.setFilter(filter);
    }

    private void setTodos() {
        switch (filter) {
        case ALL:
        default:
            filterAll();
            break;
        case ACTIVE:
            filterActive();
            break;
        case COMPLETED:
            filterCompleted();
            break;
        }
    }

    private void filterAll() {
        List<Todo> todos = todoModel.getTodos();
        todoView.setTodos(todos, todos.size(), countActive());
    }

    private void filterActive() {
        List<Todo> todosAll = todoModel.getTodos();
        List<Todo> todosActive = todoModel
                .getTodos(todo -> !todo.isCompleted());
        todoView.setTodos(todosActive, todosAll.size(), todosActive.size());
    }

    private void filterCompleted() {
        List<Todo> todosAll = todoModel.getTodos();
        List<Todo> todosCompleted = todoModel.getTodos(Todo::isCompleted);
        todoView.setTodos(todosCompleted, todosAll.size(),
                todosAll.size() - todosCompleted.size());
    }

    private int countActive() {
        return todoModel.getTodos(todo -> !todo.isCompleted()).size();
    }

    @Subscribe
    public void completedTodosCleared(ClearCompletedTodosEvent event) {
        todoModel.removeIf(Todo::isCompleted);
        setTodos();
    }

    @Subscribe
    public void todoCompletedChanged(TodoCompletedChangedEvent event) {
        todoModel.markAsCompleted(event.getTodo(),
                event.getTodo().isCompleted());
        setTodos();
    }

    @Subscribe
    public void addTodo(AddTodoEvent event) {
        todoModel.addTodo(new Todo(event.getTitle()));
        setTodos();
    }

    @Subscribe
    public void changeAllCompleted(CompleteAllTodosEvent event) {
        todoModel.markAllAsComplete(event.isCompleted());
        setTodos();
    }

    @Subscribe
    public void destroyTodo(TodoDestroyedEvent event) {
        todoModel.removeTodo(event.getTodo());
        setTodos();
    }

    @Subscribe
    public void dataLoaded(DataLoadedEvent event) {
        setTodos();
    }
}
