package by.etc.library.dao;

import java.util.List;

import javax.servlet.http.Part;

import by.etc.library.bean.Book;
import by.etc.library.bean.User;
import by.etc.library.bean.UserBook;

public interface UserDAO {

	public void addUser(String login,String password,String name,String surname) throws DaoException;
	
	public void changePassword(int id,String newPassword) throws DaoException;
	
	public int getUserId(String login,String password) throws DaoException;
	
	public String getUserStatus(int id) throws DaoException;
	
	public void orderBook(int bookId,int userId) throws DaoException;
	
	public int getBookOrderId(int bookId,int userId) throws DaoException;
	
	public void DeleteOrder(int orderId) throws DaoException;
	
	public void SubmitOrder(int orderId,int userId,int bookId,String date) throws DaoException;
	
	public boolean checkUser(String login,String password) throws DaoException;
	
	public User getUserInfo(int id) throws DaoException;
	
	public List<UserBook> getUserBooks() throws DaoException;
	
	public void deletePrivateBook(int id) throws DaoException;
	
	public boolean checkPrivateBook(int id) throws DaoException;
	
	public void editUserName(int id,String name) throws DaoException;
	
	public void editUserSurname(int id,String surName) throws DaoException;
	
	public String getUserName(int id) throws DaoException;
	
	public void editUserImage(int id,String name,String fullPath,Part file) throws DaoException;
	
	public List<Book> getBestsellers() throws DaoException;
}
