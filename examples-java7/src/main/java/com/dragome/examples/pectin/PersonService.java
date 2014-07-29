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
package com.dragome.examples.pectin;

import java.util.List;

import com.dragome.annotations.ServiceImplementation;
import com.dragome.examples.pectin.serverside.PersonServiceImpl;

@ServiceImplementation(PersonServiceImpl.class)
public interface PersonService
{
    public List<Person> getPersons();
}



