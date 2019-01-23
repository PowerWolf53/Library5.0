package by.etc.library.service;

import javax.servlet.http.Part;

import by.etc.library.bean.ServiceResponse;

public interface UserService {

	public ServiceResponse signIn(String login,String password) throws ServiceException;
	
	public void registration(String login,String password,String name,String surname) throws ServiceException;
	
	public void orderBook(int bookId,int userId) throws ServiceException;
	
	public void cancelOrder(int orderId) throws ServiceException;
	
	public void SubmitOrder(int orderId, int userId, int bookId,String date) throws ServiceException;
	
	public ServiceResponse getUserProfileInfo(int id) throws ServiceException;
	
	public ServiceResponse getUserBooks() throws ServiceException;
	
	public void refundBook(int id) throws ServiceException;
	
	public void editProfile(int id,String folder,Part file,String name,String surname) throws ServiceException;
}
