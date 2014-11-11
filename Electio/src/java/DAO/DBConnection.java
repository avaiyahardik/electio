package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String DB_URL = "jdbc:mysql://localhost:3306/db_electio?zeroDateTimeBehavior=convertToNull";
    //private static String DB_URL = "jdbc:mysql://db4free.net/electio";
    private static String DB_User = "root";
    //private static String DB_User = "electio";
    private static String DB_Pwd = "";
    //private static String DB_Pwd = "#password2014";
    private static String DB_Driver = "com.mysql.jdbc.Driver";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(DB_Driver);
            con = DriverManager.getConnection(DB_URL, DB_User, DB_Pwd);
            System.out.println("Database Connected");
        } catch (ClassNotFoundException e) {
            System.out.println("Mysql Driver not found");
        } catch (SQLException e) {
            System.out.println("DB Connection could not be established");
        }
        return con;
    }
}
