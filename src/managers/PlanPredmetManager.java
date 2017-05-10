package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import model.PlanPredmet;
import model.VolitelnyPredmet;

public class PlanPredmetManager extends AllTablesManager {

	private static Logger logger = Logger.getLogger(PlanPredmetManager.class);

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		logger.info("Process row: "+ rs.getInt("id") + " class code" + rs.getString("kod"));
		return(new PlanPredmet(rs.getInt("id"), rs.getInt("semester"), new VolitelnyPredmet(rs.getString("kod"), rs.getString("nazov"), 
				rs.getInt("pocet_studentov"), rs.getDouble("a"), rs.getDouble("b"), rs.getDouble("c"), rs.getDouble("d"),
				rs.getDouble("e"), rs.getDouble("fx"))));
	}

	public List<PlanPredmet> getAllPlanPredmetByPlan(int planId) throws SQLException {
		logger.info("Get class in plan: " + planId);
		return(selectQuery("SELECT predmet_v_plane.id, semester, kod, nazov, pocet_studentov, a, b, c, d, e, fx " +
				"FROM predmet_v_plane JOIN volitelny_predmet ON volitelny_predmet_id = volitelny_predmet.id WHERE plan_id = " + 
				planId));
	}

}
