package by.etc.library.bean;

public class BookOrder {
	private int id;
	
	private int bookId;
	
	private int userId;
	
	private String userName;
	
	private String bookName;
	
	public BookOrder() {
		
	}

public BookOrder(int id,int bookId,int userId,String userName,String bookName) {
		setId(id);
		setBookId(bookId);
		setUserId(userId);
		setUserName(userName);
		setBookName(bookName);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result=prime * result+id;
		result=prime * result+bookId;
		result=prime * result+userId;
		result=prime * result+userName.length();
		result=prime * result+bookName.length();
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean flag=false;
		
		if(obj==null)
		{
			return false;
		}
		
		if(obj==this)
		{
			flag=true;
		}
		
		if(this.hashCode()==obj.hashCode())
		{
			flag=true;
		}
		return flag;	
	}
	
	@Override
	public String toString()
	{
		return (getClass().getName()+'@'+"orderId: "+id+" bookID: "+bookId+" userId: "+userId+" bookName: "+ bookName+" userName: "+userName);
	}


}
