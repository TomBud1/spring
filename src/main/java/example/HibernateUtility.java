package example;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
	
	// use the session object to save Java object
	
	public void createNew(Customer tempCustomer) {
		
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
	
	public Customer getById(Customer tempCustomer) {
	
		session = factory.getCurrentSession();
		
		//retrieve student based on the id:primary key
		System.out.println("\nGetting student with id: " + tempCustomer.getId());
		
		session.beginTransaction();
		
		Customer myCustomer = session.get(Customer.class, tempCustomer.getId());
		
		session.getTransaction().commit();
		
		System.out.println("Get complete:");
		System.out.println(myCustomer.toString());
		System.out.println("Done!");
				
		return myCustomer;
	}

	public void factoryClose() {
		factory.close();
	}

}
