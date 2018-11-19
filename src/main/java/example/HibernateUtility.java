package example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

import org.hibernate.Session;
import example.entity.Customer;
import org.hibernate.cfg.Configuration;


public class HibernateUtility {
	
	//create session factory
	private SessionFactory factory = new Configuration()
							 .configure("hibernate.cfg.xml")
							 .addAnnotatedClass(Customer.class)
							 .buildSessionFactory();
	
	//create session
	private Session session = factory.getCurrentSession();
	
	public List<Customer> getAll(){
		
		session = factory.getCurrentSession();
		
		//start a transaction
		session.beginTransaction();
		
		//query customers
		List<Customer> customers = session.createQuery("from Customer").getResultList();
		
		return customers; 
	}
	
	public Customer getById(int id) {
		
		session = factory.getCurrentSession();
		
		//retrieve student based on the id:primary key
		System.out.println("\nGetting student with id: " + id);
		
		session.beginTransaction();
		
		Customer tempCustomer = session.get(Customer.class, id);
		
		session.getTransaction().commit();
		
		System.out.println("Get complete:");
		System.out.println(tempCustomer.toString());
		System.out.println("Done!");
				
		return tempCustomer;
	}
	
	public List<Customer> getWhere(String column, String key) {
		
		session = factory.getCurrentSession();
		
		session.beginTransaction();
		
		String query = new String();
		query = "from Customer c where c." + column + "='" + key + "'";
		
		List<Customer> customers = session.createQuery(query).getResultList();
		
		return customers;
		
	}
	
	
	public void createNew(Customer tempCustomer) {
		
		// use the session object to save Java object
		session = factory.getCurrentSession();
	
		// create a customer object
		System.out.println("Creating new customer object...");
		
		// start a transaction
		session.beginTransaction();
		
		// save the customer object
		System.out.println("Saving the customer...");
		session.save(tempCustomer);
		
		// commit transaction
		session.getTransaction().commit();
		
		System.out.println("Done!");				
	}
	
	public void update(String column, String key) {
		
		session = factory.getCurrentSession();
		
		System.out.println("Update for customers");
		
		String query = new String();
		query="update Student set " + column + "='" + key + "'";
		
		session.beginTransaction();
		
		session.createQuery(query).executeUpdate();
				
		
	}
	
	public void update(String column, String key, String where, String value) {
		
		session = factory.getCurrentSession();
		
		System.out.println("Update for customers");
		
		String query = new String();
		query ="update Customer set " + column + "='" + key + "' " + "where " + where + "='" + value + "'";
		
		session.beginTransaction();
		
		session.createQuery(query).executeUpdate();
		
		
	}
	
	public void deleteById(int id) {
		
		Customer deleteCustomer = session.get(Customer.class, id);
		session.delete(deleteCustomer);
		
		//query="delete from Customer where id=" + id;
		//session.createQuery(query).executeUpdate();
		
		session.getTransaction().commit();
		
	}

	public void factoryClose() {
		session.flush();
		session.close();
		factory.close();
	}

}
