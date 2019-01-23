package by.etc.library.service;


import java.util.List;

import javax.servlet.http.Part;

import by.etc.library.bean.PrivateBook;
import by.etc.library.bean.PrivateBookOrder;
import by.etc.library.bean.ServiceResponse;
import by.etc.library.bean.User;
import by.etc.library.bean.UserBook;
import by.etc.library.controller.RequestParam;
import by.etc.library.controller.SessionParam;
import by.etc.library.dao.BookDAO;
import by.etc.library.dao.DAOFactory;
import by.etc.library.dao.DaoException;
import by.etc.library.dao.UserDAO;

public class UserServiceImpl implements UserService {

	private DAOFactory  factory=DAOFactory.getInstance();
	
	private UserDAO userDao = factory.getUserDao();
	
	private static final String imgfolder="images/";
	
	
	@Override
	public ServiceResponse signIn(String login,String password) throws ServiceException {
		ServiceResponse resp=new ServiceResponse();
		try {
			int id=userDao.getUserId(login, password);
			if(id==0){
				throw new ServiceException("Wrong login or password");
			}else {
				resp.addSessionParam(SessionParam.ID,Integer.toString(id));
				resp.addSessionParam(SessionParam.STATUS,userDao.getUserStatus(id));
			}
			
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		return resp;
	}

	@Override
	public void registration(String login,String password,String name,String surname) throws ServiceException {
	
		try {
			boolean flag = userDao.checkUser(login, password);
			if(flag==true)
			{
				throw new ServiceException("User already exists");
			}else {				
				userDao.addUser(login, password,name,surname);
			}
		} catch (DaoException e) {
			
			throw new ServiceException(e.getMessage());
		}
	
	}

	@Override
	public void orderBook(int bookId, int userId) throws ServiceException {
		try {
			if(userDao.getBookOrderId(bookId, userId)!=0) {
				throw new ServiceException("Order already exists");	
			}else {
				userDao.orderBook(bookId, userId);
			}
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void cancelOrder(int orderId) throws ServiceException {
		UserDAO dao= DAOFactory.getInstance().getUserDao();
		try {
			dao.DeleteOrder(orderId);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public void SubmitOrder(int orderId, int userId, int bookId, String date) throws ServiceException {
		UserDAO dao = DAOFactory.getInstance().getUserDao();
		BookDAO dao2=DAOFactory.getInstance().getBookDao();
		try {
			if(dao2.getBookAmmount(bookId)==0) {
				throw new ServiceException("Not enough books");
			}
			dao.SubmitOrder(orderId, userId, bookId, date);
			int ammount=dao2.getBookAmmount(bookId);
			dao2.ChangeBookAmmoutn(bookId, (ammount-1));
		} catch (DaoException e) {
			
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public ServiceResponse getUserProfileInfo(int id) throws ServiceException {
		ServiceResponse resp=new ServiceResponse();
		List<PrivateBookOrder> bookOrderList;
		List<PrivateBook> bookList;
		User user;
		
		UserDAO userDao=DAOFactory.getInstance().getUserDao();
		BookDAO bookDao=DAOFactory.getInstance().getBookDao();
		try {
			
			user=userDao.getUserInfo(id);
			
			bookOrderList=bookDao.getPrivateOrders(id);
			
			bookList=bookDao.getPrivateBooks(id);
			System.out.println("executed");
			resp.addRequestParams(RequestParam.BOOKS, bookList);
			resp.addRequestParams(RequestParam.ORDERS, bookOrderList);
			resp.addRequestParams(RequestParam.PROFILE_INFO, user);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		return resp;
	}

	@Override
	public ServiceResponse getUserBooks() throws ServiceException {
		ServiceResponse resp=new ServiceResponse();
		UserDAO userDao=DAOFactory.getInstance().getUserDao();
		try {
			List<UserBook> bookList=userDao.getUserBooks();
			resp.addRequestParams(RequestParam.USER_BOOKS, bookList);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return resp;
	}

	@Override
	public void refundBook(int id) throws ServiceException {
		try {
			if(userDao.checkPrivateBook(id)==false) {
				throw new ServiceException(" private book not found");
			}else {
			userDao.deletePrivateBook(id);
			}
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		
	}

	@Override
	public void editProfile(int id,String folder,Part file, String name, String surName) throws ServiceException {
			
			try {
				if(name.length()!=0&&name!=null) {
				userDao.editUserName(id, name);
				}
				if(surName.length()!=0&&surName!=null) {
					userDao.editUserSurname(id, surName);
				}
				if(file!=null) {
					String fileName=userDao.getUserName(id);
					String fullPath=folder+imgfolder+fileName+id+".jpg";
					String simpleName=fileName+id+".jpg";
					userDao.editUserImage(id, simpleName,fullPath, file);
				}
			} catch (DaoException e) {
				throw new ServiceException(e.getMessage());
			}
		}
		
	

}
