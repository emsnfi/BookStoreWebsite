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
		
		return super.update(category);
	}

	@Override
	public Category get(Object id) { // should be intger
		// JPADAO 的 method
		// public E find(Class<E> type, Object id) 

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
		
		return super.findWithNamedQuery("Category.findAll");
	
	
	}

	@Override
	public long count() {
		// count the entity
		return super.countWithNamedQuery("Category.countAll");
		
	}
	
	public Category findByName(String categoryName) { 
		
		// need to create the finding method in the entity method
		// java name query
		// @NamedQuery(name = "Category.findByName", query = "SELECT c FROM Category c WHERE c.name = :name")
		// in category entity class
		// :name 表示 parameter of name
		
		// call super class (JPA dao) 中 find with name query
		// public List<E> findWithNamedQuery(String queryName, String parameterName,Object paramValue)
		List<Category> result = super.findWithNamedQuery("Category.findByName","name",categoryName);
		
		if(result != null && result.size() > 0) { // if yes 
			return result.get(0);
		}
		
		return null;
	}


}
