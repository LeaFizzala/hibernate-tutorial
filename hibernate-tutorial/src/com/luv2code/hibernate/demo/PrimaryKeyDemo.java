package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

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
					
					//create students objects
					System.out.println("Creating a new student object");
					Student tempStudent = new Student("Paul", "Wall", "paul@luv2code.com");
					System.out.println("Creating a second new student object");
					Student secondStudent = new Student("Marsha", "Jones", "mjones@educba.com");
					Student thirdStudent = new Student("Ada", "Lovelace", "ada.lovelace@gmail.com");
					
					//start a transaction
					session.beginTransaction();
					
					//save the Student object
					System.out.println("Saving the Student object");
					session.save(tempStudent);
					session.save(secondStudent);
					
					// commit transaction
					session.getTransaction().commit();
					
					System.out.println("Done, baby !");
				}
				finally {
					factory.close();
				}

	}

}
