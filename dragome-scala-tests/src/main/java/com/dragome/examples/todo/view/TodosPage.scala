/**
 * *****************************************************************************
 * Copyright (c) 2011-2014 Fernando Petrola
 *
 *  This file is part of Dragome SDK.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * ****************************************************************************
 */
package com.dragome.examples.todo.view;

import com.dragome.guia.listeners.KeyListener.KEY_ENTER
import com.dragome.guia.listeners.KeyListener.KEY_ESC
import java.util.stream.Stream
import com.dragome.annotations.PageAlias
import com.dragome.examples.todo.model.TodoManager
import com.dragome.forms.bindings.builders.ComponentBuilder
import com.dragome.forms.bindings.builders.LocalStorage
import com.dragome.guia.GuiaVisualActivity
import com.dragome.guia.components.interfaces.VisualButton
import com.dragome.guia.components.interfaces.VisualCheckbox
import com.dragome.guia.components.interfaces.VisualLabel
import com.dragome.guia.components.interfaces.VisualLink
import com.dragome.guia.components.interfaces.VisualPanel
import com.dragome.guia.components.interfaces.VisualTextField
import com.dragome.services.ServiceLocator
import com.dragome.examples.todo.Definitions._
import com.dragome.guia.components.interfaces.VisualComponent
import com.dragome.forms.bindings.builders.ChildrenBuilder

@PageAlias(alias = "todo-mvc")
class TodosPage2 extends GuiaVisualActivity {
  def build() {
    val todoManager = new TodoManager(ServiceLocator.getInstance().getParametersHandler().getFragment(), new LocalStorage());
    val builder = new ComponentBuilder(mainPanel);

    builder bindTemplate "new-todo" as textField toProperty (todoManager.getNewTodo _, todoManager.setNewTodo _) onKeyUp (() => todoManager.addTodo(), KEY_ENTER) build;

    val extracted1 = new ChildrenBuilder {
      def build(mainSectionBuilder: ComponentBuilder) {
        val allChecked = mainSectionBuilder bindTemplate "toggle-all" as checkbox toProperty (todoManager.isAllChecked _, todoManager.setAllChecked _) onClick todoManager.markAll(!todoManager.isAllChecked()) build;

        val a: () => java.lang.Boolean = () => !todoManager.getTodos().isEmpty();
        mainSectionBuilder.show(allChecked).when(a);
        mainSectionBuilder.showWhen(a);

        //      mainSectionBuilder.bindTemplate("completed-todo").as(panel).toListProperty(todoManager.getTodos _).filter(todoManager.getStatusFilter _).repeat((todo, builder) -> {
        //        builder.bindTemplate("todo-input").as(textField).toProperty(todo.getTitle _, todo.setTitle _).onKeyUp((v, c) -> todoManager.doneEditing(todo, c == KEY_ESC), KEY_ESC, KEY_ENTER).onBlur(v -> todoManager.doneEditing(todo, false)).build();
        //        builder.bindTemplate("title").as(label).toProperty(todo :: getTitle, todo :: setTitle).onDoubleClick(v -> todoManager.editTodo(todo)).build();
        //        builder.bindTemplate("completed").as(checkbox).toProperty(todo :: isCompleted, todo :: setCompleted).onClick(v -> todoManager.todoCompleted(todo)).build();
        //        builder.bindTemplate("destroy").as(button).onClick(v -> todoManager.removeTodo(todo)).build();
        //        builder.styleWith("completed").when(todo :: isCompleted);
        //        builder.styleWith("editing").when(() -> todo == todoManager.getEditedTodo());
        //      }
      }
    }

    builder.bindTemplate("main-section").as(panel).buildChildren(extracted1);
  }

}
