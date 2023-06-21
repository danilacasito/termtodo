package me.elordenador.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DatabaseMetaData;

public class TableCheck {
    public static boolean checkTable(Connection connection, String table) throws SQLException {
        
        DatabaseMetaData dbm = connection.getMetaData();
        ResultSet tables = dbm.getTables(null, null, table, null);
        if (tables.next()) {
            return true;
        } else {
            return false;
        }
    }
}