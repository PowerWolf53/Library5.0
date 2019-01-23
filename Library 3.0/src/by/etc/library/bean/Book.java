package by.etc.library.bean;

public class Book {

	private int id;
	
	private String name;
	
	private String image; 
	
	private String author;
	
	private int ammount;
	
	private String specification;
	  
	private String description;

	public Book(String name,String author,String image,int ammount,String specification,String description) {
		setAuthor(author);
		setName(name);
		setImage(image);
		setAmmount(ammount);
		setSpecification(specification);
		setDescription(description);	
	}
	
	public Book(int id,String name,String author,String image,int ammount,String specification,String description) {
		setId(id);
		setAuthor(author);
		setName(name);
		setImage(image);
		setAmmount(ammount);
		setSpecification(specification);
		setDescription(description);	
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	
	public void setAuthor(String author)
	{
	  this.author=author;	
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result=prime * result+id;
		result=prime * result+ammount;
		result=prime * result+name.length();
		result=prime * result+author.length();
		result=prime * result+image.length();
		result=prime * result+specification.length();
		result=prime * result+description.length();
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
		return (getClass().getName()+'@'+name+author+image+ammount+specification+description);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
