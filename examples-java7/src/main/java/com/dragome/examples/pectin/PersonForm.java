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

import static com.dragome.forms.bindings.client.condition.Conditions.valueOf;
import static com.dragome.forms.bindings.client.form.metadata.MetadataPlugin.metadataOf;

import com.dragome.forms.bindings.client.form.FieldModelBase;
import com.dragome.forms.bindings.client.form.FormModel;
import com.dragome.forms.bindings.client.form.ListFieldModelBase;
import com.dragome.forms.bindings.client.form.binding.FormBinder;
import com.dragome.forms.bindings.client.form.validation.binding.ValidationBinder;
import com.dragome.forms.bindings.client.style.StyleBinder;
import com.dragome.guia.components.VisualButtonImpl;
import com.dragome.guia.components.VisualCheckboxImpl;
import com.dragome.guia.components.VisualLabelImpl;
import com.dragome.guia.components.VisualRadioButton;
import com.dragome.guia.components.VisualRadioButtonImpl;
import com.dragome.guia.components.VisualTextFieldImpl;
import com.dragome.guia.components.interfaces.VisualButton;
import com.dragome.guia.components.interfaces.VisualCheckbox;
import com.dragome.guia.components.interfaces.VisualLabel;
import com.dragome.guia.components.interfaces.VisualPanel;
import com.dragome.guia.components.interfaces.VisualTextField;
import com.dragome.model.pectin.HasClickHandlersAdapter;
import com.dragome.model.pectin.VisualComponentHasEnabled;

public class PersonForm
{
    private FormBinder binder= new FormBinder();
    private StyleBinder style= new StyleBinder();
    private ValidationBinder validation= new ValidationBinder();

    private VisualTextField<String> title= new VisualTextFieldImpl<String>("title");
    private VisualLabel<String> nickNameLabel= new VisualLabelImpl<String>("nickNameLabel", "Nick Name");

    private VisualTextField<String> givenName= new VisualTextFieldImpl<String>("givenName");
    private VisualTextField<String> surname= new VisualTextFieldImpl<String>("surname");
    private VisualTextField<String> age= new VisualTextFieldImpl<String>("age");

    private NickNameEditor nickName= new NickNameEditor("nicknameEditor");
    //	private VisualTextField moneyInBank= new TextBox();

    private VisualCheckbox hasNickName= new VisualCheckboxImpl("hasNickName", "I have a nick name", false);

    private VisualRadioButton maleRadio= new VisualRadioButtonImpl("g1", "male", "Male");
    private VisualRadioButton femaleRadio= new VisualRadioButtonImpl("g1", "female", "Female");

    private VisualCheckbox cabSavCheckBox= new VisualCheckboxImpl("CabSav");
    private VisualCheckbox merlotCheckBox= new VisualCheckboxImpl("Merlot");
    private VisualCheckbox shirazCheckBox= new VisualCheckboxImpl("Shiraz");

    private VisualCheckbox wineLoverCheckBox= new VisualCheckboxImpl("wineLoverCheckBox", "I like wine", false);

    private VisualButton validateButton= new VisualButtonImpl("validateButton", "Validate");
    private VisualButton clearButton= new VisualButtonImpl("clearButton", "Clear");
    private VisualButton fakeServerErrorButton= new VisualButtonImpl("fakeValidationButton", "Fake a Server Validation Error");

    public PersonForm(PersonFormModel model, VisualPanel panel)
    {
	binder.bind(model.givenName).to(givenName);
	binder.bind(model.surname).to(surname);
	binder.bind(model.age).to(age);
	binder.bind(model.gender).withValue(Gender.MALE).to(maleRadio);
	binder.bind(model.gender).withValue(Gender.FEMALE).to(femaleRadio);
	binder.bind(model.hasNickName).to(hasNickName);
	binder.bind(model.nickName).to(nickName);
	binder.bind(model.wineLover).to(wineLoverCheckBox);
	binder.bind(model.favoriteWines).containingValue(Wine.CAB_SAV).to(cabSavCheckBox);
	binder.bind(model.favoriteWines).containingValue(Wine.MERLOT).to(merlotCheckBox);
	binder.bind(model.favoriteWines).containingValue(Wine.SHIRAZ).to(shirazCheckBox);
	binder.bind(model.title).to(title);

	binder.bind(model.validateCommand).to(new HasClickHandlersAdapter(validateButton));
	binder.bind(model.clearCommand).to(new HasClickHandlersAdapter(clearButton));
	binder.bind(model.fakeServerErrorCommand).to(new HasClickHandlersAdapter(fakeServerErrorButton));

	style.style(title).with("LordVadar").when(valueOf(model.title).is("lord vadar"));

	// Here our nick name label has it's style configured from the metadata
	// of the nickName field.  Could have also used the withValue approach
	// above in this case as well.
	style.style(nickNameLabel).with("disabled").when(metadataOf(model.nickName).isDisabled());
	//style.style(nickName).with("disabled").when(metadataOf(model.nickName).isDisabled());

	binder.disable(new VisualComponentHasEnabled(nickName)).when(metadataOf(model.nickName).isDisabled());

	binder.enable(new VisualComponentHasEnabled(cabSavCheckBox)).when(model.wineLover);
	binder.enable(new VisualComponentHasEnabled(merlotCheckBox)).when(model.wineLover);
	binder.enable(new VisualComponentHasEnabled(shirazCheckBox)).when(model.wineLover);

	validation.bindValidationOf(model.givenName).toStyleOf(givenName);
	validation.bindValidationOf(model.surname).toStyleOf(surname);

	panel.addChild(createValidationLabel(model.givenName, "givenNameLabel"));
	panel.addChild(createValidationLabel(model.surname, "surnameLabel"));
	panel.addChild(createValidationLabel(model.favoriteWines));
	panel.addChild(createValidationPanel(model));

	panel.addChild(givenName);
	panel.addChild(surname);
	panel.addChild(age);

	panel.addChild(cabSavCheckBox);
	panel.addChild(merlotCheckBox);
	panel.addChild(shirazCheckBox);

	panel.addChild(maleRadio);
	panel.addChild(femaleRadio);

	panel.addChild(hasNickName);
	panel.addChild(nickName);
	panel.addChild(wineLoverCheckBox);

	panel.addChild(title);
	panel.addChild(nickNameLabel);

	panel.addChild(validateButton);
	panel.addChild(clearButton);
	panel.addChild(fakeServerErrorButton);
    }

    private VisualLabel<String> createValidationLabel(FieldModelBase<?> field, String labelName)
    {
	VisualLabel<String> label= new VisualLabelImpl<String>(labelName);
	validation.bindValidationOf(field).to(label);
	return label;
    }

    private VisualLabel<String> createValidationLabel(ListFieldModelBase<?> field)
    {
	VisualLabel<String> label= new VisualLabelImpl<String>("label2");
	validation.bindValidationOf(field).to(label);
	return label;
    }

    private VisualLabel<String> createValidationPanel(FormModel form)
    {
	VisualLabel<String> panel= new VisualLabelImpl<String>("label3");
	validation.bindValidationOf(form).to(panel);
	return panel;
    }
}
