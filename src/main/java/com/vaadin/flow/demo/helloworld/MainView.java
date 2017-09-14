/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.flow.demo.helloworld;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.flow.demo.helloworld.controller.TodoApplication;
import com.vaadin.flow.demo.helloworld.model.TodoModel;
import com.vaadin.flow.demo.helloworld.model.data.TodoDataStore;
import com.vaadin.flow.html.Div;
import com.vaadin.flow.router.View;
import com.vaadin.ui.Composite;

@StyleSheet("frontend://bower_components/todomvc-app-css/index.css")
@StyleSheet("frontend://bower_components/todomvc-common/base.css")
public class MainView extends Composite<Div> implements View {

    public MainView() {

        TodoDataStore dataStore = new TodoDataStore();

        TodoAppComponent todoApp = new TodoAppComponent();
        todoApp.add(dataStore);

        TodoApplication application = new TodoApplication(
                new TodoModel(dataStore), todoApp);

        getContent().add(todoApp);
    }

}
