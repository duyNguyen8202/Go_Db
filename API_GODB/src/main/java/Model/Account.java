package Model;

public class Account {
    private String accountId;
    private String uername;
    private String password;
    private String accountType;
    private String customerId;
    private String staffId;
    private String status;
    
	public Account(String accountId, String uername, String password, String accountType, String customerId,
			String staffId, String status) {
		super();
		this.accountId = accountId;
		this.uername = uername;
		this.password = password;
		this.accountType = accountType;
		this.customerId = customerId;
		this.staffId = staffId;
		this.status = status;
	}
	
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getUername() {
		return uername;
	}
	public void setUername(String uername) {
		this.uername = uername;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
}
