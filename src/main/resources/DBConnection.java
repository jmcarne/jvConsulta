import java.sql.*;

public class DBConnection {

	Connection conn;
	Statement stmt;
	
	String[] connMain = {"jdbc:vertica://localhost:5433/dbname", "dbadmin", "pass"};
	String[] connSupport = {"jdbc:vertica://localhost:5433/dbname", "dbadmin", "pass"};
	
	public DBConnection(String db) {
		try {
			Class.forName("com.vertica.jdbc.Driver"); // o Class.forName("com.vertica.Driver");
		} catch (ClassNotFoundException e) {
			writeLog();
		}
		setConnection(String db);
		stmt = conn.createStatement();
	}
	
	private void setConnection(String db) {
		try {
			if (db == 'main') {
				conn = DriverManager.getConnection(connMain[0], connMain[1], connMain[2]);
			} else if(db == 'support') {
				conn = DriverManager.getConnection(connSupport[0], connSupport[1], connSupport[2]);
			} else {  // Like main
				conn = DriverManager.getConnection(connMain[0], connMain[1], connMain[2]);
			}
		} catch (Exception e) {
			writeLog(e);
		}
	}
	
	public ResultSet query(String sql) {
		return stmt.executeQuery(sql);
	}
	
	public int execute(String sql) {
		return stmt.executeUpdate(sql);
	}
	
	public void close() {
		conn.close();
	}
	
	private void writeLog(String str) {	}
}
