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
import com.dragome.forms.bindings.builders.ComponentBuilder
import com.dragome.guia.components.interfaces.VisualComponent
import com.dragome.forms.bindings.builders.Consumer
import com.dragome.forms.bindings.builders.Supplier
import com.dragome.forms.bindings.builders.ActionExecutor
import com.dragome.guia.components.interfaces.VisualPanel
import com.dragome.guia.components.interfaces.VisualCheckbox
import com.dragome.guia.components.VisualRadioButton
import com.dragome.guia.components.interfaces.VisualButton
import com.dragome.forms.bindings.builders.ChildrenBuilder
import com.dragome.guia.events.listeners.interfaces.BlurListener
import com.dragome.guia.components.interfaces.VisualLink
import com.dragome.forms.bindings.client.value.ValueSource
import com.dragome.forms.bindings.builders.helpers.ItemRepeaterHelper
import com.dragome.guia.components.VisualRadioButton
import com.dragome.guia.components.interfaces.VisualButton
import com.dragome.guia.components.interfaces.VisualCheckbox
import com.dragome.guia.components.interfaces.VisualLink
import com.dragome.guia.components.interfaces.VisualPanel
import com.dragome.guia.components.interfaces.VisualTextField
import com.dragome.guia.components.interfaces.VisualLabel
import com.dragome.guia.components.interfaces.VisualComboBox

object Definitions {
    implicit def lambda2clickListener(f: VisualComponent => Unit) = new ClickListener {
        def clickPerformed(component: VisualComponent) = f(component)
    }

    implicit def lambda2blurListener(f: => Unit) = new BlurListener {
        def blurPerformed(component: VisualComponent) = f
    }

    implicit def lambda2keyupListener(f: => Unit) = new KeyUpListener {
        def keyupPerformed(visualComponent: VisualComponent, keyCode: Int) = f
    }

    implicit def lambda2keyupListener(f: (VisualComponent, Int) => Unit) = new KeyUpListener {
        def keyupPerformed(visualComponent: VisualComponent, keyCode: Int) = f(visualComponent, keyCode)
    }

    //    implicit def lambda2ChildrenBuilder(f: ComponentBuilder => Unit) = new ChildrenBuilder {
    //        def build(builder: ComponentBuilder) = f(builder)
    //    }

    implicit def lambda2ActionExecutor(f: => Unit) = new ActionExecutor {
        def execute() = f;
    }

    implicit def getter2ItemRepeaterHelper[T](f: T => Unit) = new ItemRepeaterHelper[T] {
        def process(i: T) = f.apply(i);
    }

    implicit def getter2ValueSource[T](f: () => T) = new ValueSource[T] {
        def getValue() = f.apply();
    }

    implicit def getter2Supplier[T](f: () => T) = new Supplier[T] {
        def get() = f.apply();
    }

    implicit def setter2Consumer[T](f: T => Unit) = new Consumer[T] {
        def accept(v: T) = f(v);
    }

    implicit def lambda2keyUpListener(f: () => Unit) = new KeyUpListener {
        def keyupPerformed(visualComponent: VisualComponent, keyCode: Int) = f()
    }

    implicit def lambda2SupplierBoolean(f: => Unit) = new Supplier[java.lang.Boolean] {
        def get(): java.lang.Boolean = f.asInstanceOf[java.lang.Boolean];
    }

    implicit def boolgetter2Supplier(f: () => scala.Boolean) = new Supplier[java.lang.Boolean] {
        def get() = f.apply().asInstanceOf[java.lang.Boolean]
    }

//    implicit def sbool2jbool(bool: scala.Boolean): java.lang.Boolean = new java.lang.Boolean(bool.toString())

    def textfield = classOf[VisualTextField[_]];
    def panel = classOf[VisualPanel];
    def label = classOf[VisualLabel[_]];
    def combobox = classOf[VisualComboBox[_]];
    def checkbox = classOf[VisualCheckbox];
    def radio = classOf[VisualRadioButton];
    def button = classOf[VisualButton];
    def link = classOf[VisualLink];
}