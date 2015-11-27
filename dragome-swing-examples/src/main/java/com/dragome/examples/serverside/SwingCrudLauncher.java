package com.dragome.examples.serverside;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dragome.commons.javascript.ScriptHelper;
import com.dragome.commons.javascript.ScriptHelperInterface;
import com.dragome.examples.PersonCrudUsingBuilders;
import com.dragome.guia.GuiaServiceLocator;
import com.dragome.render.serverside.swing.SwingGuiaServiceFactory;
import com.dragome.render.serverside.swing.SwingTemplateLoadingStrategy;
import com.dragome.services.CommandLineParametersHandler;
import com.dragome.services.ServiceLocator;
import com.dragome.services.WebServiceLocator;
import com.dragome.services.serverside.ServerReflectionServiceImpl;
import com.dragome.view.VisualActivity;
import com.dragome.web.debugging.messages.MessageChannel;
import com.dragome.web.debugging.messages.Receiver;
import com.dragome.web.debugging.messages.Sender;
import com.dragome.web.dispatcher.EventDispatcherHelper;
import com.dragome.web.dispatcher.EventDispatcherImpl;

public class SwingCrudLauncher
{
	public static void main(String[] args) throws Exception
	{
		new SwingCrudLauncher().run();
	}

	public void run() throws Exception
	{
		ServiceLocator.getInstance().setReflectionService(new ServerReflectionServiceImpl());
		GuiaServiceLocator.getInstance().setServiceFactory(new SwingGuiaServiceFactory());
		ServiceLocator.getInstance().setLocalExecution(true);
		JPanel panel= new PersonCrudJPanel();
		SwingTemplateLoadingStrategy.mainPanel= panel;

		JFrame jFrame= new JFrame();
		jFrame.getContentPane().add(panel);
		jFrame.pack();
		jFrame.setVisible(true);

		//		VisualActivity activity= new PersonCrudUsingBuilders();
		//		activity.onCreate();

	
		ScriptHelper.scriptHelperInterface= new ScriptHelperInterface()
		{

			public void put(String s, Object value, Object caller)
			{

			}

			public void put(String s, boolean value, Object caller)
			{

			}

			public void put(String s, double value, Object caller)
			{

			}

			public Object eval(String script, Object caller)
			{

				return null;
			}

			public int evalInt(String jsCode, Object caller)
			{

				return 0;
			}

			public long evalLong(String jsCode)
			{

				return 0;
			}

			public float evalFloat(String jsCode)
			{

				return 0;
			}

			public double evalDouble(String jsCode)
			{

				return 0;
			}

			public char evalChar(String jsCode)
			{

				return 0;
			}

			public boolean evalBoolean(String jsCode, Object caller)
			{

				return false;
			}

			public void evalNoResult(String script, Object callerInstance)
			{

			}
		};

		WebServiceLocator.getInstance().setClientToServerMessageChannel(new MessageChannel()
		{
			public void send(String aMessage)
			{
				System.out.println("send");
			}

			public void setSender(Sender sender)
			{

			}

			public void setReceiver(Receiver receiver)
			{

			}

			public Receiver getReceiver()
			{

				return null;
			}
		});
		
		CommandLineParametersHandler parametersHandler= new CommandLineParametersHandler();
		parametersHandler.setParameter("class", PersonCrudUsingBuilders.class.getName());
		parametersHandler.setParameter("debug", "true");
		ServiceLocator.getInstance().setParametersHandler(parametersHandler);

		EventDispatcherHelper.executeMainClass();
	}
}
