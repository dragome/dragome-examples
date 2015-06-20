package com.dragome.examples.serverside;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dragome.examples.PersonCrudUsingBuilders;
import com.dragome.guia.GuiaServiceLocator;
import com.dragome.render.serverside.swing.SwingGuiaServiceFactory;
import com.dragome.render.serverside.swing.SwingTemplateLoadingStrategy;
import com.dragome.services.ServiceLocator;
import com.dragome.services.serverside.ServerReflectionServiceImpl;
import com.dragome.view.VisualActivity;

public class SwingCrudLauncher
{
	public static void main(String[] args)
	{
		new SwingCrudLauncher().run();
	}

	public void run()
	{
		ServiceLocator.getInstance().setReflectionService(new ServerReflectionServiceImpl());
		//GuiaServiceLocator.getInstance().setServiceFactory(new SwingGuiaServiceFactory());
		ServiceLocator.getInstance().setLocalExecution(true);
		JPanel panel= new PersonCrudJPanel();
		SwingTemplateLoadingStrategy.mainPanel= panel;
		
		JFrame jFrame= new JFrame();
		jFrame.getContentPane().add(panel);
		jFrame.pack();
		jFrame.setVisible(true);
		
		VisualActivity activity= new PersonCrudUsingBuilders();
		activity.onCreate();
	}
}
