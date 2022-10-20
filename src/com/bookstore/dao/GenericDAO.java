

package com.bookstore.dao;

import java.util.List;
// change T to E
public interface GenericDAO<E> {

	// entity
	public E create(E t);
	
	public E update(E t);
	
	public E get(Object id);
	
	public void delete(Object id);
	
	public List<E> listAll();
	 
	public long count();
	
}
