package application;

public class Suppliers {

	 int Sid;
	 String name;
	 String phone_numb;
	 String email;
	 String location;
	 
	 
	 
	public Suppliers() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Suppliers(int sid, String name, String phone_numb, String email, String location) {
		super();
		Sid = sid;
		this.name = name;
		this.phone_numb = phone_numb;
		this.email = email;
		this.location = location;
	}



	public int getSid() {
		return Sid;
	}



	public void setSid(int sid) {
		Sid = sid;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPhone_numb() {
		return phone_numb;
	}



	public void setPhone_numb(String phone_numb) {
		this.phone_numb = phone_numb;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}
	  
	 
}
