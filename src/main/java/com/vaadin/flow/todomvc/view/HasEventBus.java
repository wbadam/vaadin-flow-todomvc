package com.vaadin.flow.todomvc.view;

import com.google.common.eventbus.EventBus;

public interface HasEventBus {
    void setTodoEventBus(EventBus eventBus);
    EventBus getTodoEventBus();
}
