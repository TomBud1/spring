package example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import example.entity.Customer;

public class HibernateExample {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Customer.class)
								 .buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			// use the session object to save Java object
			
			// create a customer object
			System.out.println("Creating new customer objetct...");
			Customer tempCustomer = new Customer("Mary", "Jane", "maryj@test.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the customer object
			System.out.println("Saving the customer...");
			session.save(tempCustomer);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}
		
	}

}
