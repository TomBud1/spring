package example;

import example.entity.Customer;

public class HibernateExample {

	public static void main(String[] args) {
		
		HibernateUtility hibernateUtility= new HibernateUtility();
		
		try {
			Customer tempCustomer = new Customer("Alfred", "Gromee", "alfred@test.com");
			hibernateUtility.createNew(tempCustomer);
			hibernateUtility.getById(tempCustomer);
		}
		finally {
			hibernateUtility.factoryClose();
		}
		
	}

}
