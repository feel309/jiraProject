package curvc.jira.model;

import java.util.Date;

public class FileVO {
	
	private int file_id;
	private int issue_no;
	private String ori_name;
	private String st_name;
	private int file_size;
	private String file_type;
	private String reg_id;
	private Date reg_dt;	
	private String mod_id;
	private Date mod_dt;
	
	public int getFile_id() {
		return file_id;
	}
	
	public void setFile_id(int file_id) {
		this.file_id = file_id;
	}
	
	public int getIssue_no() {
		return issue_no;
	}
	
	public void setIssue_no(int issue_no) {
		this.issue_no = issue_no;
	}
	
	public String getOri_name() {
		return ori_name;
	}
	
	public void setOri_name(String ori_name) {
		this.ori_name = ori_name;
	}
	
	public String getSt_name() {
		return st_name;
	}
	
	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}
	
	public int getFile_size() {
		return file_size;
	}
	
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	
	public String getFile_type() {
		return file_type;
	}
	
	public void setFile_type(String file_type) {
		this.file_type = file_type;
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
		return "File [file_id=" + file_id + ", issue_no=" + issue_no + ", ori_name=" + ori_name + ", st_name=" + st_name
				+ ", file_size=" + file_size + ", file_type=" + file_type + ", reg_id=" + reg_id + ", reg_dt=" + reg_dt
				+ ", mod_id=" + mod_id + ", mod_dt=" + mod_dt + "]";
	}
	
	
	
}
