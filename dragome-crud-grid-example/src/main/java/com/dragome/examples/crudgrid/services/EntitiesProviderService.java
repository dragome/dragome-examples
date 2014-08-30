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
package com.dragome.examples.crudgrid.services;

import java.util.List;

import com.dragome.examples.crudgrid.model.Column;
import com.dragome.examples.crudgrid.model.Identifiable;

public interface EntitiesProviderService
{
    public <T extends Identifiable> List<T> getAll(Class<T> entityType);
    public List<Column> getColumnsFor(Class<?> entityType);
    public <T extends Identifiable> void saveAll(List<T> list);
    public void delete(Identifiable object);
    public Identifiable add(Identifiable object);
    public void update(Identifiable object);
}
