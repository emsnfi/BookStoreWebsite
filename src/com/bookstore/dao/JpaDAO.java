package com.bookstore.dao;

import javax.persistence.*;

import java.util.List;
import java.util.Map;

// for any type
public class JpaDAO<E> {
	// T stands for "Type"
	
	protected EntityManager entityManager;
	//its instance is injected by the client code such as by the DAO class

	
	// 建立 contractor 可以用 source > contractor 建立 非 default
	public JpaDAO(EntityManager entityManager){
		super(); 
		// 存取 父類別 的成員（資料成員、函數成員、建構元）
		this.entityManager = entityManager;
	}
	
	public E create(E entity) {
		//return entity object
		//使用 Hibernate 運算元據庫，都要開啟事務,得到事務物件
		entityManager.getTransaction().begin();
		
		//開啟事務  
		entityManager.persist(entity);
		
		//
		entityManager.flush();
		
		//
		entityManager.refresh(entity);
		
		//提交事務
		entityManager.getTransaction().commit();
		
		return entity;
	}
	
	public E update (E entity) {
		entityManager.getTransaction().begin();
		
		// merge the new information in the entity object 
		// with the existing information in the database
		entity = entityManager.merge(entity);
		
		// merge 更新完後 提交
		entityManager.getTransaction().commit();
		return entity;
	}
	
	// 找到物件
	public E find(Class<E> type, Object id) {
		E entity = entityManager.find(type, id);
		// 如果沒有找到物件就不用 refresh 了
		if(entity!=null) {
			entityManager.refresh(entity);;
			
		}
		
		return entity;
	}
	// 刪除物件
	public void delete(Class<E> type,Object id) {
		// 因為有修改到內容
		// 所以要更動 transaction 的部分
		entityManager.getTransaction().begin();
		
		// 使用 remove 刪除物件
		// remove 的參數為 物件的 reference 所以要先取出 reference 
		Object reference = entityManager.getReference(type, id);
		entityManager.remove(reference);
		
		entityManager.getTransaction().commit();		
	} 
	//List 物件
	public List<E> findWithNameQuery(String queryName){
		Query query = entityManager.createNamedQuery(queryName);
		return query.getResultList();
		
	}
	// 有要承接 parameter 的 function
//	public List<E> findWithNamedQuery(String queryName, Map<String,Object> parameters){
//		Query query = entityManager.createNamedQuery(queryName);
//		// 使用泛型 因為有可能被其他 class 重複使用
//		
//		query.setParameter(0, query);
//		}
	
	public List<E> findWithNamedQuery(String queryName, String parameterName,Object paramValue){
		Query query = entityManager.createNamedQuery(queryName);
		// 使用泛型 因為有可能被其他 class 重複使用
		
		query.setParameter(parameterName, paramValue);
		return query.getResultList();
	
	}
	
	// count
	public long countWithNamedQuery(String queryName) {
		Query query = entityManager.createNamedQuery(queryName);
		return (long)query.getSingleResult();
	}
	
}
