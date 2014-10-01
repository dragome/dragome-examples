package com.dragome.examples;

import java.util.Arrays;

import com.dragome.annotations.PageAlias;
import com.dragome.commons.ExecutionHandler;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.model.VisualComboBoxImpl;
import com.dragome.model.VisualPanelImpl;
import com.dragome.model.interfaces.VisualButton;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.interfaces.VisualTextField;
import com.dragome.render.interfaces.TemplateHandler;
import com.dragome.services.ServiceLocator;
import com.dragome.templates.interfaces.Template;

@PageAlias(alias= "continuation1")
public class ContinuationExample extends DragomeVisualActivity
{
	protected float result= 0f;

	public float getResult()
	{
		return result;
	}

	public void setResult(float result)
	{
		this.result= result;
	}

	public void build()
	{
		final ExecutionHandler executionHandler= ServiceLocator.getInstance().getConfigurator().getExecutionHandler();
		TemplateHandler templateHandler= ServiceLocator.getInstance().getTemplateHandler();

		Template operationPanelTemplate= mainTemplate.getChild("operation-panel");
		templateHandler.makeInvisible(operationPanelTemplate);

		final ComponentBuilder mainBuilder= new ComponentBuilder(mainPanel);

		mainBuilder.bindTemplate("result").as(VisualLabel.class).to(() -> getResult()).build();

		mainBuilder.bindTemplate("start-button").as(VisualButton.class).onClick(() -> {
			executionHandler.getExecutor().execute(() -> {
				while (true)
				{
					Template clone= templateHandler.clone(operationPanelTemplate);
					mainTemplate.insertAfter(clone, operationPanelTemplate);
					templateHandler.makeVisible(clone);

					ComponentBuilder componentBuilder= new ComponentBuilder(new VisualPanelImpl(clone));

					VisualTextField<String> valueTextField= componentBuilder.bindTemplate("value").as(VisualTextField.class).to(() -> "").build();
					VisualComboBoxImpl<Operation> operationCombo= componentBuilder.bindTemplate("operation").to(new VisualComboBoxImpl<Operation>("operation", Arrays.asList(Operation.values()))).to(() -> Operation.add).build();
					componentBuilder.bindTemplate("calc-button").as(VisualButton.class).onClick(() -> {
						setResult(operationCombo.getValue().apply(result, Integer.parseInt(valueTextField.getValue())));
						executionHandler.continueExecution();
					}).build();

					executionHandler.suspendExecution();
				}
			});
		}).build();

	}
}
