package by.etc.library.service;

import java.util.Locale;
import java.util.ResourceBundle;

import by.etc.library.bean.ServiceResponse;

public class LocalisationService {

	public ServiceResponse getLocale(String locale)
	{
		Locale loc = new Locale(locale);
		ServiceResponse resp = new ServiceResponse();
		ResourceBundle bundle = ResourceBundle.getBundle("locales/resources", loc);
		resp.addSessionParam("bundle", bundle);
		return resp;
		
	}
}
