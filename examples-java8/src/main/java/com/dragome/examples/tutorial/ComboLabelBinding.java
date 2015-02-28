package com.dragome.examples.tutorial;

import java.util.Arrays;

import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.VisualComboBoxImpl;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.web.annotations.PageAlias;

@PageAlias(alias= "combo-label-binding")
public class ComboLabelBinding extends GuiaVisualActivity
{
	protected String nickname;
	
	public void build()
	{
		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);
		
		VisualComboBoxImpl<String> combo= new VisualComboBoxImpl<String>("nickname", Arrays.asList("Pelusa", "Burrito", "Bocha", "Bruja"));
		
		componentBuilder.bindTemplate("nickname")
			.to(combo)
			.toProperty(this::getNickname, this::setNickname)
			.build();
		
		componentBuilder.bindTemplate("text")
			.as(VisualLabel.class)
			.toProperty(this::getNickname, this::setNickname)
			.build();
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname= nickname;
	}
}
