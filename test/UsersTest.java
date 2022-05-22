import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.bookstore.entity.*;

import javax.persistence.*;
import javax.persistence.EntityManagerFactory;


public class UsersTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Users user1 = new Users();
		user1.setEmail("name@codejava.net");
		user1.setFullname("Emily");
		user1.setPassword("helloworld");
		
		
		// 建立 entitymanager factory
		// 建立 entity manager
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager =entityManagerFactory.createEntityManager();
		
		
		// start transaction
		entityManager.getTransaction().begin();	
		
		// persist user object
		entityManager.persist(user1);
		
		// commit the transaction
		entityManager.getTransaction().commit();
		
		//先 close entityManager 在 close entityManagerFactory
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("A users object was persisted ");
		
		
}
	

}


