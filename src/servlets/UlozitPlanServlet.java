package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PlanManager;
import model.Firma;
import model.PlanPredmet;
import model.User;

/**
 * Servlet implementation class UlozitPlanServlet
 */
@WebServlet("/ulozitplan")
public class UlozitPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PlanManager planManager = new PlanManager();
		ArrayList<PlanPredmet> predmety_v_plane = (ArrayList<PlanPredmet>) request.getSession().getAttribute("predmety_v_plane");
		String nazovPlanu = (String) request.getSession().getAttribute("nazovPlanu");
		
		User user = (User) request.getSession().getAttribute("user");
		Firma firma = (Firma) request.getSession().getAttribute("firma");
		if(user != null) {
			planManager.insertPlan(nazovPlanu, user.getUsername(), predmety_v_plane);
		}
		else {
			planManager.insertPlan(nazovPlanu, firma.getLogin(), predmety_v_plane);
		}
		
		response.sendRedirect("plany_screen.jsp");
		return;
	}

}
