package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {
			//use the session object to save Java objects
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").list();
			
			//display the students
			displayStudents(theStudents);
			
			//query the students: lastName='Doe'
			theStudents = session.createQuery("from Student s where s.lastName='Wall'").list();
			
			//display the Students
			System.out.println("--------------------- AND THEN ------------------");
			
			displayStudents(theStudents);
			
			theStudents = session.createQuery("from Student s where s.lastName like 'J%'").list();
			
			//display the Students
			System.out.println("--------------------- AND THEN ------------------\n");
			
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done, baby !");
		}
		finally {
			factory.close();
		}
		
		
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
