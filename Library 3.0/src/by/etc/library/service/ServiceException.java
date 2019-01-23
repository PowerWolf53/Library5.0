package by.etc.library.service;

public class ServiceException extends Exception {

private static final long serialVersionUID = 5032925570399709831L;

	
	public ServiceException()
	{
		super();
	}
	
	public ServiceException(String message)
	{
		super(message);
		
	}
	
	public ServiceException(String message,Exception e)
	{
		super(message,e);
	
	}

}
