package Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Load hibernate.cfg.xml configuration
            Configuration configuration = new Configuration().configure();
            
            // Build SessionFactory
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            
            // Log success
            System.out.println("Hibernate SessionFactory created successfully.");
            
            return sessionFactory;
        } catch (Throwable ex) {
            // Log error
            System.err.println("Initial SessionFactory creation failed." + ex);
            
            // Throw exception
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
