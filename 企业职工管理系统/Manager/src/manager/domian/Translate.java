package manager.domian;

import java.sql.Date;

public class Translate {
	private int TranslateNO; //记录编号
	private String EmployeeID;//员工编号、
	private Date TranslateDate; //调动时间
	private String PriorBranch; //前部门
	private String NextBranch; //现部门
	private String PriorDuty; //前职务
	private String NextDuty; //现职务
	private String HandleName;//经办人
	private String Remark;//备注
	public Translate(int translateNO, String employeeID, Date translateDate,
			String priorBranch, String nextBranch, String priorDuty,
			String nextDuty, String handleName, String remark) {
		super();
		TranslateNO = translateNO;
		EmployeeID = employeeID;
		TranslateDate = translateDate;
		PriorBranch = priorBranch;
		NextBranch = nextBranch;
		PriorDuty = priorDuty;
		NextDuty = nextDuty;
		HandleName = handleName;
		Remark = remark;
	}
	public Translate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getTranslateNO() {
		return TranslateNO;
	}
	public void setTranslateNO(int translateNO) {
		TranslateNO = translateNO;
	}
	public String getEmployeeID() {
		return EmployeeID;
	}
	public void setEmployeeID(String employeeID) {
		EmployeeID = employeeID;
	}
	public Date getTranslateDate() {
		return TranslateDate;
	}
	public void setTranslateDate(Date translateDate) {
		TranslateDate = translateDate;
	}
	public String getPriorBranch() {
		return PriorBranch;
	}
	public void setPriorBranch(String priorBranch) {
		PriorBranch = priorBranch;
	}
	public String getNextBranch() {
		return NextBranch;
	}
	public void setNextBranch(String nextBranch) {
		NextBranch = nextBranch;
	}
	public String getPriorDuty() {
		return PriorDuty;
	}
	public void setPriorDuty(String priorDuty) {
		PriorDuty = priorDuty;
	}
	public String getNextDuty() {
		return NextDuty;
	}
	public void setNextDuty(String nextDuty) {
		NextDuty = nextDuty;
	}
	public String getHandleName() {
		return HandleName;
	}
	public void setHandleName(String handleName) {
		HandleName = handleName;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	
    
}
