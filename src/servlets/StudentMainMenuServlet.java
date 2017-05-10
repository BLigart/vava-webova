package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class StudentMainMenuServlet
 */
@WebServlet("/studentmainmenu")
public class StudentMainMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(StudentMainMenuServlet.class);
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("REQUEST \\n" + "Remote addr" + request.getRemoteAddr() + "\\n Query: " + request.getQueryString());

		response.sendRedirect("student_main_menu.jsp");
		return;
	}

}
