package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import managers.TestManager;
import model.Plan;
import model.Predmet;
import model.Rok;
import model.Test;
import model.User;

/**
 * Servlet implementation class PredmetServlet
 */
@WebServlet("/selectpredmet")
public class PredmetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PredmetServlet.class);
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("REQUEST \\n" + "Remote addr" + request.getRemoteAddr() + "\\n Query: " + request.getQueryString());

		request.setCharacterEncoding("UTF-8");
		
		User user = (User) request.getSession().getAttribute("user");
		ArrayList<Predmet> predmety = new ArrayList<Predmet>(); 
		predmety = user.getPredmety();
		
		String selectedPredmet = request.getParameter("predmetSelect");
		request.getSession().setAttribute("selectedPredmet", null);
		request.getSession().setAttribute("selectedTest", null);
		request.getSession().setAttribute("testy", null);
		
		for(int i = 0; i < predmety.size(); i++) {
			if(predmety.get(i).toString().equals(selectedPredmet)) {
				request.getSession().setAttribute("selectedPredmet", predmety.get(i));
				break;
			}
		}
		// check if selected predmet and rok
		Predmet predmet = (Predmet) request.getSession().getAttribute("selectedPredmet");
		Rok rok = (Rok) request.getSession().getAttribute("selectedRok");
		if(predmet != null && rok != null) {
			// set tests
			TestManager testManager = new TestManager();
			List<Test> testy = null;
			try {
				testy = testManager.getSelectedTests(predmet.getId(), rok.getId());
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
;
			}
			request.getSession().setAttribute("testy", testy);
		}
		
		
		
		response.sendRedirect("testy_screen.jsp");
		return;
	}

}
