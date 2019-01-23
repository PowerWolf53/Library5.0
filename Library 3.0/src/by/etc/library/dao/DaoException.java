package by.etc.library.dao;

public class DaoException extends Exception {


	private static final long serialVersionUID = 365891092680780280L;

	
	public DaoException()
	{
		super();
	}
	
	public DaoException(String message)
	{
		super(message);
	}
	
	public DaoException(String message,Exception e) 
	{
		super(message,e);
	}
}
