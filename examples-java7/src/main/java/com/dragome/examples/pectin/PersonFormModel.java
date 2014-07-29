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
package com.dragome.examples.pectin;

import static com.dragome.forms.bindings.client.form.metadata.MetadataPlugin.enable;
import static com.dragome.forms.bindings.client.form.validation.ValidationPlugin.getValidationManager;
import static com.dragome.forms.bindings.client.form.validation.ValidationPlugin.validateField;

import com.dragome.forms.bindings.client.bean.AbstractBeanModelProvider;
import com.dragome.forms.bindings.client.command.AbstractUiCommand;
import com.dragome.forms.bindings.client.command.UiCommand;
import com.dragome.forms.bindings.client.form.FieldModel;
import com.dragome.forms.bindings.client.form.FormModel;
import com.dragome.forms.bindings.client.form.FormattedFieldModel;
import com.dragome.forms.bindings.client.form.ListFieldModel;
import com.dragome.forms.bindings.client.form.validation.FieldValidator;
import com.dragome.forms.bindings.client.form.validation.message.ErrorMessage;
import com.dragome.forms.bindings.client.form.validation.validator.NotEmptyValidator;
import com.dragome.forms.bindings.client.form.validation.validator.NotNullValidator;

public class PersonFormModel extends FormModel
{
    protected FieldModel<String> givenName;
    protected FieldModel<String> surname;
    protected FieldModel<String> nickName;
    protected FieldModel<Boolean> hasNickName;
    protected FieldModel<Gender> gender;
    protected FormattedFieldModel<Integer> age;
    protected FieldModel<Boolean> wineLover;
    protected ListFieldModel<Wine> favoriteWines;

    protected FieldModel<String> title;

    protected final UiCommand validateCommand= new ValidateCommand();
    protected final UiCommand clearCommand= new ClearValidationCommand();
    protected final UiCommand fakeServerErrorCommand= new FakeServerErrorCommand();
    protected AbstractBeanModelProvider<Person> personProvider;

    public PersonFormModel(AbstractBeanModelProvider<Person> personProvider)
    {
	this.personProvider= personProvider;
	givenName= fieldOfType(String.class).boundTo(personProvider, "givenName");
	surname= fieldOfType(String.class).boundTo(personProvider, "surname");
	age= formattedFieldOfType(Integer.class).using(new AgeFormat()).boundTo(personProvider, "age");
	gender= fieldOfType(Gender.class).boundTo(personProvider, "gender");

	hasNickName= fieldOfType(Boolean.class).createWithValue(false);
	nickName= fieldOfType(String.class).boundTo(personProvider, "nickName");

	wineLover= fieldOfType(Boolean.class).boundTo(personProvider, "wineLover");
	favoriteWines= listOfType(Wine.class).boundTo(personProvider, "favoriteWines");

	title= fieldOfType(String.class).create();

	// configure our validation rules
	validateField(givenName).using(new NotEmptyValidator("Given name is required"));
	validateField(surname).using(new NotEmptyValidator("Surname is required"));
	validateField(gender).using(new NotNullValidator("Gender is required"));

	// we only validate the nickname if the user has clicked that they have one.
	validateField(nickName).using(new NotEmptyValidator("Please enter your nick name")).when(hasNickName);

	// we use the AgeFormat as the first line of validation
	validateField(age).usingFieldFormat();
	validateField(age).using(new NotNullValidator("Age is required"));

	// we only validate
	validateField(favoriteWines).using(new WineListValidator()).when(wineLover);

	// configure our metadata
	enable(nickName).when(hasNickName);
	enable(favoriteWines).when(wineLover);
    }

    public boolean validate()
    {
       return getValidationManager(this).validate();
    }

    public void commit()
    {
       personProvider.commit();
    }
    
    private class ValidateCommand extends AbstractUiCommand
    {
	protected void doExecute()
	{
	    validate();
	}
    }

    private class ClearValidationCommand extends AbstractUiCommand
    {

	protected void doExecute()
	{
	    getValidationManager(PersonFormModel.this).clear();
	}
    }

    private class FakeServerErrorCommand extends AbstractUiCommand
    {

	protected void doExecute()
	{
	    FieldValidator givenNameValidator= getValidationManager(PersonFormModel.this).getValidator(givenName);
	    // normally this would come back async from the server and get injected.
	    givenNameValidator.addExternalMessage(new ErrorMessage("The server didn't like your name.."));
	}
    }
}
