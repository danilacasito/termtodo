package me.elordenador.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            
            connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            Statement statement = connection.createStatement();
            if (TableCheck.checkTable(connection, "todolist")) {
                System.out.println("Table found");
            } else {
                System.err.println("Table NOT Found, creating it");
                statement.executeUpdate("CREATE TABLE todolist (id INTEGER PRIMARY KEY AUTOINCREMENT, item string)");
                
            }
            Menu menu = new Menu(connection);
            while (true) {
                menu.menu();
            }
        } catch (SQLException e){
            System.err.println(e.getMessage());
            System.exit(1);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                System.exit(0);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                System.exit(2);
            }
        }

    }
}