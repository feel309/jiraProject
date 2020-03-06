package curvc.jira.model;

import java.util.Date;

public class Issue {
	
	private int issue_no;
	private String summary;
	private String assignee;
	private String description;
	private int status;
	private String reg_id;
	private Date reg_dt;	
	private String mod_id;
	private Date mod_dt;
	
	public Issue() {
	
	}

	public Issue(int issue_no, String summary, String assignee, String description, int status, String reg_id,
			Date reg_dt, String mod_id, Date mod_dt) {
		super();
		this.issue_no = issue_no;
		this.summary = summary;
		this.assignee = assignee;
		this.description = description;
		this.status = status;
		this.reg_id = reg_id;
		this.reg_dt = reg_dt;
		this.mod_id = mod_id;
		this.mod_dt = mod_dt;
	}

	public int getIssue_no() {
		return issue_no;
	}

	public void setIssue_no(int issue_no) {
		this.issue_no = issue_no;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReg_id() {
		return reg_id;
	}

	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}

	public Date getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getMod_id() {
		return mod_id;
	}

	public void setMod_id(String mod_id) {
		this.mod_id = mod_id;
	}

	public Date getMod_dt() {
		return mod_dt;
	}

	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
	}

	@Override
	public String toString() {
		return "Issue [issue_no=" + issue_no + ", summary=" + summary + ", assignee=" + assignee + ", description="
				+ description + ", status=" + status + ", reg_id=" + reg_id + ", reg_dt=" + reg_dt + ", mod_id="
				+ mod_id + ", mod_dt=" + mod_dt + "]";
	}
	
	
	
	
	
}
