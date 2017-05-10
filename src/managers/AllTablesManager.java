package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.*;
//import com.journaldev.log4j.logic.MathUtils;

public abstract class AllTablesManager {
	static final String JDBC_DRIVER = "org.postgresql.Driver";
	static final String DB_URL = "jdbc:postgresql://localhost:5432/student_manager";
	static final String USER = "postgres";
	static final String PASS = "0000";
	private static Logger logger = Logger.getLogger(AllTablesManager.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List selectQuery(String queryString) throws SQLException{
		logger.info("Begin query: " + queryString);
		List result = new LinkedList();
		Connection conn = null;
		Statement stmt = null;
	    try {
	    	logger.info("Try connect to" + DB_URL);
	    	Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			logger.info("Connected");
			stmt = conn.createStatement();
			logger.info("Execute query");
			ResultSet rs = stmt.executeQuery(queryString);
			while(rs.next()){
				result.add(processRow(rs));
			}
			logger.info("Executed");
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} finally {
			logger.info("Close connection");
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	    return result;
	}

	protected abstract Object processRow(ResultSet rs) throws SQLException;
}
