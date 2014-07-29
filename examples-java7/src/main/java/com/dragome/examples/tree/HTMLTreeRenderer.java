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
package com.dragome.examples.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Element;

import com.dragome.examples.tree.views.TreeNodeView;
import com.dragome.render.canvas.interfaces.Canvas;
import com.dragome.render.html.components.MergeableElement;
import com.dragome.render.interfaces.ComponentRenderer;
import com.dragome.render.interfaces.View;
import com.dragome.services.ServiceLocator;
import com.dragome.templates.TemplateLayout;
import com.dragome.templates.TemplateRepeater;
import com.dragome.templates.interfaces.Template;

public class HTMLTreeRenderer implements ComponentRenderer<Element, VisualTreeImpl>
{
	public HTMLTreeRenderer()
	{
	}

	public Canvas<Element> render(final VisualTreeImpl visualTree)
	{
		Canvas<Element> canvas= ServiceLocator.getInstance().getTemplateManager().getCanvasFactory().createCanvas();

		canvas.setContent(new MergeableElement()
		{
			public void mergeWith(Element element)
			{
				Template rootTemplate= ((TemplateLayout) visualTree.getLayout()).getTemplate();

				List<View<TreeNode>> modelViews= new ArrayList<View<TreeNode>>();
				Template originalTemplate= visualTree.getOriginalTemplate();
				modelViews.add(new TreeNodeView(originalTemplate));

				TreeNode rootNode= visualTree.getRootNode();
				TemplateRepeater templateRepeater= new TemplateRepeater(false);
				templateRepeater.repeatItems(Arrays.asList(rootNode), templateRepeater.createItemAdder(rootTemplate, originalTemplate, modelViews, "children"));
			}
		});

		return canvas;
	}

}
