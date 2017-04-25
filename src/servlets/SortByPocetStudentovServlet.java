package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.PlanManager;
import model.Plan;
import model.PlanPredmet;

/**
 * Servlet implementation class SortByPocetStudentovServlet
 */
@WebServlet("/sortbypocetstudentov")
public class SortByPocetStudentovServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Plan> studentPlany = null;
		studentPlany = (List<Plan>) request.getSession().getAttribute("studentplany");
		for(int i = 0; i < studentPlany.size(); i++) {
			studentPlany.get(i).setSortFactor(1);
		}
		Collections.sort(studentPlany);
		request.getSession().setAttribute("studentplany", studentPlany);
		
		List<Plan> firmaPlany = null;
		firmaPlany = (List<Plan>) request.getSession().getAttribute("firmaplany");
		for(int i = 0; i < firmaPlany.size(); i++) {
			firmaPlany.get(i).setSortFactor(1);
		}
		Collections.sort(firmaPlany);
		request.getSession().setAttribute("firmaplany", firmaPlany);
		
		response.sendRedirect("zoznam_planov_screen.jsp");
		return;
	}

}
