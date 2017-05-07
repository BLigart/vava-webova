package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.VolitelnyPredmet;

public class VolitelnyPredmetManager extends AllTablesManager {
	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		return(new VolitelnyPredmet(rs.getInt("id"), rs.getString("kod"), rs.getString("nazov"), rs.getInt("pocet_studentov"),
				rs.getDouble("a"), rs.getDouble("b"), rs.getDouble("c"), rs.getDouble("d"),
				rs.getDouble("e"), rs.getDouble("fx")));
	}

	@SuppressWarnings("unchecked")
	public List<VolitelnyPredmet> getAllVolitelnyPredmet() throws SQLException {
		return(selectQuery("SELECT * FROM volitelny_predmet ORDER BY nazov"));
	}
	
	public List<VolitelnyPredmet> getAllVolitelnyPredmetByPlan(int planId) throws SQLException {
		return(selectQuery("SELECT volitelny_predmet.id, kod, nazov, pocet_studentov, a, b, c, d, e, fx " +
				"FROM predmet_v_plane JOIN volitelny_predmet ON volitelny_predmet_id = volitelny_predmet.id WHERE plan_id = " + 
				planId));
	}
	
	public void insertVolitelnyPredmet(VolitelnyPredmet volitelnyPredmet) throws SQLException, ClassNotFoundException {
		System.out.println("inserting: " + volitelnyPredmet);

		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			if(conn != null) {
				System.err.println(e.getMessage());
				System.err.println("predmet already exists");
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
