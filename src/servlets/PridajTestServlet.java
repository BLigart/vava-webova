package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import managers.TestManager;
import model.Predmet;
import model.Rok;
import model.Test;

/**
 * Servlet implementation class PridajTestServlet
 */
@WebServlet("/pridajtest")
public class PridajTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					e.printStackTrace();
				}
			}
			else {
				// insert other
				try {
					testManager.insertTest(new Test(predmet.getId(), rok.getId(), testy.size() + 1));
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
			
			// set tests
			testy = null;
			try {
				testy = testManager.getSelectedTests(predmet.getId(), rok.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.getSession().setAttribute("testy", testy);
		}
		
		response.sendRedirect("testy_screen.jsp");
		return;
	}

}
