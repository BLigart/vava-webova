package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Rok;

public class RokManager extends AllTablesManager {

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		return(new Rok(rs.getInt("id"), rs.getInt("rok_1"), rs.getInt("rok_2")));
	}

	@SuppressWarnings("unchecked")
	public List<Rok> getAllRok() throws SQLException {
		return(selectQuery("SELECT * FROM rok ORDER BY rok_1"));
	}
	
	@SuppressWarnings("unchecked")
	public List<Rok> getFirstRok() throws SQLException {
		return(selectQuery("SELECT * FROM rok ORDER BY rok_1 LIMIT 1"));
	}
	
	public void insertRok(Rok rok) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String createStatementString = "INSERT INTO rok(rok_1, rok_2) VALUES(?, ?)";
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			stmt.setInt(1, rok.getRok_1());
			stmt.setInt(2, rok.getRok_2());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			if(conn != null) {
				System.err.println(e.getMessage());
				System.err.println("rok already exists");
			}
		} finally {
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}	
}
