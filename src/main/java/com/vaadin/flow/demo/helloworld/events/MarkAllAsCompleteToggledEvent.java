package com.vaadin.flow.demo.helloworld.events;

public class MarkAllAsCompleteToggledEvent {

    boolean selected;

    public MarkAllAsCompleteToggledEvent(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
}
