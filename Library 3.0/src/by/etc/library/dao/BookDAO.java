package by.etc.library.dao;

import java.util.List;

import javax.servlet.http.Part;

import by.etc.library.bean.Book;
import by.etc.library.bean.BookOrder;
import by.etc.library.bean.PrivateBook;
import by.etc.library.bean.PrivateBookOrder;

public interface BookDAO {

	public void addBook(Book book, Part file,String fullpath) throws DaoException;
	
	public int getBookId(String name) throws DaoException;
	
	public void ChangeBookAmmoutn(int id,int number) throws DaoException;
	
	public int getBookAmmount(int id) throws DaoException;
	
	public List<Book> getAllBooks()throws DaoException;
	
	public Book getBook(int id) throws DaoException;
	
	public List<BookOrder> getOrders() throws DaoException;
	
	public List<PrivateBook> getPrivateBooks(int id) throws DaoException;
	
	public List<PrivateBookOrder> getPrivateOrders(int id) throws DaoException;
	
	public String getPrivateBookName(int privateBookId) throws DaoException;
	

}
