package com.dragome.example;

import java.util.Arrays;
import java.util.List;

import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventTarget;

import com.dragome.commons.DragomeConfiguratorImplementor;
import com.dragome.web.config.DomHandlerApplicationConfigurator;

@DragomeConfiguratorImplementor
public class DragomeConfiguration extends DomHandlerApplicationConfigurator
{
    private static List<? extends Class<?>> delegates= Arrays.asList(EventTarget.class, Event.class);

    public DragomeConfiguration()
    {
	super(delegates);
    }

    public boolean filterClassPath(String classpathEntry)
    {
	boolean include= false;

	include|= classpathEntry.contains("dragome-w3c-standards-");
	include|= classpathEntry.contains("dragome-js-jre-");
	include|= classpathEntry.contains("dragome-js-commons-");
	include|= classpathEntry.contains("dragome-core-");
	include|= classpathEntry.contains("dragome-web-");

	return include;
    }
}