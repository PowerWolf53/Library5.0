package by.etc.library.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.Part;

import by.etc.library.bean.Book;
import by.etc.library.bean.BookOrder;
import by.etc.library.bean.ServiceResponse;
import by.etc.library.controller.RequestParam;
import by.etc.library.dao.BookDAO;
import by.etc.library.dao.DAOFactory;
import by.etc.library.dao.DaoException;
import by.etc.library.dao.UserDAO;

public class BookServiceImpl implements BookService {

	private static final String imgfolder="bookimages/";
	
	@Override
	public void addBook(String name, String author, String specification,String ammount, String description,String folder, Part file) throws ServiceException {
		String image=name.toLowerCase().replaceAll(" ", "_")+".jpg";
		String fullpath=folder+imgfolder+image;
		DAOFactory factory=DAOFactory.getInstance();
		BookDAO dao=factory.getBookDao();
		try {
			if(dao.getBookId(name)!=0)
			{
				throw new ServiceException(ServiceWarning.BOOK_ALREADY_EXISTS);
			}
			Book book=new Book(name, author, image, Integer.parseInt(ammount), specification, description);
			dao.addBook(book,file,fullpath);
		} catch (DaoException e) {
			
			throw new ServiceException(e.getMessage(),e);
		}
		
	
		
	}

	@Override
	public void incrementAmmount(String name, String ammount) throws ServiceException {
		int number=Integer.parseInt(ammount);
		DAOFactory factory=DAOFactory.getInstance();
		BookDAO dao=factory.getBookDao();
		try {
			int id=dao.getBookId(name);
			if(id==0)
			{
				throw new ServiceException(ServiceWarning.NO_SUCH_BOOK);
			}
			number+=dao.getBookAmmount(id);
			dao.ChangeBookAmmoutn(id, number);
		} catch (DaoException e) {
			
			throw new ServiceException(e.getMessage(),e);
		}
		
	}

	@Override
	public ServiceResponse getAllBooks() throws ServiceException {
		BookDAO dao=DAOFactory.getInstance().getBookDao();
		ServiceResponse resp=new ServiceResponse();
		List<Book> bookList=null;
		try {
			bookList=dao.getAllBooks();
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(),e);
		}
		resp.addRequestParams(RequestParam.ALL_BOOKS, bookList);
		return resp;
	}

	@Override
	public ServiceResponse getConcreteBook(int id) throws ServiceException {
		BookDAO dao=DAOFactory.getInstance().getBookDao();
		ServiceResponse resp=new ServiceResponse();
		try {
			Book book= dao.getBook(id);
			resp.addRequestParams(RequestParam.BOOK, book);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(),e);
		}
		return resp;
	}

	@Override
	public ServiceResponse getOrders() throws ServiceException {
		BookDAO dao=DAOFactory.getInstance().getBookDao();
		ServiceResponse resp=new ServiceResponse();
		try {
			List<BookOrder> orderList =dao.getOrders();
			resp.addRequestParams(RequestParam.ORDERS, orderList);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage(),e);
		}
		return resp;
	}

	@Override
	public ServiceResponse getBestsellers() throws ServiceException {
		UserDAO dao=DAOFactory.getInstance().getUserDao();
		ServiceResponse resp=new ServiceResponse();
		try {
			List<Book> randomBookList=new ArrayList<>();
			List<Book> bookList= dao.getBestsellers();
			Collections.shuffle(bookList);
	
			for(int i=0;i<3;i++) {
				randomBookList.add(bookList.get(i));
			}
			resp.addRequestParams(RequestParam.BOOKS, randomBookList);
		} catch (DaoException e) {
			throw new ServiceException(e.getMessage());
		}
		return resp;
	}

}
