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
package com.dragome.examples.todo.view;

import static com.dragome.model.listeners.KeyListener.KEY_ENTER;
import static com.dragome.model.listeners.KeyListener.KEY_ESC;

import java.util.stream.Stream;

import com.dragome.annotations.PageAlias;
import com.dragome.debugging.execution.DragomeVisualActivity;
import com.dragome.examples.todo.model.Todo;
import com.dragome.examples.todo.model.TodoManager;
import com.dragome.forms.bindings.builders.ComponentBuilder;
import com.dragome.forms.bindings.builders.LocalStorage;
import com.dragome.model.interfaces.VisualButton;
import com.dragome.model.interfaces.VisualCheckbox;
import com.dragome.model.interfaces.VisualLabel;
import com.dragome.model.interfaces.VisualLink;
import com.dragome.model.interfaces.VisualPanel;
import com.dragome.model.interfaces.VisualTextField;
import com.dragome.services.ServiceLocator;

@PageAlias(alias= "todo-mvc")
public class TodosPage extends DragomeVisualActivity
{
	private TodoManager todoManager;

	public void build()
	{
		todoManager= new TodoManager(ServiceLocator.getInstance().getParametersHandler().getFragment(), new LocalStorage());
		ComponentBuilder componentBuilder= new ComponentBuilder(mainPanel);

		componentBuilder.bindTemplate("new-todo")
			.as(VisualTextField.class)
			.toProperty(todoManager::getNewTodo, todoManager::setNewTodo)
			.onKeyUp((v, c) -> todoManager.addTodo(), KEY_ENTER)
			.build();

		componentBuilder.bindTemplate("main-section").as(VisualPanel.class).buildChildren(this::buildMainSection);
		componentBuilder.bindTemplate("footer-section").as(VisualPanel.class).buildChildren(this::buildFooter);
	}

	private void buildMainSection(ComponentBuilder mainSectionBuilder)
	{
		VisualCheckbox allChecked= mainSectionBuilder.bindTemplate("toggle-all")
				.as(VisualCheckbox.class)
				.toProperty(todoManager::isAllChecked, todoManager::setAllChecked)
				.onClick(v -> todoManager.markAll(!todoManager.isAllChecked()))
				.build();
		
		mainSectionBuilder.show(allChecked).when(() -> !todoManager.getTodos().isEmpty());
		mainSectionBuilder.showWhen(() -> !todoManager.getTodos().isEmpty());

		mainSectionBuilder.bindTemplate("completed-todo")
			.as(VisualPanel.class)
			.toListProperty(todoManager::getTodos)
			.filter(todoManager::getStatusFilter)
			.repeat(this::buildTodo);
	}

	private void buildFooter(ComponentBuilder footerBuilder)
	{
		footerBuilder.showWhen(() -> !todoManager.getTodos().isEmpty());
		
		footerBuilder.bindTemplate("items-count")
			.as(VisualLabel.class)
			.toProperty(todoManager::getRemainingCount, todoManager::setRemainingCount)
			.build();
		
		footerBuilder.bindTemplate("items-label")
			.as(VisualLabel.class)
			.to(() -> todoManager.getRemainingCount() == 1 ? "item" : "items")
			.build();

		Stream.of("/", "/active", "/completed").forEach(location -> {
			VisualLink link= footerBuilder.bindTemplate("filter:" + location)
					.as(VisualLink.class)
					.onClick(v1 -> todoManager.setLocation(location))
					.build();
			
			footerBuilder.style(link).with("selected")
				.when(() -> todoManager.getLocation().equals(location));
		});

		footerBuilder.bindTemplate("clear-completed")
			.as(VisualPanel.class)
			.onClick(v2 -> todoManager.clearCompletedTodos())
			.buildChildren(clearCompletedBuilder -> {
    			clearCompletedBuilder.bindTemplate("clear-completed-number")
        			.as(VisualLabel.class)
        			.toProperty(todoManager::getCompletedCount, todoManager::setCompletedCount)
        			.build();
    			
    			clearCompletedBuilder.showWhen(() -> todoManager.getCompletedCount() > 0);
		});
	}

	private void buildTodo(Todo todo, ComponentBuilder builder)
	{
		builder.bindTemplate("todo-input")
    		.as(VisualTextField.class)
    		.toProperty(todo::getTitle, todo::setTitle)
    		.onKeyUp((v, c) -> todoManager.doneEditing(todo), KEY_ESC, KEY_ENTER)
    		.onBlur(v -> todoManager.doneEditing(todo))
    		.build();
		
		builder.bindTemplate("title")
    		.as(VisualLabel.class)
    		.toProperty(todo::getTitle, todo::setTitle)
    		.onDoubleClick(v -> todoManager.editTodo(todo))
    		.build();
		
		builder.bindTemplate("completed")
    		.as(VisualCheckbox.class)
    		.toProperty(todo::isCompleted, todo::setCompleted)
    		.onClick(v -> todoManager.todoCompleted(todo))
    		.build();
		
		builder.bindTemplate("destroy")
    		.as(VisualButton.class)
    		.onClick(v -> todoManager.removeTodo(todo))
    		.build();
		
		builder.styleWith("completed")
			.when(todo::isCompleted);
		
		builder.styleWith("editing")
			.when(() -> todo == todoManager.getEditedTodo());
	}
}
