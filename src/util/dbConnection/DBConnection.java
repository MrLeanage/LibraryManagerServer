package util.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conn;

    /*
     * Singleton design pattern is implemented to avoid creation of multiple instances of DBConnetion
     */
    private DBConnection() {
    }

    /*
     * initiates a new connection to database or returns the existing connection
     * @param {}
     * @returns java.sql.Connection
     */

    public static Connection getDBConnection(){

        if (conn == null) {
            DBConnection.setConnection();
        }
        return conn;
    }

    /*
     * initiates a new connection to database
     * @param {}
     * @returns void
     */
    private static void setConnection(){
        /*
         * local mysql database credentials
         */
        String url = "jdbc:mysql://localhost/";
        String dbname = "library_manager";
        String ssl = "?useSSL=false";
        String username = "root";
        String password = "root";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url + dbname + ssl, username, password);
        }catch(SQLException | ClassNotFoundException exception ){

        }

    }
}
