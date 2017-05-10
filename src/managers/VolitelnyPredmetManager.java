package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import model.VolitelnyPredmet;

public class VolitelnyPredmetManager extends AllTablesManager {
	
	private static Logger logger = Logger.getLogger(VolitelnyPredmetManager.class);
	
	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		logger.info("Process row: " + rs.getInt("id") + " class code: " + rs.getString("kod"));
		return(new VolitelnyPredmet(rs.getInt("id"), rs.getString("kod"), rs.getString("nazov"), rs.getInt("pocet_studentov"),
				rs.getDouble("a"), rs.getDouble("b"), rs.getDouble("c"), rs.getDouble("d"),
				rs.getDouble("e"), rs.getDouble("fx")));
	}

	@SuppressWarnings("unchecked")
	public List<VolitelnyPredmet> getAllVolitelnyPredmet() throws SQLException {
		logger.info("Get all classes");
		return(selectQuery("SELECT * FROM volitelny_predmet ORDER BY nazov"));
	}
	
	public List<VolitelnyPredmet> getAllVolitelnyPredmetByPlan(int planId) throws SQLException {
		logger.info("Get all classes by plan: " + planId);
		return(selectQuery("SELECT volitelny_predmet.id, kod, nazov, pocet_studentov, a, b, c, d, e, fx " +
				"FROM predmet_v_plane JOIN volitelny_predmet ON volitelny_predmet_id = volitelny_predmet.id WHERE plan_id = " + 
				planId));
	}
	
	public void insertVolitelnyPredmet(VolitelnyPredmet volitelnyPredmet) throws SQLException, ClassNotFoundException {
		logger.info("inserting: " + volitelnyPredmet);

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
	    	logger.info("Try connect to" + DB_URL);
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			logger.info("Connected");
			String createStatementString = "INSERT INTO volitelny_predmet(kod, nazov, pocet_studentov, a, b, c, "
					+ "d, e, fx) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			stmt.setString(1, volitelnyPredmet.getKod());
			stmt.setString(2, volitelnyPredmet.getNazov());
			stmt.setInt(3, volitelnyPredmet.getPocet_studentov());
			stmt.setDouble(4, volitelnyPredmet.getA());
			stmt.setDouble(5, volitelnyPredmet.getB());
			stmt.setDouble(6, volitelnyPredmet.getC());
			stmt.setDouble(7, volitelnyPredmet.getD());
			stmt.setDouble(8, volitelnyPredmet.getE());
			stmt.setDouble(9, volitelnyPredmet.getFx());
			logger.info("Prepare statement: " + stmt);
			stmt.executeUpdate();
			logger.info("Executed");
		}
		catch(SQLException e) {
			if(conn != null) {
				logger.error("Class does not exist");
				logger.error(e.getMessage(), e);
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
