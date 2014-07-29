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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.dragome.examples.tree.service.FileSystemService;
import com.dragome.services.ServiceLocator;

public class LazyTreeList implements List<TreeNode>
{

	private TreeNode treeNode;

	public TreeNode getTreeNode()
	{
		return treeNode;
	}

	public void setTreeNode(TreeNode treeNode)
	{
		this.treeNode= treeNode;
	}

	public LazyTreeList(TreeNode treeNode)
	{
		this.treeNode= treeNode;
	}

	public LazyTreeList()
	{
	}

	public int size()
	{
		return getChildren().size();
	}

	@Override
	public boolean isEmpty()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<TreeNode> iterator()
	{
		List<TreeNode> childrenOf;
		childrenOf= getChildren();

		return childrenOf.iterator();
	}

	private List<TreeNode> getChildren()
	{
		return ServiceLocator.getInstance().getServiceFactory().createSyncService(FileSystemService.class).getChildrenOf(treeNode.getName());
	}
	@Override
	public Object[] toArray()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(TreeNode e)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends TreeNode> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends TreeNode> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public TreeNode get(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeNode set(int index, TreeNode element)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(int index, TreeNode element)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public TreeNode remove(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int lastIndexOf(Object o)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<TreeNode> listIterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<TreeNode> listIterator(int index)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TreeNode> subList(int fromIndex, int toIndex)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
