package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PlanManager;
import model.Plan;

/**
 * Servlet implementation class ZoznamServlet
 */
@WebServlet("/zoznamplanov")
public class ZoznamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("selectedStudentPlan", null);
		request.getSession().setAttribute("selectedFirmaPlan", null);
		List<Plan> studentPlany = null;
		List<Plan> firmaPlany = null;
		PlanManager planManager = new PlanManager();
		try {
			studentPlany = planManager.getAllStudentPlanWithVolitelnyPredmet();
			for(int i = 0; i < studentPlany.size(); i++) {
				studentPlany.get(i).setSortValues();
				studentPlany.get(i).setSortFactor(1);
			}
			if(studentPlany.size() > 0) {
				Collections.sort(studentPlany);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("studentplany", studentPlany);
		try {
			firmaPlany = planManager.getAllFirmaPlanWithVolitelnyPredmet();
			for(int i = 0; i < firmaPlany.size(); i++) {
				firmaPlany.get(i).setSortValues();
				firmaPlany.get(i).setSortFactor(1);
			}
			if(firmaPlany.size() > 0) {
				Collections.sort(firmaPlany);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getSession().setAttribute("firmaplany", firmaPlany);
		
		response.sendRedirect("zoznam_planov_screen.jsp");
		return;
	}

}
