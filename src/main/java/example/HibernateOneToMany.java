package example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import example.entity.Course;
import example.entity.Customer;
import example.entity.Instructor;
import example.entity.InstructorDetail;

public class HibernateOneToMany {
	
	public static void main(String[] args) {
	
	SessionFactory factory = new Configuration()
			 .configure("hibernate.cfg.xml")
			 .addAnnotatedClass(Instructor.class)
			 .addAnnotatedClass(InstructorDetail.class)
			 .addAnnotatedClass(Course.class)
			 .buildSessionFactory();
	
	Session session = factory.getCurrentSession();
	
	try {
		
//		Instructor tempInstructor = new Instructor("Anne", "Marie" , "anne@luv2code.com");
//		
//		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.annemarie.com/youtube","!!!");
//		
//		tempInstructor.setInstructorDetail(tempInstructorDetail);
//		
//		session.beginTransaction();
//		session.save(tempInstructor);
//		session.getTransaction().commit();
		
//		session.beginTransaction();
//		
//		int theId = 6;
//		Instructor tempInstructor = session.get(Instructor.class, theId);
//		
//		//create some courses 
//		Course tempCourse1 = new Course("Air Guitar - The Ultiamte Guide");
//		Course tempCourse2 = new Course("tHE PINBALL Masterclass");
//		
//		//add courses to instructor
//		tempInstructor.add(tempCourse1);
//		tempInstructor.add(tempCourse2);
//		
//		//save the courses
//		session.save(tempCourse1);
//		session.save(tempCourse2);
//		
//		session.getTransaction().commit();
		
		session.beginTransaction();
		
		int theId = 6;
		Instructor tempInstructor = session.get(Instructor.class, theId);
		
		System.out.println("Instructor: " + tempInstructor);
		
		// get course for the instructor
		System.out.println("Courses: " + tempInstructor.getCourses());
		
		// commit transaction
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
