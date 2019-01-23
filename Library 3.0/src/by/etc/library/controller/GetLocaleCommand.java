package by.etc.library.controller;



import javax.servlet.http.HttpServletRequest;

import by.etc.library.bean.ServiceResponse;
import by.etc.library.service.LocalisationService;
import by.etc.library.service.ServiceFactory;

public class GetLocaleCommand  implements Command{

	@Override
	public String execute(HttpServletRequest request) {
		ServiceFactory factory=ServiceFactory.getInstance();
		LocalisationService service= factory.getLocalisationService();
		String loc=request.getParameter("locale");
		ServiceResponse resp = service.getLocale(loc);
		request.getSession().setAttribute("bundle", resp.getSessionParam("bundle"));
		return null;
	}

}
