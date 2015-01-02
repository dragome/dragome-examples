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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import com.dragome.examples.tree.service.serverside.FileSystemServiceImpl;
import com.dragome.guia.components.DefaultEventProducer;

public class FileTreeNode extends DefaultEventProducer implements TreeNode
{
	protected String name;
	protected List<TreeNode> children= new ArrayList<TreeNode>();
	protected boolean root;
	protected boolean lastChild;
	protected boolean open;
	protected boolean folder;
	protected boolean error;

	public boolean isError()
	{
		return error;
	}

	public void setError(boolean error)
	{
		this.error= error;
	}

	public boolean isOpen()
	{
		return open;
	}

	public void setOpen(boolean open)
	{
		this.open= open;

		if (hasListener(PropertyChangeListener.class))
			getListener(PropertyChangeListener.class).propertyChange(new PropertyChangeEvent(this, "open", !this.open, this.open));
	}

	public boolean isRoot()
	{
		return root;
	}

	public void setRoot(boolean root)
	{
		this.root= root;
	}

	public boolean isLastChild()
	{
		return lastChild;
	}

	public void setLastChild(boolean lastChild)
	{
		this.lastChild= lastChild;
	}

	public FileTreeNode()
	{
	}

	public void setName(String name)
	{
		this.name= name;
	}

	public FileTreeNode(String rootFolder)
	{
		this();
		this.name= rootFolder;
		children= new FileSystemServiceImpl().getChildrenOf(name);
		folder= getChildren().size() > 0;
		error= (Math.round(Math.random() * 5) == 3);
		error= getName().toLowerCase().contains("re");
	}

	public FileTreeNode(String folder, boolean isRoot)
	{
		this(folder);
		root= isRoot;
	}

	public List<TreeNode> getChildren()
	{
		return children;
	}

	public String getName()
	{
		return name.substring(name.lastIndexOf("/") + 1);
	}

	public void setChildren(List<TreeNode> children)
	{
		this.children= children;
	}

	public void setFolder(boolean folder)
	{
		this.folder= folder;
	}

	public boolean isFolder()
	{
		return folder;
	}

	public void addPropertyChangeListener(Object owner, PropertyChangeListener propertyChangeListener)
	{
		addListenerForOwner(PropertyChangeListener.class, propertyChangeListener, owner);
	}
}
