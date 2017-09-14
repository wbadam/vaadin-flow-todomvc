package com.vaadin.flow.todomvc.components;

import com.vaadin.flow.html.Input;

public class NativeCheckbox extends Input {

    public boolean isChecked() {
        return getElement().getProperty("checked", false);
    }

    public void setChecked(boolean checked) {
        getElement().setProperty("checked", checked);
    }

    public void toggle() {
        setChecked(!isChecked());
    }
}
