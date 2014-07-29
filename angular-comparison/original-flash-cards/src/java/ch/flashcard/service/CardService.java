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

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ch.flashcard.domain.Card;
import ch.flashcard.mapper.CardMapper;
import ch.flashcard.persistence.CardDeckEntity;
import ch.flashcard.persistence.CardEntity;
import ch.flashcard.persistence.HibernateUtil;

/**
 * Provides access to the cards in the database.
 * 
 * @author Marc Baur, Adrian Herzog
 */
@SuppressWarnings("unchecked")
public class CardService
{

    private final CardMapper cardMapper= new CardMapper();

    public final Card getCardById(final int id)
    {
	Session session= HibernateUtil.getSessionFactory().openSession();
	Transaction transaction= session.beginTransaction();

	CardEntity cardEntity= (CardEntity) session.get(CardEntity.class, id);
	Card card= cardMapper.mapToDomain(cardEntity);

	transaction.commit();
	session.close();

	return card;
    }

    public final List<Card> getAllCards()
    {
	Session session= HibernateUtil.getSessionFactory().openSession();
	Transaction transaction= session.beginTransaction();

	final Query query= session.createQuery("from CardDeck");
	List<CardEntity> cardEntities= query.list();
	List<Card> cards= cardMapper.mapToDomainList(cardEntities);

	transaction.commit();
	session.close();

	return cards;
    }

    public final List<Card> getAllCardsByCardDeck(final int cardDeckId)
    {
	Session session= HibernateUtil.getSessionFactory().openSession();
	Transaction transaction= session.beginTransaction();

	CardDeckEntity cardDeckEntity= (CardDeckEntity) session.get(CardDeckEntity.class, cardDeckId);
	final Criteria criteria= session.createCriteria(CardEntity.class);
	criteria.add(Restrictions.eq("carddeck", cardDeckEntity));
	List<CardEntity> cardEntities= criteria.list();
	List<Card> cards= cardMapper.mapToDomainList(cardEntities);

	transaction.commit();
	session.close();

	return cards;
    }

}
