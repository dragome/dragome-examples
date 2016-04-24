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

import com.dragome.web.annotations.PageAlias
import com.dragome.guia.events.listeners.interfaces.ClickListener
import com.dragome.guia.events.listeners.interfaces.KeyUpListener
import com.dragome.guia.GuiaVisualActivity
import com.dragome.forms.bindings.builders.ComponentBuilder
import com.dragome.guia.components.interfaces.VisualComponent
import com.dragome.guia.components.interfaces.{ VisualLabel, VisualTextField }
import scala.beans.BeanProperty
import com.dragome.forms.bindings.builders.Supplier
import com.dragome.forms.bindings.builders.Consumer

import Definitions._
import com.dragome.forms.bindings.builders.TemplateBindingBuilder

trait ScalaBinder {
    var builder = new ComponentBuilder(null);

    def bind(n: String) = new ProfileOpsCls(builder.bindTemplate(n))

    // syntax
    class ProfileOpsCls(tbb: TemplateBindingBuilder) {
        def as(v: Class[VisualComponent]) = tbb.as(v)
    }
}