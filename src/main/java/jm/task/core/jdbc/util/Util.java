package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/store?serverTimezone=Europe/Moscow";
    private static final String NAME = "Slavik";
    private static final String PASSWORD = "1e2e3e4e";
    private static SessionFactory sessionFactory;
    public Util() {

    }
    public static Connection connection() throws  SQLException {
        return DriverManager.getConnection(URL, NAME, PASSWORD);
    }
    public static Session getSession(){
        Configuration config = new Configuration()
                .addAnnotatedClass(User.class)
                .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.connection.username", NAME)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.connection.autocommit", "true")
                .setProperty("hibernate.connection.show_sql", "true");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
        sessionFactory = config.buildSessionFactory(serviceRegistry);
        return sessionFactory.openSession();
    }
    public static void closeHibernate(Session session){
        session.flush();
        session.close();
        sessionFactory.close();
    }
}
