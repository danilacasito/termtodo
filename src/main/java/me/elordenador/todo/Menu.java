package me.elordenador.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;


public class Menu {
    Connection conn = null;
    Scanner sc;
    public Menu(Connection conn) {
        this.conn = conn;
        this.sc = new Scanner(System.in);
    }
    public void menu() {
        System.out.println("---------------------------------------------");
        System.out.println(" CLI TODO");
        System.out.println("---------------------------------------------");
        System.out.println(" 1. Add Item");
        System.out.println(" 2. List Items");
        System.out.println(" 3. Remove Item");
        System.out.println(" 4. Exit");
        System.out.println("---------------------------------------------");
        System.out.print("Opcion: ");
        String option = sc.nextLine();
        if (option.equals("1")) {
            try {
                Statement statement = this.conn.createStatement();
                String todoitem = sc.nextLine();
                statement.executeUpdate("INSERT INTO todolist (item) VALUES ('"+todoitem+"')");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            
        } else if (option.equals("2")) {
            try {
                Statement statement = this.conn.createStatement();
                System.out.println("---------------------------------------------");
                System.out.println(" Items");
                System.out.println("---------------------------------------------");
                int i = 0;
                ResultSet result = statement.executeQuery("SELECT * FROM todolist");
                while (result.next()) {
                    System.out.println(" "+result.getInt("id")+". "+result.getString("item"));
                }
                System.out.println("---------------------------------------------");
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
            
            
        } else if (option.equals("3")) {
            System.out.println("Pronto");
        } else if (option.equals("4")) {
            System.out.println("Adios!");
            System.exit(0);
        } else {
            System.out.println("Acción desconocida");
        }
    }
}