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

import static com.dragome.forms.bindings.builders.helpers.BinderHelper.bind;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.BUTTON;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.CHECKBOX;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.LABEL;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.LINK;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.onBlur;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.onClick;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.onDoubleClick;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.onKeyUp;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.PANEL;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.showWhen;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.style;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.styleWith;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.TEXTFIELD;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.to;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.toListProperty;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.toProperty;
import static com.dragome.guia.events.listeners.interfaces.KeyListener.ENTER;
import static com.dragome.guia.events.listeners.interfaces.KeyListener.ESC;

import java.util.stream.Stream;

import com.dragome.examples.todo.model.Todo;
import com.dragome.examples.todo.model.TodoManager;
import com.dragome.forms.bindings.builders.LocalStorage;
import com.dragome.forms.bindings.builders.helpers.BinderHelper;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.services.ServiceLocator;
import com.dragome.web.annotations.PageAlias;

@PageAlias(alias= "todo-mvc")
public class TodosPage extends GuiaVisualActivity
{
	@PageAlias(alias= "todoManager")
	private TodoManager todoManager;

	@PageAlias(alias= "build")
	public void build()
	{
		todoManager= new TodoManager(ServiceLocator.getInstance().getParametersHandler().getFragment(), new LocalStorage());
		BinderHelper.start(mainPanel);

		bind("new-todo").as(TEXTFIELD, () ->
		{
			toProperty(todoManager::getNewTodo, todoManager::setNewTodo);
			onKeyUp((v, c) -> todoManager.addTodo(), ENTER);
		});

		bind("main-section").as(PANEL, () ->
		{
			mainSection();
		});

		bind("footer-section").as(PANEL, () ->
		{
			footerSection();
		});
	}

	private void mainSection()
	{
		showWhen(() -> !todoManager.getTodos().isEmpty());

		bind("toggle-all").as(CHECKBOX, () ->
		{
			toProperty(todoManager::isAllChecked, todoManager::setAllChecked);
			onClick(() -> todoManager.markAll(!todoManager.isAllChecked()));
			showWhen(() -> !todoManager.getTodos().isEmpty());
		});

		bind("completed-todo").as(PANEL, () ->
		{
			toListProperty(todoManager::getTodos).filter(todoManager::getStatusFilter).repeat((Todo todo) ->
			{
				styleWith("completed").when(todo::isCompleted);
				styleWith("editing").when(() -> todo == todoManager.getEditedTodo());

				bind("destroy").as(BUTTON, () -> onClick(() -> todoManager.removeTodo(todo)));

				bind("todo-input").as(TEXTFIELD, () ->
				{
					toProperty(todo::getTitle, todo::setTitle);
					onKeyUp((v, c) -> todoManager.doneEditing(todo, c == ESC), ESC, ENTER);
					onBlur(v -> todoManager.doneEditing(todo, false));
				});

				bind("title").as(LABEL, () ->
				{
					toProperty(todo::getTitle, todo::setTitle);
					onDoubleClick(() -> todoManager.editTodo(todo));
				});

				bind("completed").as(CHECKBOX, () ->
				{
					toProperty(todo::isCompleted, todo::setCompleted);
					onClick(() -> todoManager.todoCompleted(todo));
				});
			});
		});
	}

	private void footerSection()
	{
		showWhen(() -> !todoManager.getTodos().isEmpty());

		bind("items-count").as(LABEL, () -> toProperty(todoManager::getRemainingCount, todoManager::setRemainingCount));
		bind("items-label").as(LABEL, () -> to(() -> todoManager.getRemainingCount() == 1 ? "item" : "items"));

		Stream.of("/", "/active", "/completed").forEach(location ->
		{
			bind("filter:" + location).as(LINK, () ->
			{
				onClick(() -> todoManager.setLocation(location));
				style().with("selected").when(() -> todoManager.getLocation().equals(location));
			});
		});

		bind("clear-completed").as(PANEL, () ->
		{
			onClick(() -> todoManager.clearCompletedTodos());
			showWhen(() -> todoManager.getCompletedCount() > 0);

			bind("clear-completed-number").as(LABEL, () ->
			{
				toProperty(todoManager::getCompletedCount, todoManager::setCompletedCount);
			});
		});
	}

}
