package org.dxc.factorydesign;

import org.dxc.service.*;


public class ServiceFactory {
	private static final IStudentService service;
	static
	{
		service = new StudentServiceImplementation();
	}
	public static IStudentService getService() {
		return service;
	}
}
