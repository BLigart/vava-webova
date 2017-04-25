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

import model.Plan;
import model.PlanPredmet;
import model.VolitelnyPredmet;

public class PlanManager extends AllTablesManager {

	@Override
	protected Object processRow(ResultSet rs) throws SQLException {
		return(new Plan(rs.getInt("id"), rs.getString("creator_name"), rs.getString("nazov")));
	}
	
	@SuppressWarnings("unchecked")
	public List<Plan> getAllStudentPlan() throws SQLException {
		return(selectQuery("SELECT plan.id, creator_name, nazov FROM plan LEFT JOIN firma ON creator_name "
				+ "LIKE login WHERE firma.id IS NULL"));
	}
	
	@SuppressWarnings("unchecked")
	public List<Plan> getAllFirmaPlan() throws SQLException {
		return(selectQuery("SELECT plan.id, creator_name, nazov FROM plan JOIN firma ON creator_name LIKE login"));
	}
	
	public List<Plan> getAllPlanByAutor(String autor) throws SQLException {
		return(selectQuery("SELECT plan.id, creator_name, nazov FROM plan WHERE creator_name LIKE '" + autor + "'"));
	}
	
	public List<Plan> getAllStudentPlanWithVolitelnyPredmet() throws SQLException {
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
		Connection conn = null;
		ArrayList<PreparedStatement> stmt = new ArrayList<PreparedStatement>();
		ArrayList<String> createStatementString = new ArrayList<String>();
		try {
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			conn.setAutoCommit(false);
			
			createStatementString.add("INSERT INTO plan(nazov, creator_name) VALUES(?, ?)");
			stmt.add((PreparedStatement) conn.prepareStatement(createStatementString.get(0)));
			stmt.get(0).setString(1, nazov);
			stmt.get(0).setString(2, creator_name);
			stmt.get(0).executeUpdate();
			
			Statement stmtPlan = conn.createStatement();
			ResultSet rs = stmtPlan.executeQuery("SELECT * FROM plan WHERE nazov LIKE '" + nazov + "'");
			List<Plan> plany = new LinkedList<Plan>();
			while(rs.next()){
				plany.add((Plan) processRow(rs));
			}
			
			for(int i = 0; i < predmety_v_plane.size(); i++) {
				createStatementString.add("INSERT INTO predmet_v_plane(semester, plan_id, volitelny_predmet_id) VALUES(?, ?, ?)");
				stmt.add((PreparedStatement) conn.prepareStatement(createStatementString.get(i + 1)));
				stmt.get(i + 1).setInt(1, predmety_v_plane.get(i).getSemester());
				stmt.get(i + 1).setInt(2, plany.get(0).getId());
				stmt.get(i + 1).setInt(3, predmety_v_plane.get(i).getVolitelnyPredmet().getId());
				stmt.get(i + 1).executeUpdate();
			}
			
			conn.commit();
		}
		catch(SQLException e) {
			if(conn != null) {
				System.err.println(e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			for(int i = 0; i < stmt.size(); i++) {
				if(stmt.get(i) != null){
					try {
						stmt.get(i).close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void deletePlan(Plan plan) {
		Connection conn = null;
		ArrayList<PreparedStatement> stmt = new ArrayList<PreparedStatement>();
		ArrayList<String> createStatementString = new ArrayList<String>();
		try {
			try {
				Class.forName(JDBC_DRIVER);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			conn.setAutoCommit(false);
			
			for(int i = 0; i < plan.getPlanPredmety().size(); i++) {
				createStatementString.add("DELETE FROM predmet_v_plane WHERE id = " + plan.getPlanPredmety().get(i).getId());
				stmt.add((PreparedStatement) conn.prepareStatement(createStatementString.get(i)));
				stmt.get(i).executeUpdate();
			}
			
			createStatementString.add("DELETE FROM plan WHERE id = " + plan.getId());
			stmt.add((PreparedStatement) conn.prepareStatement(createStatementString.get(plan.getPlanPredmety().size())));
			stmt.get(plan.getPlanPredmety().size()).executeUpdate();
			
			conn.commit();
		}
		catch(SQLException e) {
			if(conn != null) {
				System.err.println(e.getMessage());
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			for(int i = 0; i < stmt.size(); i++) {
				if(stmt.get(i) != null){
					try {
						stmt.get(i).close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			if(conn != null){
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
