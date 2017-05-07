package managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Firma;

public class FirmaManager extends AllTablesManager {

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		return(new Firma(rs.getInt("id"), rs.getString("login"), rs.getString("password")));
	}
	
	public List<Firma> getFirmaByLoginPassword(String login, String password) throws SQLException {
		return(selectQuery("SELECT * FROM firma WHERE login LIKE '" + login + "' AND password LIKE '" 
	+ password + "'"));
	}
}
