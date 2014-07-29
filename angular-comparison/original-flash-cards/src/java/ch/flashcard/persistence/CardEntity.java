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
package ch.flashcard.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Marc Baur, Adrian Herzog
 */
@Entity
@Table(name= "cards")
public class CardEntity
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String question;
    private String answer;
    @ManyToOne
    private CardDeckEntity carddeck;

    public CardEntity()
    {
    }

    public CardEntity(String question, String answer)
    {
	super();
	this.question= question;
	this.answer= answer;
    }

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

    public final CardDeckEntity getCarddeck()
    {
	return carddeck;
    }

    public final void setCarddeck(final CardDeckEntity carddeck)
    {
	this.carddeck= carddeck;
    }

}
