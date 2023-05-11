package Model;

public class Account {
    private String accountId;
    private String accountType;
    private String customerId;
    private String staffId;
    private boolean acc_status;
    private String acc_password;

    public boolean isAcc_status() {
		return acc_status;
	}

	public void setAcc_status(boolean acc_status) {
		this.acc_status = acc_status;
	}

	public String getAcc_password() {
		return acc_password;
	}

	public void setAcc_password(String acc_password) {
		this.acc_password = acc_password;
	}

	public Account(String accountId, String accountType, String customerId, String staffId,boolean status,String password) {
        this.accountId = accountId;
        this.accountType = accountType;
        this.customerId = customerId;
        this.staffId = staffId;
        this.acc_status=status;
        this.acc_password=password;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
}
