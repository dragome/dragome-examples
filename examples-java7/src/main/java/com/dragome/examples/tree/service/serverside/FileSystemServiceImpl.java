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
package com.dragome.examples.tree.service.serverside;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.dragome.examples.tree.FileTreeNode;
import com.dragome.examples.tree.TreeDemoPage;
import com.dragome.examples.tree.TreeNode;
import com.dragome.examples.tree.service.FileSystemService;

public class FileSystemServiceImpl implements FileSystemService
{
    public TreeNode getRoot()
    {
	TreeDemoPage.setupSerializationMechanism();
	FileTreeNode fileTreeNode= new FileTreeNode("/home/fernando/t/paypal", true);
	fileTreeNode.setLastChild(true);
	return fileTreeNode;
    }

    public List<TreeNode> getChildrenOf(String name)
    {
	TreeDemoPage.setupSerializationMechanism();

	List<TreeNode> result= new ArrayList<TreeNode>();
	File file= new File(name);
	File[] listFiles= file.listFiles();
	if (listFiles != null)
	{
	    TreeNode fileTreeNode= null;
	    for (File child : listFiles)
	    {
//		if (child.isDirectory())
		{
		    fileTreeNode= new FileTreeNode(child.getAbsolutePath());
		    result.add(fileTreeNode);
		}
	    }
	    
	    if (fileTreeNode != null)
		fileTreeNode.setLastChild(true);

	}

	return result;
    }
}
