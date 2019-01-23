package by.etc.library.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import by.etc.library.bean.User;
import by.etc.library.bean.UserBook;

public class SQLUserDao implements UserDAO {

	@Override
	public void addUser(String login, String password,String name,String surname) throws DaoException {
	
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		String sql="INSERT INTO USERS(LOGIN,PASSWORD) VALUES (?,?)";
		String sql2="INSERT  INTO `library`.`userprofile` (users_id,name,surname) VALUES ((SELECT id FROM `library`.`users` WHERE login=\""+login+"\"),\""+name+"\",\""+surname+"\")";
		
		PreparedStatement st=null; 
		PreparedStatement st2=null;
		
		try {
			connection.setAutoCommit(false);
			st=connection.prepareStatement(sql);
			st2=connection.prepareStatement(sql2);
			
			
			st.setString(1, login);
			st.setString(2, password);
			
			
		
			st.execute();
			st2.execute();
			
			
			
			connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
					e.printStackTrace();
				} catch (SQLException e1) {
					throw new DaoException("Connection problems",e);
				}
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					if(connection!=null) {
					connection.setAutoCommit(true);
					st.close();
					pool.freeConnection(connection);
					}
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		
	}

	@Override
	public void changePassword(int id, String newPassword) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="UPDATE USERS SET password="+newPassword+" WHERE id="+id;
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(sql);
			st.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);				
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
	}

	@Override
	public int getUserId(String login, String password) throws DaoException {
		int id=0;
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="SELECT * FROM USERS";
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{	
				if(rs.getString("login").equals(login)&&rs.getString("password").equals(password)) {
				id=rs.getInt("id");
				}
			}
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		return id;
	}

	@Override
	public String getUserStatus(int id) throws DaoException {
	     String status=null;
	     ConnectionPool pool=ConnectionManager.getPool();
			Connection connection=pool.getConnection();
			String sql="SELECT * FROM USERS";
			Statement st=null; 
			try {
				st=connection.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next())
				{	
					if(rs.getInt("id")==id)
					{
						status=rs.getString("status");
					}
				}
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
				}finally {
					try {
						st.close();
						pool.freeConnection(connection);
						
					} catch (SQLException e) {
						throw new DaoException("Connection problems",e);
				}
				}
			return status;
	}

	@Override
	public void orderBook(int bookId, int userId) throws DaoException {
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		String sql="INSERT INTO bookqueries(book_id,users_id) VALUES (?,?)";
		PreparedStatement st=null; 
		try {	
			st=connection.prepareStatement(sql);		
			st.setInt(1, bookId);
			st.setInt(2, userId);
			st.execute();
			} catch (SQLException e) {
				try {
					connection.rollback();
					e.printStackTrace();
				} catch (SQLException e1) {
					throw new DaoException("Connection problems",e);
				}
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					if(connection!=null) {
					connection.setAutoCommit(true);
					st.close();
					pool.freeConnection(connection);
					}
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		
		
		
	}

	@Override
	public int getBookOrderId(int bookId, int userId) throws DaoException {
		int id=0;
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="SELECT * FROM bookqueries";
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{	
				if(rs.getInt("book_id")==bookId&&rs.getInt("users_id")==userId)
				{
				id=rs.getInt("id");
				}
			}
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		return id;
		
	}

	@Override
	public void DeleteOrder(int orderId) throws DaoException {
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="DELETE FROM bookqueries WHERE id="+orderId;
		Statement st=null; 
		try {
			
			st=connection.prepareStatement(sql);
			st.execute(sql);
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
	}

	@Override
	public void SubmitOrder(int orderId, int userId, int bookId,String date) throws DaoException {
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		String sql="DELETE FROM bookqueries WHERE id="+orderId;
		String sql2="INSERT INTO `library`.`privatebooks` (`users_id`, `books_id`, `expiredate`) VALUES (?, ?, ?)";
		
		PreparedStatement st=null; 
		PreparedStatement st2=null;
		
		try {
			connection.setAutoCommit(false);
			st=connection.prepareStatement(sql);
			st2=connection.prepareStatement(sql2);
			st2.setInt(1, userId);
			st2.setInt(2, bookId);
			st2.setString(3, date);
			st.execute();
			st2.execute();
			connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
					e.printStackTrace();
				} catch (SQLException e1) {
					throw new DaoException("Connection problems",e);
				}
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					if(connection!=null) {
					connection.setAutoCommit(true);
					st.close();
					pool.freeConnection(connection);
					}
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
	}

	@Override
	public boolean checkUser(String login, String password) throws DaoException {
		boolean flag=false; 
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="SELECT * FROM USERS";
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{	
				if(rs.getString("login").equals(login)||rs.getString("password").equals(password)) {
				flag=true;
				}
			}
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		return flag;
	}

	@Override
	public User getUserInfo(int id) throws DaoException {
		User user=null;
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="SELECT * FROM userprofile WHERE users_id="+id;
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
		
		
			while(rs.next())
			{	
				String name=rs.getString("name");
				String surname=rs.getString("surname");
				String image=rs.getString("image");
				user=new User(name,surname,image);
			}
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		return user;
	}

	@Override
	public List<UserBook> getUserBooks() throws DaoException {
		List<UserBook> bookList = new ArrayList<>();
		String sql="SELECT privatebooks.id,books.name,books.author,userprofile.name,userprofile.surname,privatebooks.expiredate\r\n" + 
				"FROM privatebooks INNER JOIN books INNER JOIN userprofile\r\n" + 
				"ON privatebooks.books_id=books.id AND privatebooks.users_id=userprofile.users_id";
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{	
				int orderNumber=rs.getInt(1);
				String bookName = rs.getString(2);
				String bookAuthor = rs.getString(3);
				String userName= rs.getString(4);
				String userSurname= rs.getString(5);
				String expireDate= rs.getString(6);
				bookList.add(new UserBook(orderNumber,bookName,bookAuthor,userName,userSurname,expireDate));
			}
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		return bookList;
	}

	@Override
	public void deletePrivateBook(int id) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="DELETE FROM privatebooks WHERE id="+id;
		Statement st=null; 
		try {
			
			st=connection.prepareStatement(sql);
			st.execute(sql);
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		
	}

	@Override
	public boolean checkPrivateBook(int id) throws DaoException {
		boolean flag=false; 
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="SELECT *FROM privatebooks WHERE id="+id;
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			flag=true;
		}
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		return flag;
		
	}

	@Override
	public void editUserName(int id, String name) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="UPDATE userprofile SET name='"+name+"' WHERE users_id="+id;
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(sql);
			st.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);				
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		
	}

	@Override
	public void editUserSurname(int id, String surName) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="UPDATE userprofile SET surname='"+surName+"' WHERE users_id="+id;
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(sql);
			st.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);				
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		
	}

	@Override
	public String getUserName(int id) throws DaoException {
		String name=null; 
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="SELECT *FROM userprofile WHERE users_id="+id;
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
		while(rs.next()) {
			name=rs.getString(1);
		}
			} catch (SQLException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		return name;
	}

	@Override
	public void editUserImage(int id,String name,String fullPath,Part file) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="UPDATE userprofile SET image='"+name+"' WHERE users_id="+id;
		PreparedStatement st=null;
		try {
			connection.setAutoCommit(false);
			st=connection.prepareStatement(sql);
			st.execute();
			file.write(fullPath);
			connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
					e.printStackTrace();
				} catch (SQLException e1) {
					throw new DaoException("Connection problems",e);
				}
				throw new DaoException("Connection problems",e);
			} catch (IOException e) {
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					if(connection!=null) {
					connection.setAutoCommit(true);
					st.close();
					pool.freeConnection(connection);
					}
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		
	}

}
