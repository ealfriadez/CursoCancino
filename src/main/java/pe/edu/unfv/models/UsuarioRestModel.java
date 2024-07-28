package pe.edu.unfv.models;

public class UsuarioRestModel {

	private String id;
	
	private String name;
	
	private String username;
	
	private String email;
	
	private AddressRestModel address;
	
	private CompanyRestModel company;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AddressRestModel getAddress() {
		return address;
	}

	public void setAddress(AddressRestModel address) {
		this.address = address;
	}

	public CompanyRestModel getCompany() {
		return company;
	}

	public void setCompany(CompanyRestModel company) {
		this.company = company;
	}
}
