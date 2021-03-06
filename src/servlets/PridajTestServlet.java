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

import managers.OtazkaManager;
import managers.TestManager;
import model.Otazka;
import model.Plan;
import model.Predmet;
import model.Rok;
import model.Test;

/**
 * Servlet implementation class PridajTestServlet
 */
@WebServlet("/pridajtest")
public class PridajTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(PridajTestServlet.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("REQUEST \\n" + "Remote addr" + request.getRemoteAddr() + "\\n Query: " + request.getQueryString());

		Predmet predmet = (Predmet) request.getSession().getAttribute("selectedPredmet");
		Rok rok = (Rok) request.getSession().getAttribute("selectedRok");
		if(predmet != null && rok != null) {
			request.getSession().setAttribute("selectedTest", null);
			List<Test> testy = null;
			testy = (List<Test>) request.getSession().getAttribute("testy");
			TestManager testManager = new TestManager();
			if(testy == null) {
				// insert first
				try {
					testManager.insertTest(new Test(predmet.getId(), rok.getId(), 1));
				} catch (ClassNotFoundException | SQLException e) {
					logger.warn(e.getMessage(), e);
;
				}
			}
			else {
				// insert other
				try {
					testManager.insertTest(new Test(predmet.getId(), rok.getId(), testy.size() + 1));
				} catch (ClassNotFoundException | SQLException e) {
					logger.warn(e.getMessage(), e);
;
				}
			}
			
			// set tests
			testy = null;
			try {
				testy = testManager.getSelectedTests(predmet.getId(), rok.getId());
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
;
			}
			//System.out.println("1testy size:" + testy.get(0) + "*");
			request.getSession().setAttribute("testy", testy);
			//System.out.println("2testy size:" + testy.get(0) + "*");
			request.getSession().setAttribute("selectedTest", testy.get(testy.size() - 1));
			//System.out.println("3testy size:" + testy.get(0) + "*");
			OtazkaManager otazkaManager = new OtazkaManager();
			List<Otazka> otazky = null;
			try {
				otazky = otazkaManager.getAllOtazkaFromTest(testy.get(testy.size() - 1).getId());
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
;
			}
			request.getSession().setAttribute("otazky", otazky);
		}
		
		response.sendRedirect("testy_screen.jsp");
		return;
	}

}
