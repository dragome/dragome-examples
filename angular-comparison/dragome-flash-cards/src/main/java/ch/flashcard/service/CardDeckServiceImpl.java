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

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.flashcard.HibernateDetachUtility;
import ch.flashcard.persistence.CardDeckEntity;
import ch.flashcard.persistence.CardEntity;
import ch.flashcard.persistence.HibernateUtil;

/**
 * Provides access to the card decks in the database.
 * 
 * @author Marc Baur, Adrian Herzog
 */
@SuppressWarnings("unchecked")
public class CardDeckServiceImpl implements CardDeckService
{
	public final CardDeckEntity getCardDeckById(final int id)
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction transaction= session.beginTransaction();

		CardDeckEntity cardDeckEntity= (CardDeckEntity) session.get(CardDeckEntity.class, id);

		transaction.commit();
		session.close();

		return cardDeckEntity;
	}

	public final List<CardDeckEntity> getAllCardDecks()
	{
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction transaction= session.beginTransaction();

		final Query query= session.createQuery("from CardDeckEntity");
		List<CardDeckEntity> cardDeckEntities= query.list();

		HibernateDetachUtility.detachObject(cardDeckEntities);

		transaction.commit();
		session.close();

		if (cardDeckEntities.isEmpty())
			cardDeckEntities= createCardDecks();

		return cardDeckEntities;
	}

	private List<CardDeckEntity> createCardDecks()
	{
		List<CardDeckEntity> cardDecks= new ArrayList<CardDeckEntity>();

		Session session= HibernateUtil.getSessionFactory().openSession();

		Transaction transaction= session.beginTransaction();

		List<CardEntity> cards= new ArrayList<CardEntity>();
		cards.add(new CardEntity("When was the Confederation gegröndet?", "In 1291"));
		cards.add(new CardEntity("What is the capital of Switzerland?", "Bern"));
		cards.add(new CardEntity("How and when the 30-year-old war is ended?", "Peace of Westphalia in 1648"));
		cards.add(new CardEntity("When the Switzerland solves the Roman Empire? How is it called?", "1648, Confoederatio Helvetica"));
		CardDeckEntity cardDeck= createCardDeck(cards, "switzerland.png", "Swiss history");
		session.persist(cardDeck);
		cardDecks.add(cardDeck);

		cards= new ArrayList<CardEntity>();

		cards.add(new CardEntity("What man played the lead role in Notting Hill?", "Hugh Grant"));
		cards.add(new CardEntity("Who was the last James Bond movie leading man?", "Daniel Craig"));
		cardDeck= createCardDeck(cards, "hollywood.png", "Hollywood stars");
		session.persist(cardDeck);
		cardDecks.add(cardDeck);

		cards= new ArrayList<CardEntity>();

		cards.add(new CardEntity("good night", "buona notte"));
		cards.add(new CardEntity("thank you", "grazie"));
		cards.add(new CardEntity("You speak good Italian", "Lei parla italiano bene"));
		cards.add(new CardEntity("Nice to meet you!", "Piacere!"));
		cards.add(new CardEntity("please", "prego"));
		cards.add(new CardEntity("From Rimini we drive to Turin.", "Da Rimini partiamo by Torino."));
		cardDeck= createCardDeck(cards, "italy.png", "Italian");
		session.persist(cardDeck);
		cardDecks.add(cardDeck);

		transaction.commit();
		session.close();

		return cardDecks;
	}

	private CardDeckEntity createCardDeck(List<CardEntity> cards, String coverImage, String title)
	{
		CardDeckEntity cardDeckEntity= new CardDeckEntity(title, coverImage, cards);
		for (CardEntity cardEntity : cards)
		{
			cardEntity.setCarddeck(cardDeckEntity);
		}

		return cardDeckEntity;
	}

}
