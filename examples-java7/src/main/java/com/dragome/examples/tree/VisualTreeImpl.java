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

import com.dragome.guia.GuiaServiceLocator;
import com.dragome.guia.components.VisualPanelImpl;
import com.dragome.templates.TemplateLayout;
import com.dragome.templates.interfaces.Template;

public class VisualTreeImpl extends VisualPanelImpl
{
    private TreeNode rootNode;
    private Template originalTemplate;

    public VisualTreeImpl()
    {
    }

    public TreeNode getRootNode()
    {
	return rootNode;
    }

    public void setRootNode(TreeNode rootNode)
    {
	this.rootNode= rootNode;
    }

    public VisualTreeImpl(Template rootTemplate, TreeNode rootNode, String aName)
    {
	super(aName);
	this.setOriginalTemplate(rootTemplate);
	this.rootNode= rootNode;
	Template clone= GuiaServiceLocator.getInstance().getTemplateHandler().clone(rootTemplate);

	setLayout(new TemplateLayout(clone));
    }

    public Template getOriginalTemplate()
    {
	return originalTemplate;
    }

    public void setOriginalTemplate(Template originalTemplate)
    {
	this.originalTemplate= originalTemplate;
    }
}
