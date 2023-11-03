package tests;

import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.hibernate.cfg.Configuration;

public class HibernateTest {
    public static void main(String[] args) {
        // Configure Hibernate and build the SessionFactory
        SessionFactory sessionFactory = new Configuration().configure("hibernate-cfg.xml").buildSessionFactory();

        // Open a session, this will initialize Hibernate and connect to the database
        Session session = sessionFactory.openSession();

        // If there are no exceptions until this point, Hibernate is configured properly
        System.out.println("Hibernate is configured properly!");

        // Close the session and SessionFactory
        session.close();
        sessionFactory.close();
    }
}

