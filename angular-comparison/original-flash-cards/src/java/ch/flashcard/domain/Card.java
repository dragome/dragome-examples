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
package ch.flashcard.domain;

/**
 * @author Marc Baur, Adrian Herzog
 */
public class Card
{

    private int id;
    private String question;
    private String answer;
    private int cardDeckId;

    public final int getId()
    {
	return id;
    }

    public final void setId(final int id)
    {
	this.id= id;
    }

    public final String getQuestion()
    {
	return question;
    }

    public final void setQuestion(final String question)
    {
	this.question= question;
    }

    public final String getAnswer()
    {
	return answer;
    }

    public final void setAnswer(final String answer)
    {
	this.answer= answer;
    }

    public final int getCardDeckId()
    {
	return cardDeckId;
    }

    public final void setCardDeckId(final int cardDeckId)
    {
	this.cardDeckId= cardDeckId;
    }

}
