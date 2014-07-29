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
package ch.flashcard.mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marc Baur, Adrian Herzog
 * 
 * @param <D>
 *            Domain Type
 * @param <E>
 *            Entity Type
 */
public abstract class AbstractMapper<D, E>
{

    public abstract D mapToDomain(final E entityItem);

    public final List<D> mapToDomainList(final List<E> entityItems)
    {
	List<D> mappedItems= new ArrayList<D>(entityItems.size());
	for (E entityItem : entityItems)
	{
	    mappedItems.add(mapToDomain(entityItem));
	}
	return mappedItems;
    }

}
