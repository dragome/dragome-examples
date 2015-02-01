package com.dragome.examples.serverside;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.dragome.ExamplesApplicationConfigurator;
import com.dragome.serverside.debugging.websocket.ClassTransformerDragomeWebSocketHandler;
import com.dragome.services.ServiceLocator;

public class SwingLauncher
{
	public static void main(String[] args) throws Exception
	{
		UIManager.setLookAndFeel(new NimbusLookAndFeel());
		ServiceLocator.getInstance().setConfigurator(new ExamplesApplicationConfigurator());
		ClassTransformerDragomeWebSocketHandler.executeMethod("com.dragome.examples.serverside.SwingCrudLauncher", "run", null);
	}
}
