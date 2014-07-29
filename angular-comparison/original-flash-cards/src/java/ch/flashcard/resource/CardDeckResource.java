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

import ch.flashcard.domain.CardDeck;
import ch.flashcard.service.CardDeckService;

/**
 * REST resources for accessing card decks.
 * 
 * @author Marc Baur, Adrian Herzog
 */
@Path("/carddecks")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class CardDeckResource
{

    private final CardDeckService cardDeckService= new CardDeckService();

    @GET
    public final List<CardDeck> getAllCardDecks()
    {
	return cardDeckService.getAllCardDecks();
    }

    @GET
    @Path("{id}")
    public final CardDeck getCardDeckById(@PathParam("id")
    final String id)
    {
	return cardDeckService.getCardDeckById(Integer.valueOf(id));
    }

}
