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
import model.Firma;
import model.Plan;
import model.User;

/**
 * Servlet implementation class ZmazatPlanServlet
 */
@WebServlet("/zmazatplan")
public class ZmazatPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selectedPlan = request.getParameter("planSelect");
		if(selectedPlan.equals("")) {
			response.sendRedirect("zmazanie_planu_screen.jsp");
			return;
		}
		String planNazov = selectedPlan.substring(0, selectedPlan.lastIndexOf("("));
		String planAutor = selectedPlan.substring(selectedPlan.lastIndexOf("(") + 4, selectedPlan.length() - 1);
		List<Plan> plany = (List<Plan>) request.getSession().getAttribute("plany");
		for(int i = 0; i < plany.size(); i++) {
			if(plany.get(i).getNazov().equals(planNazov) && plany.get(i).getCreator_name().equals(planAutor)) {
				PlanManager planManager = new PlanManager();
				planManager.deletePlan(plany.get(i));
				
				User user = (User) request.getSession().getAttribute("user");
				Firma firma = (Firma) request.getSession().getAttribute("firma");
				String autor = null;
				if(user != null) {
					autor = user.getUsername();
				}
				else if(firma != null) {
					autor = firma.getLogin();
				}
				plany = null;
				try {
					plany = planManager.getAllPlanByAutorWithVolitelnyPredmet(autor);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.getSession().setAttribute("plany", plany);
				break;
			}
		}
		response.sendRedirect("zmazanie_planu_screen.jsp");
		return;
	}

}
