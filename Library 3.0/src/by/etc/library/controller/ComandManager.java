package by.etc.library.controller;

import java.util.HashMap;
import java.util.Map;

public class ComandManager {

	private static final ComandManager instance = new ComandManager();
	
	private Map<String,Command> comands= new HashMap<>();
	
	public static ComandManager getInstance()
	{
		return instance;
	}
	
	private ComandManager()
	{
		comands.put(ComandName.REGISTRATION, new RegistrationCommand());
		comands.put(ComandName.AUTHORISATION, new AuthorisationCommand());
		comands.put(ComandName.LOGOUT, new LogoutCommand());
		comands.put(ComandName.GETLOCALE, new GetLocaleCommand());
		comands.put(ComandName.BOOKADDITION, new BookAdditionCommand());
		comands.put(ComandName.BOOKINCREMENT, new BookIncrementCommand());
		comands.put(ComandName.GET_ALL_BOOKS, new GetAllBooksCommand());
		comands.put(ComandName.GET_CONCRETE_BOOK, new GetConcreteBookCommand());
		comands.put(ComandName.ORDER_BOOK, new OrderBookCommand());
		comands.put(ComandName.GET_ORDERS, new GetOrdersCommand());
		comands.put(ComandName.CANCEL_ORDER, new CancelOrderCommand());
		comands.put(ComandName.SUBMIT_ORDER, new SubmitOrderCommand());
		comands.put(ComandName.GET_PROFILE_INFO, new GetProfileInfoCommand());
		comands.put(ComandName.GET_USER_BOOKS, new GetUserBooksCommand());
		comands.put(ComandName.REFUND_BOOK, new RefundBookCommand());
		comands.put(ComandName.EDIT_PROFILE, new EditProfileCommand());
	}
	
	public Command getCommand(String command)
	{
		return comands.get(command);
	}
	
}
