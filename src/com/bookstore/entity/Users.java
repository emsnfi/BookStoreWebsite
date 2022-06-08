package com.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name="users") 
@NamedQueries({
	@NamedQuery(name ="Users.findAll",query="Select u from Users u Order by u.fullname"),
	@NamedQuery(name="Users.countAll",query="Select Count(u) from Users u")
})
public class Users {  // 用複數 而非使用 user 因為 user 是 mysql 的 reserved keyword 所以要避免
	private  Integer userId; // 要 mapping 到 database 的 column 要使用 jpa annotation
	private String email;
	private String fullname;
	private String password;
	// plain old java object class
	// have to use 語法 去建立 ORM 框架 讓 class 可以 match 到 databa se
	

	public Users() { //no parameter contructor
		super();
		// TODO Auto-generated constructor stub
	}
	// 給 create user 的 contructor
	public Users(String email, String fullname, String password) {
		super();
		this.email = email;
		this.fullname = fullname;
		this.password = password;
	}
	@Column(name="user_id") // 如果 field name 跟 java code 取的名字不同 才需要此 JPA annotation
	@Id // 用於此 column 是 primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}
 
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="full_name")
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}



