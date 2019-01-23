package by.etc.library.service;

public class ServiceFactory {

	public static final ServiceFactory instance= new ServiceFactory();
	
	private ServiceFactory() {};
	
	public static ServiceFactory getInstance()
	{
		return instance;
	}
	
	private UserService userService = new UserServiceImpl();
	
	private LocalisationService localisationService = new LocalisationService();
	
	private BookService bookService= new BookServiceImpl();
	
	public UserService getUserService()
	{
		return userService;
	}
	
	public LocalisationService getLocalisationService()
	{
		return localisationService;
	}
	
	public BookService getBookService()
	{
		return bookService;
	}
}
