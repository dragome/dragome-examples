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

import ch.flashcard.domain.CardDeck;
import ch.flashcard.persistence.CardDeckEntity;

/**
 * @author Marc Baur, Adrian Herzog
 */
public class CardDeckMapper extends AbstractMapper<CardDeck, CardDeckEntity>
{

    @Override
    public final CardDeck mapToDomain(final CardDeckEntity cardDeckEntity)
    {
	CardDeck cardDeck= new CardDeck();

	cardDeck.setCoverImage(cardDeckEntity.getCoverImage());
	cardDeck.setId(cardDeckEntity.getId());
	cardDeck.setNumberOfCards(cardDeckEntity.getCards().size());
	cardDeck.setTitle(cardDeckEntity.getTitle());

	return cardDeck;
    }

}
