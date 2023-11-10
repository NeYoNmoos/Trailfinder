package at.fhv.hike.hibernate.sessionmanager;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@WebListener
public class HibernateContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        sce.getServletContext().setAttribute("SessionFactory", sessionFactory);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        SessionFactory sessionFactory = (SessionFactory) sce.getServletContext().getAttribute("SessionFactory");
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }
}