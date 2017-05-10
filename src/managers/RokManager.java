package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import model.Rok;

public class RokManager extends AllTablesManager {

	private static Logger logger = Logger.getLogger(RokManager.class);

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		logger.info("Process row: " + rs.getInt("id"));
		return(new Rok(rs.getInt("id"), rs.getInt("rok_1"), rs.getInt("rok_2")));
	}

	@SuppressWarnings("unchecked")
	public List<Rok> getAllRok() throws SQLException {
		logger.info("Get all years");
		return(selectQuery("SELECT * FROM rok ORDER BY rok_1"));
	}
	
	@SuppressWarnings("unchecked")
	public List<Rok> getFirstRok() throws SQLException {
		logger.info("Get first year");
		return(selectQuery("SELECT * FROM rok ORDER BY rok_1 LIMIT 1"));
	}
	
	public void insertRok(Rok rok) throws SQLException, ClassNotFoundException {
		logger.info("Insert year: " + rok);
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	    	logger.info("Try connect to" + DB_URL);
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	logger.info("Connected");
			logger.info("Start insert");
			String createStatementString = "INSERT INTO rok (rok_1, rok_2) VALUES(?, ?)";
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			stmt.setInt(1, rok.getRok_1());
			stmt.setInt(2, rok.getRok_2());
			stmt.executeUpdate();
			logger.info("Insert finished");
		}
		catch(SQLException e) {
			if(conn != null) {
				logger.warn("Year already exists");
				//logger.warn(e.getMessage(), e);
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
