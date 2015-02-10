/*******************************************************************************
 * Copyright (c) 2011-2015 Fernando Petrola
 * 
 *  This file is part of Dragome SDK.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.dragome.examples.todo

import com.dragome.annotations.PageAlias
import com.dragome.guia.GuiaVisualActivity
import com.dragome.forms.bindings.builders.ComponentBuilder
import com.dragome.guia.components.interfaces.VisualComponent
import com.dragome.guia.listeners.ClickListener
import com.dragome.guia.components.interfaces.{ VisualLabel, VisualTextField }
import scala.beans.BeanProperty
import com.dragome.forms.bindings.builders.Consumer
import com.dragome.forms.bindings.builders.Supplier
import com.dragome.forms.bindings.builders.ActionExecutor
 
object Definitions {
  implicit def lambda2clickListener(f: VisualComponent => Unit) = new ClickListener {
    def clickPerformed(component: VisualComponent) = f(component)
  }

  implicit def lambda2ActionExecutor(f: => Unit) = new ActionExecutor {
    def execute() = f;
  }

  implicit def getter2Supplier[T](f: () => T) = new Supplier[T] {
    def get() = f.apply();
  }

  implicit def setter2Consumer[T](f: T => Unit) = new Consumer[T] {
    def accept(v: T) = f(v);
  }

  def textField = classOf[VisualTextField[_]];
  def label = classOf[VisualLabel[_]];
}