import com.cellnex.api.sca.config.Constants;

import java.sql.*;

public class DBConnection {

	private Connection conn;
	private Statement stmt;
	
	private String[] connMain = {"jdbc:vertica://172.16.2.85:5433/dbname", "dbadmin", "password"};
	private String[] connSupport = {"jdbc:vertica://172.16.2.85:5433/dbname", "dbadmin", "password"};
	
	public DBConnection(String db) {
		try {

			Class.forName("com.vertica.jdbc.Driver"); // o Class.forName("com.vertica.Driver");

		} catch (ClassNotFoundException e) {

			writeLog(e.getMessage());
		}

		setConnection(db);

		try {

			stmt = conn.createStatement();

		} catch (SQLException se){

			System.out.println("State SQL: " + se.getSQLState());

		}

	}
	
	private void setConnection(String db) {
		try {
			if (!"".equals(Constants.MAIN) && Constants.MAIN.equalsIgnoreCase(db)) {

				conn = DriverManager.getConnection(connMain[0], connMain[1], connMain[2]);

			} else if(!"".equals(Constants.MAIN) && Constants.SUPPORT.equalsIgnoreCase(db)) {

				conn = DriverManager.getConnection(connSupport[0], connSupport[1], connSupport[2]);

			} else {  // Like main

				conn = DriverManager.getConnection(connMain[0], connMain[1], connMain[2]);
			}
		} catch (Exception e) {

			writeLog(e.getMessage());
		}
	}
	
	public ResultSet query(String sql) {

		try {

			return stmt.executeQuery(sql);

		} catch (SQLException x){

			System.out.printf("SQL State: " + x.getSQLState());
		}
	}
	
	public int execute(String sql) {

		try {

			return stmt.executeUpdate(sql);

		} catch (SQLException l){

			System.out.println("State SQL: " + l.getSQLState());
		}


	}
	
	public void close() {
		try {

			conn.close();

		} catch (SQLException e){

			System.out.println("State SQL: " + e.getSQLState());
		}
	}
	
	private void writeLog(String str) {
		System.out.println("Log: " + str);
	}
}
