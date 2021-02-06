package hse.project.entities.prototypes;

public interface VirtualMachine {

	public String getId();
	
	public String getName();
	
	//public String getGroupId();
	
	public String getGroupName();
	
	//public String getUserName();
	
	public String getLogin();
	
	public String getPassword();
	
	public void setId(String id);
	
	public void setName(String name);
	
	public void setGroupName(String groupName);
	
	public void setLogin(String login);
	
	public void setPassword(String password);
	
}
