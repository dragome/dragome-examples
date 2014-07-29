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

import ch.flashcard.persistence.CardEntity;

public interface CardService
{
	public abstract CardEntity getCardById(int id);
	public abstract List<CardEntity> getAllCards();
	public abstract List<CardEntity> getAllCardsByCardDeck(int cardDeckId);
}
