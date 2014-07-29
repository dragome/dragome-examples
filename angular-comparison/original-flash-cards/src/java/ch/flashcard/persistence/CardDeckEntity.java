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

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Marc Baur, Adrian Herzog
 */
@Entity
@Table(name= "carddecks")
public class CardDeckEntity
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(name= "cover_image")
    private String coverImage;
    @OneToMany(mappedBy= "carddeck", cascade= CascadeType.ALL)
    private List<CardEntity> cards;

    public CardDeckEntity()
    {
    }

    public CardDeckEntity(String title, String coverImage, List<CardEntity> cards)
    {
	super();
	this.title= title;
	this.coverImage= coverImage;
	this.cards= cards;
    }

    public final List<CardEntity> getCards()
    {
	return cards;
    }

    public final void setCards(final List<CardEntity> cards)
    {
	this.cards= cards;
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
