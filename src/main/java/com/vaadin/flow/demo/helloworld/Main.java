package com.vaadin.flow.demo.helloworld;

import com.vaadin.annotations.HtmlImport;
import com.vaadin.annotations.Tag;
import com.vaadin.flow.demo.helloworld.Main.SectionMainModel;
import com.vaadin.flow.template.PolymerTemplate;
import com.vaadin.flow.template.model.TemplateModel;

@Tag("section-main")
@HtmlImport("frontend://Main.html")
public class Main extends PolymerTemplate<SectionMainModel> {

    public interface SectionMainModel extends TemplateModel {

    }
}
