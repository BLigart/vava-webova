package servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Plan;
import model.Test;

/**
 * Servlet implementation class StudentPlanServlet
 */
@WebServlet("/selectstudentplan")
public class StudentPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		request.getSession().setAttribute("selectedStudentPlan", null);
		request.getSession().setAttribute("selectedFirmaPlan", null);
		String selectedStudentPlan = request.getParameter("studentplanSelect");
		if(selectedStudentPlan.equals("")) {
			response.sendRedirect("zoznam_planov_screen.jsp");
			return;
		}
		String planNazov = selectedStudentPlan.substring(0, selectedStudentPlan.lastIndexOf("("));
		String planAutor = selectedStudentPlan.substring(selectedStudentPlan.lastIndexOf("(") + 4, selectedStudentPlan.length() - 1);
		//System.out.println("nazov:" + planNazov + "*autor:" + planAutor + "*");
		List<Plan> studentPlany = (List<Plan>) request.getSession().getAttribute("studentplany");
		for(Plan plan : studentPlany) {
			if(plan.getNazov().equals(planNazov) && plan.getCreator_name().equals(planAutor)) {
				Collections.sort(plan.getPlanPredmety());
				request.getSession().setAttribute("selectedStudentPlan", plan);
				break;
			}
		}
		
		response.sendRedirect("zoznam_planov_screen.jsp");
		return;
	}

}
