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
import com.dragome.forms.bindings.builders.helpers.BinderHelper._
import com.dragome.guia.events.listeners.interfaces.BlurListener
import com.dragome.guia.components.interfaces.VisualLink
import com.dragome.forms.bindings.client.value.ValueSource
import com.dragome.forms.bindings.builders.helpers.{BinderHelper, ItemRepeaterHelper, RepeaterBuilderHelper}
import com.dragome.guia.components.VisualRadioButton
import com.dragome.guia.components.interfaces.VisualButton
import com.dragome.guia.components.interfaces.VisualCheckbox
import com.dragome.guia.components.interfaces.VisualLink
import com.dragome.guia.components.interfaces.VisualPanel
import com.dragome.guia.components.interfaces.VisualTextField
import com.dragome.guia.components.interfaces.VisualLabel
import com.dragome.guia.components.interfaces.VisualComboBox
import collection.JavaConverters._

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

  implicit def lambda2ActionExecutor(f: => Any) = new ActionExecutor {
    def execute() = f;
  }

  //    implicit def lambda2ActionExecutor(f: => Unit) = new ActionExecutor {
  //        def execute() = f;
  //    }

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

  //    implicit def boolgetter2Supplier(f: () => scala.Boolean) = new Supplier[java.lang.Boolean] {
  //        def get() = f.apply().asInstanceOf[java.lang.Boolean]
  //    }

  //    implicit def sbool2jbool(bool: scala.Boolean): java.lang.Boolean = new java.lang.Boolean(bool.toString())

  def textfield = classOf[VisualTextField[_]];

  def panel = classOf[VisualPanel];

  def label[T] = classOf[VisualLabel[T]];

  def combobox = classOf[VisualComboBox[_]];

  def checkbox = classOf[VisualCheckbox];

  def radio = classOf[VisualRadioButton];

  def button = classOf[VisualButton];

  def link = classOf[VisualLink];


  class AsComponent[T <: VisualComponent](componentType: Class[T], binderHelper: BinderHelper) {
    def component(y: => Unit): T = {
      binderHelper.as(componentType, y)
    }
  }

  class UseTemplate(binderHelper: BinderHelper) {
    def as[T <: VisualComponent](componentType: Class[T]): AsComponent[T] = {
      new AsComponent[T](componentType, binderHelper)
    }
  }

  object bind {
    def template(in: String): UseTemplate = {
      new UseTemplate(BinderHelper.bind(in))
    }
  }

  object show {
    def when(in: => Unit) = {
      null
    }
  }

  class style2(in: String) {
    def when(y: => scala.Boolean) = {
      styleWith(in).when(new Supplier[java.lang.Boolean] {
        def get: java.lang.Boolean = {
          y
        }
      })
    }
  }

  object style {
    def using(in: String): style2 = {
      new style2(in)
    }
  }

  class keyHandler(aKeyCode: Int) {
    def up(y: ActionExecutor) = {
      onKeyUp(new KeyUpListener() {
        def keyupPerformed(visualComponent: VisualComponent, keyCode: Int) {
          y.execute()
        }
      }, aKeyCode)
    }
  }

  class keyup2 {
    def perform(y: KeyUpListener) = {
    }
  }

  object on {
    def click(in: => Unit) = {
      onClick(in)
    }

    def doubleClick(in: => Unit) = {
      onDoubleClick(in)
    }

    def key(in: Int): keyHandler = {
      new keyHandler(in)
    }

    def keys(keysCodes: Int*): keysHandler = {
      new keysHandler(keysCodes)
    }

    class keysHandler(keysCodes: Seq[Int]) {
      def up(y: KeyUpListener) = {
        onKeyUp(y, keysCodes(1))
      }
    }

    object on {
    }

    def blur(in: => Unit) = {
      onBlur(new BlurListener() {
        def blurPerformed(visualComponent: VisualComponent) {
          in
        }
      })
    }
  }

  object to {
    def property[S](getter: Supplier[S], setter: Consumer[S]) = {
      toProperty(getter, setter)
    }

    def expression[S](getter: ValueSource[S]) = {
      BinderHelper.to(getter)
    }

    def value[S](getter: S) = {
      BinderHelper.to(() => getter)
    }

    def doubleClick(in: => Unit) = {
      null
    }

    def blur(in: => Unit) = {
      null
    }

    def list[S](getter: Supplier[java.util.List[S]]): RepeaterBuilderHelper[S] = {
      toListProperty(getter)
    }

    def list[S](getter: () => List[S]): RepeaterBuilderHelper[S] = {
      toListProperty(new Supplier[java.util.List[S]] {
        def get(): java.util.List[S] = {
          getter().asJava
        }
      })
    }

  }

  object use {
    def panel(in: VisualPanel) = {
      BinderHelper.start(in)
    }
  }

}