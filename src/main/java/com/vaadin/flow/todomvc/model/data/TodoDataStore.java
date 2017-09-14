package com.vaadin.flow.todomvc.model.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.HtmlImport;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.todomvc.controller.events.DataLoadedEvent;
import com.vaadin.flow.todomvc.model.data.TodoDataStore.DataStoreModel;
import com.vaadin.flow.todomvc.model.data.beans.Todo;
import com.vaadin.flow.template.PolymerTemplate;
import com.vaadin.flow.template.model.TemplateModel;

import elemental.json.Json;
import elemental.json.JsonArray;
import elemental.json.JsonObject;
import elemental.json.JsonValue;
import elemental.json.impl.JsonUtil;

@Tag("data-store")
@HtmlImport("frontend://TodoDataStore.html")
public class TodoDataStore extends PolymerTemplate<DataStoreModel> implements
        DataHandler<Todo> {

    public interface DataStoreModel extends TemplateModel {
        String getStoredData();

        void setStoredData(String data);

        void setPageLoaded(boolean loaded);
    }

    private final List<Todo> todos;

    private EventBus eventBus;

    public TodoDataStore() {
        getElement().synchronizeProperty("storedData", "data-loaded");
        getElement().addEventListener("data-loaded", event -> {
            readFromStorage();

            getTodoEventBus().post(new DataLoadedEvent());
        });

        todos = new ArrayList<>();

        getModel().setPageLoaded(true);
    }

    @Override
    public void add(Todo todo) {
        todos.add(todo);
        writeToStorage();
    }

    @Override
    public void remove(Todo todo) {
        todos.remove(todo);
        writeToStorage();
    }

    @Override
    public void remove(Collection<Todo> remove) {
        todos.removeAll(remove);
        writeToStorage();
    }

    @Override
    public void setCompleted(Todo todo, boolean completed) {
        todo.setCompleted(completed);
        writeToStorage();
    }

    @Override
    public void setCompleted(Collection<Todo> todos, boolean completed) {
        todos.forEach(todo -> todo.setCompleted(completed));
        writeToStorage();
    }

    @Override
    public List<Todo> getAll() {
        return Collections.unmodifiableList(todos);
    }

    private void writeToStorage() {
        JsonArray array = Json.createArray();
        for (int i = 0; i < todos.size(); i++) {
            array.set(i, toJson(todos.get(i)));
        }

        getModel().setStoredData(array.toJson());
    }

    private void readFromStorage() {
        todos.clear();

        String storedData = getModel().getStoredData();
        if (storedData != null && storedData.length() > 0) {
            JsonValue json = JsonUtil.parse(storedData);
            if (json instanceof JsonArray) {
                JsonArray array = JsonUtil.parse(storedData);
                for (int i = 0; i < array.length(); i++) {
                    todos.add(fromJson(array.getObject(i)));
                }
            }
        }
    }

    private JsonObject toJson(Todo todo) {
        JsonObject json = Json.createObject();
        json.put("id", todo.getId());
        json.put("title", todo.getText());
        json.put("completed", todo.isCompleted());
        return json;
    }

    private Todo fromJson(JsonObject json) {
        return new Todo(json.getString("id"), json.getString("title"),
                json.getBoolean("completed"));
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
