package by.etc.library.service;

import javax.servlet.http.Part;

import by.etc.library.bean.ServiceResponse;



public interface BookService {

	public void addBook(String name,String author,String specification,String ammount,String description,String folder,Part file) throws ServiceException;
	
	public void incrementAmmount(String name,String ammount)throws ServiceException;
	
	public ServiceResponse getAllBooks()throws ServiceException;
	
	public ServiceResponse getConcreteBook(int id)throws ServiceException;
	
	public ServiceResponse getOrders() throws ServiceException;
	
	public ServiceResponse getBestsellers() throws ServiceException;
	
	
}
