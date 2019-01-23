package by.etc.library.bean;

public class User {
	
	private String name;
	
	private String surname;
	
	private String image;
	
	public User() {
		
	}
	
	public User(String name,String surname,String image) {
		setName(name);
		setSurname(surname);
		setImage(image);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result=prime * result+name.length();
		result=prime * result+surname.length();
		result=prime * result+image.length();
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
		return (getClass().getName()+'@'+"name: "+name+" surname: "+surname+" image: "+image);
	}
	
}
