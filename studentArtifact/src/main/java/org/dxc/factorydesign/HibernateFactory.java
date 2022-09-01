package org.dxc.factorydesign;

import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



public class HibernateFactory {
	 private static final SessionFactory factory;
	 static {
		 factory = new Configuration().configure("resource/hibernate.cfg.xml").buildSessionFactory();
			
	 }

	public static SessionFactory getFactoryObject()
	{
		return factory;
	}
}
