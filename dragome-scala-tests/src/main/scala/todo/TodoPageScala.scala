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
import com.dragome.forms.bindings.builders.ActionExecutor
import com.dragome.forms.bindings.builders.helpers.BinderHelper

@PageAlias(alias = "simple-binding")
class TodoPageScala() extends GuiaVisualActivity {

  @BeanProperty
  var todoManager: TodoManager = null;

  def as[C](c: Class[C])(cond: Unit) = {
    BinderHelper bind(""
  }

  def bind(c: String): TodoPageScala = {
    this;
  }

  def build {

    todoManager = new TodoManager(ServiceLocator.getInstance().getParametersHandler().getFragment(), new LocalStorage());

    start(mainPanel);

    bind("new-todo").as(textField)
    {
      toProperty(todoManager::getNewTodo, todoManager::setNewTodo)
    }

    bind("main-section").as(panel)
    {
        showWhen !todoManager.getTodos().isEmpty()

        bind("toggle-all").as(checkbox)
        {
            showWhen !todoManager.getTodos().isEmpty()
            toProperty todoManager::isAllChecked, todoManager::setAllChecked 
            onClick todoManager.markAll(!todoManager.isAllChecked())
        }

        bind("completed-todo").as(panel)
        {
            bind("todo-input").as(textfield) 
            {
                toProperty todoManager::getTitle, todoManager::setTitle
                onKeyUp (v, c) -> todoManager.doneEditing(todo, c == KEY_ESC), KEY_ESC, KEY_ENTER
                onBlur todoManager.doneEditing(todo, false)
            }
            bind("title").as(label) 
            {
                toProperty todoManager::getTitle, todoManager::setTitle
                onDoubleClick todoManager.editTodo(todo)
            }

            bind("completed").as(checkbox) 
            {
                toProperty(todo::isCompleted, todo::setCompleted)
                onClick todoManager.todoCompleted(todo)
            }
        }
    }


    bind("footer-section").as(panel)
    {
        bind("items-count").as(label)
        {
            toProperty todoManager::getRemainingCount, todoManager::setRemainingCount
        }
        bind("items-label").as(label)
        {
            to todoManager.getRemainingCount() == 1 ? "item" : "items"
        }

        bind("clear-completed").as(panel)
        {
            showWhen todoManager.getCompletedCount() > 0
            onClick todoManager.clearCompletedTodos()

            bind("clear-completed-number").as(label) 
            {
                toProperty todoManager::getCompletedCount, todoManager::setCompletedCount
            }
        }
    }

  }

}