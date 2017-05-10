package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import model.Plan;

/**
 * Servlet implementation class NazovServlet
 */
@WebServlet("/setnazov")
public class NazovServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(NazovServlet.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("REQUEST \\n" + "Remote addr" + request.getRemoteAddr() + "\\n Query: " + request.getQueryString());

		request.setCharacterEncoding("UTF-8");
		String nazov = request.getParameter("navozplanu");
		request.getSession().setAttribute("nazovPlanu", nazov);
		response.sendRedirect("novy_plan_screen.jsp");
		return;
	}

}
