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

import ch.flashcard.domain.Card;
import ch.flashcard.persistence.CardEntity;

/**
 * @author Marc Baur, Adrian Herzog
 */
public class CardMapper extends AbstractMapper<Card, CardEntity>
{

    @Override
    public final Card mapToDomain(final CardEntity cardEntity)
    {
	Card card= new Card();

	card.setAnswer(cardEntity.getAnswer());
	card.setCardDeckId(cardEntity.getCarddeck().getId());
	card.setId(cardEntity.getId());
	card.setQuestion(cardEntity.getQuestion());

	return card;
    }

}
