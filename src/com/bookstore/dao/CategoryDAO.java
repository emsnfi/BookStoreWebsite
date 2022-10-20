package com.bookstore.dao;

import java.util.List;
import com.bookstore.entity.*;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import com.bookstore.entity.Category;
public class CategoryDAO extends JpaDAO<Category> implements GenericDAO<Category> {

	public CategoryDAO(EntityManager entityManager) {
		super(entityManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Category create(Category category) {
		// TODO Auto-generated method stub
		return super.create(category);
	}

	@Override
	public Category update(Category category) {
		// TODO Auto-generated method stub
		return super.update(category);
	}

	@Override
	public Category get(Object id) {
		// JPADAO 的 method
		// public E find(Class<E> type, Object id) 
		// TODO Auto-generated method stub
		return super.find(Category.class,id);
	}

	@Override
	public void delete(Object id) {
		
		super.delete(Category.class, id);
	}

	@Override
	// 使用 name query 的方式
	public List<Category> listAll() {
		
		// need to create named query entity
		// @NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c ORDER BY c.name"),
		
		return super.findWithNameQuery("Category.findAll");
	
	
	}

	@Override
	public long count() {
		// count the entity
		return super.countWithNamedQuery("Category.countAll");
		
	}

}
