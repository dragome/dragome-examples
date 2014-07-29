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
package ch.flashcard.service;

import java.util.List;

import ch.flashcard.persistence.CardDeckEntity;

import com.dragome.annotations.ServiceImplementation;

@ServiceImplementation(CardDeckServiceImpl.class)
public interface CardDeckService
{
	public abstract CardDeckEntity getCardDeckById(int id);
	public abstract List<CardDeckEntity> getAllCardDecks();
}
