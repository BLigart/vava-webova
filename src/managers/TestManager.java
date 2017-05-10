package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import model.Test;

public class TestManager extends AllTablesManager {

	private static Logger logger = Logger.getLogger(TestManager.class);

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		logger.info("Process row" + rs.getInt("id") + " class: " + rs.getInt("predmet_id"));
		return(new Test(rs.getInt("id"), rs.getInt("predmet_id"), rs.getInt("rok_id"), rs.getInt("poradie")));
	}

	@SuppressWarnings("unchecked")
	public List<Test> getSelectedTests(int predmet_id, int rok_id) throws SQLException {
		logger.info("Get test: " + predmet_id);
		return(selectQuery("SELECT * FROM test WHERE predmet_id = " + predmet_id + 
				" AND rok_id = " + rok_id + " ORDER BY poradie"));
	}
	
	public void insertTest(Test test) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	    	logger.info("Try connect to" + DB_URL);
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			logger.info("Connected");
			logger.info("Begin insert");
			String createStatementString = "INSERT INTO test(predmet_id, rok_id, poradie) VALUES(?, ?, ?)";
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			stmt.setInt(1, test.getPredmet_id());
			stmt.setInt(2, test.getRok_id());
			stmt.setInt(3, test.getPoradie());
			stmt.executeUpdate();
			logger.info("Insert finished");
		}
		catch(SQLException e) {
			if(conn != null) {
				logger.warn("Test already exists");
				logger.warn(e.getMessage(), e);
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
