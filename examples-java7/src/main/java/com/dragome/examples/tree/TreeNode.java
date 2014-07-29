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

import java.beans.PropertyChangeListener;
import java.util.List;

import com.dragome.model.interfaces.EventProducer;

public interface TreeNode extends EventProducer
{
    public void setChildren(List<TreeNode> children);
    public List<TreeNode> getChildren();
    public String getName();
    public abstract void setName(String name);
    public abstract void setLastChild(boolean lastChild);
    public abstract boolean isLastChild();
    public abstract void setRoot(boolean root);
    public abstract boolean isRoot();
    public abstract void setOpen(boolean open);
    public abstract boolean isOpen();
    public abstract void setFolder(boolean folder);
    public abstract boolean isFolder();
    public void addPropertyChangeListener(Object owner, PropertyChangeListener propertyChangeListener);
}
