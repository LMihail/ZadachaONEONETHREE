package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    Session session;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS testtable(ID int primary key auto_increment, NAME varchar(40), LASTNAME varchar(40), AGE int );");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        int hql = session.createSQLQuery("DROP TABLE IF EXISTS testtable;")
                .executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("delete from testtable where id = :param");
        query.setParameter("param", id);
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        Session session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User");
        users = query.list();
        session.getTransaction().commit();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        session = Util.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createSQLQuery("TRUNCATE TABLE testtable");
        int result = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
