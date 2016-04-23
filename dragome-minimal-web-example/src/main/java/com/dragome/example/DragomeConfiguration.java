package com.dragome.example;

import java.net.URL;

import com.dragome.commons.DragomeConfiguratorImplementor;
import com.dragome.web.config.DomHandlerApplicationConfigurator;

@DragomeConfiguratorImplementor
public class DragomeConfiguration extends DomHandlerApplicationConfigurator
{
	public boolean filterClassPath(String classpathEntry)
	{
		boolean include= false;

		include|= classpathEntry.contains("dragome-w3c-standards-");
		include|= classpathEntry.contains("dragome-js-jre-");
		include|= classpathEntry.contains("dragome-js-commons-");
		include|= classpathEntry.contains("dragome-core-");
		include|= classpathEntry.contains("dragome-web-");
		include|= classpathEntry.contains("guice");
		include|= classpathEntry.contains("guava");
		include|= classpathEntry.contains("javax.inject");
		include|= classpathEntry.contains("javaee-web-api");


		return include;
	}

	public boolean isRemoveUnusedCode()
	{
		return true;
	}

	public URL getAdditionalCodeKeepConfigFile()
	{
		return getClass().getResource("/proguard-extra.conf");
	}
}