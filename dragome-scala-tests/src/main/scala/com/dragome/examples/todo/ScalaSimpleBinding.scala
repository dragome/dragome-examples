/** *****************************************************************************
  * Copyright (c) 2011-2015 Fernando Petrola
  *
  * This file is part of Dragome SDK.
  *
  * All rights reserved. This program and the accompanying materials
  * are made available under the terms of the GNU Public License v3.0
  * which accompanies this distribution, and is available at
  * http://www.gnu.org/licenses/gpl.html
  * *****************************************************************************/
package com.dragome.examples.todo

import com.dragome.examples.todo.Definitions._
import com.dragome.forms.bindings.builders.helpers.BinderHelper
import com.dragome.guia.GuiaVisualActivity
import com.dragome.web.annotations.PageAlias

import scala.beans.BeanProperty

@PageAlias(alias = "simple-binding")
class ScalaSimpleBinding() extends GuiaVisualActivity {
  @BeanProperty var name = ""
  @BeanProperty var lastName = ""
  @BeanProperty var x = 1

  def build {
    BinderHelper.start(mainPanel)

    bind template "name" as textfield component {
      to property(getName _, setName _)
    }

    bind template "lastName" as textfield component {
      to property(getLastName _, setLastName _)
    }

    bind template "label" as label component {
      to property(getName _, setName _)
      on click setLastName("clicked3")
    }
    bind template "label2" as label component {
      to property(getLastName _, setLastName _)
    }

    bind template "left" as label component {
      to expression getX _
    }

    bind template "top" as label component {
      to expression getX _
    }

    bind template "up" as button component {
      on click setX(x + 10)
    }

    bind template "down" as button component {
      on click setX(x - 10)
    }
  }
}