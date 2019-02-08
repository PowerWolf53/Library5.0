package by.etc.library.dao;


import java.io.IOException;
import java.util.Properties;





public class DBAccesManager {
	
	private static final String PROPERTIES="db.properties";
	
	private String user;
	
	private String db;
	
	private String url;
	
	private String driver;
	
	private String params;
	
	private int size;
	
	private String password;
	
	
	public DBAccesManager() throws DaoException
	{
		Properties prop=new Properties();
		
		
		 try {
			prop.load(DBAccesManager.class.getClassLoader().getResourceAsStream(PROPERTIES));
			setUser(prop.getProperty(DBParam.USER));
			setPassword(prop.getProperty(DBParam.PASSWORD));
			setSize(Integer.parseInt(prop.getProperty(DBParam.SIZE)));
			setParams(prop.getProperty(DBParam.PARAMS));
			setUrl(prop.getProperty(DBParam.URL));
			setDb(prop.getProperty(DBParam.DB));
			setDriver(prop.getProperty(DBParam.DRIVER));
			
			
		} catch (IOException e) {
			 
			throw new DaoException("connection problems");
			
		}
		
	}



	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public String getDb() {
		return db;
	}



	public void setDb(String db) {
		this.db = db;
	}



	public String getDriver() {
		return driver;
	}



	public void setDriver(String driver) {
		this.driver = driver;
	}



	public String getUrl() {
		return url;
	}



	public void setUrl(String url) {
		this.url = url;
	}



	public String getParams() {
		return params;
	}



	public void setParams(String params) {
		this.params = params;
	}



	public int getSize() {
		return size;
	}



	public void setSize(int size) {
		this.size = size;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
