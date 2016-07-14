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

import scala.beans.BeanProperty

import com.dragome.examples.todo.Definitions._
import com.dragome.forms.bindings.builders.helpers.BinderVisualActivity
import com.dragome.templates.interfaces.Template
import com.dragome.web.annotations.PageAlias
import com.dragome.guia.GuiaServiceLocator
import com.dragome.templates.TemplateImpl

@PageAlias(alias = "tree")
class TreeExample1 extends BinderVisualActivity {
    @BeanProperty var displayValue = ""
    var nodeTemplate: Template = null
    var leafTemplate: Template = null

    class Node(aLabel: String, aChildren: List[Node] = List()) {
        @BeanProperty var label = aLabel
        @BeanProperty var children = aChildren
    }

    def nodeTemplateClone: Template = GuiaServiceLocator.getInstance().getTemplateHandler().clone(nodeTemplate);
    def leafTemplateClone: Template = GuiaServiceLocator.getInstance().getTemplateHandler().clone(leafTemplate);

    def build {

        nodeTemplate = templateHandlingStrategy.loadTemplate("tree", "node");
        leafTemplate = templateHandlingStrategy.loadTemplate("tree", "leaf");

        val node = new Node("root")
        var secondChild = new Node("child1.2.0", List(new Node("child1.2.1"), new Node("child1.1.2.2")))
        var firstChild = new Node("child1", List(new Node("child1.1"), new Node("child1.2", List(secondChild))))
        //        firstChild= new Node("first-child")

        node.children = List(firstChild, new Node("child2", List(secondChild, firstChild)))

        bind template "node" as panel component {
            bindNode(node)
        }
    }

    def bindNode(node: Node) {
        if (node.children.isEmpty)
            replace template leafTemplateClone
        else
            bind template "child" as panel component {
                replace template nodeTemplateClone
                to list node.getChildren _ repeat bindNode _
            }

        bind template "label" as label component {
            to property (node.getLabel _, node.setLabel _)
            on click println(node.getLabel() + " clicked")
        }
    }
}