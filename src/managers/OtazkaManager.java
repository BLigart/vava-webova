package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Otazka;

public class OtazkaManager extends AllTablesManager {

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		return(new Otazka(rs.getInt("id"), rs.getString("otazka"), rs.getString("odpoved"), 
				rs.getInt("test_id")));
	}
	
	@SuppressWarnings("unchecked")
	public List<Otazka> getAllOtazkaFromTest(int test_id) throws SQLException {
		return(selectQuery("SELECT * FROM otazka WHERE test_id = " + test_id + " ORDER BY id"));
	}
	
	public void insertOtazka(Otazka otazka) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			String createStatementString = "INSERT INTO otazka(otazka, odpoved, test_id) VALUES(?, ?, ?)";
			stmt = (PreparedStatement) conn.prepareStatement(createStatementString);
			stmt.setString(1, otazka.getOtazka());
			stmt.setString(2, otazka.getOdpoved());
			stmt.setInt(3, otazka.getTest_id());
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			if(conn != null) {
				System.err.println(e.getMessage());
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
