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
package ch.flashcard.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author Marc Baur, Adrian Herzog
 */
public final class HibernateUtil
{

    private static final SessionFactory SESSION_FACTORY;
    private static final ServiceRegistry SERVICE_REGISTRY;

    static
    {
	try
	{
	    Configuration configuration= new Configuration();
	    configuration.configure();
	    SERVICE_REGISTRY= new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
	    SESSION_FACTORY= configuration.buildSessionFactory(SERVICE_REGISTRY);
	}
	catch (Throwable ex)
	{
	    System.err.println("Initial SessionFactory creation failed." + ex);
	    throw new ExceptionInInitializerError(ex);
	}
    }

    private HibernateUtil()
    {
    }

    public static SessionFactory getSessionFactory()
    {
	return SESSION_FACTORY;
    }

}
