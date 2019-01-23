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
		String sql="INSERT INTO BOOKS(NAME,IMAGE,DESCRIPTION,AMMOUNT,AUTHOR,SPECIFICATION) VALUES (?,?,?,?,?,?)";
		PreparedStatement st=null;
		try {
			connection.setAutoCommit(false);
			st=connection.prepareStatement(sql);
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

	@Override
	public int getBookId(String name) throws DaoException {
		int id=0;
		String sql="SELECT *FROM BOOKS";
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{	
				if(rs.getString("name").equals(name)) {
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
	public void ChangeBookAmmoutn(int id,int number) throws DaoException {
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		String sql="UPDATE BOOKS SET ammount=? WHERE id=?";
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(sql);
			st.setInt(1, number);
			st.setInt(2, id);
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
	public int getBookAmmount(int id) throws DaoException {
		int ammount=0;
		String sql="SELECT *FROM BOOKS";
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{	
				if(rs.getInt("id")==id) {
				ammount=rs.getInt("ammount");
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
		return ammount;
	}

	@Override
	public List<Book> getAllBooks() throws DaoException {
		List<Book> bookList = new ArrayList<>();
		String sql="SELECT *FROM BOOKS";
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
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
	public Book getBook(int id) throws DaoException {
		Book book=null;
		String sql="SELECT *FROM BOOKS";
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
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
				throw new DaoException("Book not found");
				
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
		return book;
	}

	@Override
	public List<BookOrder> getOrders() throws DaoException {
		List<BookOrder> orderList = new ArrayList<>();
		String sql="SELECT bookqueries.id,userprofile.name,books.name,userprofile.users_id,books.id\r\n" + 
				"FROM bookqueries INNER JOIN userprofile INNER JOIN books\r\n" + 
				"ON bookqueries.users_id=userprofile.users_id AND bookqueries.book_id=books.id";
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection(); 
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
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
				throw new DaoException("Connection problems",e);
			}finally {
				try {
					st.close();
					pool.freeConnection(connection);
					
				} catch (SQLException e) {
					throw new DaoException("Connection problems",e);
			}
			}
		
	
		return orderList;
	}

	@Override
	public List<PrivateBook> getPrivateBooks(int id) throws DaoException {
		List<PrivateBook> bookList = new ArrayList<>();
		String sql="SELECT privatebooks.id,books.name,books.author,privatebooks.expiredate FROM privatebooks\r\n" + 
				"INNER JOIN books\r\n" + 
				" ON privatebooks.books_id=books.id\r\n" + 
				" WHERE privatebooks.users_id="+id;
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{	
			 int orderId=rs.getInt(1);
			 String name=rs.getString(2);
			 String author=rs.getString(3);
			 String expireDate=rs.getString(4);
			 bookList.add(new PrivateBook(orderId,name,author,expireDate));
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
		//return null;
	}

	@Override
	public List<PrivateBookOrder> getPrivateOrders(int id) throws DaoException {
		List<PrivateBookOrder> bookOrderList = new ArrayList<>();
		String sql="SELECT bookqueries.id,books.name,books.author\r\n" + 
				"FROM bookqueries INNER JOIN books\r\n" + 
				"ON bookqueries.book_id=books.id\r\n" + 
				"WHERE bookqueries.users_id="+id;
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		Statement st=null; 
		try {
			st=connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{	
			 int orderId=rs.getInt(1);
			 String name=rs.getString(2);
			 String author=rs.getString(3);
			
			 bookOrderList.add(new PrivateBookOrder(orderId,name,author));
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
		return bookOrderList;
	
	}

	@Override
	public String getPrivateBookName(int privateBookId) throws DaoException {
		String name=null;
		String sql="SELECT books.name FROM books \r\n" + 
				"WHERE books.id=(SELECT privatebooks.books_id FROM privatebooks WHERE privatebooks.id=?)";
		ConnectionPool pool=ConnectionManager.getPool();
		Connection connection=pool.getConnection();
		PreparedStatement st=null; 
		try {
			st=connection.prepareStatement(sql);
			st.setInt(1, privateBookId);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{	
			
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
}
