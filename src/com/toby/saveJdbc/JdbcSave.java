package com.toby.saveJdbc;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.toby.model.Student;


public class JdbcSave {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
		
		Session session = null;

		try {
			String studentId = "1";
			
			session = sessionFactory.getCurrentSession();
			
			session.beginTransaction();
									
			Student student = session.get(Student.class, studentId);
			
			if(student != null) {
				session.delete(student);
			}
			
			session.getTransaction().commit();	
			
			session = sessionFactory.getCurrentSession();

			session.beginTransaction();
			
			session.createQuery("Delete from Student s where s.ID = 2").executeUpdate();
			
			session.getTransaction().commit();	

		}finally {
			sessionFactory.close();
		}
	
	}

}
