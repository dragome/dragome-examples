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

import com.dragome.web.annotations.PageAlias
import com.dragome.guia.GuiaVisualActivity
import com.dragome.forms.bindings.builders.ComponentBuilder
import scala.beans.BeanProperty
import Definitions._

@PageAlias(alias = "simple-binding")
class ScalaSimpleBinding() extends GuiaVisualActivity {
  @BeanProperty var text = ""

  def build {
    val builder = new ComponentBuilder(mainPanel);

//    builder bindTemplate "textfield" as textfield toProperty (getText _, setText _) build;
//    builder bindTemplate "label" as label toProperty getText _ onClick setText("clicked2") build;
  }
}