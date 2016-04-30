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

import com.dragome.examples.todo.Definitions._
import com.dragome.forms.bindings.builders.helpers.BinderVisualActivity
import com.dragome.guia.GuiaServiceLocator
import com.dragome.templates.TemplateImpl
import com.dragome.templates.interfaces.Template
import com.dragome.web.annotations.PageAlias

import scala.beans.BeanProperty

@PageAlias(alias = "calculator")
class TreeExample1 extends BinderVisualActivity {
  @BeanProperty var displayValue = ""

  class Node {
    @BeanProperty var label = ""
    @BeanProperty val children = List[Node]()
  }

  def build {

    val template: Template = TemplateImpl.getTemplateElementInDepth(mainTemplate, "node")
    val clonedTemplate = GuiaServiceLocator.getInstance().getTemplateHandler().clone(template);

    val node = new Node

    bindNode(node, clonedTemplate)
  }

  def bindNode(node: Node, template: Template) {
    bind template "label" as label component {
      to property(node.getLabel _, node.setLabel _)
    }

    bind template "child" as panel component {
      replace template template
      to list node.getChildren _ repeat (bindNode(_, template))
    }
  }
}