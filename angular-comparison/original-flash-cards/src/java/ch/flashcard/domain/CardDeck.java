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
public class CardDeck
{

    private int id;
    private String title;
    private String coverImage;
    private int numberOfCards;

    public final int getNumberOfCards()
    {
	return numberOfCards;
    }

    public final void setNumberOfCards(final int numberOfCards)
    {
	this.numberOfCards= numberOfCards;
    }

    public final int getId()
    {
	return id;
    }

    public final void setId(final int id)
    {
	this.id= id;
    }

    public final String getTitle()
    {
	return title;
    }

    public final void setTitle(final String title)
    {
	this.title= title;
    }

    public final String getCoverImage()
    {
	return coverImage;
    }

    public final void setCoverImage(final String coverImage)
    {
	this.coverImage= coverImage;
    }

}
