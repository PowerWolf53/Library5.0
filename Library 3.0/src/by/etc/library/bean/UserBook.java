package by.etc.library.bean;

public class UserBook {

	private int number;
	
	private String bookName;
	
	private String bookAuthor;
	
	private String userName;
	
	private String userSurname;
	
	private String expireDate;
	
	public UserBook() {
		
	}

	public UserBook(int number,String bookName,String bookAuthor,String userName,String userSurname,String expireDate)
	{
		setNumber(number);
		setBookName(bookName);
		setBookAuthor(bookAuthor);
		setUserName(userName);
		setUserSurname(userSurname);
		setExpireDate(expireDate);
	}
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSurname() {
		return userSurname;
	}

	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result=prime * result+number;
		result=prime * result+bookName.length();
		result=prime * result+bookAuthor.length();
		result=prime * result+userName.length();
		result=prime * result+userSurname.length();
		result=prime * result+expireDate.length();
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
		return (getClass().getName()+'@'+"number: "+number+" bookName: "+bookName+" bookAuthor: "+bookAuthor+" userName: "+userName+" userSurname" +
	userSurname+" expireDate:"+expireDate);
	}
}
