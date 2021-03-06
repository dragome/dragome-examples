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
package ch.flashcard.gui;

import java.util.List;

import ch.flashcard.persistence.CardDeckEntity;
import ch.flashcard.service.CardDeckService;

import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.VisualLabelImpl;
import com.dragome.guia.components.VisualPanelImpl;
import com.dragome.guia.components.interfaces.VisualComponent;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.guia.events.listeners.interfaces.ClickListener;
import com.dragome.render.ItemProcessorImpl;
import com.dragome.templates.TemplateRepeater;
import com.dragome.templates.interfaces.Template;
import com.dragome.web.annotations.PageAlias;

@PageAlias(alias= "deck")
public class CardDeckPage extends GuiaVisualActivity
{
	public void build()
	{
		loadMainTemplate("carddeck");
		//		templateManager= new HTMLTemplateManager();
		//	templateManager= ServiceLocator.getTemplateManager();

		final Template template= mainTemplate.getChild("panel");
		showCards(template);
	}

	private void showCards(final Template template)
	{
		CardDeckService cardDeckService= serviceFactory.createSyncService(CardDeckService.class);

		List<CardDeckEntity> cardDecks= cardDeckService.getAllCardDecks();

		new TemplateRepeater<CardDeckEntity>().repeatItems(cardDecks, new ItemProcessorImpl<CardDeckEntity>(template, "card")
		{
			public void fillTemplates(final CardDeckEntity cardDeck, List<Template> aRowTemplate)
			{
				VisualPanel rowPanel= new VisualPanelImpl(aRowTemplate.get(0));

				rowPanel.addChild(new VisualLabelImpl<String>("title", cardDeck.getTitle()));
				rowPanel.addChild(new VisualLabelImpl<String>("number-of-cards", cardDeck.getCards().size() + " Cards"));

				VisualLabelImpl<String> image= new VisualLabelImpl<String>("image", cardDeck.getCoverImage());
				image.addClickListener(new ClickListener()
				{
					public void clickPerformed(VisualComponent aVisualComponent)
					{
						openActivity(new CardDetailsPage(cardDeck));
					}
				});

				rowPanel.addChild(image);
			}
		});
	}
}
