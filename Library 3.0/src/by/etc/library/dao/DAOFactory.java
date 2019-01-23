package by.etc.library.dao;

public class DAOFactory {
	
	private static final DAOFactory instance = new DAOFactory();
	
	private final UserDAO userDao=new SQLUserDao();
	
	private final BookDAO bookDao=new SQLBookDAO();
	
	private DAOFactory() {}
	
	public static DAOFactory getInstance()
	{
		return instance;
	}
	
	public UserDAO getUserDao()
	{
		return userDao;
	}
	
	public BookDAO getBookDao()
	{
		return bookDao;
	}
}
