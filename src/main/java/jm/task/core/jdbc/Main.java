package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        User one = new User("One", "Onovich", (byte) 18);
        User two = new User("Two", "Twonovich", (byte) 20);
        User three = new User("Three", "Threenovich", (byte) 21);
        User four = new User("Four", "Fourovich", (byte) 22);

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser(one.getName(), one.getLastName(), one.getAge());
        userService.saveUser(two.getName(), two.getLastName(), two.getAge());
        userService.saveUser(three.getName(), three.getLastName(), three.getAge());
        userService.saveUser(four.getName(), four.getLastName(), four.getAge());
        List<User> list = userService.getAllUsers();
        for (User user : list) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();



    }
}
