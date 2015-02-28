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

import java.lang.reflect.Type;
import java.util.Map;

import com.dragome.examples.tree.service.FileSystemService;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.VisualPanelImpl;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.render.html.components.HTMLComponentRenderer;
import com.dragome.services.ServiceLocator;
import com.dragome.services.interfaces.SerializationService;
import com.dragome.templates.TemplateImpl;
import com.dragome.templates.interfaces.Template;
import com.dragome.web.annotations.PageAlias;

import flexjson.JSONContext;
import flexjson.ObjectBinder;
import flexjson.ObjectFactory;
import flexjson.TypeContext;
import flexjson.transformer.AbstractTransformer;
import flexjson.transformer.Transformer;

@PageAlias(alias= "tree-sync-demo")
public class TreeDemoPage extends GuiaVisualActivity
{
	private static boolean ready;

	public void initialize()
	{
		setupSerializationMechanism();
	}

	public static void setupSerializationMechanism()
	{
		if (!ready)
		{
			SerializationService serializationService= ServiceLocator.getInstance().getSerializationService();
			serializationService.addTransformer(new AbstractTransformer()
			{
				public void transform(Object object)
				{
					JSONContext context= getContext();
					LazyTreeList lazyTreeList= (LazyTreeList) object;
					TreeNode treeNode= lazyTreeList.getTreeNode();

					TypeContext typeContext= context.writeOpenObject();
					context.writeName("class");
					context.writeQuoted(LazyTreeList.class.getName());
					context.writeComma();

					context.writeName("treeNode");
					Transformer transformer= context.getTransformer(treeNode);
					transformer.transform(treeNode);
					context.writeCloseObject();
				}
			}, LazyTreeList.class);

			serializationService.addFactory(LazyTreeList.class, new ObjectFactory()
			{
				public Object instantiate(ObjectBinder context, Object value, Type targetType, Class targetClass)
				{
					LazyTreeList lazyTreeList= new LazyTreeList();
					context.bindIntoObject((Map) value, lazyTreeList, targetType);
					return lazyTreeList;
				}
			});

			ready= true;
		}
	}

	public void build()
	{
		HTMLComponentRenderer.addRenderFor(VisualTreeImpl.class, HTMLTreeRenderer.class);

		TreeNode rootNode= serviceFactory.createSyncService(FileSystemService.class).getRoot();

		Template temp1= templateHandlingStrategy.loadTemplate("tree-demo", "tree-skin");
		Template temp2= templateHandlingStrategy.loadTemplate("junit-demo", "tree-skin");

		Template rootTemplate= TemplateImpl.getTemplateElementInDepth(temp1, "panel.tree-root");
		VisualTreeImpl tree= new VisualTreeImpl(rootTemplate, rootNode, "tree");
		Template rootTemplate2= TemplateImpl.getTemplateElementInDepth(temp2, "panel.tree-root");
		VisualTreeImpl tree2= new VisualTreeImpl(rootTemplate2, rootNode, "tree2");

		Template visibleContainer= mainTemplate.getChild("panel2");
		VisualPanel mainPanel= new VisualPanelImpl(visibleContainer);
		mainPanel.addChild(tree);
		mainPanel.addChild(tree2);
	}
}
