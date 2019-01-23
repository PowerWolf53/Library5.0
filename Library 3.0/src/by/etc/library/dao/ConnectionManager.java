package by.etc.library.dao;

public class ConnectionManager {

	private static ConnectionPool pool=null;
	
	private static final DBAccesManager manager= new DBAccesManager();
	
	public static ConnectionPool getPool()
	{
		if(pool==null)
		{
			try {
				String user=manager.getUser();
				String db=manager.getDb();		
				String password=manager.getPassword();		
				String url=manager.getUrl();		
				String driver=manager.getDriver();	
				int size=manager.getSize();
				String params=manager.getParams();
				pool=new ConnectionPool(db,params,user,password,url,driver,size);
			} catch (DaoException e) {
				
			
			}
	}
		return pool;
	}
}
	

