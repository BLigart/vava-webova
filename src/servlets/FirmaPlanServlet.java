package servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.Plan;

/**
 * Servlet implementation class FirmaPlanServlet
 */
@WebServlet("/selectfirmaplan")
public class FirmaPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(FirmaPlanServlet.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("REQUEST \\n" + "Remote addr" + request.getRemoteAddr() + "\\n Query: " + request.getQueryString());

		request.setCharacterEncoding("UTF-8");
		request.getSession().setAttribute("selectedStudentPlan", null);
		request.getSession().setAttribute("selectedFirmaPlan", null);
		String selectedFirmaPlan = request.getParameter("firmaplanSelect");
		if(selectedFirmaPlan.equals("")) {
			response.sendRedirect("zoznam_planov_screen.jsp");
			return;
		}
		String planNazov = selectedFirmaPlan.substring(0, selectedFirmaPlan.lastIndexOf("("));
		String planAutor = selectedFirmaPlan.substring(selectedFirmaPlan.lastIndexOf("(") + 4, selectedFirmaPlan.length() - 1);
		//System.out.println("nazov:" + planNazov + "*autor:" + planAutor + "*");
		List<Plan> firmaPlany = (List<Plan>) request.getSession().getAttribute("firmaplany");
		for(Plan plan : firmaPlany) {
			if(plan.getNazov().equals(planNazov) && plan.getCreator_name().equals(planAutor)) {
				Collections.sort(plan.getPlanPredmety());
				request.getSession().setAttribute("selectedFirmaPlan", plan);
				break;
			}
		}
		
		response.sendRedirect("zoznam_planov_screen.jsp");
		return;
	}

}
