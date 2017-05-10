package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import model.Otazka;

public class OtazkaManager extends AllTablesManager {

	private static Logger logger = Logger.getLogger(OtazkaManager.class);

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		logger.info("Process row: " + rs.getInt("id") + ": " + rs.getString("otazka") + " test id: " + rs.getInt("test_id"));
		return(new Otazka(rs.getInt("id"), rs.getString("otazka"), rs.getString("odpoved"), 
				rs.getInt("test_id")));
	}
	
	@SuppressWarnings("unchecked")
	public List<Otazka> getAllOtazkaFromTest(int test_id) throws SQLException {
		logger.info("Get whole test: " + test_id);
		return(selectQuery("SELECT * FROM otazka WHERE test_id = " + test_id + " ORDER BY id"));
	}
	
	public void insertOtazka(Otazka otazka) throws SQLException, ClassNotFoundException {
		logger.info("Insert question");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	    	logger.info("Try connect to" + DB_URL);
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String createStatementString = "INSERT INTO otazka(otazka, odpoved, test_id) VALUES(?, ?, ?)";
			logger.info("Prepared statement: " + createStatementString);
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			stmt.setString(1, otazka.getOtazka());
			stmt.setString(2, otazka.getOdpoved());
			stmt.setInt(3, otazka.getTest_id());
			logger.info("Execute insert");
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			logger.warn(e.getMessage(), e);
			if(conn != null) {
				System.err.println(e.getMessage());
			}
		} finally {
			logger.info("Close connection");
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}
}
