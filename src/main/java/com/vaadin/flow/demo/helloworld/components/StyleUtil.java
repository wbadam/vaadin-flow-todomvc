package com.vaadin.flow.demo.helloworld.components;

import com.vaadin.ui.HasStyle;

public class StyleUtil {

    public static void setVisible(HasStyle element, boolean visible) {
        if (visible) {
            element.getStyle().remove("display");
        } else {
            element.getStyle().set("display", "none");
        }
    }
}
