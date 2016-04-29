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


import com.dragome.examples.todo.Definitions._
import com.dragome.examples.todo.model.{Todo, TodoManager}
import com.dragome.forms.bindings.builders.LocalStorage
import com.dragome.forms.bindings.builders.helpers.BinderHelper
import com.dragome.guia.GuiaVisualActivity
import com.dragome.guia.events.listeners.interfaces.KeyListener._
import com.dragome.services.ServiceLocator
import com.dragome.web.annotations.PageAlias

import scala.beans.BeanProperty


@PageAlias(alias = "scala-todo")
class TodoPageScala() extends GuiaVisualActivity {

    @BeanProperty
    var todoManager: TodoManager = null;

    todoManager = new TodoManager(ServiceLocator.getInstance().getParametersHandler().getFragment(), new LocalStorage());
    BinderHelper.start(mainPanel);

    def build() {
        bind template "new-todo" as textfield component {
            to property (todoManager.getNewTodo _, todoManager.setNewTodo _)
            on key ENTER up todoManager.addTodo()
        }

        bind template "main-section" as panel component {
            show when !todoManager.getTodos().isEmpty()

            bind template "toggle-all" as checkbox component {
                to property (todoManager.isAllChecked _, todoManager.setAllChecked _)
                on click todoManager.markAll(!todoManager.isAllChecked())
                show when !todoManager.getTodos().isEmpty()
            }

            bind template "completed-todo" as panel component {
                to list todoManager.getTodos _ filter todoManager.getStatusFilter _ repeat buildTodo _
            }
        }

        bind template "footer-section" as panel component {
            show when !todoManager.getTodos().isEmpty()

            bind template "items-count" as label component {
                to property (todoManager.getRemainingCount _, todoManager.setRemainingCount _)
            }

            bind template "items-label" as label component {
                to expression (() => if (todoManager.getRemainingCount() == 1) "item" else "items")
            }

            bind template "clear-completed" as panel component {
                on click todoManager.clearCompletedTodos()
                show when todoManager.getCompletedCount() > 0

                bind template "clear-completed-number" as label component {
                    to property (todoManager.getCompletedCount _, todoManager.setCompletedCount _)
                }
            }

            List("/", "/active", "/completed") foreach (location => {
                bind template "filter:" + location as link component {
                    style using "selected" when todoManager.getLocation().equals(location)
                    on click todoManager.setLocation(location)
                }
            })
        }
    }

    def buildTodo(todo: Todo) {
        style using "completed" when todo.isCompleted
        style using "editing" when todo == todoManager.getEditedTodo()

        bind template "todo-input" as textfield component {
            to property (todo.getTitle _, todo.setTitle _)
            on key ESC up todoManager.doneEditing(todo, true)
            on key ENTER up todoManager.doneEditing(todo, false)
            on blur todoManager.doneEditing(todo, false)
        }

        bind template "title" as label component {
            to property (todo.getTitle _, todo.setTitle _)
            on doubleClick todoManager.editTodo(todo)
        }

        bind template "completed" as checkbox component {
            to property (todo.isCompleted _, todo.setCompleted _)
            on click todoManager.todoCompleted(todo)
        }

        bind template "destroy" as button component {
            on click todoManager.removeTodo(todo)
        }
    }

}