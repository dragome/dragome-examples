package com.dragome.example;

import com.dragome.commons.DragomeConfiguratorImplementor;
import com.dragome.web.config.DomHandlerApplicationConfigurator;

@DragomeConfiguratorImplementor
public class DragomeConfiguration extends DomHandlerApplicationConfigurator
{
    public boolean filterClassPath(String classpathEntry)
    {
	boolean include= false;

	include|= classpathEntry.contains("dragome-js-jre-");
	include|= classpathEntry.contains("dragome-js-commons-");
	include|= classpathEntry.contains("dragome-core-");
	include|= classpathEntry.contains("dragome-web-");

	return include;
    }
}