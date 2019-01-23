package by.etc.library.bean;

public class PrivateBookOrder {

	private int id;
	
	private String name;
	
	private String author;
	
	public PrivateBookOrder(int id,String name,String author) {
		setId(id);
		setName(name);
		setAuthor(author);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result=prime * result+id;	
		result=prime * result+name.length();
		result=prime * result+author.length();
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
		return (getClass().getName()+'@'+"orderId: "+id+"  bookName: "+ name+" author: "+author);
	}
	
	
}
