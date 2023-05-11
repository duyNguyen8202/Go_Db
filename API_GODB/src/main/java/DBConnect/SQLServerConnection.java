package DBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;;

public class SQLServerConnection {
	
	public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
	    String dbDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    String dbURL = "jdbc:sqlserver://localhost:1433";
	    String dbName = "LTDD";
	    String dbUsername = "sa";
	    String dbPassword = "123456";
	    String connectionURL = dbURL + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
	    Connection conn = null;
	    try {
	        Class.forName(dbDriver);
	        conn = DriverManager.getConnection(connectionURL, dbUsername, dbPassword);
	        System.out.println("Connected to database successfully!");
	    } catch (ClassNotFoundException ex) {
	        System.out.println("Could not find database driver class: " + ex.getMessage());
	        ex.printStackTrace();
	    } catch (SQLException ex) {
	        System.out.println("Error connecting to database: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	    return conn;
	}
}
