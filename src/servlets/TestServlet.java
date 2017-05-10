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
import model.Otazka;
import model.Test;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/selecttest")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(TestServlet.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("REQUEST \\n" + "Remote addr" + request.getRemoteAddr() + "\\n Query: " + request.getQueryString());

		String selectedTest = request.getParameter("testSelect");
		@SuppressWarnings("unchecked")
		List<Test> testy = (List<Test>) request.getSession().getAttribute("testy");
		
		if(testy == null) {
			response.sendRedirect("testy_screen.jsp");
			return;
		}
		
		for(int i = 0; i < testy.size(); i++) {
			if(testy.get(i).toString().equals(selectedTest)) {
				request.getSession().setAttribute("selectedTest", testy.get(i));
				break;
			}
		}
		
		Test test = (Test) request.getSession().getAttribute("selectedTest");
		
		if(test == null) {
			response.sendRedirect("testy_screen.jsp");
			return;
		}
		
		OtazkaManager otazkaManager = new OtazkaManager();
		List<Otazka> otazky = null;
		try {
			otazky = otazkaManager.getAllOtazkaFromTest(test.getId());
		} catch (SQLException e) {
			logger.warn(e.getMessage(), e);
;
		}
		request.getSession().setAttribute("otazky", otazky);
		
		response.sendRedirect("testy_screen.jsp");
		return;
	}

}
