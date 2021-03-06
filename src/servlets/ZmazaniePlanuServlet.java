package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import managers.PlanManager;
import model.Firma;
import model.Plan;
import model.User;

/**
 * Servlet implementation class ZmazaniePlanuServlet
 */
@WebServlet("/zmazanieplanu")
public class ZmazaniePlanuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ZmazaniePlanuServlet.class);
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("REQUEST \\n" + "Remote addr" + request.getRemoteAddr() + "\\n Query: " + request.getQueryString());

		User user = (User) request.getSession().getAttribute("user");
		Firma firma = (Firma) request.getSession().getAttribute("firma");
		List<Plan> plany = null;
		PlanManager planManager = new PlanManager();
		if(user != null) {
			String autor = user.getUsername();
			try {
				plany = planManager.getAllPlanByAutorWithVolitelnyPredmet(autor);
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
;
			}
			request.getSession().setAttribute("plany", plany);
		}
		else if(firma != null) {
			String autor = firma.getLogin();
			try {
				plany = planManager.getAllPlanByAutorWithVolitelnyPredmet(autor);
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
;
			}
			request.getSession().setAttribute("plany", plany);
		}
		
		response.sendRedirect("zmazanie_planu_screen.jsp");
		return;
	}

}
