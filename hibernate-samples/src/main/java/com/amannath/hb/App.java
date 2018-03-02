package com.amannath.hb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public static void main(String[] args) {
        System.out.println("Maven + Hibernate + Oracle");
        Session session = getSessionFactory().openSession();

        session.beginTransaction();
        Employee e = new Employee();

        e.setId(100);
        e.setName("ABCDE");
        e.setAge(40);

        session.save(e);
        session.getTransaction().commit();
    }

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
