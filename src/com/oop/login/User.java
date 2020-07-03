package com.oop.login;

public class User {
		
		private String email;
		private String pass;
		private String confpass;
		private String type;
		private String id;
		
	
		
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}
		public String getConfpass() {
			return confpass;
		}
		public void setConfpass(String confpass) {
			this.confpass = confpass;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		public User(String email, String pass, String confpass, String type, String id) {
			super();
			this.email = email;
			this.pass = pass;
			this.confpass = confpass;
			this.type = type;
			this.id = id;
		}
		
		public User(String type) {
			super();
			this.type = type;
		}
		
		
		
		
		
		
		
		
		
		
		

}
