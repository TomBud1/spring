package example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import example.entity.Customer;
import example.entity.Instructor;
import example.entity.InstructorDetail;

public class HibernateOneToOne {
	
	public static void main(String[] args) {
	
	SessionFactory factory = new Configuration()
			 .configure("hibernate.cfg.xml")
			 .addAnnotatedClass(Instructor.class)
			 .addAnnotatedClass(InstructorDetail.class)
			 .buildSessionFactory();
	
	Session session = factory.getCurrentSession();
	
	try {
		
		Instructor tempInstructor = new Instructor("John", "McCarthy" , "john@luv2code.com");
		
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube","Luv 2 code !!!");
		
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		
		session.beginTransaction();
		
		// save the instructor
		// this will ALSO save the instructor_detail object
		// because of CascadeType.ALL
		System.out.println("Saving instructor: " + tempInstructor);
		
		session.save(tempInstructor);
		
		session.getTransaction().commit();
		
		System.out.println("Done!");
		
	}
	finally {
		factory.close();
	}
	}
}
