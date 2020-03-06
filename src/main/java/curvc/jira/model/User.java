package curvc.jira.model;

import java.util.Date;

public class User {
	
	private String user_id;
	private String pw; 
	private String re_pw; 
	private String name; 
	private String email; 
	private char del_yn; 
	private int auth;
	private String reg_id;
	private Date reg_dt;
	private String mod_id;
	private Date mod_dt;
	
	public User() {
	
	}
	
	
	public User(String user_id, String pw, String re_pw, String name, String email, char del_yn, int auth,
			String reg_id, Date reg_dt, String mod_id, Date mod_dt) {
		super();
		this.user_id = user_id;
		this.pw = pw;
		this.re_pw = re_pw;
		this.name = name;
		this.email = email;
		this.del_yn = del_yn;
		this.auth = auth;
		this.reg_id = reg_id;
		this.reg_dt = reg_dt;
		this.mod_id = mod_id;
		this.mod_dt = mod_dt;
	}



	public String getUser_id() {
		return user_id;
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public String getPw() {
		return pw;
	}
	
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	public String getRe_pwd() {
		return re_pw;
	}
	
	public void setRe_pwd(String re_pw) {
		this.re_pw = re_pw;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public char getDel_yn() {
		return del_yn;
	}
	
	public void setDel_yn(char del_yn) {
		this.del_yn = del_yn;
	}
	
	public int getAuth() {
		return auth;
	}
	
	public void setAuth(int auth) {
		this.auth = auth;
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
		return "User [user_id=" + user_id + ", pw=" + pw + ", re_pw=" + re_pw + ", name=" + name + ", email="
				+ email + ", del_yn=" + del_yn + ", auth=" + auth + ", reg_id=" + reg_id + ", reg_dt=" + reg_dt
				+ ", mod_id=" + mod_id + ", mod_dt=" + mod_dt + "]";
	}
	
	
}
