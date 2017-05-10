package managers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import model.Firma;

public class FirmaManager extends AllTablesManager {
	private static Logger logger = Logger.getLogger(FirmaManager.class);

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		logger.info("Process row:" + rs.getInt("id") + rs.getString("login"));
		return(new Firma(rs.getInt("id"), rs.getString("login"), rs.getString("password")));
	}
	
	public List<Firma> getFirmaByLoginPassword(String login, String password) throws SQLException {
		logger.info("Find company by login: " + login);
		return(selectQuery("SELECT * FROM firma WHERE login LIKE '" + login + "' AND password LIKE '" 
	+ password + "'"));
	}
}
