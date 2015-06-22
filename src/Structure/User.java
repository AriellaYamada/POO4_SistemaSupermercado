package Structure;

public class User {

	private String name;
	private String address;
	private String tel;
	private String email;
	private String id;
	private String password;

	public User (String name, String address, String tel, String email, String id, String password) {
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.id = id;
		this.password = password;
	}

	public String getName() { return name; }
	public String getAddress() { return  address; }
	public String getTel() { return tel; }
	public String getEmail() { return email; }
	public String getId () { return id; }
	public String getPassword () { return  password; }
}
