/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationfxcovid;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Zaiouane
 */
public class NewHibernateUtil {

   public static  SessionFactory sessionFactory;
	static {
		try {
			// Création de la SessionFactory à partir de hibernate.cfg.xml
			sessionFactory = new Configuration().configure
				("hibernate.cfg.xml").buildSessionFactory();
		} catch (HibernateException ex) {
			System.out.println("Initial SessionFactory creation failed." + ex.getMessage());
			//throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() {  return sessionFactory;    }
}

