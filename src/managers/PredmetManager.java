package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import model.Predmet;

public class PredmetManager extends AllTablesManager {

	private static Logger logger = Logger.getLogger(PredmetManager.class);

	protected Predmet processRow(ResultSet rs) throws SQLException {
		logger.info("Process row: " + rs.getInt("id"));
		return(new Predmet(rs.getInt("id"), rs.getString("kod"), rs.getString("nazov")));
	}

	@SuppressWarnings("unchecked")
	public List<Predmet> getAllPredmet() throws SQLException {
		logger.info("Get all classes");
		return(selectQuery("SELECT * FROM predmet ORDER BY name"));
	}
	
	@SuppressWarnings("unchecked")
	public List<Predmet> getPredmet(String kod) throws SQLException {
		logger.info("Get class by code: " + kod);
		return(selectQuery("SELECT * FROM predmet WHERE kod = '" + kod + "'"));
	}

	public void insertPredmet(Predmet predmet) throws SQLException, ClassNotFoundException {
		logger.info("Insert class: " + predmet);

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	    	logger.info("Try connect to" + DB_URL);
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
	    	logger.info("Connected");
			logger.info("Start insert");
			String createStatementString = "INSERT INTO predmet(kod, nazov) VALUES(?, ?)";
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			stmt.setString(1, predmet.getKod());
			stmt.setString(2, predmet.getNazov());
			stmt.executeUpdate();
			logger.info("Insert finished");
		}
		catch(SQLException e) {
			if(conn != null) {
				logger.warn("Class already exists");
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
