package com.dragome.examples.canvas.delegate;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.dragome.commons.javascript.ScriptHelper;

public class JsDelegateInvocationHandler implements InvocationHandler
{
	private JsDelegateInitializer initialiazer;
	private boolean initialized= false;

	public JsDelegateInvocationHandler(JsDelegateInitializer initialiazer)
	{
		this.initialiazer= initialiazer;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
	{
		if (!initialized)
		{
			initialiazer.init(proxy);
			initialized= true;
		}

		ScriptHelper.put("proxy", proxy, this);

		ScriptHelper.put("argumentsArray", new Object(), this);
		ScriptHelper.evalNoResult("argumentsArray=[]", this);

		int i= 0;
		if (args != null)
			for (Object argument : args)
			{
				if (!argument.getClass().isInterface())
					ScriptHelper.put("value", argument.toString(), this);
				else
					ScriptHelper.put("value", argument, this);

				ScriptHelper.put("index", i, this);
				ScriptHelper.eval("argumentsArray[index]=value", this);

				i++;
			}

		if (method.getName().startsWith("set") && args.length == 1)
		{
			String script= "proxy.jsDelegate." + method.getName().toLowerCase().charAt(3) + method.getName().substring(4) + "= argumentsArray[0]";	
			ScriptHelper.put("invoker", "(function (){" + script + ";})", this);
		}
		else
		{
			String script= "proxy.jsDelegate." + method.getName() + ".apply(proxy.jsDelegate, argumentsArray)";
			ScriptHelper.put("invoker", "(function (){return " + script + ";})", this);
		}

		Object result1;
		if (method.getReturnType().equals(Double.class) || method.getReturnType().equals(double.class))
			result1= ScriptHelper.evalDouble("eval(invoker)()", this);
		else
			result1= ScriptHelper.eval("eval(invoker)()", this);
		
		final Object result= result1;

		if (method.getReturnType().isInterface())
		{
			Object proxiedResult= Proxy.newProxyInstance(getClass().getClassLoader(), new Class<?>[] { method.getReturnType() }, new JsDelegateInvocationHandler(new JsDelegateInitializer()
			{
				public void init(Object proxy)
				{
					ScriptHelper.put("delegate", proxy, this);
					ScriptHelper.put("original", result, this);
					ScriptHelper.eval("delegate.jsDelegate= original", this);
				}
			}));

			return proxiedResult;
		}

		return result1;
	}
}