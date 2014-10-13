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
package com.dragome.examples.tree.views;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dragome.examples.tree.TreeNode;
import com.dragome.model.VisualLabelImpl;
import com.dragome.model.VisualPanelImpl;
import com.dragome.model.interfaces.VisualComponent;
import com.dragome.model.interfaces.VisualPanel;
import com.dragome.model.listeners.ClickListener;
import com.dragome.render.DefaultView;
import com.dragome.render.interfaces.TemplateHandler;
import com.dragome.services.GuiaServiceLocator;
import com.dragome.templates.TemplateRepeater;
import com.dragome.templates.interfaces.Template;

public class TreeNodeView extends DefaultView<TreeNode>
{
	TemplateRepeater<TreeNode> templateRepeater= new TemplateRepeater<TreeNode>(false);

	protected class PropertyListener implements PropertyChangeListener
	{
		private TreeNode treeNode;
		private List<Template> templates;
		private Template originalTemplate;
		private TreeNodeView treeNodeView;

		protected PropertyListener(TreeNode treeNode, List<Template> templates, Template originalTemplate, TreeNodeView treeNodeView)
		{
			this.treeNode= treeNode;
			this.templates= templates;
			this.originalTemplate= originalTemplate;
			this.treeNodeView= treeNodeView;
		}
		public void propertyChange(PropertyChangeEvent evt)
		{
			List<Template> templates2= templates;
			final Template template= templates2.get(0);

			TemplateHandler templateHandler= GuiaServiceLocator.getInstance().getTemplateHandler();

			templateHandler.makeInvisible(template);
			if ((Boolean) evt.getOldValue())
				templateHandler.makeInvisible(templates2.get(1));

			templateRepeater.repeatItems(Arrays.asList(treeNode), templateRepeater.createItemAdder(template.getParent(), originalTemplate, Arrays.asList(treeNodeView), template.getName()));

			template.getParent().removeChild(template.getName());
			if ((Boolean) evt.getOldValue())
			{
				Template template2= templates2.get(1);
				template2.getParent().removeChild(template2.getName());
			}
		}
	}

	Map<TreeNode, PropertyListener> listeners= new HashMap<TreeNode, PropertyListener>();

	public TreeNodeView(Template originalTemplate)
	{
		super(originalTemplate);
	}

	public void composeView(final TreeNode model, List<Template> templates)
	{
		super.composeView(model, templates);
		Template template= templates.get(0);
		VisualPanel connectorPanel= new VisualPanelImpl(template);

		connectorPanel.addChild(new VisualLabelImpl<String>("label", model.getName()));

		if (model.isFolder())
		{
			addConnectorClickListener(model, connectorPanel, templates);

			if (model.isOpen())
			{
				templateRepeater.repeatItems(model.getChildren(), templateRepeater.createItemAdder(templates.get(1), originalTemplate, Arrays.asList(this), "children"));
			}
		}
	}

	public void addConnectorClickListener(final TreeNode treeNode, final VisualPanel rowPanel, final List<Template> templates)
	{
		PropertyListener propertyChangeListener= listeners.get(treeNode);
		if (propertyChangeListener != null)
		{
			propertyChangeListener.templates= templates;
		}
		else
		{
			PropertyListener propertyChangeListener2= new PropertyListener(treeNode, templates, originalTemplate, TreeNodeView.this);
			listeners.put(treeNode, propertyChangeListener2);
			treeNode.addPropertyChangeListener(this, propertyChangeListener2);
		}

		final VisualPanel connectorPanel= new VisualPanelImpl(templates.get(0).getChild("[event=click]"));
		connectorPanel.addClickListener(new ClickListener()
		{
			public void clickPerformed(VisualComponent aVisualComponent)
			{
				treeNode.setOpen(!treeNode.isOpen());
			}
		});
		rowPanel.addChild(connectorPanel);
	}
}
