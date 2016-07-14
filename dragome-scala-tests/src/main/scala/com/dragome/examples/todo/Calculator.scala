/**
 * *****************************************************************************
 * Copyright (c) 2011-2015 Fernando Petrola
 *
 * This file is part of Dragome SDK.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * ****************************************************************************
 */
package com.dragome.examples.todo

import com.dragome.commons.javascript.ScriptHelper
import com.dragome.examples.todo.Definitions._
import com.dragome.forms.bindings.builders.helpers.BinderVisualActivity
import com.dragome.guia.components.interfaces.{ VisualComponent, VisualLabel }
import com.dragome.web.annotations.PageAlias

import scala.beans.BeanProperty

@PageAlias(alias = "calculator")
class Calculator extends BinderVisualActivity {
    @BeanProperty var displayValue = ""

    def build {
        bind template "displayValue" as label component {
            to property (getDisplayValue _, setDisplayValue _)
        }

        bind template "equalSign" as label component {
            on click {
                setDisplayValue(ScriptHelper.evalFloat(displayValue, this).toString)
            }
        }
        bind template "digitKeys" as panel component {
            to list (() => List(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)) repeat showButton _
        }
        bind template "operationKeys" as panel component {
            to list (() => List("+", "-", "*", "/")) repeat showButton _
        }
    }

    def showButton(button: Any) {
        bind template "label" as label component {
            to value button
            on click setDisplayValue(displayValue + button.toString)
        }
    }
}