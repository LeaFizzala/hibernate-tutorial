package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class ReadStudent {

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
					
					//create a student object
					System.out.println("Creating a new student object");
					Student sillyStudent = new Student("Daffy", "Duck", "dduck@luv2code.com");
					
					//start a transaction
					session.beginTransaction();
					
					//save the Student object
					System.out.println("Saving the Student object");
					session.save(sillyStudent);
					
					
					// commit transaction
					session.getTransaction().commit();
					
					//find out the student's id: primary key
					System.out.println("Saved student. Generated ID: " + sillyStudent.getId());
					
					//get a new session and start transaction
					session = factory.getCurrentSession();
					session.beginTransaction();
					
					//retrieve student based on the id primary key
					System.out.println("Getting student with id: " + sillyStudent.getId());
					
					Student myStudent = session.get(Student.class, sillyStudent.getId());
					
					System.out.println("Get full student : " + myStudent);
					
					//commit the transaction (in Hibernate you always have to use Transactions to create AND read)
					session.getTransaction().commit();
					System.out.println("Done, baby !");
				}
				finally {
					factory.close();
				}
	}

}
