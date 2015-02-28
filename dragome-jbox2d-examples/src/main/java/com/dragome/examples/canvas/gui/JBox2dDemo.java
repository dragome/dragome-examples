package com.dragome.examples.canvas.gui;

import org.jbox2d.gwt.showcase.client.example.BlobTest4;
import org.jbox2d.gwt.showcase.client.example.ExampleList;
import org.jbox2d.gwt.showcase.client.example.Web;
import org.jbox2d.gwt.showcase.client.framework.BaseExample;
import org.jbox2d.gwt.showcase.client.rendering.ExampleView;

import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.SimpleRenderer;
import com.dragome.guia.components.VisualComboBoxImpl;
import com.dragome.guia.components.interfaces.VisualComboBox;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.web.annotations.PageAlias;

@PageAlias(alias= "demo")
public class JBox2dDemo extends GuiaVisualActivity
{
	public void build()
	{
		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);

		ExampleView exampleView= new ExampleView(mainTemplate.getChild("canvas").getContent());

		VisualComboBox<BaseExample> exampleList= new VisualComboBoxImpl<>();
		exampleList.setRenderer(new SimpleRenderer<BaseExample>()
		{
			public String render(BaseExample object)
			{
				return object.getTestName();
			}
		});
		exampleList.setAcceptableValues(ExampleList.tests);

		
		VisualLabel<String> label= componentBuilder.bindTemplate("instructions").as(VisualLabel.class).build();
		
		componentBuilder.bindTemplate("example-select").to(exampleList).onValueChange(() -> {
			try	
			{
				BaseExample newInstance= exampleList.getValue();
				exampleView.onStartExample(newInstance);
				label.setValue(newInstance.getInstructions().toString());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}).build();
		

		BaseExample example= new BlobTest4();
		example= new Web();
		exampleView.onStartExample(example);
		label.setValue(example.getInstructions().toString());
	}
}