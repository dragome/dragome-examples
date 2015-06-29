/*******************************************************************************
 * Copyright (c) 2011-2014 Fernando Petrola
 * 
 *  This file is part of Dragome SDK.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 ******************************************************************************/
package com.dragome.examples.templates;

import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.VisualButtonImpl;
import com.dragome.guia.components.VisualPanelImpl;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.render.html.HTMLTemplateLoadingStrategy;
import com.dragome.templates.interfaces.Template;
import com.dragome.web.annotations.PageAlias;

@PageAlias(alias= "template-loading-example1")
public class TemplateLoadingExample1Page extends GuiaVisualActivity
{
	public void build()
	{
		Template temp1= ((HTMLTemplateLoadingStrategy) templateHandlingStrategy).createTemplateFromHtml("<div> <button data-template=\"button1\">Click</button>	</div>");
//		Template temp1= templateHandlingStrategy.loadTemplate("template1", "panel_1_A");
		
		Template visibleContainer= mainTemplate.getChild("panel_0_A");
		temp1.setName("panel_0_B");
		visibleContainer.addChild(temp1);

		VisualPanel childPanel= new VisualPanelImpl(temp1);

		childPanel.addChild(new VisualButtonImpl("button1", (v) -> System.out.println("hello")));
	}
}
