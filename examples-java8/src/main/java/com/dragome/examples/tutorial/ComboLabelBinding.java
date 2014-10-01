package com.dragome.examples.tutorial;

import java.util.Arrays;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.model.VisualComboBoxImpl;
import com.dragome.model.interfaces.VisualLabel;

@PageAlias(alias= "combo-label-binding")
public class ComboLabelBinding extends DragomeVisualActivity
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
