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

import com.dragome.guia.events.listeners.interfaces.KeyUpListener
import com.dragome.guia.GuiaVisualActivity
import com.dragome.guia.components.interfaces.VisualComponent
import scala.beans.BeanProperty
import com.dragome.forms.bindings.builders.Supplier
import com.dragome.forms.bindings.builders.Consumer
import Definitions._
import com.dragome.web.annotations.PageAlias
import com.dragome.forms.bindings.builders.LocalStorage
import com.dragome.services.ServiceLocator
import com.dragome.forms.bindings.builders.helpers.BinderHelper._
import com.dragome.guia.events.listeners.interfaces.KeyListener._
import com.dragome.forms.bindings.builders.helpers.BinderHelper
import com.dragome.examples.todo.model.TodoManager
import com.dragome.examples.todo.model.Todo
import com.dragome.forms.bindings.builders.helpers.RepeaterBuilderHelper
import com.dragome.forms.bindings.builders.ActionExecutor
import java.lang.Boolean

@PageAlias(alias = "scala-todo")
class TodoPageScala() extends GuiaVisualActivity {

    @BeanProperty
    var todoManager: TodoManager = null;

    //    def as[C](c: Class[C])(cond: Unit) = {
    //    }
    //
    //    def bind(c: String): TodoPageScala = {
    //        this;
    //    }

    class AsComponent(componentType: Class[_ <: VisualComponent], binderHelper: BinderHelper) {
        def binding[T >: VisualComponent](y: => Unit): T = {
            binderHelper.as(componentType, y).asInstanceOf[T]
        }
    }

    class UseTemplate(binderHelper: BinderHelper) {
        def as(componentType: Class[_ <: VisualComponent]): AsComponent = {
            new AsComponent(componentType, binderHelper)
        }
    }

    object use {
        def template(in: String): UseTemplate = {
            new UseTemplate(bind(in))
        }
    }

    object show {
        def when(in: => Unit) = {
            null
        }
    }

    class style2(in: String) {
        def when(y: Supplier[java.lang.Boolean]) = {
            styleWith(in).when(y)
        }
    }

    object style {
        def using(in: String): style2 = {
            new style2(in)
        }
    }

    class keyup1(keyCode: Int) {
        def perform(y: ActionExecutor) = {
            onKeyUp(new KeyUpListener() {
                def keyupPerformed(visualComponent: VisualComponent, keyCode: Int) {
                    y
                }
            }, keyCode)
        }
    }

    class keyup2 {
        def perform(y: KeyUpListener) = {
        }
    }

    object on {
        def click(in: => Unit) = {
            null
        }
        def doubleClick(in: => Unit) = {
            null
        }
        def keyUp(in: Int): keyup1 = {
            new keyup1(in)
        }
        def keysUp(in: Int*): keyup2 = {
            null
        }
        def blur(in: => Unit) = {
            null
        }
    }

    object to {
        def property[S](getter: Supplier[S], setter: Consumer[S]) = {
            null
        }

        def expression[S](getter: Supplier[S]) = {
            null
        }

        def doubleClick(in: => Unit) = {
            null
        }
        def keyUp(in: Int): keyup1 = {
            null
        }
        def blur(in: => Unit) = {
            null
        }

        def list[S](getter: Supplier[java.util.List[S]]): RepeaterBuilderHelper[S] = {
            toListProperty(getter)
            null
        }
    }

    todoManager = new TodoManager(ServiceLocator.getInstance().getParametersHandler().getFragment(), new LocalStorage());
    BinderHelper.start(mainPanel);

    def build() {

        use template "new-todo" as textfield binding {
            to property (todoManager.getNewTodo _, todoManager.setNewTodo _)
            on keyUp KEY_ENTER perform todoManager.addTodo()
        }

        use template "main-section" as panel binding {

            use template "toggle-all" as checkbox binding {
                //                to property (todoManager.isAllChecked _, todoManager.setAllChecked _)
                on click todoManager.markAll(!todoManager.isAllChecked())
                show when !todoManager.getTodos().isEmpty()
            }

            use template "completed-todo" as panel binding {
                //                to list todoManager.getTodos _ filter todoManager.getStatusFilter _ repeat ((t: Todo) => buildTodo(t))
            }

            show when !todoManager.getTodos().isEmpty()
        }

        use template "footer-section" as panel binding {

            show when !todoManager.getTodos().isEmpty()

            use template "items-count" as label binding {
                to property (todoManager.getRemainingCount _, todoManager.setRemainingCount _);
            }

            use template "items-label" as label binding {
                to expression (() => if (todoManager.getRemainingCount() == 1) "item" else "items")
            }

            use template "clear-completed" as panel binding {
                use template "clear-completed-number" as label binding {
                    to property (todoManager.getCompletedCount _, todoManager.setCompletedCount _);
                }

                show when todoManager.getCompletedCount() > 0
                on click todoManager.clearCompletedTodos()
            };

            var location = "filter:/"

            use template location as link binding {
                on click todoManager.setLocation(location)
                style using "selected" when { () => todoManager.getLocation().equals(location) }
            }
        }
    }

    def buildTodo(todo: Todo) {

        style using "completed" when { () => todo.isCompleted }
        style using "editing" when { () => todo == todoManager.getEditedTodo() }

        use template "todo-input" as textfield binding {
            to property (todo.getTitle _, todo.setTitle _);
            on keysUp (KEY_ESC, KEY_ENTER) perform ((v: VisualComponent, c: Int) => todoManager.doneEditing(todo, c == KEY_ESC))
            on blur todoManager.doneEditing(todo, false)
        }

        use template "title" as label binding {
            to property (todo.getTitle _, todo.setTitle _);
            on doubleClick todoManager.editTodo(todo)
        }

        use template "completed" as checkbox binding {
            //            to property (todo.isCompleted _, todo.setCompleted _);
            on click todoManager.todoCompleted(todo)
        }

        use template "destroy" as button binding {
            on click todoManager.removeTodo(todo)
        }
    }

}