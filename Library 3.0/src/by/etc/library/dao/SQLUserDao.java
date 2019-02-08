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

import by.etc.library.bean.Book;
import by.etc.library.bean.User;
import by.etc.library.bean.UserBook;

public class SQLUserDao implements UserDAO {

	@Override
	public void addUser(String login, String password,String name,String surname) throws DaoException {
	
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		
		
		PreparedStatement st=null; 
		PreparedStatement st2=null;
		
		try {
			connection.setAutoCommit(false);
			st=connection.prepareStatement(SQLStatement.USER_ADDITION);
			st2=connection.prepareStatement(SQLStatement.USER_PROFILE_ADDITION);
			
			
			st.setString(1, login);
			st.setString(2, password);
			
			
		
			st.execute();
			st2.setString(1, login);
			st2.setString(2, name);
			st2.setString(3, surname);
			st2.execute();
			
			
			
			connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
					e.printStackTrace();
				} catch (SQLException e1) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
				}
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
					connection.setAutoCommit(true);
					st.close();
					pool.freeConnection(connection);
					}
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		
	}

	@Override
	public void changePassword(int id, String newPassword) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(SQLStatement.USER_PASSWORD_EDITION);
			st.setString(1, newPassword);
			st.setInt(2, id);
			st.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}				
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
	}

	@Override
	public int getUserId(String login, String password) throws DaoException {
		int id=0;
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(SQLStatement.SELECT_ALL_USERS);
			while(rs.next())
			{	
				if(rs.getString("login").equals(login)&&rs.getString("password").equals(password)) {
				id=rs.getInt("id");
				}
			}
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}
					
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		return id;
	}

	@Override
	public String getUserStatus(int id) throws DaoException {
	     String status=null;
	     ConnectionPool pool=ConnectionManager.getPool();
			Connection connection=pool.getConnection();
			Statement st=null; 
			try {
				st=connection.createStatement();
				ResultSet rs = st.executeQuery(SQLStatement.SELECT_ALL_USERS);
				while(rs.next())
				{	
					if(rs.getInt("id")==id)
					{
						status=rs.getString("status");
					}
				}
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
				}finally {
					try {
						if(connection!=null) {
							connection.setAutoCommit(true);
							st.close();
							pool.freeConnection(connection);
							}
						
					} catch (SQLException e) {
						throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
				}
				}
			return status;
	}

	@Override
	public void orderBook(int bookId, int userId) throws DaoException {
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		
		PreparedStatement st=null; 
		try {	
			st=connection.prepareStatement(SQLStatement.ORDER_BOOK);		
			st.setInt(1, bookId);
			st.setInt(2, userId);
			st.execute();
			} catch (SQLException e) {
				try {
					connection.rollback();
					e.printStackTrace();
				} catch (SQLException e1) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
				}
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
					connection.setAutoCommit(true);
					st.close();
					pool.freeConnection(connection);
					}
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		
		
		
	}

	@Override
	public int getBookOrderId(int bookId, int userId) throws DaoException {
		int id=0;
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(SQLStatement.SELECT_ALL_QUERIES);
			while(rs.next())
			{	
				if(rs.getInt("book_id")==bookId&&rs.getInt("users_id")==userId)
				{
				id=rs.getInt("id");
				}
			}
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		return id;
		
	}

	@Override
	public void DeleteOrder(int orderId) throws DaoException {
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		PreparedStatement st=null; 
		try {
			
			st=connection.prepareStatement(SQLStatement.DELETE_ORDER);
			st.setInt(1, orderId);
			st.execute();
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}
					
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
	}

	@Override
	public void SubmitOrder(int orderId, int userId, int bookId,String date) throws DaoException {
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		

		
		PreparedStatement st=null; 
		PreparedStatement st2=null;
		
		try {
			connection.setAutoCommit(false);
			st=connection.prepareStatement(SQLStatement.DELETE_ORDER);
			st2=connection.prepareStatement(SQLStatement.SUBMIT_ORDER);
			st.setInt(1, orderId);
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
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
				}
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
					connection.setAutoCommit(true);
					st.close();
					pool.freeConnection(connection);
					}
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
	}

	@Override
	public boolean checkUser(String login, String password) throws DaoException {
		boolean flag=false; 
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(SQLStatement.SELECT_ALL_USERS);
			while(rs.next())
			{	
				if(rs.getString("login").equals(login)||rs.getString("password").equals(password)) {
				flag=true;
				}
			}
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}
					
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		return flag;
	}

	@Override
	public User getUserInfo(int id) throws DaoException {
		User user=null;
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(SQLStatement.GET_USER_INFO);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
		
		
			while(rs.next())
			{	
				String name=rs.getString("name");
				String surname=rs.getString("surname");
				String image=rs.getString("image");
				user=new User(name,surname,image);
			}
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}
					
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		return user;
	}

	@Override
	public List<UserBook> getUserBooks() throws DaoException {
		List<UserBook> bookList = new ArrayList<>();
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(SQLStatement.GET_USER_BOOKS);
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
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}
					
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		return bookList;
	}

	@Override
	public void deletePrivateBook(int id) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		PreparedStatement st=null; 
		try {
			
			st=connection.prepareStatement(SQLStatement.DELETE_PRIVATE_BOOK);
			st.setInt(1, id);
			st.execute();
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}
					
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		
	}

	@Override
	public boolean checkPrivateBook(int id) throws DaoException {
		boolean flag=false; 
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(SQLStatement.CHECK_PRIVATE_BOOK);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
		while(rs.next()) {
			flag=true;
		}
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}
					
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		return flag;
		
	}

	@Override
	public void editUserName(int id, String name) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(SQLStatement.EDIT_USER_NAME);
			st.setInt(2, id);
			st.setString(1, name);
			st.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}				
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		
	}

	@Override
	public void editUserSurname(int id, String surName) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(SQLStatement.EDIT_USER_SURNAME);
			st.setString(1, surName);
			st.setInt(2, id);
			st.executeUpdate();
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}			
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		
	}

	@Override
	public String getUserName(int id) throws DaoException {
		String name=null; 
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(SQLStatement.GET_USER_NAME);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
		while(rs.next()) {
			name=rs.getString(1);
		}
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}
					
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		return name;
	}

	@Override
	public void editUserImage(int id,String name,String fullPath,Part file) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
	
		PreparedStatement st=null;
		try {
			connection.setAutoCommit(false);
			st=connection.prepareStatement(SQLStatement.EDIT_USER_IMAGE);
			st.setString(1, name);
			st.setInt(2, id);
			st.execute();
			file.write(fullPath);
			connection.commit();
			} catch (SQLException e) {
				try {
					connection.rollback();
					e.printStackTrace();
				} catch (SQLException e1) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
				}
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			} catch (IOException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
					connection.setAutoCommit(true);
					st.close();
					pool.freeConnection(connection);
					}
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		
	}

	@Override
	public List<Book> getBestsellers() throws DaoException {
List<Book> bookList = new ArrayList<>();
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(SQLStatement.GET_BESTSELLERS);
			while(rs.next())
			{	
				int id=rs.getInt(1);
				String bookName = rs.getString(2);
				String bookImage=rs.getString(3);
				String bookDescription=rs.getString(4);
				int amount=rs.getInt(5);
				String bookAuthor = rs.getString(6);
				String specification= rs.getString(7);
				
				bookList.add(new Book(id,bookName,bookAuthor,bookImage,amount,specification,bookDescription));
			}
			} catch (SQLException e) {
				throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}finally {
				try {
					if(connection!=null) {
						connection.setAutoCommit(true);
						st.close();
						pool.freeConnection(connection);
						}
					
				} catch (SQLException e) {
					throw new DaoException(DAOWarning.CONNECTION_PROBLEMS,e);
			}
			}
		return bookList;
		
		
	}

}
