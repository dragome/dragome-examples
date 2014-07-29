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
package ch.flashcard.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.flashcard.domain.Card;
import ch.flashcard.service.CardService;

/**
 * REST resources for accessing cards.
 * 
 * @author Marc Baur, Adrian Herzog
 */
@Path("/cards")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class CardResource
{

    private final CardService cardService= new CardService();

    @GET
    public final List<Card> getAllCards()
    {
	return cardService.getAllCards();
    }

    @GET
    @Path("carddeck/{id}")
    public final List<Card> getAllCardsByCardDeckId(@PathParam("id")
    final String id)
    {
	return cardService.getAllCardsByCardDeck(Integer.valueOf(id));
    }

    @GET
    @Path("{id}")
    public final Card findCardById(@PathParam("id")
    final String id)
    {
	return cardService.getCardById(Integer.valueOf(id));
    }

}
