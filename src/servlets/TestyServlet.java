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

import managers.RokManager;
import model.Predmet;
import model.Rok;
import model.User;

/**
 * Servlet implementation class TestyServlet
 */
@WebServlet("/testy")
public class TestyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TestyServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("REQUEST \\n" + "Remote addr" + request.getRemoteAddr() + "\\n Query: " + request.getQueryString());

		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("REQUEST \\n" + "Remote addr" + request.getRemoteAddr() + "\\n Query: " + request.getQueryString());

		User user = (User) request.getSession().getAttribute("user");
		
		try {
			user.get_predmety();
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
;
		}
		request.getSession().setAttribute("user", user);
		
		ArrayList<Predmet> predmety = new ArrayList<Predmet>(); 
		predmety = user.getPredmety();
		
		request.getSession().setAttribute("predmety", predmety);
		request.getSession().setAttribute("selectedPredmet", null);
		request.getSession().setAttribute("selectedRok", null);
		request.getSession().setAttribute("selectedTest", null);
		request.getSession().setAttribute("testy", null);
		
		RokManager rokManager = new RokManager();
		int rok = user.getRok();
		List<Rok> prvyRok = null;
		try {
			prvyRok = rokManager.getFirstRok();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		for(int i = prvyRok.get(0).getRok_1(); i <= rok; i++) {
			try {
				rokManager.insertRok(new Rok(i, i + 1));
			} catch (ClassNotFoundException | SQLException e) {
				logger.warn(e.getMessage(), e);
;
			}
		}
		
		
		List<Rok> roky = null;
		try {
			roky = rokManager.getAllRok();
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
;
		}
		request.getSession().setAttribute("roky", roky);
		
		response.sendRedirect("testy_screen.jsp");
		return;
	}

}
