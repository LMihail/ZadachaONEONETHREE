package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.PreparedStatement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    Session session;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        userDaoJDBC.createUsersTable();
//        Session session = Util.getSessionFactory().openSession();
//        session.beginTransaction();
//        Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS testtable(id int primary key auto_increment, name varchar(40), lastName varchar(40), age int );");
//        query.executeUpdate();
//        session.getTransaction().commit();
//        session.close();
    }

    @Override
    public void dropUsersTable() {
        userDaoJDBC.dropUsersTable();
//        Session session = Util.getSessionFactory().openSession();
//        session.beginTransaction();
//        int hql = session.createSQLQuery("DROP TABLE IF EXISTS testtable;").executeUpdate();
//        session.getTransaction().commit();
//        session.close();
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

//        String hql = "delete User where id = " + id;
//        Query query = session.createSQLQuery(hql);

        Query query = session.createSQLQuery("delete User where id = :param");
        query.setParameter("param", id);

        int result = query.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        session = Util.getSessionFactory().openSession();
        users = session.createQuery("FROM User").list();
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
