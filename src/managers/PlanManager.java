package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import model.Plan;
import model.PlanPredmet;
import model.VolitelnyPredmet;

public class PlanManager extends AllTablesManager {

	private static Logger logger = Logger.getLogger(PlanManager.class);

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		logger.info("Process row: " + rs.getInt("id"));
		return(new Plan(rs.getInt("id"), rs.getString("creator_name"), rs.getString("nazov")));
	}
	
	@SuppressWarnings("unchecked")
	public List<Plan> getAllStudentPlan() throws SQLException {
		logger.info("Get all student plans");
		return(selectQuery("SELECT plan.id, creator_name, nazov FROM plan LEFT JOIN firma ON "
				+ "creator_name LIKE login WHERE firma.id IS NULL"));
	}
	
	@SuppressWarnings("unchecked")
	public List<Plan> getAllFirmaPlan() throws SQLException {
		logger.info("Get all company plans");
		return(selectQuery("SELECT plan.id, creator_name, nazov FROM plan JOIN firma ON creator_name "
				+ "LIKE login"));
	}
	
	public List<Plan> getAllPlanByAutor(String autor) throws SQLException {
		logger.info("Get all plans by author: " + autor);
		return(selectQuery("SELECT plan.id, creator_name, nazov FROM plan WHERE creator_name LIKE '" 
	+ autor + "'"));
	}
	
	public List<Plan> getAllStudentPlanWithVolitelnyPredmet() throws SQLException {
		logger.info("Get all student plans with classes");
		List<Plan> plany = getAllStudentPlan();
		PlanPredmetManager planPredmetManager = new PlanPredmetManager();
		for(Plan plan : plany) {
			List<PlanPredmet> planPredmety = planPredmetManager.getAllPlanPredmetByPlan(plan.getId());
			for(PlanPredmet planPredmet : planPredmety) {
				plan.addPlanPredmet(planPredmet);
			}
		}
		return plany;
	}
	
	public List<Plan> getAllFirmaPlanWithVolitelnyPredmet() throws SQLException {
		logger.info("Get all company plans with classes");
		List<Plan> plany = getAllFirmaPlan();
		PlanPredmetManager planPredmetManager = new PlanPredmetManager();
		for(Plan plan : plany) {
			List<PlanPredmet> planPredmety = planPredmetManager.getAllPlanPredmetByPlan(plan.getId());
			for(PlanPredmet planPredmet : planPredmety) {
				plan.addPlanPredmet(planPredmet);
			}
		}
		return plany;
	}

	public List<Plan> getAllPlanByAutorWithVolitelnyPredmet(String autor) throws SQLException {
		logger.info("Get all plans by author with classes");
		List<Plan> plany = getAllPlanByAutor(autor);
		PlanPredmetManager planPredmetManager = new PlanPredmetManager();
		for(Plan plan : plany) {
			List<PlanPredmet> planPredmety = planPredmetManager.getAllPlanPredmetByPlan(plan.getId());
			for(PlanPredmet planPredmet : planPredmety) {
				plan.addPlanPredmet(planPredmet);
			}
		}
		return plany;
	}

	public void insertPlan(String nazov, String creator_name, ArrayList<PlanPredmet> predmety_v_plane) {
		logger.info("Insert plan: " + nazov);
		Connection conn = null;
		ArrayList<PreparedStatement> stmt = new ArrayList<PreparedStatement>();
		ArrayList<String> createStatementString = new ArrayList<String>();
		try {
	    	logger.info("Try connect to" + DB_URL);
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage(), e);
			}
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(false);
	    	logger.info("Connected");
			
			createStatementString.add("INSERT INTO plan(nazov, creator_name) VALUES(?, ?)");
			stmt.add((PreparedStatement) conn.prepareStatement(createStatementString.get(0)));
			stmt.get(0).setString(1, nazov);
			stmt.get(0).setString(2, creator_name);
			stmt.get(0).executeUpdate();
			logger.info("Start inserting plan and classes");
			Statement stmtPlan = conn.createStatement();
			ResultSet rs = stmtPlan.executeQuery("SELECT * FROM plan WHERE nazov LIKE '" + nazov + "'");
			List<Plan> plany = new LinkedList<Plan>();
			while(rs.next()){
				plany.add((Plan) processRow(rs));
			}
			
			for(int i = 0; i < predmety_v_plane.size(); i++) {
				createStatementString.add("INSERT INTO predmet_v_plane(semester, plan_id, "
						+ "volitelny_predmet_id) VALUES(?, ?, ?)");
				stmt.add((PreparedStatement) conn.prepareStatement(createStatementString.get(i + 1)));
				stmt.get(i + 1).setInt(1, predmety_v_plane.get(i).getSemester());
				stmt.get(i + 1).setInt(2, plany.get(0).getId());
				stmt.get(i + 1).setInt(3, predmety_v_plane.get(i).getVolitelnyPredmet().getId());
				stmt.get(i + 1).executeUpdate();
			}
			
			conn.commit();
			logger.info("Insert finished");
			
		}
		catch(SQLException e) {
			if(conn != null) {
				logger.warn(e.getMessage(), e);
				try {
					logger.warn("Rollback");
					conn.rollback();
				} catch (SQLException e1) {
					logger.warn(e1.getMessage(), e1);
				}
			}
		} finally {
			logger.info("Close connection");
			for(int i = 0; i < stmt.size(); i++) {
				if(stmt.get(i) != null){
					try {
						stmt.get(i).close();
					} catch (SQLException e) {
						logger.warn(e.getMessage(), e);
					}
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					logger.warn(e.getMessage(), e);
				}
			}
		}
	}
	
	public void deletePlan(Plan plan) {
		logger.info("Delete plan: " + plan.getId());
		Connection conn = null;
		ArrayList<PreparedStatement> stmt = new ArrayList<PreparedStatement>();
		ArrayList<String> createStatementString = new ArrayList<String>();
		try {
	    	logger.info("Try connect to" + DB_URL);
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage(), e);
			}
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);
			logger.info("Connected");
			
			logger.info("Begin deleting");
			for(int i = 0; i < plan.getPlanPredmety().size(); i++) {
				createStatementString.add("DELETE FROM predmet_v_plane WHERE id = " + 
			plan.getPlanPredmety().get(i).getId());
				stmt.add((PreparedStatement) conn.prepareStatement(createStatementString.get(i)));
				stmt.get(i).executeUpdate();
			}
			
			createStatementString.add("DELETE FROM plan WHERE id = " + plan.getId());
			stmt.add((PreparedStatement) conn.prepareStatement(createStatementString.get(
					plan.getPlanPredmety().size())));
			stmt.get(plan.getPlanPredmety().size()).executeUpdate();
			
			conn.commit();
			logger.info("Delete finished");
		}
		catch(SQLException e) {
			if(conn != null) {
				logger.warn(e.getMessage(), e);
				try {
					conn.rollback();
				} catch (SQLException e1) {
					logger.warn(e1.getMessage(), e1);
				}
			}
		} finally {
			logger.info("Close connection");
			for(int i = 0; i < stmt.size(); i++) {
				if(stmt.get(i) != null){
					try {
						stmt.get(i).close();
					} catch (SQLException e) {
						logger.warn(e.getMessage(), e);
					}
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					logger.warn(e.getMessage(), e);
				}
			}
		}
	}
}
