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
		
		//Instructor tempInstructor = new Instructor("Chad", "Darby" , "chad@luv2code.com");
		
		//InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.luv2code.com/youtube","Luv to code !!!");
		
		//tempInstructor.setInstructorDetail(tempInstructorDetail);
		
	
		
		// save the instructor
		// this will ALSO save the instructor_detail object
		// because of CascadeType.ALL
		//System.out.println("Saving instructor: " + tempInstructor);
		
		session.beginTransaction();
		//session.save(tempInstructor);
		
		
		int theId = 4;
		InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
		
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);
		
		System.out.println("the associated instructor: " + tempInstructorDetail.getInstructor());

		//session.delete(tempInstructorDetail);
		
		session.getTransaction().commit();

		
		System.out.println("Done!");
		
	}
	catch (Exception exc) {
		exc.printStackTrace();
	}
	finally {
		// handle connection leak issue
		session.close();
		
		factory.close();
	}
	}
}
