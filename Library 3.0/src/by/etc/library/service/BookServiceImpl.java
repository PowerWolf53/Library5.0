package by.etc.library.service;

import java.util.List;

import javax.servlet.http.Part;

import by.etc.library.bean.Book;
import by.etc.library.bean.BookOrder;
import by.etc.library.bean.ServiceResponse;
import by.etc.library.controller.RequestParam;
import by.etc.library.dao.BookDAO;
import by.etc.library.dao.DAOFactory;
import by.etc.library.dao.DaoException;

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
				throw new ServiceException("book already exists");
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
				throw new ServiceException("no such book exists");
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

}
