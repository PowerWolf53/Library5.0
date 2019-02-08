package by.etc.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
	
	
	private LinkedBlockingQueue<Connection> freeConnections=null;
	
	private LinkedBlockingQueue<Connection> takenConnections=null;
	
	private String user=null;
	
	private String db=null;
	
	private String password=null;
	
	private String url=null;
	
	private String driver=null;
	
	private int size=0;
	
	private String params=null;
	
	
	public ConnectionPool(String db,String params, String user, String password, String url, String driver, int size) throws DaoException
	{
		this.db=db;
		this.params=params;
		this.user=user;
		this.password=password;
		this.driver=driver;
		this.url=url;
		this.size=size;
		initData();
	}
	
	private void initData() throws DaoException {
	
		freeConnections=new LinkedBlockingQueue<>(size);
		takenConnections=new LinkedBlockingQueue<>(size);
		
		try {
			Class.forName(driver);
			
		} catch (ClassNotFoundException e1) {
			throw new DaoException(DAOWarning.CONNECTION_PROBLEMS);
		}
		for(int i=0;i<size;i++)
		{
			try {
				Connection connection = DriverManager.getConnection(url+db+params, user, password);
				try {
					freeConnections.put(connection);
				} catch (InterruptedException e) {
					throw new DaoException("connection problems",e);
				}
			} catch (SQLException e) {
			
				throw new DaoException("connection problems",e);
			}
			}
			
		
		
		
	}
	
	
	public Connection getConnection() throws DaoException 
	{
		Connection con = null;
		try {
			con=freeConnections.take();
			takenConnections.put(con);
		} catch (InterruptedException e) {
			throw new DaoException("Connection problems",e);
		}
		return con;
	
		
	
		
}
	
	
	public void freeConnection(Connection connection) throws DaoException
	{
		takenConnections.remove(connection);
		try {
			freeConnections.put(connection);
		} catch (InterruptedException e) {
			throw new DaoException("Connection problems,please try again",e);
		}
	}
}
	
	
	

