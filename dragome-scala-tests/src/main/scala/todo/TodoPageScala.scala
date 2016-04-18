/**
 * *****************************************************************************
 * Copyright (c) 2011-2015 Fernando Petrola
 *
 *  This file is part of Dragome SDK.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * ****************************************************************************
 */
package com.dragome.examples.todo

import com.dragome.guia.events.listeners.interfaces.ClickListener

import com.dragome.guia.events.listeners.interfaces.KeyUpListener
import com.dragome.guia.GuiaVisualActivity
import com.dragome.forms.bindings.builders.ComponentBuilder
import com.dragome.guia.components.interfaces.VisualComponent
import com.dragome.guia.components.interfaces.{ VisualLabel, VisualTextField }
import scala.beans.BeanProperty
import com.dragome.forms.bindings.builders.Supplier
import com.dragome.forms.bindings.builders.Consumer

import Definitions._
import com.dragome.web.annotations.PageAlias
import com.dragome.forms.bindings.builders.LocalStorage
import com.dragome.services.ServiceLocator
import com.dragome.guia.components.interfaces.VisualPanel
import com.dragome.guia.events.listeners.interfaces.KeyListener
import com.dragome.forms.bindings.builders.ChildrenBuilder
import com.dragome.forms.bindings.builders.helpers.BinderHelper._
import com.dragome.guia.events.listeners.interfaces.KeyListener._
import com.dragome.forms.bindings.builders.ActionExecutor
import com.dragome.forms.bindings.builders.helpers.BinderHelper
import com.dragome.examples.todo.model.TodoManager
import com.dragome.examples.todo.model.Todo
import com.dragome.forms.bindings.builders.helpers.ItemRepeaterHelper

@PageAlias(alias = "simple-binding")
class TodoPageScala() extends GuiaVisualActivity {

    @BeanProperty
    var todoManager: TodoManager = null;

    def as[C](c: Class[C])(cond: Unit) = {
    }

    def bind(c: String): TodoPageScala = {
        this;
    }

    def showWhen(c: Unit): TodoPageScala = {
        this;
    }

    def styleWhen(s: String, c: Unit): TodoPageScala = {
        this;
    }
    todoManager = new TodoManager(ServiceLocator.getInstance().getParametersHandler().getFragment(), new LocalStorage());
    BinderHelper.start(mainPanel);

    def build() {

        bind("new-todo").as(textField) {
            toProperty(todoManager.getNewTodo _, todoManager.setNewTodo _);
            onKeyUp(todoManager.addTodo(), KEY_ENTER);
        }

        bind("main-section").as(panel) {

            showWhen(!todoManager.getTodos().isEmpty())

            bind("toggle-all").as(checkbox) {
                toProperty(todoManager.isAllChecked _, todoManager.setAllChecked _)
                onClick(todoManager.markAll(!todoManager.isAllChecked()))
                showWhen(!todoManager.getTodos().isEmpty())
            }

            bind("completed-todo").as(panel) {
                toListProperty(todoManager.getTodos _)
                    .filter(todoManager.getStatusFilter _)
                    .repeat((t: Todo) => buildTodo(t));
            }
        }

        bind("footer-section").as(panel) {

            showWhen(!todoManager.getTodos().isEmpty());

            bind("items-count").as(label) {
                toProperty(todoManager.getRemainingCount _, todoManager.setRemainingCount _);
            }

            bind("items-label").as(label) {
                to(() => if (todoManager.getRemainingCount() == 1) "item" else "items");
            }

            bind("clear-completed").as(panel) {
                onClick(todoManager.clearCompletedTodos());
                showWhen(todoManager.getCompletedCount() > 0);

                bind("clear-completed-number").as(label) {
                    toProperty(todoManager.getCompletedCount _, todoManager.setCompletedCount _);
                }
            }

            for (location <- List("/", "/active", "/completed")) {
                bind("filter:" + location).as(link) {
                    onClick(todoManager.setLocation(location));
                    styleWhen("selected", () => todoManager.getLocation().equals(location));
                }
            }
        }
    }

    def buildTodo(todo: Todo) {

        styleWhen("completed", todo.isCompleted);
        styleWhen("editing", todo == todoManager.getEditedTodo());

        bind("todo-input").as(textField) {
            toProperty(todo.getTitle _, todo.setTitle _);
            onKeyUp((v: VisualComponent, c: Int) => todoManager.doneEditing(todo, c == KEY_ESC), KEY_ESC, KEY_ENTER);
            onBlur(todoManager.doneEditing(todo, false));
        }

        bind("title").as(label) {
            toProperty(todo.getTitle _, todo.setTitle _);
            onDoubleClick(todoManager.editTodo(todo));
        }

        bind("completed").as(checkbox) {
            toProperty(todo.isCompleted _, todo.setCompleted _);
            onClick(todoManager.todoCompleted(todo));
        }

        bind("destroy").as(button) {
            onClick(todoManager.removeTodo(todo));
        }
    }

}