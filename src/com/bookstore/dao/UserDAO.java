package com.bookstore.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.bookstore.entity.Users;

// Users 為一個 type 
public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {
	
	// 因為 jpadao 沒有 default coontractor 所以要新增
	public UserDAO(EntityManager entityManager) {
		super(entityManager);
	}
	// implement genericDAO
	
	// 沒有 create function，因為 JPA 已經 implement create method
	
	public Users create(Users user) {
		return super.create(user);
	}
	
	@Override
	public Users update(Users user) {
		// TODO Auto-generated method stub
		// return jpadao class update 的值
		return super.update(user);
	}

	@Override
	public Users get(Object userId) {
		// TODO Auto-generated method stub
	
		return super.find(Users.class, userId);
	}

	@Override
	public void delete(Object userId) {
		// TODO Auto-generated method stub
		super.delete(Users.class, userId);
	}

	@Override
	public List<Users> listAll() {
		// TODO Auto-generated method stub
		// 放 User.java 中 要使用的 namequery 的 name
		return super.findWithNameQuery("Users.findAll");
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
//		long result = super.findWithNameQuery("Users.countAll");
		return super.countWithNamedQuery("Users.countAll");
	}
 
	
	
}
