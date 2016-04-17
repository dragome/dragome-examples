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
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.onBlur;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.onClick;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.onDoubleClick;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.onKeyUp;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.showWhen;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.style;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.styleWith;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.to;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.toListProperty;
import static com.dragome.forms.bindings.builders.helpers.BinderHelper.toProperty;
import static com.dragome.guia.events.listeners.interfaces.KeyListener.KEY_ENTER;
import static com.dragome.guia.events.listeners.interfaces.KeyListener.KEY_ESC;

import java.util.stream.Stream;

import com.dragome.examples.todo.model.Todo;
import com.dragome.examples.todo.model.TodoManager;
import com.dragome.forms.bindings.builders.LocalStorage;
import com.dragome.forms.bindings.builders.helpers.BinderHelper;
import com.dragome.guia.GuiaVisualActivity;
import com.dragome.guia.components.interfaces.VisualButton;
import com.dragome.guia.components.interfaces.VisualCheckbox;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.guia.components.interfaces.VisualLink;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.guia.components.interfaces.VisualTextField;
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

		bind("new-todo").as(VisualTextField.class, () -> {
			toProperty(todoManager::getNewTodo, todoManager::setNewTodo);
			onKeyUp((v, c) -> todoManager.addTodo(), KEY_ENTER);
		});

		bind("main-section").as(VisualPanel.class, () -> {

			bind("toggle-all").as(VisualCheckbox.class, () -> {
				toProperty(todoManager::isAllChecked, todoManager::setAllChecked);
				onClick(() -> todoManager.markAll(!todoManager.isAllChecked()));
				showWhen(() -> !todoManager.getTodos().isEmpty());
			});

			showWhen(() -> !todoManager.getTodos().isEmpty());

			bind("completed-todo").as(VisualPanel.class, () -> {
				toListProperty(todoManager::getTodos).filter(todoManager::getStatusFilter).repeat(this::buildTodo);
			});
		});
		bind("footer-section").as(VisualPanel.class, () -> {
			buildFooter();
		});
	}

	private void buildFooter()
	{
		showWhen(() -> !todoManager.getTodos().isEmpty());

		bind("items-count").as(VisualLabel.class, () -> {
			toProperty(todoManager::getRemainingCount, todoManager::setRemainingCount);
		});

		bind("items-label").as(VisualLabel.class, () -> {
			to(() -> todoManager.getRemainingCount() == 1 ? "item" : "items");
		});

		Stream.of("/", "/active", "/completed").forEach(location -> {
			bind("filter:" + location).as(VisualLink.class, () -> {
				onClick(() -> todoManager.setLocation(location));
				style().with("selected").when(() -> todoManager.getLocation().equals(location));
			});

		});

		bind("clear-completed").as(VisualPanel.class, () -> {
			onClick(() -> todoManager.clearCompletedTodos());
			bind("clear-completed-number").as(VisualLabel.class, () -> {
				toProperty(todoManager::getCompletedCount, todoManager::setCompletedCount);
			});

			showWhen(() -> todoManager.getCompletedCount() > 0);
		});

	}

	private void buildTodo(Todo todo)
	{
		bind("todo-input").as(VisualTextField.class, () -> {
			toProperty(todo::getTitle, todo::setTitle);
			onKeyUp((v, c) -> todoManager.doneEditing(todo, c == KEY_ESC), KEY_ESC, KEY_ENTER);
			onBlur(v -> todoManager.doneEditing(todo, false));
		});
		bind("title").as(VisualLabel.class, () -> {
			toProperty(todo::getTitle, todo::setTitle);
			onDoubleClick(() -> todoManager.editTodo(todo));
		});

		bind("completed").as(VisualCheckbox.class, () -> {
			toProperty(todo::isCompleted, todo::setCompleted);
			onClick(() -> todoManager.todoCompleted(todo));
		});
		bind("destroy").as(VisualButton.class, () -> {
			onClick(() -> todoManager.removeTodo(todo));
		});
		styleWith("completed").when(todo::isCompleted);
		styleWith("editing").when(() -> todo == todoManager.getEditedTodo());
	}
}
