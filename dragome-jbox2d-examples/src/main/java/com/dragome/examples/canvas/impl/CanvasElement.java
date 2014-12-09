package com.dragome.examples.canvas.impl;

import org.w3c.dom.Element;
import org.w3c.dom.css.CSSStyleDeclaration;

import com.dragome.commons.javascript.ScriptHelper;
import com.dragome.examples.canvas.delegate.CanvasRenderingContext2D;
import com.dragome.examples.canvas.delegate.HTMLCanvasElement;
import com.dragome.html.dom.w3c.AbstractElement;
import com.dragome.html.dom.w3c.BrowserElement;

public class CanvasElement extends BrowserElement implements HTMLCanvasElement
{

	public CanvasElement(Element element)
	{
		ScriptHelper.put("element", element, this);
		ScriptHelper.evalNoResult("this.node= element.node", this);
	}

	
	public String getTitle()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setTitle(String title)
	{
		// TODO Auto-generated method stub
		
	}

	
	public String getLang()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setLang(String lang)
	{
		// TODO Auto-generated method stub
		
	}

	
	public String getDir()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setDir(String dir)
	{
		// TODO Auto-generated method stub
		
	}

	
	public String getClassName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setClassName(String className)
	{
		// TODO Auto-generated method stub
		
	}

	
	public int getWidth()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void setWidth(int width)
	{
		// TODO Auto-generated method stub
		
	}

	
	public int getHeight()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	
	public void setHeight(int height)
	{
		// TODO Auto-generated method stub
		
	}

	
	public CanvasRenderingContext2D getContext(String contextId)
	{
		ScriptHelper.put("contextId", contextId, this);
		Object eval= ScriptHelper.eval("this.node.getContext(contextId)", this);
		return new Context2d(eval);
	}

	public void setCoordinateSpaceWidth(int canvasWidth)
	{
	}

	public void setCoordinateSpaceHeight(int canvasHeight)
	{
	}

	public void setInnerHTML(String html)
	{
		// TODO Auto-generated method stub
		
	}

	public String getInnerHTML()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getOuterHTML()
	{
		// TODO Auto-generated method stub
		return null;
	}

	
	public void setInnerText(String text)
	{
		// TODO Auto-generated method stub
		
	}

	
	public void click()
	{
		// TODO Auto-generated method stub
		
	}

	
	public CSSStyleDeclaration getStyle()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
