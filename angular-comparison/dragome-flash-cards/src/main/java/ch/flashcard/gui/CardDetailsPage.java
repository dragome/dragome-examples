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
import ch.flashcard.persistence.CardEntity;
import ch.flashcard.service.CardDeckService;

import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.VisualButtonImpl;
import com.dragome.guia.components.VisualLabelImpl;
import com.dragome.guia.components.VisualLinkImpl;
import com.dragome.guia.components.VisualPanelImpl;
import com.dragome.guia.components.interfaces.VisualButton;
import com.dragome.guia.components.interfaces.VisualComponent;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.guia.components.interfaces.VisualLink;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.guia.listeners.ClickListener;
import com.dragome.templates.interfaces.Template;

public class CardDetailsPage extends GuiaVisualActivity
{
	protected CardDeckEntity cardDeckEntity;
	protected List<CardDeckEntity> cardDecks;
	protected int currentQuestion= 0;

	protected VisualButton moreButton;
	protected VisualButton showAnswerButton;
	protected VisualButton backButton;
	protected VisualLabel<String> questionLabel;
	protected VisualLabel<String> answerLabel;
	protected VisualLabel<String> percentageLabel;
	protected VisualLabel<String> percentageValue;
	protected VisualPanel answerPanel;
	protected VisualPanel questionMarkPanel;
	protected VisualLabel<String> titleLabel;

	public CardDetailsPage(CardDeckEntity cardDeckEntity)
	{
		this.cardDeckEntity= cardDeckEntity;
	}

	public void build()
	{
		loadMainTemplate("card-details");
		//	templateManager= new HTMLTemplateManager();

		final Template template= mainTemplate.getChild("panel");
		showCards(template);
	}

	protected void showCards(final Template template)
	{
		CardDeckService cardDeckService= serviceFactory.createSyncService(CardDeckService.class);
		cardDecks= cardDeckService.getAllCardDecks();

		VisualPanel panel= new VisualPanelImpl(template);

		titleLabel= new VisualLabelImpl<String>("title", cardDeckEntity.getTitle());
		questionMarkPanel= new VisualPanelImpl(template.getChild("question-mark"));
		questionLabel= new VisualLabelImpl<String>("question", "");
		percentageValue= new VisualLabelImpl<String>("percentage-value", "0");
		percentageLabel= new VisualLabelImpl<String>("percentage-label", "0%");
		answerPanel= new VisualPanelImpl(template.getChild("answer-panel"));
		answerLabel= new VisualLabelImpl<String>("answer", "");
		VisualLabel<String> cardsLabel= new VisualLabelImpl<String>("number-of-cards", cardDeckEntity.getCards().size() + "");
		VisualLink homeLink= new VisualLinkImpl("home-link");
		homeLink.addClickListener(new ClickListener()
		{
			public void clickPerformed(VisualComponent aVisualComponent)
			{
				openActivity(new CardDeckPage());
			}
		});

		answerPanel.addChild(answerLabel);

		createShowAnswerButton();
		createMoreButton();
		createBackButton();

		panel.addChild(percentageValue);
		panel.addChild(percentageLabel);
		panel.addChild(titleLabel);
		panel.addChild(questionLabel);
		panel.addChild(answerPanel);
		panel.addChild(questionMarkPanel);
		panel.addChild(showAnswerButton);
		panel.addChild(moreButton);
		panel.addChild(backButton);
		panel.addChild(cardsLabel);
		panel.addChild(homeLink);

		updateQuestion();
		moreButton.getStyle().setVisible(false);
		updateProgress();
	}

	protected void createBackButton()
	{
		backButton= new VisualButtonImpl("back-button", "");
		backButton.addClickListener(new ClickListener()
		{
			public void clickPerformed(VisualComponent aVisualComponent)
			{
				currentQuestion--;
				updateProgress();
				updateQuestion();
			}
		});
	}

	protected void createMoreButton()
	{
		moreButton= new VisualButtonImpl("more-button", "");
		moreButton.addClickListener(new ClickListener()
		{
			public void clickPerformed(VisualComponent aVisualComponent)
			{
				updateQuestionState(false);
				updateQuestion();
			}
		});
	}

	protected void createShowAnswerButton()
	{
		showAnswerButton= new VisualButtonImpl("show-answer-button", "");
		showAnswerButton.addClickListener(new ClickListener()
		{
			public void clickPerformed(VisualComponent aVisualComponent)
			{
				updateQuestionState(true);
				currentQuestion++;
				updateProgress();
			}
		});
	}

	protected void updateProgress()
	{
		int percent= Math.round((100f / cardDeckEntity.getCards().size() * currentQuestion));
		percentageValue.setValue("" + percent);
		percentageLabel.setValue(percent > 0 ? percent + "%" : "");
		updateButtons();
	}

	private void updateButtons()
	{
		backButton.getStyle().setEnabled(currentQuestion > 0);
		moreButton.getStyle().setEnabled(currentQuestion < cardDeckEntity.getCards().size());
	}

	protected void updateQuestion()
	{
		if (currentQuestion >= 0 && currentQuestion < cardDeckEntity.getCards().size())
		{
			CardEntity cardEntity= cardDeckEntity.getCards().get(currentQuestion);
			questionLabel.setValue(cardEntity.getQuestion());
			answerLabel.setValue(cardEntity.getAnswer());
		}
	}

	private void updateQuestionState(boolean showAnswer)
	{
		showAnswerButton.getStyle().setVisible(!showAnswer);
		questionMarkPanel.getStyle().setVisible(!showAnswer);
		answerPanel.getStyle().setVisible(showAnswer);
		moreButton.getStyle().setVisible(showAnswer);
	}
}
