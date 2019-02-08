package by.etc.library.dao;

public class ConnectionManager {

	private static ConnectionPool pool=null;
	
	private static DBAccesManager manager;
	
	public static ConnectionPool getPool() throws DaoException
	{
		 
		if(pool==null)
		{
			try {
				if(manager==null) {
				 manager= new DBAccesManager();
				 
				}
				String user=manager.getUser();
				String db=manager.getDb();		
				String password=manager.getPassword();		
				String url=manager.getUrl();		
				String driver=manager.getDriver();	
				int size=manager.getSize();
				String params=manager.getParams();
				pool=new ConnectionPool(db,params,user,password,url,driver,size);
			} catch (DaoException e) {
				
			throw new DaoException(e.getMessage());
			}
	}
		return pool;
	}
}
	

