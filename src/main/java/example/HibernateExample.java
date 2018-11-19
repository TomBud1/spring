package example;

import java.util.List;

import example.entity.Customer;

public class HibernateExample {

	public static void main(String[] args) {
		
		HibernateUtility hibernateUtility= new HibernateUtility();
		
		try {
//			Customer tempCustomer = new Customer("Alfred", "Gromee", "alfred@test.com");
//			hibernateUtility.createNew(tempCustomer);
//			hibernateUtility.getById(8);
//			List<Customer> theCustomers = hibernateUtility.getAll();
						
//			List<Customer> theCustomers = hibernateUtility.getWhere("email", "jerryt@test.com");
//			for(Customer tempCustomer : theCustomers) {
//				System.out.println(tempCustomer);
//			}
//			System.out.println("Done!");
			
			//hibernateUtility.update("last_name" , "Tom" , "last_name" , "Beck");
			
			List<Customer> theCustomers = hibernateUtility.getAll();
			for(Customer tempCustomer : theCustomers) {
				System.out.println(tempCustomer);
			}
			System.out.println("Done!");
			
			hibernateUtility.deleteById(2);
			

			theCustomers = hibernateUtility.getAll();
			for(Customer tempCustomer : theCustomers) {
				System.out.println(tempCustomer);
			}
			System.out.println("Done!");
			
			
		}
		finally {
			hibernateUtility.factoryClose();
		}
		
	}

}
