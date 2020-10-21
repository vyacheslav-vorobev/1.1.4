package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS `store`.`user` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NOT NULL,\n" +
                "  `lastName` VARCHAR(45) NOT NULL,\n" +
                "  `age` INT(45) NOT NULL,\n" +
                "  PRIMARY KEY (`id`, `name`, `lastName`, `age`));").executeUpdate();
        transaction.commit();
        Util.closeHibernate(session);
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("drop table if exists user").executeUpdate();
        transaction.commit();
        Util.closeHibernate(session);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        Util.closeHibernate(session);
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        User user = (User)session.load(User.class, id);
        session.delete(user);
        transaction.commit();
        Util.closeHibernate(session);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> users = criteria.list();
        Util.closeHibernate(session);
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE store.user;").executeUpdate();
        session.getTransaction().commit();
        Util.closeHibernate(session);
    }
}
