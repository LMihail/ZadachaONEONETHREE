package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    Util util = new Util();
    Statement statement = null;
    Connection connection = null;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Connection connection = util.setConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE testtable (ID INT  auto_increment primary key, NAME VARCHAR(20), LASTNAME VARCHAR(20), AGE INT )");
            System.out.println("Таблица создана");
        } catch (SQLException throwables) {
            System.out.println("Таблица уже существует");
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        try {
            Connection connection = util.setConnection();
            statement = connection.createStatement();
            statement.execute("TRUNCATE TABLE testtable");
            System.out.println("Таблица удалена");
        } catch (SQLException throwables) {
            System.out.println("Ошибка удаления таблицы");
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT INTO testtable (NAME , LASTNAME, AGE) VALUES (" +
                name + ", " + lastName + ", " + age + ")";
        try {
            Connection connection = util.setConnection();
            statement = connection.createStatement();
            statement.execute(saveUser);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            System.out.println("Ошибка добавления юзера");
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String removeUserById = "DELETE FROM testtable WHERE ID = " +
                id + ")";
        try {
            Connection connection = util.setConnection();
            statement = connection.createStatement();
            statement.executeUpdate(removeUserById);
            System.out.println("Юзер удалён");
        } catch (SQLException throwables) {
            System.out.println("Юзер по данному id не найден");
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = util.setConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM testtable");
            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String lastName = resultSet.getString("LASTNAME");
                byte age = resultSet.getByte("AGE");
                User user = new User(name, lastName, age);
                users.add(user);
            }
            System.out.println("Список юзеров получен");
        } catch (SQLException throwables) {
            System.out.println("Ошибка получения списка юзеров");
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        try {
            Connection connection = util.setConnection();
            statement = connection.createStatement();
            statement.execute("DROP TABLE testtable");
            System.out.println("Таблица очищена");
        } catch (SQLException throwables) {
            System.out.println("Ошибка очистки таблицы");
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
