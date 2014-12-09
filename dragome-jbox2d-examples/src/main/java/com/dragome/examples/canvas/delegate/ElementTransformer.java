package com.dragome.examples.canvas.delegate;

import java.lang.reflect.Proxy;

import org.w3c.dom.Element;

import com.dragome.commons.javascript.ScriptHelper;

public class ElementTransformer
{
	public static <T extends Element> T transformElementTo(final Element foundElement, Class<T> type)
	{
		return wrap(new JsDelegateInitializer()
		{
			public void init(Object proxy)
			{
				ScriptHelper.put("newElement", proxy, this);
				ScriptHelper.put("foundElement", foundElement, this);
				ScriptHelper.eval("newElement.jsDelegate= foundElement.node", this);
			}
		}, type);
	}

	public static <T> T wrap(JsDelegateInitializer jsDelegateInitializer, Class<T> type)
	{
		return (T) Proxy.newProxyInstance(ElementTransformer.class.getClassLoader(), new Class<?>[] { type }, new JsDelegateInvocationHandler(jsDelegateInitializer));
	}
}
