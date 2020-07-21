package resource;

public enum EmployeeResource {
	
	GetEmployeeApi("/apitest");
	
	private String resource;

	EmployeeResource(String resource) {
		this.resource=resource;
	}

	
	public String getResource()
	{
		return resource;
	}
	
	

}
