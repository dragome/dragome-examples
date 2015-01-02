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
package com.dragome.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.dragome.annotations.PageAlias;
import com.dragome.forms.bindings.client.form.binding.FormBinder;
import com.dragome.forms.bindings.client.value.MutableValueModel;
import com.dragome.forms.bindings.client.value.ValueHolder;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.VisualButtonImpl;
import com.dragome.guia.components.VisualLabelImpl;
import com.dragome.guia.components.VisualPanelImpl;
import com.dragome.guia.components.VisualTextAreaImpl;
import com.dragome.guia.components.VisualTextFieldImpl;
import com.dragome.guia.components.interfaces.VisualComponent;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.guia.components.interfaces.VisualTextArea;
import com.dragome.guia.components.interfaces.VisualTextField;
import com.dragome.guia.listeners.ClickListener;
import com.dragome.model.interfaces.ValueChangeEvent;
import com.dragome.model.interfaces.ValueChangeHandler;
import com.dragome.render.ItemProcessorImpl;
import com.dragome.templates.TemplateRepeater;
import com.dragome.templates.interfaces.Template;

@PageAlias(alias= "template-editor")
public class TemplateEditor extends GuiaVisualActivity
{
	protected List<MutableValueModel<String>> replacements= new ArrayList<>();
	protected VisualTextArea<String> templateField;
	protected VisualLabelImpl<String> resultLabel;

	public void build()
	{
		templateField= new VisualTextAreaImpl<>("template", "");
		resultLabel= new VisualLabelImpl<>("result");

		mainPanel.addChild(new VisualButtonImpl("update-button", new ClickListener()
		{
			public void clickPerformed(VisualComponent aVisualComponent)
			{
				updateLabel();
			}
		}));
		mainPanel.addChild(new VisualButtonImpl("add-button", new ClickListener()
		{
			public void clickPerformed(VisualComponent aVisualComponent)
			{
				ValueHolder<String> e= new ValueHolder<String>();
				replacements.add(e);
				showReplacements((List) Arrays.asList(e));
			}
		}));

		mainPanel.addChild(templateField);
		mainPanel.addChild(resultLabel);

		showReplacements(replacements);
	}
	private void updateLabel()
	{
		String value= templateField.getValue();
		int i= 0;
		for (MutableValueModel<String> hasValue : replacements)
		{
			i++;
			String value2= hasValue.getValue();
			if (value2 != null)
				value= value.replace("${" + i + "}", value2);
		}
		resultLabel.setValue(value);
	}

	public void showReplacements(List<MutableValueModel<String>> aReplacements)
	{
		new TemplateRepeater<MutableValueModel<String>>().repeatItems(aReplacements, new ItemProcessorImpl<MutableValueModel<String>>(mainTemplate, "replacements")
		{
			public void fillTemplate(final MutableValueModel<String> item, Template aTemplate)
			{
				final VisualPanel panel= new VisualPanelImpl(aTemplate);

				panel.addChild(new VisualButtonImpl("delete-button",new ClickListener()
				{
					public void clickPerformed(VisualComponent aVisualComponent)
					{
						replacements.remove(item);
						panel.getParent().removeChild(panel);
					}
				})); 
						
				VisualTextField<String> templateField= new VisualTextFieldImpl<>("replacement");
				new FormBinder().bind(item).to(templateField);
				templateField.addValueChangeHandler(new ValueChangeHandler<String>()
				{
					public void onValueChange(ValueChangeEvent<String> event)
					{
						updateLabel();
					}
				});

				panel.addChild(templateField);
				mainPanel.addChild(panel);
			}
		});
	}
}
