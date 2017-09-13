package com.vaadin.flow.demo.helloworld.view;

import com.google.common.eventbus.EventBus;

public interface HasEventBus {
    void setTodoEventBus(EventBus eventBus);
    EventBus getTodoEventBus();
}
