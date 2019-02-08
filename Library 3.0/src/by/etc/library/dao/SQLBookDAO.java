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
import by.etc.library.bean.BookOrder;
import by.etc.library.bean.PrivateBook;
import by.etc.library.bean.PrivateBookOrder;

public class SQLBookDAO implements BookDAO {

	
	
	@Override
	public void addBook(Book book, Part file,String fullpath) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		PreparedStatement st=null;
		try {
			connection.setAutoCommit(false);
			st=connection.prepareStatement(SQLStatement.ADD_BOOK);
			st.setString(1, book.getName());
			st.setString(2, book.getImage());
			st.setString(3, book.getDescription());
			st.setString(4, String.valueOf(book.getAmmount()));
			st.setString(5, book.getAuthor());
			st.setString(6, book.getSpecification());
			file.write(fullpath);
			st.execute();
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
	public int getBookId(String name) throws DaoException {
		int id=0;
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(SQLStatement.GET_BOOK_ID);
			while(rs.next())
			{	
				if(rs.getString("name").equals(name)) {
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
	public void ChangeBookAmmoutn(int id,int number) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(SQLStatement.CHANGE_BOOK_AMMOUNT);
			st.setInt(1, number);
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
	public int getBookAmmount(int id) throws DaoException {
		int ammount=0;
	
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(SQLStatement.GET_BOOK_AMMOUNT);
			while(rs.next())
			{	
				if(rs.getInt("id")==id) {
				ammount=rs.getInt("ammount");
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
		return ammount;
	}

	@Override
	public List<Book> getAllBooks() throws DaoException {
		List<Book> bookList = new ArrayList<>();
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(SQLStatement.GET_ALL_BOOKS);
			while(rs.next())
			{	
				int id=rs.getInt("id");
				String name= rs.getString("name");
				String author = rs.getString("author");
				String specification=rs.getString("specification");
				int ammount = rs.getInt("ammount");
				String image = rs.getString("image");
				String description = rs.getString("description");
				bookList.add(new Book(id,name,author,image,ammount,specification,description));
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
	public Book getBook(int id) throws DaoException {
		Book book=null;
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(SQLStatement.GET_ALL_BOOKS);
			while(rs.next())
			{	
				if(rs.getInt("id")==id) {
				int bookid=rs.getInt("id");
				String name= rs.getString("name");
				String author = rs.getString("author");
				String specification=rs.getString("specification");
				int ammount = rs.getInt("ammount");
				String image = rs.getString("image");
				String description = rs.getString("description");
				book=new Book(bookid,name,author,image,ammount,specification,description);
				}
				
			}
			if(book==null) {
				throw new DaoException(DAOWarning.BOOK_NOT_FOUND);
				
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
		return book;
	}

	@Override
	public List<BookOrder> getOrders() throws DaoException {
		List<BookOrder> orderList = new ArrayList<>();
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection(); 
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(SQLStatement.GET_ORDERS);
			while(rs.next())
			{	
				int id=rs.getInt(1);
				int bookId=rs.getInt(5);
				int userId=rs.getInt(4);
				String userName=rs.getString(2);
				String bookName=rs.getString(3);
				orderList.add(new BookOrder(id,bookId,userId,userName,bookName));
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
		
	
		return orderList;
	}

	@Override
	public List<PrivateBook> getPrivateBooks(int id) throws DaoException {
		List<PrivateBook> bookList = new ArrayList<>();
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(SQLStatement.GET_PRIVATE_BOOKS);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{	
			 int orderId=rs.getInt(1);
			 String name=rs.getString(2);
			 String author=rs.getString(3);
			 String expireDate=rs.getString(4);
			 bookList.add(new PrivateBook(orderId,name,author,expireDate));
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
		//return null;
	}

	@Override
	public List<PrivateBookOrder> getPrivateOrders(int id) throws DaoException {
		List<PrivateBookOrder> bookOrderList = new ArrayList<>();
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(SQLStatement.GET_PRIVATE_ORDERS);
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{	
			 int orderId=rs.getInt(1);
			 String name=rs.getString(2);
			 String author=rs.getString(3);
			
			 bookOrderList.add(new PrivateBookOrder(orderId,name,author));
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
		return bookOrderList;
	
	}

	@Override
	public String getPrivateBookName(int privateBookId) throws DaoException {
		String name=null;
		
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(SQLStatement.GET_PRIVATE_BOOK_NAME);
			st.setInt(1, privateBookId);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{	
			
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
}
