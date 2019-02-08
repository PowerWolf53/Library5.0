package by.etc.library.service;

import java.util.Locale;
import java.util.ResourceBundle;

import by.etc.library.bean.ServiceResponse;
import by.etc.library.controller.SessionParam;

public class LocalisationService {

	private static final String LOCALE_PATH="locales/resources";
	
	public ServiceResponse getLocale(String locale)
	{
		Locale loc = new Locale(locale);
		ServiceResponse resp = new ServiceResponse();
		ResourceBundle bundle = ResourceBundle.getBundle(LOCALE_PATH, loc);
		resp.addSessionParam(SessionParam.BUNDLE, bundle);
		return resp;
		
	}
}
