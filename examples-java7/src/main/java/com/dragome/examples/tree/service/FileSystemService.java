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
package com.dragome.examples.tree.service;

import java.util.List;

import com.dragome.annotations.ServiceImplementation;
import com.dragome.examples.tree.TreeNode;
import com.dragome.examples.tree.service.serverside.FileSystemServiceImpl;

@ServiceImplementation(FileSystemServiceImpl.class)
public interface FileSystemService
{
    TreeNode getRoot();
    List<TreeNode> getChildrenOf(String name);
}
